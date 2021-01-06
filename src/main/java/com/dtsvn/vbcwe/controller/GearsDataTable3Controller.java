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
import com.dtsvn.vbcwe.controller.validator.GearsDataTable3Validator;
import com.dtsvn.vbcwe.form.GearsDataTable3Form;
import com.dtsvn.vbcwe.service.GearsDataTable3Service;

@Controller
@RequestMapping("/dataTable3")
public class GearsDataTable3Controller {

	/**
	 * thời gian tự động save survey
	 */
	@Value("${system.autosave.interval}")
	private String autosaveInterval;

	@Autowired
	GearsDataTable3Service service;
	
	@Autowired
    private GearsDataTable3Validator gearsDataTable3Validator;

	@InitBinder("table3Form")
    public void initAddBinder(WebDataBinder binder) {
        binder.setValidator(gearsDataTable3Validator);
    }
	
	/**
	 * Load Form
	 * @param model
	 * @param table3Form
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, @ModelAttribute("table3Form") GearsDataTable3Form table3Form) {
		
		// lay data tu service
		table3Form.setDataTable3DtoList(service.loadDataByUserId());
		table3Form.setStatusInBrdComp(service.getStatusInBrdComp());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input3";
	}
	
	/**
	 * Luu Data
	 * @param model
	 * @param table3Form
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("table3Form") GearsDataTable3Form table3Form) {

		// Luu data, khong validate
		service.saveDataTempl(table3Form.getDataTable3DtoList(), Constant.DATA_INPUT_SAVED);
		// lay data tu service
		table3Form.setDataTable3DtoList(service.loadDataByUserId());
		table3Form.setStatusInBrdComp(service.getStatusInBrdComp());
		// add các thông số config
		model.addAttribute("autosaveInterval", autosaveInterval);

		return "membership/input3";
	}

	/**
	 * Hoàn Thành Data
	 * @param model
	 * @param table3Form
	 * @return
	 */
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	public String complete(Model model, @ModelAttribute("table3Form") @Validated GearsDataTable3Form table3Form,
			BindingResult bindingResult) {
		
		// check input form
        if (bindingResult.hasErrors()) {
        	model.addAttribute("autosaveInterval", autosaveInterval);
        	return "membership/input3";
        }

		// Luu data, khong validate
		service.saveDataTempl(table3Form.getDataTable3DtoList(), Constant.DATA_INPUT_COMPLETED);
		// lay data tu service
		table3Form.setDataTable3DtoList(service.loadDataByUserId());
		table3Form.setStatusInBrdComp(service.getStatusInBrdComp());

		return "redirect:/membership/";
	}
}
