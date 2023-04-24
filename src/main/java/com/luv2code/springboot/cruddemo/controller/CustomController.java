package com.luv2code.springboot.cruddemo.controller;


import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class CustomController {

    @Autowired
    private EmployeeService employeeService;



    @GetMapping("/list")
    public String showListOfEmployee(Model model){

        List<Employee> employeeList = employeeService.findAll();



        model.addAttribute("employees", employeeList);

        return "employees/list-employee";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee theEmployee = new Employee();
        model.addAttribute("employee",theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveUpdate(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel){
        Employee employee = employeeService.findOne(theId);

        theModel.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        employeeService.deleteEmployee(theId);

        return "redirect:/employees/list";
    }




}
