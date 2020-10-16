package splsoft.cajero.control;

import splsoft.cajero.modelo.Banco;
import splsoft.cajero.modelo.Cuenta;

/**
 * Comando usado para listar las cuentas 
 */
public class ComandoImprimirLog implements Comando {

	@Override
	public String getNombre() {
		return "Imprimir Log";
	}

	@Override
	public void ejecutar(Banco contexto, String cedula) throws Exception {
		
		System.out.println("Imprimiendo log de transacciones");
		System.out.println();

	}

}
