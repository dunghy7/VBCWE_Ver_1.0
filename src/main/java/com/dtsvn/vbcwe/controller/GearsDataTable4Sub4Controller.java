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
import com.dtsvn.vbcwe.controller.validator.GearsDataTable4Sub4Validator;
import com.dtsvn.vbcwe.form.GearsDataTable4Sub4Form;
import com.dtsvn.vbcwe.service.GearsDataTable4Service;

@Controller
@RequestMapping("/dataTable4Sub4")
public class GearsDataTable4Sub4Controller {

	/**
	 * thời gian tự động save survey
	 */
	@Value("${system.autosave.interval}")
	private String autosaveInterval;

	@Autowired
	GearsDataTable4Service service;
	
	@Autowired
    private GearsDataTable4Sub4Validator gearsDataTable4Sub4Validator;

	@InitBinder("table4Sub4Form")
    public void initAddBinder(WebDataBinder binder) {
        binder.setValidator(gearsDataTable4Sub4Validator);
    }
	
	/**
	 * Load Form
	 * @param model
	 * @param table4Sub4Form
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, @ModelAttribute("table4Sub4Form") GearsDataTable4Sub4Form table4Sub4Form) {
		
		// lay data tu service
        table4Sub4Form.setDataTable4DtoList(service.loadDataByUserId(Constant.TABLE4_SUB4_NAME, Constant.JSON_TEMPL.TABLE4_SUB4));
		table4Sub4Form.setStatusInCarrer(service.getStatusInCarrer());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input4.4";
	}
	
	/**
	 * Luu Data
	 * @param model
	 * @param table4Sub4Form
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("table4Sub4Form") GearsDataTable4Sub4Form table4Sub4Form) {

		// Luu data, khong validate
        service.saveDataTempl(table4Sub4Form.getDataTable4DtoList(), Constant.TABLE4_SUB4_NAME, Constant.DATA_INPUT_SAVED);
		// lay data tu service
        table4Sub4Form.setDataTable4DtoList(service.loadDataByUserId(Constant.TABLE4_SUB4_NAME, Constant.JSON_TEMPL.TABLE4_SUB4));
		table4Sub4Form.setStatusInCarrer(service.getStatusInCarrer());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input4.4";
	}

	/**
	 * Hoàn Thành Data
	 * @param model
	 * @param table4Sub4Form
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String complete(Model model, @ModelAttribute("table4Sub4Form") @Validated GearsDataTable4Sub4Form table4Sub4Form,
			BindingResult bindingResult) {
		
		// check input form
        if (bindingResult.hasErrors()) {
        	model.addAttribute("autosaveInterval", autosaveInterval);
        	return "membership/input4.4";
        }

		// Luu data, khong validate
        service.saveDataTempl(table4Sub4Form.getDataTable4DtoList(), Constant.TABLE4_SUB4_NAME, Constant.DATA_INPUT_COMPLETED);
		// lay data tu service
        table4Sub4Form.setDataTable4DtoList(service.loadDataByUserId(Constant.TABLE4_SUB4_NAME, Constant.JSON_TEMPL.TABLE4_SUB4));
		table4Sub4Form.setStatusInCarrer(service.getStatusInCarrer());

		return "redirect:/membership/";
	}
}
