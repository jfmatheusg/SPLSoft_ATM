import java.sql.Timestamp;

import splsoft.cajero.control.Comando;
import splsoft.utils.AuditManager;

public aspect Auditoria {

	pointcut login() : call(* validarClave(..));
	pointcut operationDet() : call(* splsoft.cajero.modelo.Cuenta..*(long));
	pointcut operation(): call(* splsoft.cajero.Cajero.retornaComandoSeleccionado(..));
	
	boolean around(): login() {
	    Object result = proceed();
	    if((boolean) result) {
	    		AuditManager audit = new AuditManager();
	    		String usuario = (String) thisJoinPoint.getArgs()[0];
	    		audit.addAudit(new Timestamp(System.currentTimeMillis()) + " - Login del usuario: " + usuario);  	
	    }
		return (boolean) result;
	  }
	
	Comando around (): operation(){
		Comando result = proceed();
		
		if(result != null) {
			String auditLine = (new Timestamp(System.currentTimeMillis())) + " - Operación seleccionada: " + result.getNombre();

			AuditManager audit = new AuditManager();
			audit.addAudit(auditLine); 
		}
		
		
		return result;
	}
	
	after (): operationDet() {
		Object[] args = thisJoinPoint.getArgs();
		String auditLine = (new Timestamp(System.currentTimeMillis())) + " - Operación ejecutada: " + thisJoinPoint.getSignature().getName() + " - Parametros: ";
		for (Object obj : args) {
			auditLine = auditLine + obj.toString() + "; ";
		}
		AuditManager audit = new AuditManager();
		audit.addAudit(auditLine); 
	}
}