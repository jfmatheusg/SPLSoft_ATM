import java.util.ArrayList;
import java.util.List;


public aspect ListadoTransacciones {
	
private List<String> logs = new ArrayList<String>();
	 
	public void addLog(String transaction) {
		logs.add(transaction);
	}
	
	public void printLog() {
		for (String log: logs) {
			if (log!=null)
				{System.out.println(log);}
		}
	}
	
	pointcut operationDet() : call(* splsoft.cajero.modelo.Cuenta..*(long));
	pointcut imrpimir() : execution(* splsoft.cajero.control.ComandoImprimirLog.ejecutar(..));
	
	after (): operationDet() {
		String transaction = thisJoinPoint.getSignature().getName() + " " + thisJoinPoint.getArgs()[0];
		this.addLog(transaction);
	}
	
	after (): imrpimir() {
		this.printLog();
	}
}