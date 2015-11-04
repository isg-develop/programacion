package Vista;

import java.sql.Connection;

import Controlador.Controlador;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Formulario {

	protected Shell shlFormulario;
	private Text textnombre;
	private Text textmatricula;
	private Text textdireccion;
	private Text textedad;
	private Text textsexo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Formulario window = new Formulario();
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
		shlFormulario.open();
		shlFormulario.layout();
		while (!shlFormulario.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlFormulario = new Shell();
		shlFormulario.setSize(450, 402);
		shlFormulario.setText("Formulario");
		
		Label lblNombre = new Label(shlFormulario, SWT.NONE);
		lblNombre.setBounds(10, 28, 55, 15);
		lblNombre.setText("Nombre");
		
		Label lblMatricula = new Label(shlFormulario, SWT.NONE);
		lblMatricula.setBounds(10, 70, 55, 15);
		lblMatricula.setText("Matricula");
		
		Label lblDireccion = new Label(shlFormulario, SWT.NONE);
		lblDireccion.setBounds(10, 124, 55, 15);
		lblDireccion.setText("Direccion");
		
		Label lblEdad = new Label(shlFormulario, SWT.NONE);
		lblEdad.setBounds(10, 173, 55, 15);
		lblEdad.setText("Edad");
		
		Label lblSexo = new Label(shlFormulario, SWT.NONE);
		lblSexo.setBounds(10, 215, 55, 15);
		lblSexo.setText("Sexo");
		
		textnombre = new Text(shlFormulario, SWT.BORDER);
		textnombre.setBounds(110, 28, 76, 21);
		
		textmatricula = new Text(shlFormulario, SWT.BORDER);
		textmatricula.setBounds(110, 64, 76, 21);
		
		textdireccion = new Text(shlFormulario, SWT.BORDER);
		textdireccion.setBounds(110, 124, 76, 21);
		
		textedad = new Text(shlFormulario, SWT.BORDER);
		textedad.setBounds(110, 167, 76, 21);
		
		textsexo = new Text(shlFormulario, SWT.BORDER);
		textsexo.setBounds(110, 212, 76, 21);
		
		Button btnGuardar = new Button(shlFormulario, SWT.NONE);
		btnGuardar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Connection con= Modelo.Conexion.getConnection();
				Controlador obj= new Controlador();
				obj.Registro(textnombre.getText(),textmatricula.getText(),textdireccion.getText(),textedad.getText(),textsexo.getText(),con);
				
					
				
			}
		});
		btnGuardar.setBounds(24, 298, 75, 25);
		btnGuardar.setText("Guardar");
		
		Button btnCancelar = new Button(shlFormulario, SWT.NONE);
		btnCancelar.setBounds(236, 298, 75, 25);
		btnCancelar.setText("Cancelar");

	}

}
