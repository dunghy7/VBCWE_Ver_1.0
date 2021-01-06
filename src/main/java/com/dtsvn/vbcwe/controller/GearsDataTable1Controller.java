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
import com.dtsvn.vbcwe.controller.validator.GearsDataTable1Validator;
import com.dtsvn.vbcwe.form.GearsDataTable1Form;
import com.dtsvn.vbcwe.service.GearsDataTable1Service;

@Controller
@RequestMapping("/dataTable1")
public class GearsDataTable1Controller {

    /**
     * thời gian tự động save survey
     */
    @Value("${system.autosave.interval}")
    private String autosaveInterval;

    @Autowired
    GearsDataTable1Service service;

    @Autowired
    private GearsDataTable1Validator gearsDataTable1Validator;

    @InitBinder("table1Form")
    public void initAddBinder(WebDataBinder binder) {
        binder.setValidator(gearsDataTable1Validator);
    }

    /**
     * Load Form
     * 
     * @param model
     * @param table1Form
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, @ModelAttribute("table1Form") GearsDataTable1Form table1Form) {

        // lay data tu service
        table1Form.setDataTable1DtoList(service.loadDataByUserId());
        table1Form.setStatusInEmplNvmt(service.getStatusInEmplNvmt());
        // add các thông số config
        model.addAttribute("autosaveInterval", autosaveInterval);

        return "membership/input1";
    }

    /**
     * Luu Data
     * 
     * @param model
     * @param table1Form
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("table1Form") GearsDataTable1Form table1Form) {

        // Luu data, khong validate
        service.saveDataTempl(table1Form.getDataTable1DtoList(), Constant.DATA_INPUT_SAVED);
        // lay data tu service
        table1Form.setDataTable1DtoList(service.loadDataByUserId());
        table1Form.setStatusInEmplNvmt(service.getStatusInEmplNvmt());
        // add các thông số config
        model.addAttribute("autosaveInterval", autosaveInterval);

        return "membership/input1";
    }

    /**
     * Hoàn Thành Data
     * 
     * @param model
     * @param table1Form
     * @return
     */
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String complete(Model model, @ModelAttribute("table1Form") @Validated GearsDataTable1Form table1Form,
            BindingResult bindingResult) {

        // check input form
        if (bindingResult.hasErrors()) {
            model.addAttribute("autosaveInterval", autosaveInterval);
            return "membership/input1";
        }

        // Luu data, khong validate
        service.saveDataTempl(table1Form.getDataTable1DtoList(), Constant.DATA_INPUT_COMPLETED);
        // lay data tu service
        table1Form.setDataTable1DtoList(service.loadDataByUserId());
        table1Form.setStatusInEmplNvmt(service.getStatusInEmplNvmt());

        return "redirect:/membership/";
    }
}
