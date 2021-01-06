package com.dtsvn.vbcwe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dtsvn.vbcwe.common.CommonHelper;
import com.dtsvn.vbcwe.common.MessageConstants;
import com.dtsvn.vbcwe.controller.validator.BenchmarkValidator;
import com.dtsvn.vbcwe.dto.BenchmarkDTO;
import com.dtsvn.vbcwe.form.BenchmarkForm;
import com.dtsvn.vbcwe.service.BenchmarkService;

@Controller
@RequestMapping("/benchmark")
public class BenchmarkController {

        @Autowired
        BenchmarkService service;

        @Autowired
        protected CommonHelper commonHelper;

        @Autowired
        private BenchmarkValidator benchmarkValidator;

        @InitBinder("benchmarkForm")
        public void initAddBinder(WebDataBinder binder) {
            binder.setValidator(benchmarkValidator);
        }

        /**
         * Load Form
         * 
         * @param model
         * @param table1Form
         * @return
         */
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String index(Model model, @ModelAttribute("benchmarkForm") BenchmarkForm benchmarkForm) {
            // lay data tu service
            List<BenchmarkDTO> benchmarkList = service.findAllBenchmark();
            benchmarkForm.setBenchmarkList(benchmarkList);

            return "admin/benchmark";
        }

        /**
         * Luu Data
         * 
         * @param model
         * @param table1Form
         * @return
         */
        @RequestMapping(value = "/update", method = RequestMethod.POST)
        public String update(Model model, @ModelAttribute("benchmarkForm") @Validated BenchmarkForm benchmarkForm,
                BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("benchmarkForm", benchmarkForm);
                return "admin/benchmark";
            }
            try {
                service.updateBenchmark(benchmarkForm.getBenchmarkList());
                model.addAttribute("messageSuccess", commonHelper.getMessage(MessageConstants.MSG_UPDATE_SUCCESS));
            } catch (Exception e) {
                model.addAttribute("messageError", commonHelper.getMessage(e.getMessage()));
            return "admin/benchmark";
            }

            return "admin/benchmark";
        }
}
