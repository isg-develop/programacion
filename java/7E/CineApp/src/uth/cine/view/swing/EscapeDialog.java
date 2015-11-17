/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uth.cine.view.swing;

/**
 *
 * @author Neo
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Extend this instead of JDialog to automatically handle
 * common operations like what happens when the user
 * pushes ESC or Enter as well as selecting field
 * content when focus travels to a JTextField.
 */
public abstract class EscapeDialog extends JDialog
implements ContainerListener, KeyListener, FocusListener {

    int dialogResult = DialogResult.OK;
  
    /**
     * Constructor registers to know about adding/removing
     * widgets to the dialog so that we can add key handlers
     * to them all
     */
    public EscapeDialog(Frame owner, String title) {
        super(owner, title, true);
        addKeyAndContainerListenerRecursively(this);
        setDialogResult(DialogResult.NONE);
    }

    public EscapeDialog(Frame owner, String title, boolean isModal) {
        super(owner, title, isModal);
        addKeyAndContainerListenerRecursively(this);
        setDialogResult(DialogResult.NONE);
    }

     public int getDialogResult() {
        return dialogResult;
    }

    protected void setDialogResult(int dialogResult) {
        this.dialogResult = dialogResult;
        if(dialogResult == DialogResult.OK) {
            doClose();
        }
    }

    public int showDialog() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((size.width - getWidth()) / 2, (size.height - getHeight()) / 2, getWidth(), getHeight());
        this.dialogResult = DialogResult.NONE;
        setVisible(true);
        return getDialogResult();
    }

    /**
     * An element has focus--if it is a JTextField, select
     * the content (so that typing will easily overwrite the
     * current content
     */
    public void focusGained(FocusEvent e) {
        Object object = e.getSource();
        if (object instanceof JTextField) {
            JTextField field = (JTextField) object;
            field.setSelectionStart(0);
            field.setSelectionEnd(field.getText().length());
        }
    }

    public void focusLost(FocusEvent e) {}

    /**
     * Something was added to the JDialog, so register as a
     * KeyListener and ContainerListener
     */
    public void componentAdded(ContainerEvent e)
    {
        addKeyAndContainerListenerRecursively(e.getChild());
    }

    /**
     * Something was removed from the JDialog, so unregister as a
     * KeyListener and ContainerListener
     */
    public void componentRemoved(ContainerEvent e)
    {
        removeKeyAndContainerListenerRecursively(e.getChild());
    }

    /**
     * Register as a KeyListener to the new component, and if it is a
     * container, also register as a ContainerListener so we know
     * if it gets new child objects.
     */
    private void addKeyAndContainerListenerRecursively(Component c) {
        // Add KeyListener to the Component passed as an argument
        c.addKeyListener(this);
        // Check if the Component is a Container
        if (c instanceof Container) {
            // Component c is a Container. The following cast is safe.
            Container cont = (Container)c;
            // Add ContainerListener to the Container.
            cont.addContainerListener(this);
            // Get the Container's array of children Components.
            Component[] children = cont.getComponents();
            // For every child repeat the above operation.
            for(int i = 0; i < children.length; i++){
                addKeyAndContainerListenerRecursively(children[i]);
            }
        }
        if (c instanceof JTextField)
            c.addFocusListener(this);
    }

    /**
     * Same as above, only remove.
     */
    private void removeKeyAndContainerListenerRecursively(Component c) {
        // Add KeyListener to the Component passed as an argument
        c.removeKeyListener(this);
        // Check if the Component is a Container
        if(c instanceof Container) {
            // Component c is a Container. The following cast is safe.
            Container cont = (Container)c;
            // Add ContainerListener to the Container.
            cont.addContainerListener(this);
            // Get the Container's array of children Components.
            Component[] children = cont.getComponents();
            // For every child repeat the above operation.
            for(int i = 0; i < children.length; i++){
                removeKeyAndContainerListenerRecursively(children[i]);
            }
        }
    }

    /**
     * A key was pressed--was it ENTER or ESC?
     */
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ESCAPE) {
            performEscapeAction(e);
        } else if(code == KeyEvent.VK_ENTER) {
            performEnterAction(e);
        }
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    /**
     * Enter key was pressed
     */
    protected abstract void performEnterAction(KeyEvent e);
    /**
     * ESC key was pressed
     */
    protected abstract void performEscapeAction(KeyEvent e);

    protected void doClose() {
        setVisible(false);
        dispose();
    }
}
