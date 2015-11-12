/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uth.cine.domain.entities;

/**
 *
 * @author ISG
 */
public class Employee {
    
    Long employeeId;
    String name;
    String role;
    String code;
    private String pass;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee() {}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String preValidate() {
        return "";        
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    
}
