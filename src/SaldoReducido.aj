import splsoft.cajero.modelo.Cuenta;

public aspect SaldoReducido {
	pointcut validarSaldo() : execution(* splsoft.cajero.modelo.Cuenta.retirar(..));
	
	void around() throws Exception: validarSaldo()  {
		long valor = (long) thisJoinPoint.getArgs()[0];
		Cuenta cuenta = (Cuenta) thisJoinPoint.getThis();
		long saldoFinal = cuenta.getSaldo()-valor;
		
		if (valor < 0) {
			throw new Exception("No se puede retirar un valor negativo");
		}
		if (valor > cuenta.getSaldo()) {
			throw new Exception("No se puede retirar un valor mayor al saldo");
		}
		if (saldoFinal < 200000) {
			throw new Exception("No se puede tener un saldo menor a 200000");
		}
		
		cuenta.setSaldo(saldoFinal);
	}
}