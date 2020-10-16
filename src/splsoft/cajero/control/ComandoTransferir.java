package splsoft.cajero.control;

import java.util.Scanner;

import splsoft.cajero.modelo.Banco;
import splsoft.cajero.modelo.Cuenta;

/**
 * Comando usado para transferir dinero entre cuentas
 */
public class ComandoTransferir implements Comando {

	@Override
	public String getNombre() {
		return "Transferir dinero";
	}

	@SuppressWarnings("resource")
	@Override
	public void ejecutar(Banco contexto, String cedula) throws Exception {
		
		System.out.println("Transferencia de Dinero");
		System.out.println();
		
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);				
		Cuenta cuentaOrigen = contexto.buscarCuenta(cedula);

		System.out.println("Ingrese el número de cuenta destino");
		String numeroCuentaDestino = console.nextLine();
		
		Cuenta cuentaDestino = contexto.buscarCuenta(numeroCuentaDestino);
		if (cuentaDestino == null) {
			throw new Exception("No existe cuenta con el número " + numeroCuentaDestino);
		}
		
		System.out.println("Saldo actual destino: " + cuentaDestino.getSaldo());
		System.out.println("Ingrese el valor a transferir");
		String valor = console.nextLine();
	
		try {
			
			// se retira primero y luego se consigna
			// si no se puede retirar, no se hace la consignación
			
			long valorNumerico = Long.parseLong(valor);
			this.transferir(cuentaOrigen, cuentaDestino, valorNumerico);
			
			System.out.println("Nuevo saldo destino: " + cuentaDestino.getSaldo());
		
		} catch (NumberFormatException e) {
			throw new Exception("Valor a transferir no válido : " + valor);
		}
	}
	
	public void transferir (Cuenta origen, Cuenta destino, long valor) throws Exception {
		origen.retirar(valor);
		destino.consignar(valor);
	}
}
