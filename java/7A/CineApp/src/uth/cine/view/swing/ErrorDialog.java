/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.view.swing;

import java.util.logging.Level;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

/**
 *
 * @author Neo
 */
public class ErrorDialog {

    public static void showDialog(Exception ex, String title, String msg) {
        ErrorInfo info = new ErrorInfo("Error", msg, null, title, ex, Level.ALL, null);
        JXErrorPane.showDialog(null, info);
    }
}
