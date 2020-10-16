package splsoft.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class AuditManager {
	private String auditName="./audit.txt";
	private File auditFile;
	private FileWriter auditWriter;
	
	public void addAudit(String auditLine) {
		try {
			auditFile = new File(auditName);
			auditWriter = new FileWriter(auditFile,true);
			BufferedWriter bw = new BufferedWriter(auditWriter);
			bw.write(auditLine);
			bw.newLine();
			bw.close();
		}catch(Exception e) {
			System.out.println("Error escribiendo auditoria");
		}
	}
	
}
