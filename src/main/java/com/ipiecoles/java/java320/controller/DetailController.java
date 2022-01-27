package com.ipiecoles.java.java320.controller;

import com.ipiecoles.java.java320.model.Commercial;
import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.model.Manager;
import com.ipiecoles.java.java320.model.Technicien;
import com.ipiecoles.java.java320.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

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

    @RequestMapping(method = RequestMethod.GET, value = "/new/{className}")
    public String newEmploye(@PathVariable (value = "className") String className, final ModelMap model) {
        switch (className.toLowerCase()) {
            case "commercial": model.put("employe", new Commercial()); break;
            case "technicien": model.put("employe", new Technicien()); break;
            case "manager": model.put("employe", new Manager()); break;
        }
        return "detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/technicien", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView createUpdateTechnicien(Technicien employe) {
        Long id = employe.getId();
        if (id != null) {
            employe = employeService.updateEmploye(id, employe);
        } else {
            employe = employeService.creerEmploye(employe);
        }
        return new RedirectView("/employes/" + employe.getId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/commercial", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createUpdateCommercial(Commercial employe, final ModelMap model) {
        Long id = employe.getId();
        if (id != null) {
            employe = employeService.updateEmploye(id, employe);
        } else {
            employe = employeService.creerEmploye(employe);
        }
        model.put("employe", employe);
        return "detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/manager", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createUpdateManager(Manager employe, final ModelMap model) {
        Long id = employe.getId();
        if (id != null) {
            employe = employeService.updateEmploye(id, employe);
        } else {
            employe = employeService.creerEmploye(employe);
        }
        model.put("employe", employe);
        return "detail";
    }

}
