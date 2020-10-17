package splsoft.cajero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import properties.PropertyManager;
import splsoft.cajero.control.Comando;
import splsoft.cajero.control.ComandoConsignar;
import splsoft.cajero.control.ComandoImprimirLog;
import splsoft.cajero.control.ComandoConsultarSaldo;
import splsoft.cajero.control.ComandoRetirar;
import splsoft.cajero.control.ComandoTransferir;
import splsoft.cajero.modelo.Banco;
import splsoft.cajero.modelo.Cuenta;

/**
 * Simulador de un Cajero de Banco
 */
public class Cajero {
	
	/**
	 * Programa principal
	 * @param args parámetros de línea de comandos. Son ignorados por el programa.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		// crea el banco
		Banco banco = new Banco();
		
		//Control de input
		Scanner console = new Scanner(System.in);
		String valorIngresado;
		String cedula;
		String clave;
		boolean fin = false;
		
		// crea unas cuentas, para la prueba
		banco.agregarCuenta(new Cuenta("1", "clave", 500000));
		banco.agregarCuenta(new Cuenta("2", "clave", 300000));
		banco.agregarCuenta(new Cuenta("3", "clave", 400000));
		
		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = cargaComandos(banco);
		
		
		// Ciclo del Programa
		// ==================

		
		//Se realiza la autenticación del Cliente
		do {
			System.out.println("Cajero Automático");
			System.out.println("=================");
			System.out.println();
			System.out.println("Autentíquese por favor");
			System.out.println("Ingrese su cedula:");
			cedula = console.nextLine();
			System.out.println("Ingrese su clave:");
			clave = console.nextLine();
		}while(!banco.validarClave(cedula, clave));
		
		System.out.println();
		
		do {
			
			// muestra los nombres de los comandos
			muestraMenuConComandos(comandos);
			System.out.println("X.- Salir");
			
			// la clase Console no funciona bien en Eclipse
			valorIngresado = console.nextLine();
			
			// obtiene el comando a ejecutar
			Comando comandoSeleccionado = retornaComandoSeleccionado(comandos, valorIngresado);
			if (comandoSeleccionado != null) {
				
				// intenta ejecutar el comando
				try {
					comandoSeleccionado.ejecutar(banco,cedula);
					
				} catch (Exception e) {
					// si hay una excepción, muestra el mensaje
					System.err.println(e.getMessage());
				}
				
			} 
			// si no se obtuvo un comando, puede ser la opción de salir
			else if (valorIngresado.equalsIgnoreCase("X")) {
				fin = true;
			}
			
			System.out.println();
		} while ( !fin );
		
		System.out.println("Gracias por usar el programa.");
	}
	
	
	// Manejo de los comandos de la aplicación
	// =======================================
	
	// carga los comandos usados en el programa
	private static List<Comando> cargaComandos(Banco banco) {
		
		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = new ArrayList<>();
		boolean transfer = PropertyManager.getProperty("Transferencias");
		boolean deposit = PropertyManager.getProperty("Consignaciones");
		
		comandos.add(new ComandoConsultarSaldo());
		comandos.add(new ComandoRetirar());
		if(deposit) {
			comandos.add(new ComandoConsignar());
		}
		if (transfer) {
			comandos.add(new ComandoTransferir());
		}
		comandos.add(new ComandoImprimirLog());

		return comandos;
	}
	
	
	// Muestra el listado de comandos, para mostrar un menú al usuario
	// muestra el índice en el arreglo de comandos y el nombre del comando
	private static void muestraMenuConComandos(List<Comando> comandos) {
		System.out.println("Menú Cajero Automático");
		System.out.println("======================");
		System.out.println();
		// muestra los nombres de los comandos 
		for (int i=0; i < comandos.size(); i++) {
			System.out.println(i + ".-" + comandos.get(i).getNombre());
		}
	}
	
	
	// dado el texto ingresado por el usuario, retorna el comando correspondiente
	// retorna null si el texto no es un número o no existe ningún comando con ese índice
	private static Comando retornaComandoSeleccionado(List<Comando> comandos, String valorIngresado) {
		
		Comando comandoSeleccionado = null;
		
		// el valorIngresado es un número ?
		if (valorIngresado.matches("[0-9]")) {			
			int valorSeleccionado = Integer.valueOf(valorIngresado);
			// es un índice válido para la lista de comandos
			if (valorSeleccionado < comandos.size()) {
				comandoSeleccionado = comandos.get(valorSeleccionado);
			}
		}
		
		return comandoSeleccionado;
	}
	
	
}
