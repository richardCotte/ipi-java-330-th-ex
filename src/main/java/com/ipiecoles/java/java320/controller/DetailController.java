package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employes")
public class DetailController {

    @Autowired
    private EmployeService employeService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable (value = "id") Long id, final ModelMap model) {
        Employe employe = employeService.findById(id);
        model.put("employe", employe);
        return ("detail");
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String searchByMatricule(@RequestParam String matricule, final ModelMap model) {
        model.put("employe", employeService.findMyMatricule(matricule));
        return "detail";
    }

}
