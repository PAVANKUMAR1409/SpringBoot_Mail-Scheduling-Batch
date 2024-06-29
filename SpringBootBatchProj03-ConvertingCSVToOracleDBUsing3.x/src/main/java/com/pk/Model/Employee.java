package com.pk.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="EmployeeBatch")
@Data
public class Employee {
	@Id
	private Integer empno;
	@Column(length=30)
	private String ename;
	@Column(length=30)
	private String eadd;
	private Double salary;
	private Double grossSalary;
	private Double netSalary;
	

}
