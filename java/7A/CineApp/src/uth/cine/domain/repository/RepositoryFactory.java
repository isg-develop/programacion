/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uth.cine.domain.repository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ISG
 */
public class RepositoryFactory {

    private static final  ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    private RepositoryFactory(){}

    public static EmployeeRepository getEmployeeRepository() {
	return (EmployeeRepository) context.getBean("employeeRepository");
    }

   

}
