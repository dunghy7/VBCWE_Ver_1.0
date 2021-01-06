package com.dtsvn.vbcwe.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.dtsvn.vbcwe.common.Constant;

public class ExcelUtils {

	/**
	 * Copies a row from a row index on the given workbook and sheet to another row
	 * index. If the destination row is already occupied, shift all rows down to
	 * make room.
	 *
	 */
	public static void copyRow(Workbook workbook, Sheet worksheet, int from, int to) {
		Row sourceRow = worksheet.getRow(from);
		Row newRow = worksheet.getRow(to);

		if (alreadyExists(newRow))
			worksheet.shiftRows(to, worksheet.getLastRowNum(), 1);
		else
			newRow = worksheet.createRow(to);

		for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
			Cell oldCell = sourceRow.getCell(i);
			Cell newCell = newRow.createCell(i);
			if (oldCell != null) {
				copyCellStyle(workbook, oldCell, newCell);
				copyCellComment(oldCell, newCell);
				copyCellHyperlink(oldCell, newCell);
				copyCellDataTypeAndValue(oldCell, newCell);
			}
		}

		copyAnyMergedRegions(worksheet, sourceRow, newRow);
	}

	private static void copyCellStyle(Workbook workbook, Cell oldCell, Cell newCell) {
		CellStyle style = workbook.createCellStyle();
		style.cloneStyleFrom(oldCell.getCellStyle());
		newCell.setCellStyle(style);
	}

	private static void copyCellComment(Cell oldCell, Cell newCell) {
		if (newCell.getCellComment() != null)
			newCell.setCellComment(oldCell.getCellComment());
	}

	private static void copyCellHyperlink(Cell oldCell, Cell newCell) {
		if (oldCell.getHyperlink() != null)
			newCell.setHyperlink(oldCell.getHyperlink());
	}

	private static void copyCellDataTypeAndValue(Cell oldCell, Cell newCell) {
		setCellDataType(oldCell, newCell);
		setCellDataValue(oldCell, newCell);
	}

	private static void setCellDataType(Cell oldCell, Cell newCell) {
		newCell.setCellType(oldCell.getCellTypeEnum());
	}

	private static void setCellDataValue(Cell oldCell, Cell newCell) {
		switch (oldCell.getCellTypeEnum()) {
		case BLANK:
			newCell.setCellValue(oldCell.getStringCellValue());
			break;
		case BOOLEAN:
			newCell.setCellValue(oldCell.getBooleanCellValue());
			break;
		case ERROR:
			newCell.setCellErrorValue(oldCell.getErrorCellValue());
			break;
		case FORMULA:
			newCell.setCellFormula(oldCell.getCellFormula());
			break;
		case NUMERIC:
			newCell.setCellValue(oldCell.getNumericCellValue());
			break;
		case STRING:
			newCell.setCellValue(oldCell.getRichStringCellValue());
			break;
		case _NONE:
			break;
		default:
			break;
		}
	}

	public static String getCellDataValue(Workbook workbook, Cell cell) {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		switch (evaluator.evaluate(cell).getCellType()) {
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case STRING:
			return cell.getStringCellValue();
		case BLANK:
		case ERROR:
		case FORMULA:
		case _NONE:
		default:
			break;
		}
		return Constant.BLANK;
	}

	private static boolean alreadyExists(Row newRow) {
		return newRow != null;
	}

	private static void copyAnyMergedRegions(Sheet worksheet, Row sourceRow, Row newRow) {
		for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
			copyMergeRegion(worksheet, sourceRow, newRow, worksheet.getMergedRegion(i));
		}
	}

	private static void copyMergeRegion(Sheet worksheet, Row sourceRow, Row newRow, CellRangeAddress mergedRegion) {
		CellRangeAddress range = mergedRegion;
		if (range.getFirstRow() == sourceRow.getRowNum()) {
			int lastRow = newRow.getRowNum() + (range.getLastRow() - range.getFirstRow());
			CellRangeAddress newCellRangeAddress = new CellRangeAddress(newRow.getRowNum(), lastRow,
					range.getFirstColumn(), range.getLastColumn());
			worksheet.addMergedRegion(newCellRangeAddress);
		}
	}
}
