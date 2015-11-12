/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uth.cine.domain.services;

import java.util.List;
import uth.cine.domain.entities.Employee;

/**
 *
 * @author COMMIT
 */
public interface EmployeeService {

    public List findAll();

    public Long save(Employee employee);

    public Employee findBySupplierId(Long id);

    public List findByCode(String code);
    
    public Employee getUser(String user, String pass);

}
