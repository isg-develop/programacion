package Vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import Modelo.Conexion;

public class Ingresar {

	protected Shell shlLogin;
	private Text txtNombre;
	private Text textContraseña;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Ingresar window = new Ingresar();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLogin.open();
		shlLogin.layout();
		while (!shlLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLogin = new Shell();
		shlLogin.setSize(450, 300);
		shlLogin.setText("Login");
		
		Label lblNombre = new Label(shlLogin, SWT.NONE);
		lblNombre.setBounds(52, 46, 55, 15);
		lblNombre.setText("Nombre");
		
		Label lblContrasea = new Label(shlLogin, SWT.NONE);
		lblContrasea.setBounds(52, 107, 76, 15);
		lblContrasea.setText("Contrase\u00F1a");
		
		txtNombre = new Text(shlLogin, SWT.BORDER);
		txtNombre.setBounds(156, 46, 76, 21);
		
		textContraseña = new Text(shlLogin, SWT.BORDER | SWT.PASSWORD);
		textContraseña.setBounds(156, 101, 76, 21);
		
		Button btnEntrar = new Button(shlLogin, SWT.NONE);
		btnEntrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
                  Connection con= Conexion.getConnection();
		    	
				if(con != null)
				System.out.println("Todo bien......");
				
				boolean respuesta = false;
				
				
				String Nombre = txtNombre.getText();
				String pass = textContraseña.getText();
				String consulta="SELECT id_Usuario ,nombre ,Contraseña "
						+ " FROM usuario"
						+ " where nombre =? AND Contraseña  =? ";
				
					ResultSet rs=null;
				    PreparedStatement ps=null;
					
				try {
					ps=con.prepareStatement(consulta);
					ps.setString(1,Nombre);
					ps.setString(2, pass);
					rs=ps.executeQuery();
					
				
					while(rs.next()){
						respuesta=true;
					}
					if(respuesta){
						Formulario princ = new Formulario();
						 shlLogin.setVisible(false);
						princ.open();
						
					}
					
					else{
						MessageBox mensaje = new MessageBox ( shlLogin, SWT.ERROR);
						mensaje.setText("Error");
						mensaje.setMessage("Entradas incorrectas");
						mensaje.open();
						txtNombre.setText("");
						textContraseña.setText("");
																	
				}
												
					
				} catch (Exception e1) {
					System.out.println("Error, no fue posible establecer la conexion: " + e1.getMessage());
					
				}
			
				
				}
				
			
		});
		btnEntrar.setBounds(143, 186, 75, 25);
		btnEntrar.setText("Entrar");

	}

}
