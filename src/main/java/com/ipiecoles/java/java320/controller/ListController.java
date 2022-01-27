package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employes")
public class ListController {

    @Autowired
    private EmployeService employeService;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String list(@RequestParam Integer page, @RequestParam Integer size,
                     @RequestParam String sortProperty, @RequestParam String sortDirection, final ModelMap model) {
        model.put("page", employeService.findAllEmployes(page, size, sortProperty, sortDirection));
        return "list";
    }

}
