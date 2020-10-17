package splsoft.cajero.control;

import splsoft.cajero.modelo.Banco;
import splsoft.cajero.modelo.Cuenta;

/**
 * Comando usado para listar las cuentas 
 */
public class ComandoConsultarSaldo implements Comando {

	@Override
	public String getNombre() {
		return "Consultar Saldo";
	}

	@Override
	public void ejecutar(Banco contexto, String cedula) throws Exception {
		
		System.out.println("Saldo de cuenta");
		System.out.println();
	
		Cuenta cuentaCliente = contexto.buscarCuenta(cedula);
		System.out.println(cuentaCliente.getNumero() + " : $ " + cuentaCliente.getSaldo());
		

	}

}
