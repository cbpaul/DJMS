package com.paul.service;

import javax.jws.WebService;

import com.paul.model.Employee;

@WebService
public interface EmployeeManager extends GenericManager<Employee, Long> {
    
}