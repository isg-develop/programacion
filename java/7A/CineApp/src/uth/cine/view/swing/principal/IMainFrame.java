/* ideass.icomercial.tlax.principal.IMain.java */
package uth.cine.view.swing.principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.beans.PropertyVetoException;
import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import uth.cine.view.images.ImageLoader;
import uth.cine.view.swing.ErrorDialog;
import uth.cine.view.swing.employment.EmployeeWindow;

/***
 * @author ISG
 */
public final class IMainFrame extends javax.swing.JFrame {

    UIManager.LookAndFeelInfo[] lafs;
    final static String LOOKANDFEEL = "System";
    String lookAndFeel = null;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public IMainFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, Exception {
        lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
       initComponents();
       this.setLook_and_feel(LOOKANDFEEL);
        if (this.funValido()) {
            
            jXPanel1.setVisible(true);
            jXTaskPane1.setVisible(true);
            jXTaskPane2.setVisible(true);
            jXTaskPane3.setVisible(true);
            JMenuAbout.setVisible(true);
            JMenuArchivo.setVisible(true);
            JMenuExit.setVisible(true);
            jMenu1.setVisible(true);
            jMenu3.setVisible(true);
            jMenuBar1.setVisible(true);
            //PresentationWindows presentationWindow = new PresentationWindows();
            //showInDesktopPane(presentationWindow);
            //userLabel.setText(Ini.USUARIO + "    ");
            //if (Ini.USARIO_ROL.equals("SU")) {
            //jMenuItem12.setVisible(false); //sol. cred.
            //}
            //screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //setBounds((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2, this.getWidth(), this.getHeight() - 60);
        } else {
            //System.exit(0);
        }

    }

    public JInternalFrame getCurrentView() {
        return JContenedor.getSelectedFrame();
    }

    void clearContent() {
        JInternalFrame[] frames = this.JContenedor.getAllFrames();
        for (JInternalFrame frame : frames) {
            JContenedor.remove(frame);
        }
    }

    void getEmployeeView() {
        try {
            EmployeeWindow ewin = EmployeeWindow.getInstance(this);
            showInDesktopPane(ewin);
        } catch (Exception ex) {
            ErrorDialog.showDialog(ex, "Error", "Error al mostrar employee windows.    ");
        }
    }
    

