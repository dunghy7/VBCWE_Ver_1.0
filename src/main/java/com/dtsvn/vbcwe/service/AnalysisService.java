package com.dtsvn.vbcwe.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.docx4j.dml.chart.CTBarChart;
import org.docx4j.dml.chart.CTBarSer;
import org.docx4j.dml.chart.CTNumVal;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.PresentationMLPackage;
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.DrawingML.Chart;
import org.docx4j.openpackaging.parts.PresentationML.SlidePart;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.docx4j.openpackaging.parts.WordprocessingML.EmbeddedPackagePart;
import org.docx4j.utils.BufferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.dao.MembershipMapper;
import com.dtsvn.vbcwe.dto.MemberSurveyDTO;
import com.dtsvn.vbcwe.dto.SurveyResultRatingDTO;
import com.dtsvn.vbcwe.entity.ReportEntity;
import com.dtsvn.vbcwe.entity.SurMembershipRptEntity;
import com.dtsvn.vbcwe.repository.SurMembershipRptRepository;
import com.dtsvn.vbcwe.util.CommonUtils;

@Service
public class AnalysisService extends BaseService {

    @Autowired
    private SurMembershipRptRepository surMembershipRptRepository;

    @Autowired
    MembershipMapper membershipMapper;

    @Autowired
    private GearsDataTable1Service gearsDataTable1Service;

    @Autowired
    private GearsDataTable2Service gearsDataTable2Service;
    
    @Autowired
    private GearsDataTable4Service gearsDataTable4Service;

    @Value("${report.executor.interval}")
    private long executorInterval;

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public void init() {
        scheduledExecutorService.scheduleAtFixedRate(task1, 2, executorInterval, TimeUnit.MINUTES);
    }

    Runnable task1 = () -> {
        System.out.println("Thread name ..." + Thread.currentThread().getName() + "\n");
        System.out.println("START Processing report ..." + new Date() + "\n");
        processReport();
        System.out.println("END Processing report ..." + new Date() + "\n");
    };
   
