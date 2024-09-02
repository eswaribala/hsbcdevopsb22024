package com.hsbc.hospitalmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hsbc.hospitalmanagement.dtos.EmployeeRequest;
import com.hsbc.hospitalmanagement.models.Employee;
import com.hsbc.hospitalmanagement.services.CountryService;
import com.hsbc.hospitalmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/signup")
    public String loadSignUp(Model model)  {
        model.addAttribute("employee",new Employee());

        model.addAttribute("countryNames",
                countryService.getCountryNames());
        model.addAttribute("employeeRequest",
                new EmployeeRequest());
        return "signup.html";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employeeRequest")
                               EmployeeRequest employeeRequest,
                               Model model){
        System.out.println(employeeRequest.getDesignation());

        if(employeeRequest.getFirstName().length()>0) {
            employeeService.addEmployee(employeeRequest);
            return "signin.html";
        }
        else
            return "redirect://signup";


    }
}
