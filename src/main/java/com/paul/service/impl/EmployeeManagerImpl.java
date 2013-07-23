package com.paul.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.dao.EmployeeDao;
import com.paul.model.Employee;
import com.paul.service.EmployeeManager;

@Service("employeeManager")
@WebService(serviceName = "EmployeeService", endpointInterface = "com.paul.service.EmployeeManager")
public class EmployeeManagerImpl extends GenericManagerImpl<Employee, Long> implements EmployeeManager {
    EmployeeDao employeeDao;

    @Autowired
    public EmployeeManagerImpl(EmployeeDao employeeDao) {
        super(employeeDao);
        this.employeeDao = employeeDao;
    }
}