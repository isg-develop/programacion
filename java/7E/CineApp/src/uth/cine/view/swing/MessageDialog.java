/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uth.cine.view.swing;


/**
 *
 * @author Neo
 */
public class MessageDialog {

    public static void showDialog(java.awt.Frame parent, String title, String message) {
        ShowInformationDialog info = new ShowInformationDialog(parent,title,message);
        info.showDialog();
        
    }
}
