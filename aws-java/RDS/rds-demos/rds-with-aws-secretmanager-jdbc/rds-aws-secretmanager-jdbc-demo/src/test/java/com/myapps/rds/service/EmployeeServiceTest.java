package com.myapps.rds.service;

import com.myapps.rds.model.Employee;
import com.myapps.rds.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void testCreateEmployee(){
        Employee employee= new Employee(1L,"John Doe","IT", Instant.now());
        Mockito.when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);
        Employee result = employeeService.createEmployee(employee);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("John Doe");
        assertThat(result.getDepartment()).isEqualTo("IT");
    }

    @Test
    void testGetAll(){
        Employee employee1= new Employee(1L,"John Doe","IT", Instant.now());
        Employee employee2= new Employee(2L,"David Smith","Admin", Instant.now());
        Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1,employee2));

        List<Employee> employees = employeeService.getAll();
        assertThat(employees).isNotEmpty();
        assertThat(employees).hasSize(2);
        assertThat(employees.get(0).getId()).isEqualTo(1L);
        assertThat(employees.get(0).getName()).isEqualTo("John Doe");
        assertThat(employees.get(0).getDepartment()).isEqualTo("IT");
        assertThat(employees.get(1).getId()).isEqualTo(2L);
        assertThat(employees.get(1).getName()).isEqualTo("David Smith");
        assertThat(employees.get(1).getDepartment()).isEqualTo("Admin");

    }

}
