package tareas;
import java.util.Scanner;
public class Modulo2Tarea2 {
    private Scanner teclado;
	public Modulo2Tarea2(){
		// constructor		
	}
	
	public void imprmirNumero(){
		teclado = new Scanner(System.in);
		System.out.print("Ingrese primer valor: ");
		int numero = teclado.nextInt();
		System.out.print("El numero es: " + numero);		
	}
	
	public static void main(String[] ar){
		Modulo2Tarea2 objeto = new Modulo2Tarea2();
		objeto.imprmirNumero();		
	}
}
