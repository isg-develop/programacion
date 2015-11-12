/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.domain.services;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ISG
 */
public class ServiceFactory {

    private static final  ApplicationContext context;


    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    private ServiceFactory() {
    }

    public static EmployeeService getEmployeeService() {
        return (EmployeeService) context.getBean("employeeService");
    }

}
