package splsoft.cajero.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa el Banco. Contiene una colección de cuentas
 */
public class Banco {

	// mapa con las cuentas. la llave es el número de la cuenta
	private Map<String, Cuenta> cuentas = new HashMap<>();
	
	/**
	 * Constructor sin parámetros
	 */
	public Banco() { }
	
	
	/**
	 * Retorna un listado con las cuentas
	 * @return listado con las cuentas del Banco-
	 */
	public Collection<Cuenta> getCuentas() {
		return cuentas.values();
	}
	
	/**
	 * Busca una cuenta por el número.  
	 * Retorna una cuenta, si la encuentra, o null, si no la encuentra 
	 * @param numero número de la cuenta a buscar
	 * @return	instancia de cuenta con ese número, null si no existe
	 */
	public Cuenta buscarCuenta(String numero) {
		return cuentas.get(numero);
	}

	/**
	 * agrega una cuenta al banco
	 * @param cuenta Cuenta a agregar al banco
	 */
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.put(cuenta.getNumero(), cuenta);
	}
	
	// valida la clave
	public boolean validarClave(String cedula,String clave ) {
		Cuenta cuentaUsuario = this.buscarCuenta(cedula);
		// compara la clave enviada con la clave guardada
		if (cuentaUsuario.getClave().equals(clave)) {
			return true;
		}
		return false;
	}
	
}
