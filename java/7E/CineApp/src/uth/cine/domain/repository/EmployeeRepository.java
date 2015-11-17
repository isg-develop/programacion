/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.domain.repository;

import java.util.List;
import uth.cine.domain.entities.Employee;

/**
 *
 * @author Enrique Irwin Illan Garcia
 */
public interface EmployeeRepository {

    public List findAll();
    
    public List findAllbyCode(String code);

    public Employee findById(Long employeeId);

    public Long insert(Employee employee);

    public Long update(Employee employee);
    
     public Employee getUser(String user, String pass);
}
