package com.person.controller;

import com.person.bean.Params;
import com.person.bean.Station;
import com.person.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping(value = "/findjob")
    public String first(Model model) {
        Map<String, List<Params>> map=jobService.findJobMenu();
        model.addAttribute("map",map);
        return "First";
    }

    @GetMapping(value = "/onejob")
    public String onejob(Model model, HttpServletRequest request, HttpServletResponse response) {
        String value=request.getParameter("value");
        List<Station> list=jobService.findStation(value);
        model.addAttribute("list",list);
        return "Findjob";
    }

    @GetMapping(value = "/forjob")
    public String forjob(Model model, HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("id");
        Station station=jobService.findOneStation(id);
        model.addAttribute("station",station);
        return "Forjob";
    }
}
