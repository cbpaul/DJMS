package com.paul.dao.hibernate;

import com.paul.model.Employee;
import com.paul.dao.EmployeeDao;
import com.paul.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("employeeDao")
public class EmployeeDaoHibernate extends GenericDaoHibernate<Employee, Long> implements EmployeeDao {

    public EmployeeDaoHibernate() {
        super(Employee.class);
    }
}
