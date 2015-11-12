/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.view.swing;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author developer
 */
public class PathDirectory {

    public static String getSaveFilePath(String description, String extension) {
        String path = null;

        try {
            final JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(new FileNameExtensionFilter(description, extension));
            fc.setDialogTitle("Guardar archivo (*." + extension + ") ");
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                path = file.getPath() + "." + extension;
                System.out.println(path);
            } else {
                System.out.println("Open command cancelled by user.");
                return null;
            }
        } catch (Exception ex) {
            //evito el error
            path = JOptionPane.showInputDialog(null, "Teclee la dirección completa y el nombre del archivo:", "C:/");
        }
        if (path.equals("C:/")) {
            JOptionPane.showMessageDialog(null, "El archivo selecionado no existe.   ", "Archivo no valido", 1);
            return null;
        }
//        File file = new File(path);
//        if (file.isFile() == false) {
//            JOptionPane.showMessageDialog(null, "El archivo selecionado no existe.   ", "Archivo no valido", 1);
//            return null;
//        }

        return path;
    }

    private static String getDateName() {

        Calendar fechaHoy = Calendar.getInstance();
        java.util.Date fecha = new java.util.Date();
        fechaHoy.setTime(fecha);
        SimpleDateFormat formato1 = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat formato2 = new SimpleDateFormat("Hmmss");

        String name = formato1.format(fecha) + "_" + formato2.format(fecha);
        return name;
    }

    public static String getSaveFilePath(String description, String extension, String fileName, Boolean addDateName) {
        String path = null;

        try {
            final JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(new FileNameExtensionFilter(description, extension));
            fc.setDialogTitle("Guardar archivo (*." + extension + ") ");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String myFileName = fileName;
                if (addDateName == true) {
                    myFileName += getDateName();
                }
                myFileName += "." + extension;
                path = file.getPath() + "/" + myFileName;
                System.out.println(path);
            } else {
                System.out.println("Open command cancelled by user.");
                return null;
            }
        } catch (Exception ex) {
            //evito el error
            path = JOptionPane.showInputDialog(null, "Teclee el directorio:", "C:/");
        }
        if (path.equals("")) {
            JOptionPane.showMessageDialog(null, "El directorio selecionado no existe.   ", "Directorio no valido", 1);
            return null;
        }
//        File file = new File(path);
//        if (file.isFile() == false) {
//            JOptionPane.showMessageDialog(null, "El directorio selecionado no existe.   ", "Directorio no valido", 1);
//            return null;
//        }

        return path;
    }

    public static String getOpenFilePath(String description, String extension) {
        String path = null;
        try {

            final JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(new FileNameExtensionFilter(description, extension));
//            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                path = file.getPath();
                System.out.println(path);
            } else {
                System.out.println("Open command cancelled by user.");
                return null;
            }
        } catch (Exception ex) {
            //evito el error
            path = JOptionPane.showInputDialog(null, "Teclee la dirección completa y el nombre del archivo:", "C:/");
        }
        File file = new File(path);
        if (file.exists() == false) {
            JOptionPane.showMessageDialog(null, "El archivo selecionado no existe.   ", "Archivo no valido", 1);
            return null;
        }
        return path;
    }

    public static String getOpenOnlyPathDirectory(String description, String extension) {
        String path = null;
        try {

            final JFileChooser fc = new JFileChooser();
            fc.addChoosableFileFilter(new FileNameExtensionFilter(description, extension));
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                path = file.getPath();
                System.out.println(path);
            } else {
                System.out.println("Open command cancelled by user.");
                return null;
            }
        } catch (Exception ex) {
            //evito el error
            path = JOptionPane.showInputDialog(null, "Teclee la dirección completa (directorio):", "C:/");
        }
        if (path.equals("")) {
            JOptionPane.showMessageDialog(null, "El directorio selecionado no existe.   ", "Directorio no valido", 1);
            return null;
        }
//        File file = new File(path);
//        if (file.exists() == false) {
//            JOptionPane.showMessageDialog(null, "El directorio selecionado no existe.   ", "Directorio no valido", 1);
//            return null;
//        }
        return path;
    }
}