   /**
    * processing report
    */
   private void processReport() {
       List<SurMembershipRptEntity> surMembershipRptList = surMembershipRptRepository
               .findByStatusEquals(Constant.MEMBER_REPORT_STATUS.REPORT_PENDING_PROCESS.value);
       if (surMembershipRptList != null && !surMembershipRptList.isEmpty()) {
           for (SurMembershipRptEntity surMembershipRpt : surMembershipRptList) {
               String userId = surMembershipRpt.getUserId();
               // Load survey info
               ReportEntity reportEntity = membershipMapper.findOneReportById(userId);

               // Convert to MemberSurveyDTO
               MemberSurveyDTO memberSurveyDTO = gson.fromJson(reportEntity.getInputData(), MemberSurveyDTO.class);

               // Processing report file
               try {
                   processReportFile(userId, memberSurveyDTO);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
   }
   
   /**
    * read and replace data template file
    * @param memberSurveyDTO
    * @throws Exception
    */
   private void processReportFile(String userId, MemberSurveyDTO memberSurveyDTO) throws Exception {
       // Get file template
       InputStream templateFile = new ClassPathResource("/templ/MembershipTemplate.pptx").getInputStream();

       // Khởi tạo đối tượng Presentation làm việc với powerpoint
       PresentationMLPackage presentationMLPackage = (PresentationMLPackage) OpcPackage.load(templateFile);

       // mapping các biến và value
       HashMap<String, String> variableReplaces = new HashMap<String, String>();
       variableReplaces.put("comName", memberSurveyDTO.getCompanyName());

       // thay thế giá trị trong slide
       List<SlidePart> slideParts = presentationMLPackage.getMainPresentationPart().getSlideParts();

       for (int i = 0; i < slideParts.size(); i++) {
           slideParts.get(i).setJaxbElement(CommonUtils.variableReplace(slideParts.get(i).getJaxbElement(),
                   slideParts.get(i).getJAXBContext(), null, variableReplaces));
       }

       // thay thế giá trị trong DiagramData , Chart
       CommonUtils.variableReplaceInData(presentationMLPackage, variableReplaces);

       // Update data for slide 6
       HashMap<String, Long> surveyResult = CommonUtils.calSurveyScore(memberSurveyDTO.getSurvey(),
               Constant.USER_TYPE_MEMBER);
       updateChartSlide6(presentationMLPackage, "/ppt/charts/chart1.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet1.xlsx", surveyResult);

       // Update data for slide 17
       updateChartSlide6(presentationMLPackage, "/ppt/charts/chart9.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet9.xlsx", surveyResult);

       // Update data for slide 10
       float[][] dataSlide10 = gearsDataTable2Service.getChartData4Slide8(userId);
       updateChart(presentationMLPackage, "/ppt/charts/chart2.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet2.xlsx", dataSlide10);

       // Update data for slide 11
       float[][] dataSlide11 = gearsDataTable4Service.getChartData4Slide9(userId);
       updateChart(presentationMLPackage, "/ppt/charts/chart3.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet3.xlsx", dataSlide11);

       // Update data for slide 12
       float[][] dataSlide12 = gearsDataTable4Service.getChartData4Slide10(userId);
       updateChart(presentationMLPackage, "/ppt/charts/chart4.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet4.xlsx", dataSlide12);

       // Update data for slide 13
       float[][] dataSlide13 = gearsDataTable1Service.getChartData4Slide11_12(userId, "12");
       updateChart(presentationMLPackage, "/ppt/charts/chart5.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet5.xlsx", dataSlide13);

       // Update data for slide 14
       float[][] dataSlide14 = gearsDataTable1Service.getChartData4Slide11_12(userId, "11");
       updateChart(presentationMLPackage, "/ppt/charts/chart6.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet6.xlsx", dataSlide14);

       // Update data for slide 15
       float[][] dataSlide15 = gearsDataTable1Service.getChartData4Slide13(userId);
       updateChart(presentationMLPackage, "/ppt/charts/chart7.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet7.xlsx", dataSlide15);

       // Update data for slide 16
       float[][] dataSlide16 = gearsDataTable2Service.getChartData4Slide14(userId);
       updateChart(presentationMLPackage, "/ppt/charts/chart8.xml", "/ppt/embeddings/Microsoft_Excel_Worksheet8.xlsx", dataSlide16);

       // Create folder
       String folderPath = System.getProperty("user.dir") + File.separatorChar + Constant.OUTPUT_FOLDER_PATH_NAME + File.separatorChar + memberSurveyDTO.getCompanyName();
       File folder = new java.io.File(folderPath);
       if (!folder.exists()) {
           folder.mkdir();
       }

       // Create file
       String fileName = MessageFormat.format("{0}_{1}.pptx",
               CommonUtils.localDateToString(LocalDate.now(), Constant.LOCAL_DATE_FORMAT),
               memberSurveyDTO.getCompanyName());
       String tmpFilePath = Paths.get(folderPath, fileName).toString();
       File tmpFile = new File(tmpFilePath);

       presentationMLPackage.save(tmpFile);

       System.out.println("done .. " + tmpFile + "\n\n");

       // Update file path and status
       updateReportStatus(userId, memberSurveyDTO.getCompanyName() + File.separatorChar + fileName);
   }

   /**
    * Update data for slide 6
    * @param ppt
    * @param chartPartName
    * @param xlsPartName
    * @param surveyScore
    * @throws Docx4JException
    */
   private void updateChartSlide6(PresentationMLPackage ppt, String chartPartName, String xlsPartName, HashMap<String, Long> surveyScore) throws Docx4JException {
       if (surveyScore == null)
           return;

       // Convert survey scores
       List<SurveyResultRatingDTO> lstSurveyResultRatingDTO = new ArrayList<>();
       for (Map.Entry<String, Long> score : surveyScore.entrySet()) {
           SurveyResultRatingDTO surveyResultRatingDTO = new SurveyResultRatingDTO(score.getKey());
           Long value = score.getValue();
           if (value <= 25) {
               surveyResultRatingDTO.setMinStandard(value);
           } else if (25 < value && value <= 50) {
               surveyResultRatingDTO.setActive(value);
           } else if (50 < value && value <= 75) {
               surveyResultRatingDTO.setStategic(value);
           } else if (value > 75) {
               surveyResultRatingDTO.setLeadingPratice(value);
           }
           lstSurveyResultRatingDTO.add(surveyResultRatingDTO);
       }

       // Update file xml
       Chart chart = (Chart) ppt.getParts().get(new PartName(chartPartName));

       List<Object> objects = chart.getJaxbElement().getChart().getPlotArea().getAreaChartOrArea3DChartOrLineChart();

       for (Object object : objects) {

           if (object instanceof CTBarChart) {
               List<CTBarSer> ctBarSers = ((CTBarChart) object).getSer();
               for (int i = 0; i < ctBarSers.size(); i++) {
                   CTBarSer ctBarSer = ctBarSers.get(i);
                   List<CTNumVal> ctNumVals = ctBarSer.getVal().getNumRef().getNumCache().getPt();
                   for (int j = 0; j < ctNumVals.size(); j++) {
                       CTNumVal ctNumVal = ctNumVals.get(j);
                       if (i == 0) {
                           ctNumVal.setV(lstSurveyResultRatingDTO.get(j).getMinStandard().toString());
                       } else if (i == 1) {
                           ctNumVal.setV(lstSurveyResultRatingDTO.get(j).getActive().toString());
                       } else if (i == 2) {
                           ctNumVal.setV(lstSurveyResultRatingDTO.get(j).getStategic().toString());
                       } else if (i == 3) {
                           ctNumVal.setV(lstSurveyResultRatingDTO.get(j).getLeadingPratice().toString());
                       }
                   }
               }
           }
       }

       // Update data excel
       EmbeddedPackagePart epp = (EmbeddedPackagePart) ppt.getParts().get(new PartName(xlsPartName));

       if (epp == null) {
           throw new Docx4JException("Could find EmbeddedPackagePart: " + xlsPartName);
       }

       InputStream is = BufferUtil.newInputStream(epp.getBuffer());

       SpreadsheetMLPackage spreadSheet = (SpreadsheetMLPackage) SpreadsheetMLPackage.load(is);

       Map<PartName, Part> partsMap = spreadSheet.getParts().getParts();
       Iterator<Entry<PartName, Part>> it = partsMap.entrySet().iterator();

       while (it.hasNext()) {
           Map.Entry<PartName, Part> pairs = it.next();

           if (partsMap.get(pairs.getKey()) instanceof WorksheetPart) {

               WorksheetPart wsp = (WorksheetPart) partsMap.get(pairs.getKey());

               List<Row> rows = wsp.getJaxbElement().getSheetData().getRow();

               for (Row row : rows) {
                   Long rowIdx = row.getR();
                   // ignore first row
                   if (rowIdx == 1) {
                       continue;
                   }

                   List<Cell> cells = row.getC();
                   for (Cell cell : cells) {
                       if (cell.getR().equals("B" + rowIdx)) {
                           cell.setV(lstSurveyResultRatingDTO.get(rowIdx.intValue() - 2).getMinStandard().toString());
                       } else if (cell.getR().equals("C" + rowIdx)) {
                           cell.setV(lstSurveyResultRatingDTO.get(rowIdx.intValue() - 2).getActive().toString());
                       } else if (cell.getR().equals("D" + rowIdx)) {
                           cell.setV(lstSurveyResultRatingDTO.get(rowIdx.intValue() - 2).getStategic().toString());
                       } else if (cell.getR().equals("E" + rowIdx)) {
                           cell.setV(
                                   lstSurveyResultRatingDTO.get(rowIdx.intValue() - 2).getLeadingPratice().toString());
                       }
                   }
               }
           }
       }

       /*
        * Convert the Spreadsheet to a binary format, set it on the
        * EmbeddedPackagePart, add it back onto the deck and save to a file.
        * 
        */
       ByteArrayOutputStream baos = new ByteArrayOutputStream();

       SaveToZipFile saver = new SaveToZipFile(spreadSheet);

       saver.save(baos);
       epp.setBinaryData(baos.toByteArray());
   }
   
   /**
    * update data for slide 14
    * @param ppt
    * @param chartPartName
    * @param xlsPartName
    * @param data
    * @throws Docx4JException
    */
   private void updateChart(PresentationMLPackage ppt, String chartPartName, String xlsPartName, float[][] data) throws Docx4JException {
       if (data == null)
           return;

       // Update file xml
       Chart chart = (Chart) ppt.getParts().get(new PartName(chartPartName));

       List<Object> objects = chart.getJaxbElement().getChart().getPlotArea().getAreaChartOrArea3DChartOrLineChart();

       for (Object object : objects) {

           if (object instanceof CTBarChart) {
               List<CTBarSer> ctBarSers = ((CTBarChart) object).getSer();
               for (int i = 0; i < ctBarSers.size(); i++) {
                   CTBarSer ctBarSer = ctBarSers.get(i);
                   List<CTNumVal> ctNumVals = ctBarSer.getVal().getNumRef().getNumCache().getPt();
                   for (int j = 0; j < ctNumVals.size(); j++) {
                       CTNumVal ctNumVal = ctNumVals.get(j);
                       ctNumVal.setV(String.valueOf(data[j][i]));
                   }
               }
           }
       }

       // Update data excel
       EmbeddedPackagePart epp = (EmbeddedPackagePart) ppt.getParts().get(new PartName(xlsPartName));

       if (epp == null) {
           throw new Docx4JException("Could find EmbeddedPackagePart: " + xlsPartName);
       }

       InputStream is = BufferUtil.newInputStream(epp.getBuffer());

       SpreadsheetMLPackage spreadSheet = (SpreadsheetMLPackage) SpreadsheetMLPackage.load(is);

       Map<PartName, Part> partsMap = spreadSheet.getParts().getParts();
       Iterator<Entry<PartName, Part>> it = partsMap.entrySet().iterator();

       while (it.hasNext()) {
           Map.Entry<PartName, Part> pairs = it.next();

           if (partsMap.get(pairs.getKey()) instanceof WorksheetPart) {

               WorksheetPart wsp = (WorksheetPart) partsMap.get(pairs.getKey());

               List<Row> rows = wsp.getJaxbElement().getSheetData().getRow();

               for (Row row : rows) {
                   Long rowIdx = row.getR();
                   // ignore first row
                   if (rowIdx == 1) {
                       continue;
                   }

                   List<Cell> cells = row.getC();
                   for (Cell cell : cells) {
                       if (cell.getR().equals("B" + rowIdx)) {
                           cell.setV(String.valueOf(data[(int) (rowIdx - 2)][0]));
                       } else if (cell.getR().equals("C" + rowIdx)) {
                           cell.setV(String.valueOf(data[(int) (rowIdx - 2)][1]));
                       } else if (cell.getR().equals("D" + rowIdx)) {
                           cell.setV(String.valueOf(data[(int) (rowIdx - 2)][2]));
                       } else if (cell.getR().equals("E" + rowIdx)) {
                           cell.setV(String.valueOf(data[(int) (rowIdx - 2)][3]));
                       }
                   }
               }
           }
       }

       /*
        * Convert the Spreadsheet to a binary format, set it on the
        * EmbeddedPackagePart, add it back onto the deck and save to a file.
        * 
        */
       ByteArrayOutputStream baos = new ByteArrayOutputStream();

       SaveToZipFile saver = new SaveToZipFile(spreadSheet);

       saver.save(baos);
       epp.setBinaryData(baos.toByteArray());
   }
   
   private void updateReportStatus(String userId, String filePath) {
       ReportEntity reportEntity = new ReportEntity();
       reportEntity.setUserId(userId);
       reportEntity.setFilePath(filePath);
       reportEntity.setStatus(Constant.MEMBER_REPORT_STATUS.REPORT_PENDING_APPROVE.value);

       //Update report status
       int record = membershipMapper.updateReport(reportEntity);
       if(record == 0) {
           System.out.println("Update report status fail! .. " + filePath + "\n\n");
       }
   }
}
