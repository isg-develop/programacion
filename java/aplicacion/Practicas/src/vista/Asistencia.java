package vista;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo.sedesol.mx.Conexion;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;






public class Asistencia {

	protected Shell shlLista;
	private Text txtAdmin;
	private Text txtPassword;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Asistencia window = new Asistencia();
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
		shlLista.open();
		shlLista.layout();
		while (!shlLista.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLista = new Shell();
		shlLista.setSize(450, 300);
		shlLista.setText("Asistencia");
		
		Label lblNombre = new Label(shlLista, SWT.NONE);
		lblNombre.setBounds(122, 52, 55, 15);
		lblNombre.setText("Nombre");
		
		Label lblFecha = new Label(shlLista, SWT.NONE);
		lblFecha.setBounds(122, 121, 75, 15);
		lblFecha.setText("Contrase\u00F1a");
		
		txtAdmin = new Text(shlLista, SWT.BORDER);
		txtAdmin.setBounds(207, 49, 76, 21);
		
		txtPassword = new Text(shlLista, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setBounds(207, 118, 76, 21);
		
		Button btnGuardar = new Button(shlLista, SWT.NONE);
		btnGuardar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				Connection con=  Conexion.getConnection();
		    	
				if(con != null)
				System.out.println("Conectado.....");
				
				boolean respuesta = false;
				
				

				String Nombre = txtAdmin.getText();
				String pass = txtPassword.getText();
				String consulta="SELECT id_usuario ,Nombre ,contraseña "
						+ " FROM usuario"
						+ " where Nombre =? AND contraseña  =? ";
						
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
						RegistroPersonal princ = new RegistroPersonal();
						shlLista.setVisible(false);
						princ.open();
						
					}
					
					else{
						MessageBox mensaje = new MessageBox (shlLista, SWT.ERROR);
						mensaje.setText("Error");
						mensaje.setMessage("Entradas incorrectas");
						mensaje.open();
						txtAdmin.setText("");
						txtPassword.setText("");
																	
				}
												
					
				} catch (Exception e1) {
					System.out.println("Error, no fue posible establecer la conexion: " + e1.getMessage());
					
				}
			
				
				}
					
			
		});
		btnGuardar.setBounds(24, 226, 75, 25);
		btnGuardar.setText("Acceder");
		
		Button btnCancelar = new Button(shlLista, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCancelar.setBounds(329, 226, 75, 25);
		btnCancelar.setText("Cancelar");

	}
}
