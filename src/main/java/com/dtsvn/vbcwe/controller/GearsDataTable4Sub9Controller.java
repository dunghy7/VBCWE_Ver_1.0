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
import com.dtsvn.vbcwe.controller.validator.GearsDataTable4Sub9Validator;
import com.dtsvn.vbcwe.form.GearsDataTable4Sub9Form;
import com.dtsvn.vbcwe.service.GearsDataTable4Service;

@Controller
@RequestMapping("/dataTable4Sub9")
public class GearsDataTable4Sub9Controller {

	/**
	 * thời gian tự động save survey
	 */
	@Value("${system.autosave.interval}")
	private String autosaveInterval;
	
	@Autowired
	GearsDataTable4Service service;
	
	@Autowired
    private GearsDataTable4Sub9Validator gearsDataTable4Sub9Validator;

	@InitBinder("table4Sub9Form")
    public void initAddBinder(WebDataBinder binder) {
        binder.setValidator(gearsDataTable4Sub9Validator);
    }
	
	/**
	 * Load Form
	 * @param model
	 * @param table4Sub9Form
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, @ModelAttribute("table4Sub9Form") GearsDataTable4Sub9Form table4Sub9Form) {
		
		// lay data tu service
        table4Sub9Form.setDataTable4DtoList(service.loadDataByUserId(Constant.TABLE4_SUB9_NAME, Constant.JSON_TEMPL.TABLE4_SUB9));
		table4Sub9Form.setStatusInCarrer(service.getStatusInCarrer());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input4.9";
	}
	
	/**
	 * Luu Data
	 * @param model
	 * @param table4Sub9Form
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("table4Sub9Form") GearsDataTable4Sub9Form table4Sub9Form) {

		// Luu data, khong validate
        service.saveDataTempl(table4Sub9Form.getDataTable4DtoList(), Constant.TABLE4_SUB9_NAME, Constant.DATA_INPUT_SAVED);
		// lay data tu service
        table4Sub9Form.setDataTable4DtoList(service.loadDataByUserId(Constant.TABLE4_SUB9_NAME, Constant.JSON_TEMPL.TABLE4_SUB9));
		table4Sub9Form.setStatusInCarrer(service.getStatusInCarrer());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input4.9";
	}

	/**
	 * Hoàn Thành Data
	 * @param model
	 * @param table4Sub9Form
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String complete(Model model, @ModelAttribute("table4Sub9Form") @Validated GearsDataTable4Sub9Form table4Sub9Form,
			BindingResult bindingResult) {
		
		// check input form
        if (bindingResult.hasErrors()) {
        	model.addAttribute("autosaveInterval", autosaveInterval);
        	return "membership/input4.9";
        }

		// Luu data, khong validate
        service.saveDataTempl(table4Sub9Form.getDataTable4DtoList(), Constant.TABLE4_SUB9_NAME, Constant.DATA_INPUT_COMPLETED);
		// lay data tu service
        table4Sub9Form.setDataTable4DtoList(service.loadDataByUserId(Constant.TABLE4_SUB9_NAME, Constant.JSON_TEMPL.TABLE4_SUB9));
		table4Sub9Form.setStatusInCarrer(service.getStatusInCarrer());
		// add các thông số config
		//model.addAttribute("autosaveInterval", autosaveInterval);

		return "redirect:/membership/";
	}
}
