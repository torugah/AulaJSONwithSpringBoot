package com.devsuperior.userdept.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.userdept.entities.Department;
import com.devsuperior.userdept.repositories.DepartmentRepository;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public Department insert(@RequestBody Department departmentDTO){
        Department department = departmentRepository.save(departmentDTO);
        return department;
    }

}
