package com.pk.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.Model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

}
