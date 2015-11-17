/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import uth.cine.domain.entities.Employee;
import uth.cine.domain.repository.EmployeeRepository;

/**
 *
 * @author ISG
 */
public class EmployeeRepositoryJdbcImpl extends SimpleJdbcDaoSupport implements EmployeeRepository {

    private static final String EMPLOYEE_SELECT =
            "SELECT id, nombre, rol, usuario AS code, pass from ct_empleados ";
    private static final String EMPLOYEE_SELECT_BY_ID =
            EMPLOYEE_SELECT + " WHERE id = ?";
    private static final String SELECT_USER_BY_ID =
            EMPLOYEE_SELECT + " WHERE usuario = ? and pass = ?";
     private static final String UPDATE_EMPLOYEE_BY_ID =
            " UPDATE  ct_empleados SET nombre=?, rol=? WHERE id = ? ";
    

    @Override
    public List findAll() {
        // Realizacmos la consulta
        List matches = getJdbcTemplate().query(
                EMPLOYEE_SELECT, new EmployeeRowMapper());

        return matches;
    }
    
    @Override
    public List findAllbyCode(String code) {
        // Realizacmos la consulta
        String selectAllSql = EMPLOYEE_SELECT + " where usuario like ? "; 
        code = "%"+code+"%" ; 
        List matches = getJdbcTemplate().query(selectAllSql,  new Object[] { code }, new EmployeeRowMapper());
        return matches;
    }

    @Override
    public Employee findById(Long id) {
        return (Employee) this.getSimpleJdbcTemplate().queryForObject(EMPLOYEE_SELECT_BY_ID, new EmployeeRowMapper(), id);
    }
    
    @Override
    public Long insert(Employee data) {
        long newId;
        data.preValidate(); 
        String INSERT_New =
            " INSERT INTO ct_empleados(nombre, rol, usuario, pass)" +
            " VALUES ( ?, ?, ?, ?) RETURNING id ";
        newId = getSimpleJdbcTemplate().queryForLong(INSERT_New,
                data.getName(),
                data.getRole(),
                data.getCode(),
                data.getPass());

        // Si es necesario guardar un listado 
        /*
        for (Iterator it = data.getLineItems().iterator(); it.hasNext();) {
            LineItem lineItem = (LineItem) it.next();
            getSimpleJdbcTemplate().update(
                    LINE_ITEMS,
                    lineItem.getProductId(),
                    newPurchaseOrderId);
        }
        */
        return newId;
    }

    @Override
    public Long update(Employee employee) {
        return (long) this.getSimpleJdbcTemplate().update(
                UPDATE_EMPLOYEE_BY_ID,
                employee.getName(),
                employee.getRole(),
                employee.getEmployeeId());
        
    }

    @Override
    public Employee getUser(String user, String pass) {
         return (Employee) this.getSimpleJdbcTemplate().queryForObject(SELECT_USER_BY_ID, new EmployeeRowMapper(), user,pass);
    }

    
    class EmployeeRowMapper implements ParameterizedRowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

            Employee employee = new Employee();
            employee.setEmployeeId(rs.getLong("id"));
            employee.setName(rs.getString("nombre"));
            employee.setRole(rs.getString("rol"));
            employee.setCode(rs.getString("code"));
            employee.setPass(rs.getString("pass"));
            return employee;
        }
    };
}
