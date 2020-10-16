package splsoft.utils;

import java.util.List;

public class TransactionLog {
	private List<String> logs;
	
	public void addLog(String transaction) {
		logs.add(transaction);
	}
	
	public void printLog() {
		for (String log: logs) {
			System.out.println(log);
		}
	}
}
