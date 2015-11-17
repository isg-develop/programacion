
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.domain.services.impl;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import uth.cine.domain.entities.Employee;
import uth.cine.domain.repository.EmployeeRepository;
import uth.cine.domain.services.EmployeeService;

/**
 *
 * @author User
 */
public class EmployeeServiceImpl implements EmployeeService{

    Long rowAfected;
    public Long getRowAfected() {
        return rowAfected;
    }

    public void setRowAfected(Long rowAfected) {
        this.rowAfected = rowAfected;
    }
    
    PlatformTransactionManager transactionManager;
    private EmployeeRepository  employeeRepository;
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    /**
     * @return the employeeRepository
     */
    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    /**
     * @param employeeRepository the employeeRepository to set
     */
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    

    // METODOS
    @Override
    public List findByCode(String code) {
         return getEmployeeRepository().findAllbyCode(code);
    }
    
    @Override
    public List findAll() {
        return getEmployeeRepository().findAll();
    }

    @Override
    public Long save(final Employee employee) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionTemplate.PROPAGATION_REQUIRED);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            public void doInTransactionWithoutResult(TransactionStatus status) {
                 // Actualizo los saldos por los nuevos pagos
                long isNew;
                if (employee.getEmployeeId()==null)
                    isNew=0;
                else
                    isNew=employee.getEmployeeId();
                
                if (isNew > 0){
                setRowAfected(getEmployeeRepository().update(employee));
                }else{
                setRowAfected(getEmployeeRepository().insert(employee));
                employee.setEmployeeId(getRowAfected());
                }
            }
        });
        return getRowAfected();
    
    }


    @Override
    public Employee findBySupplierId(Long id) {
        return getEmployeeRepository().findById(id);
    }

    @Override
    public Employee getUser(String user, String pass) {
        return getEmployeeRepository().getUser(user, pass);
    }

    

    
   
}