    private void getCajaView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getPuntoVentaView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getClienteView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getPagosView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getReporteView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void getPromocionesView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void showInDesktopPane(JInternalFrame frame) {

        try {
            JInternalFrame[] frames = this.JContenedor.getAllFrames();
            for (JInternalFrame frame1 : frames) {
                JContenedor.remove(frame1);
            }
            BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
            ui.setNorthPane(null);
            frame.setBorder(null);
            JContenedor.add(frame);
            frame.setMaximum(true);
            frame.setVisible(true);
        } catch (PropertyVetoException ex) {
            // Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }

    }

    private boolean funValido() throws Exception {
        Boolean valido = false;
        Dimension ssize = Toolkit.getDefaultToolkit().getScreenSize();
        dlgConfirmUser dlg = new dlgConfirmUser(null, true);
        dlg.setBounds((ssize.width - dlg.getWidth()) / 2, (ssize.height - dlg.getHeight()) / 2, dlg.getWidth(), dlg.getHeight());
        dlg.setVisible(true);
        int numero = dlg.getReturnStatus();
        if (numero == 1) {//mientras halla presionado OK, entonces realizara la operacion

        }
        return valido;
    }

    private void confirmClose() {
        try {
            JInternalFrame[] frames = this.JContenedor.getAllFrames();
            for (JInternalFrame frame : frames) {
                JContenedor.remove(frame);
            }
            this.setVisible(false);
            this.dispose();

            //this.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JLabel getStatusMessageLabel() {
        return statusMessageLabel;
    }

    private void setLook_and_feel(String altLookAndFeel) throws ClassNotFoundException, ClassNotFoundException, IllegalAccessException, UnsupportedLookAndFeelException, InstantiationException {
        // Menu for the look and feels (lafs).
        lafs = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo laf : lafs) {
            if (laf.getName().equals(altLookAndFeel)) {
                lookAndFeel = laf.getClassName();
                UIManager.setLookAndFeel(lookAndFeel);
            }
        }
        ButtonGroup lafGroup = new ButtonGroup();

        for (UIManager.LookAndFeelInfo laf : lafs) {
            JRadioButtonMenuItem rb = new JRadioButtonMenuItem(laf.getName());
            //this.jMenuCompras.add(rb);
            this.menu_Look.add(rb);
            rb.setSelected(UIManager.getLookAndFeel().getName().equals(laf.getName()));
            rb.putClientProperty("UIKey", laf);
            rb.addItemListener((ItemEvent ae) -> {
                changeLF(ae);
            });
            lafGroup.add(rb);
        }
    }

    private void changeLF(ItemEvent ev) {
        JRadioButtonMenuItem rb2 = (JRadioButtonMenuItem) ev.getSource();
        if (rb2.isSelected()) {
            try {
                UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) rb2.getClientProperty("UIKey");
                UIManager.setLookAndFeel(info.getClassName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                System.err.println("unable to set UI " + e.getMessage());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        userLabel = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        JContenedor = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
        clientejideItem = new com.jidesoft.swing.JideButton();
        empleadosjideItem = new com.jidesoft.swing.JideButton();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        ventasjideItem = new com.jidesoft.swing.JideButton();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        cajajideItem = new com.jidesoft.swing.JideButton();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        JMenuArchivo = new javax.swing.JMenu();
        JMenuExit = new javax.swing.JMenuItem();
        menu_Look = new javax.swing.JMenu();
        jMenuPuntoVenta = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenu1 = new javax.swing.JMenu();
        itemClientes = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        salesReportMenu = new javax.swing.JMenuItem();
        inventoryReportMenuItem = new javax.swing.JMenuItem();
        JMenuAbout = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cine App");
        setBackground(new java.awt.Color(255, 255, 255));
        setName("Imain"); // NOI18N
        setState(5);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        statusPanel.setPreferredSize(new java.awt.Dimension(733, 30));

        userLabel.setText("Usuario:");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userLabel)
                .addGap(18, 18, 18)
                .addComponent(statusMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel)
                    .addComponent(statusMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSplitPane1.setDividerSize(9);
        jSplitPane1.setLastDividerLocation(2);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(1, 3));

        JContenedor.setBackground(new java.awt.Color(248, 247, 250));
        JContenedor.setPreferredSize(new java.awt.Dimension(1024, 690));
        jSplitPane1.setRightComponent(JContenedor);

        jPanel1.setPreferredSize(new java.awt.Dimension(5, 314));

        jXTaskPane3.setForeground(new java.awt.Color(255, 0, 0));
        jXTaskPane3.setTitle("Clientes y CxC");

        clientejideItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/cliente24.png"))); // NOI18N
        clientejideItem.setText("Clientes");
        clientejideItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientejideItemActionPerformed(evt);
            }
        });
        jXTaskPane3.getContentPane().add(clientejideItem);

        empleadosjideItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/users.png"))); // NOI18N
        empleadosjideItem.setText("Empleados");
        empleadosjideItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosjideItemActionPerformed(evt);
            }
        });
        jXTaskPane3.getContentPane().add(empleadosjideItem);

        jXTaskPane1.setBackground(new java.awt.Color(255, 255, 255));
        jXTaskPane1.setForeground(new java.awt.Color(255, 0, 0));
        jXTaskPane1.setTitle("Cine App");

        ventasjideItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/puntoventa24.png"))); // NOI18N
        ventasjideItem.setText("Punto de venta");
        ventasjideItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasjideItemActionPerformed(evt);
            }
        });
        jXTaskPane1.getContentPane().add(ventasjideItem);

        jXTaskPane2.setForeground(new java.awt.Color(255, 0, 0));
        jXTaskPane2.setTitle("Reportes");

        cajajideItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uth/cine/view/images/caja_ico.png"))); // NOI18N
        cajajideItem.setText("Corte de caja");
        cajajideItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajajideItemActionPerformed(evt);
            }
        });
        jXTaskPane2.getContentPane().add(cajajideItem);

        jXPanel1.setBackground(new java.awt.Color(214, 223, 247));

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXTaskPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXTaskPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXTaskPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        JMenuArchivo.setText("Archivo");
        JMenuArchivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        JMenuExit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JMenuExit.setMnemonic('S');
        JMenuExit.setText("Salir");
        JMenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JMenuExitMousePressed(evt);
            }
        });
        JMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuExitActionPerformed(evt);
            }
        });
        JMenuExit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JMenuExitKeyPressed(evt);
            }
        });
        JMenuArchivo.add(JMenuExit);

        menu_Look.setText("LookAndFeel");
        menu_Look.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JMenuArchivo.add(menu_Look);

        jMenuBar1.add(JMenuArchivo);

        jMenuPuntoVenta.setText("Ventas"); // NOI18N
        jMenuPuntoVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuPuntoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPuntoVentaActionPerformed(evt);
            }
        });

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jMenuItem7.setText("Corte de caja");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuPuntoVenta.add(jMenuItem7);
        jMenuPuntoVenta.add(jSeparator2);

        jMenuBar1.add(jMenuPuntoVenta);

        jMenu1.setText("Clientes y CxC");
        jMenu1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        itemClientes.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 11)); // NOI18N
        itemClientes.setText("Clientes");
        itemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClientesActionPerformed(evt);
            }
        });
        jMenu1.add(itemClientes);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Reportes");
        jMenu3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        salesReportMenu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        salesReportMenu.setText("Operaciones diarias (Ventas)");
        salesReportMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesReportMenuActionPerformed(evt);
            }
        });
        jMenu3.add(salesReportMenu);

        inventoryReportMenuItem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        inventoryReportMenuItem.setText("Promociones");
        inventoryReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryReportMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(inventoryReportMenuItem);

        jMenuBar1.add(jMenu3);

        JMenuAbout.setText("About");
        JMenuAbout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jMenuItem8.setText("Ver.1.7.12112015");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        JMenuAbout.add(jMenuItem8);

        jMenuBar1.add(JMenuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void JMenuExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMenuExitMousePressed
        this.dispose();
    }//GEN-LAST:event_JMenuExitMousePressed

    private void JMenuExitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JMenuExitKeyPressed
        this.dispose();
    }//GEN-LAST:event_JMenuExitKeyPressed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus

        /*
            ic.getConexion();//GEN-LAST:event_formWindowGainedFocus
         */
    }

    private void jPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPreciosActionPerformed
//       // no usado
    }//GEN-LAST:event_jPreciosActionPerformed

    private void jOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOrdenCompraActionPerformed
  // no usado
    }//GEN-LAST:event_jOrdenCompraActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        ImageLoader.changeIcon(this);
    }//GEN-LAST:event_formWindowActivated


private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
    /*
     Dimension ssize = Toolkit.getDefaultToolkit().getScreenSize();
     FechaCorteDialog dlg = new FechaCorteDialog(null, true);
     dlg.setBounds((ssize.width - dlg.getWidth()) / 2, (ssize.height - dlg.getHeight()) / 2, dlg.getWidth(), dlg.getHeight());
     String rol = "ADMINISTRADOR";
     if (rol.equals("ADMINISTRADOR") || rol.equals("SUPERVISOR")) { 
        
     } else {
     dlg.setDateInactive(false);
     dlg.setDocInactive(false);
     }
     dlg.setVisible(true);
     int res = dlg.getReturnStatus();
     if (res == 1) {
     DocumentCorteDiario corte = new DocumentCorteDiario();
     corte.cargarCorte(dlg.getDate(), dlg.getDocumento());
     }
     */
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    this.confirmClose();
}//GEN-LAST:event_formWindowClosing

private void itemClientesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_itemClientesActionPerformed
{//GEN-HEADEREND:event_itemClientesActionPerformed
    //load clientes
    getClienteView();
}//GEN-LAST:event_itemClientesActionPerformed

