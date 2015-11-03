package vista;

import java.sql.Connection;



import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RegistroPersonal {

	protected Shell shlRegistroDePersonal;
	private Text textNombre;
	private Text textApellidoP;
	private Text textApellidoM;
	private Text textMatricula;
	private Text textCarrera;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegistroPersonal window = new RegistroPersonal();
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
		shlRegistroDePersonal.open();
		shlRegistroDePersonal.layout();
		while (!shlRegistroDePersonal.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRegistroDePersonal = new Shell();
		shlRegistroDePersonal.setSize(450, 300);
		shlRegistroDePersonal.setText("Informacion");
		
		Label lblNombre = new Label(shlRegistroDePersonal, SWT.NONE);
		lblNombre.setBounds(20, 37, 55, 15);
		lblNombre.setText("Nombre");
		
		Label lblApellidoPaterno = new Label(shlRegistroDePersonal, SWT.NONE);
		lblApellidoPaterno.setBounds(20, 74, 88, 15);
		lblApellidoPaterno.setText("Apellido Paterno");
		
		Label lblApellidoMaterno = new Label(shlRegistroDePersonal, SWT.NONE);
		lblApellidoMaterno.setBounds(20, 121, 98, 15);
		lblApellidoMaterno.setText("Apellido Materno");
		
		textNombre = new Text(shlRegistroDePersonal, SWT.BORDER);
		textNombre.setBounds(128, 31, 76, 21);
		
		textApellidoP = new Text(shlRegistroDePersonal, SWT.BORDER);
		textApellidoP.setBounds(128, 74, 76, 21);
		
		textApellidoM = new Text(shlRegistroDePersonal, SWT.BORDER);
		textApellidoM.setBounds(128, 118, 76, 21);
		
		Label lblMatricula = new Label(shlRegistroDePersonal, SWT.NONE);
		lblMatricula.setBounds(20, 165, 55, 15);
		lblMatricula.setText("Matricula");
		
		textMatricula = new Text(shlRegistroDePersonal, SWT.BORDER);
		textMatricula.setBounds(128, 159, 76, 21);
		
		Button btnGuardar = new Button(shlRegistroDePersonal, SWT.NONE);
		btnGuardar.addSelectionListener(new SelectionAdapter() {
			
			private Object con;
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Connection con= modelo.sedesol.mx.Conexion.getConnection();
				controlador.sedesol.mx.Controlador obj= new controlador.sedesol.mx.Controlador();
				obj.insertarRegistro(textNombre.getText(),textApellidoP.getText(),textApellidoM.getText(),textMatricula.getText(),textCarrera.getText().toString(),con);
				
					
				
				
			}
		});
		btnGuardar.setBounds(10, 226, 75, 25);
		btnGuardar.setText("Guardar");
		
		Button btnCancelar = new Button(shlRegistroDePersonal, SWT.NONE);
		btnCancelar.setBounds(224, 226, 75, 25);
		btnCancelar.setText("Cancelar");
		
		Label lblCarrera = new Label(shlRegistroDePersonal, SWT.NONE);
		lblCarrera.setBounds(20, 196, 55, 15);
		lblCarrera.setText("Carrera");
		
		textCarrera = new Text(shlRegistroDePersonal, SWT.BORDER);
		textCarrera.setBounds(128, 208, 76, 21);

	}
}
