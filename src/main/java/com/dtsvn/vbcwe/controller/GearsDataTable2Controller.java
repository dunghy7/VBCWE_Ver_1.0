package com.dtsvn.vbcwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.controller.validator.GearsDataTable2Validator;
import com.dtsvn.vbcwe.form.GearsDataTable2Form;
import com.dtsvn.vbcwe.service.GearsDataTable2Service;

@Controller
@RequestMapping("/dataTable2")
public class GearsDataTable2Controller {

	/**
	 * thời gian tự động save survey
	 */
	@Value("${system.autosave.interval}")
	private String autosaveInterval;

	@Autowired
	GearsDataTable2Service service;
	
	@Autowired
    private GearsDataTable2Validator gearsDataTable2Validator;

	@InitBinder("table2Form")
    public void initAddBinder(WebDataBinder binder) {
        binder.setValidator(gearsDataTable2Validator);
    }
	
	/**
	 * Load Form
	 * @param model
	 * @param table2Form
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, @ModelAttribute("table2Form") GearsDataTable2Form table2Form) {
		
		// lay data tu service
		table2Form.setDataTable2DtoList(service.loadDataByUserId());
		table2Form.setStatusInEmpl(service.getStatusInEmpl());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input2";
	}
	
	/**
	 * Luu Data
	 * @param model
	 * @param table2Form
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("table2Form") GearsDataTable2Form table2Form) {

		// Luu data, khong validate
		service.saveDataTempl(table2Form.getDataTable2DtoList(), Constant.DATA_INPUT_SAVED);
		// lay data tu service
		table2Form.setDataTable2DtoList(service.loadDataByUserId());
		table2Form.setStatusInEmpl(service.getStatusInEmpl());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input2";
	}

	/**
	 * Hoàn Thành Data
	 * @param model
	 * @param table2Form
	 * @return
	 */
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String complete(Model model, @ModelAttribute("table2Form") @Validated GearsDataTable2Form table2Form,
			BindingResult bindingResult) {
		
		// check input form
        if (bindingResult.hasErrors()) {
        	model.addAttribute("autosaveInterval", autosaveInterval);
        	return "membership/input2";
        }

		// Luu data, khong validate
		service.saveDataTempl(table2Form.getDataTable2DtoList(), Constant.DATA_INPUT_COMPLETED);
		// lay data tu service
		table2Form.setDataTable2DtoList(service.loadDataByUserId());
		table2Form.setStatusInEmpl(service.getStatusInEmpl());

		return "redirect:/membership/";
	}
}
