/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EmploymentWindow.java
 *
 */
package uth.cine.view.swing.employment;


import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import uth.cine.domain.entities.Employee;
import uth.cine.domain.services.ServiceFactory;
import uth.cine.view.images.ImageLoader;
import uth.cine.view.swing.DialogResult;
import uth.cine.view.swing.ErrorDialog;
import uth.cine.view.swing.TableDataToExcel;
import uth.cine.view.swing.principal.IMainFrame;

/**
 *
 * @author COMMIT
 */
public class EmployeeWindow extends javax.swing.JInternalFrame {

    private final IMainFrame mainFrame;
    private static EmployeeWindow employeeWindow = null;
    private EmployeeTableModel model = new EmployeeTableModel();

    /** Creates new form PurchaseOrderMainWindow
     * @param mainFrame
     * @throws java.lang.Exception */
    public EmployeeWindow(IMainFrame mainFrame) throws Exception {
        initComponents();
        this.jLabel19.setIcon(ImageLoader.LogoIcon);  // carga el logo icon de la tienda
//        loadData();
        this.tabla.setModel(model);
        personalizeTable();
        this.mainFrame = mainFrame;
        loadData();
    }

    public static EmployeeWindow getInstance(IMainFrame mainFrame) throws Exception {
        if (employeeWindow == null) {
            employeeWindow = new EmployeeWindow(mainFrame);
        }
        return employeeWindow;
    }

    private void edit() {

        try {

            HashMap map = this.getSelectedRow();
            Employee dataRow = (Employee) map.get("dataRow");
            Integer indexModel = (Integer) map.get("indexModel");
            if (dataRow != null) {
                EmployeeDetailWindow dlg = new EmployeeDetailWindow(this.mainFrame, dataRow);
                int r = dlg.showDialog();
                if (r == DialogResult.OK) {
                    refresh(dataRow.getEmployeeId(), indexModel);
                }
            }


        } catch (Exception ex) {
            ErrorDialog.showDialog(ex, "Error", "Error al abrir el detalle del cliente.   ");
        }
    }

    private HashMap getSelectedRow() {

        HashMap map = new HashMap();
        Employee dataRow = null;
        int indexModel = -1;

        int selectedRow = (tabla.getSelectedRow());

        if (selectedRow >= 0) {
            indexModel = this.tabla.convertRowIndexToModel(selectedRow);
            dataRow = (Employee) model.getRowAt(indexModel);
        }
        map.put("dataRow", dataRow);
        map.put("indexModel", indexModel);

        return map;

    }

