package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccueilController {

    @Autowired
    private EmployeService employeService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/"
    )

    public String accueil(final ModelMap model) {
        model.put("nbEmployes", employeService.countAllEmploye());
        return "accueil";
    }

}
