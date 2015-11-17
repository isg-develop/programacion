/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uth.cine.view.images;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author ISG
 */
public class ImageLoader {

    // Every time a image is added, an attribute must be added
    /** The Constant TIENDA. */
    public static final ImageIcon TIENDA = getImage("puntoventa24.png");
    public static final ImageIcon LogoIcon = getImage("puntoventa24.png");     
    
    /**
     * Returns an image.
     * @param imgName
     * @return An ImageIcon
     */
    private static ImageIcon getImage(String imgName) {
        URL imgURL = ImageLoader.class.getResource(imgName);
        return imgURL != null ? new ImageIcon(imgURL) : null;
    }
 
    public static void changeIcon(JFrame ventana) {
        ventana.setIconImage(ImageLoader.LogoIcon.getImage());
    }
    
}