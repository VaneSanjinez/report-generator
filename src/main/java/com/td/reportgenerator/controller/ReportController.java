package com.td.reportgenerator.controller;

import com.td.reportgenerator.model.Report;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @RequestMapping(value="/report", method = RequestMethod.GET)
    public Report getTodayReport(){
        return null;
    }
}
