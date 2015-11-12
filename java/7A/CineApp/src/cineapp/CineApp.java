/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import uth.cine.view.swing.principal.IMainFrame;
import uth.cine.view.swing.principal.Plantilla;

/**
 * Universidad Tecnologica de Huetjotzingo
 * @author ISG  uth.tics.tareas@gmail.com
 */
public class CineApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            System.out.println("Ini");
            //List list = ServiceFactory.getEmployeeService().findAll();
            IMainFrame principal = new IMainFrame();
                    principal.setVisible(true);//im.show();
            principal.setExtendedState(JFrame.MAXIMIZED_BOTH);
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(CineApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String fechaActual() {
        Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(hoy);
        return fecha;
    }
    
}