private void jMenuPuntoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPuntoVentaActionPerformed
    // TODO add your handling code here:
    // menu principal
}//GEN-LAST:event_jMenuPuntoVentaActionPerformed


private void salesReportMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesReportMenuActionPerformed
    // TODO add your handling code here:
//    try {
//        SalesReportWindow salesReportWindow = SalesReportWindow.getInstance("TICKET");
//        showInDesktopPane(salesReportWindow);
//    } catch (Exception ex) {
//        ex.printStackTrace();
//        ErrorDialog.showDialog(ex, "Error", "Error al mostrar la ventana de Ventas (reporte).    ");
//    }
}//GEN-LAST:event_salesReportMenuActionPerformed

private void inventoryReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryReportMenuItemActionPerformed
    // TODO add your handling code here:
  getPromocionesView();
}//GEN-LAST:event_inventoryReportMenuItemActionPerformed

private void jideButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton3ActionPerformed
    // TODO add your handling code here:
   getClienteView();
}//GEN-LAST:event_jideButton3ActionPerformed

private void jideButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton7ActionPerformed
    // TODO add your handling code here:
    getPuntoVentaView();
}//GEN-LAST:event_jideButton7ActionPerformed

private void jideButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton8ActionPerformed
    // TODO add your handling code here:
   getPagosView();
}//GEN-LAST:event_jideButton8ActionPerformed

private void jideButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton6ActionPerformed
    // TODO add your handling code here:
   getReporteView();
}//GEN-LAST:event_jideButton6ActionPerformed

private void jideButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jideButton10ActionPerformed
    // TODO add your handling code here:
    getCajaView();
}//GEN-LAST:event_jideButton10ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void JMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuExitActionPerformed
        // TODO add your handling code here:
        //salir
        confirmClose();
    }//GEN-LAST:event_JMenuExitActionPerformed

    private void itemEmploymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEmploymentsActionPerformed
        // TODO add your handling code here:
        getEmployeeView();
    }//GEN-LAST:event_itemEmploymentsActionPerformed

    private void ventasjideItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasjideItemActionPerformed
        // TODO add your handling code here:
        getPuntoVentaView();
    }//GEN-LAST:event_ventasjideItemActionPerformed

    private void clientejideItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientejideItemActionPerformed
        // TODO add your handling code here:
        getClienteView();
    }//GEN-LAST:event_clientejideItemActionPerformed

    private void empleadosjideItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosjideItemActionPerformed
        // TODO add your handling code here:
        getEmployeeView();
    }//GEN-LAST:event_empleadosjideItemActionPerformed

    private void cajajideItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajajideItemActionPerformed
        // TODO add your handling code here:
        getCajaView();
    }//GEN-LAST:event_cajajideItemActionPerformed

    /*"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane JContenedor;
    public javax.swing.JMenu JMenuAbout;
    public javax.swing.JMenu JMenuArchivo;
    public javax.swing.JMenuItem JMenuExit;
    public com.jidesoft.swing.JideButton cajajideItem;
    public com.jidesoft.swing.JideButton clientejideItem;
    public com.jidesoft.swing.JideButton empleadosjideItem;
    public javax.swing.JMenuItem inventoryReportMenuItem;
    public javax.swing.JMenuItem itemClientes;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem7;
    public javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenu jMenuPuntoVenta;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JSplitPane jSplitPane1;
    public org.jdesktop.swingx.JXPanel jXPanel1;
    public org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    public org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    public org.jdesktop.swingx.JXTaskPane jXTaskPane3;
    public javax.swing.JMenu menu_Look;
    public javax.swing.JProgressBar progressBar;
    public javax.swing.JMenuItem salesReportMenu;
    public javax.swing.JLabel statusMessageLabel;
    public javax.swing.JPanel statusPanel;
    public javax.swing.JLabel userLabel;
    public com.jidesoft.swing.JideButton ventasjideItem;
    // End of variables declaration//GEN-END:variables

   

}