    private void loadData() {
        try {
            // TODO add your handling code here:
            String code = this.clientCodeText.getText();
            List list;
            if (this.optionClientCombo.getSelectedItem().equals("Codigo")) {
                if (!code.equals("")) {
                    list = ServiceFactory.getEmployeeService().findByCode(code);
                } else {
                    list = ServiceFactory.getEmployeeService().findAll();
                }
            } else {  // es por nombre 
                if (!code.equals("")) {
                    list = ServiceFactory.getEmployeeService().findByCode(code);
                } else {
                    list = ServiceFactory.getEmployeeService().findAll();
                }
            }

            model = new EmployeeTableModel(list);
            this.tabla.setModel(model);
            personalizeTable();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeWindow.class.getName()).log(Level.SEVERE, null, ex);
            ErrorDialog.showDialog(ex, "Error", "Error al cargar los datos solicitados.    ");
        }

    }

    private void personalizeTable() {
        this.tabla.setAutoCreateRowSorter(true);
//        this.tabla.setShowHorizontalLines(false);
        this.tabla.getColumnModel().getColumn(EmployeeTableModel.NOMBRE).setPreferredWidth(300);
        countLabel.setText(((Object) tabla.getRowCount()).toString() + " registro(s) ");

    }

    private void refresh(Long key, int indexModel) {
        if (indexModel != -1) {
            try {
                Employee replace = ServiceFactory.getEmployeeService().findBySupplierId(key);
                model.replace(replace, indexModel);
            } catch (Exception ex) {
                try {
                    loadData();
                } catch (Exception ex1) {
                    Logger.getLogger(EmployeeWindow.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } else {
            int re = JOptionPane.showConfirmDialog(this, "Desea refrescar los datos en pantalla?.   ", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (re == 0) {
                try {
                    loadData();
                } catch (Exception ex1) {
                    Logger.getLogger(EmployeeWindow.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        newButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        excelButton = new javax.swing.JButton();
        seachButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        countLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        optionClientCombo = new javax.swing.JComboBox();
        clientCodeText = (new javax.swing.JFormattedTextField() {
            protected void processFocusEvent(FocusEvent e)
            {
                super.processFocusEvent(e);
                if (e.getID() == FocusEvent.FOCUS_GAINED) {
                    selectAll();
                    setBackground(new Color(255,255,51));
                } else {
                    setBackground(Color.white);
                }
            }
        });
        searchClientButton = new javax.swing.JButton();

        jPanel9.setBackground(new java.awt.Color(0, 102, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/cliente24.png"))); // NOI18N
        jLabel19.setText("  Clientes");
        jLabel19.setRequestFocusEnabled(false);
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 4, 540, 20));

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/insertar.gif"))); // NOI18N
        newButton.setText("Nuevo");
        newButton.setFocusable(false);
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newButton);

        EditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/libretaIco.gif"))); // NOI18N
        EditButton.setText("Editar");
        EditButton.setFocusable(false);
        EditButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(EditButton);

        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/report_go.png"))); // NOI18N
        exportButton.setText("Exportar");
        exportButton.setFocusable(false);
        exportButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(exportButton);

        excelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/excel.gif"))); // NOI18N
        excelButton.setText("Excel");
        excelButton.setFocusable(false);
        excelButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        excelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(excelButton);

        seachButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/refresh.gif"))); // NOI18N
        seachButton.setText("Actualizar");
        seachButton.setToolTipText("Actualiza la ventana");
        seachButton.setFocusable(false);
        seachButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        seachButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seachButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(seachButton);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        countLabel.setText("RowCount 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(501, Short.MAX_VALUE)
                .addComponent(countLabel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(countLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        optionClientCombo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        optionClientCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Nombre" }));

        clientCodeText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                clientCodeTextKeyPressed(evt);
            }
        });

        searchClientButton.setText("Buscar");
        searchClientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchClientButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionClientCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clientCodeText, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchClientButton)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(optionClientCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(clientCodeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchClientButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:

        try {
            EmployeeDetailWindow dlg = new EmployeeDetailWindow(this.mainFrame);
            int r = dlg.showDialog();
            if (r == DialogResult.OK) {
                Employee current = ServiceFactory.getEmployeeService().findBySupplierId(dlg.employee.getEmployeeId());
                model.add(current);
            }

        } catch (Exception ex) {
            ErrorDialog.showDialog(ex, "Error", "Error al abrir el detalle del cliente.   ");
        }

}//GEN-LAST:event_newButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        // TODO add your handling code here:
        edit();
}//GEN-LAST:event_EditButtonActionPerformed

    private void excelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelButtonActionPerformed
        try {
            // TODO add your handling code here:
            TableDataToExcel excel = new TableDataToExcel(tabla);
            excel.ExportToExcel();
        } catch (Exception ex) {
            Logger.getLogger(EmployeeWindow.class.getName()).log(Level.SEVERE, null, ex);
            ErrorDialog.showDialog(ex, "Error", "Error al exporta a excel.    ");
        }
}//GEN-LAST:event_excelButtonActionPerformed

    private void seachButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seachButtonActionPerformed

        // TODO add your handling code here:
        loadData();

}//GEN-LAST:event_seachButtonActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            edit();
        }
}//GEN-LAST:event_tablaMouseClicked

    private void clientCodeTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clientCodeTextKeyPressed
        // TODO add your handling code here:
        Boolean r = false;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadData();
        }
}//GEN-LAST:event_clientCodeTextKeyPressed

    private void searchClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchClientButtonActionPerformed
        // TODO add your handling code here:
        loadData();
    }//GEN-LAST:event_searchClientButtonActionPerformed
   
    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_exportButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditButton;
    private javax.swing.JTextField clientCodeText;
    private javax.swing.JLabel countLabel;
    private javax.swing.JButton excelButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton newButton;
    private javax.swing.JComboBox optionClientCombo;
    private javax.swing.JButton seachButton;
    private javax.swing.JButton searchClientButton;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
