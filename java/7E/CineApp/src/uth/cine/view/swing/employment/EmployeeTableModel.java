/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.view.swing.employment;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import uth.cine.domain.entities.Employee;

/**
 *
 * @author DEVELOPER
 */
public class EmployeeTableModel extends AbstractTableModel {

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int ROL = 2;
    public static final int COLUMN_COUNT = 3;
    
    List<Employee> data = new ArrayList();

    public EmployeeTableModel() {
    }

    public EmployeeTableModel(List data) {
        this.data = data;
    }

    public void add(List<Employee> newRows) {
        int first = data.size();
        int last = first + newRows.size() - 1;
        data.addAll(newRows);
        fireTableRowsInserted(first, last);
    }

    public void add(Employee newRow) {
        if (newRow != null) {
            int index = data.size();
            data.add(newRow);
            fireTableRowsInserted(index, index);
        }
    }

    public void replace(Employee row, int rowIndex) {
        data.remove(rowIndex);
        data.add(rowIndex, row);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    public Employee getRowAt(int row) {
        return this.data.get(row);
    }

    public void clear() {
        while (data.size() > 0) {
            data.clear(); //.remove();
        }
    }

    @Override
    public Class getColumnClass(int column) {
//        return getValueAt(0,column).getClass();
        // Devuelve la clase que hay en cada columna.
        switch (column) {
            case NOMBRE:
                return String.class;
            default:
                // Devuelve una clase Object por defecto.
                return Object.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee dataO = this.data.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return dataO.getEmployeeId();
            case NOMBRE:
                return dataO.getName();
            case ROL:
                return dataO.getRole();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return "Serie";
            case NOMBRE:
                return "Nombre";
            case ROL:
                return "Rol";
            
            default:
                // Devuelve una clase Object por defecto.
                return null;
        }
    }
}
