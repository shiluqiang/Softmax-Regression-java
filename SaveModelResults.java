import java.io.*;

public class SaveModelResults {
	public static void savemodel(String filename, double [][] W) throws IOException{
		File f = new File(filename);
		// ����FileOutputStream����
		FileOutputStream fip = new FileOutputStream(f);
		// ����OutputStreamWriter����
		OutputStreamWriter writer = new OutputStreamWriter(fip,"UTF-8");
		//����ģ�;����Ԫ�ظ���
		int n = W.length;
		int m = W[0].length;
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < n-1; j ++) {
			for (int i = 0; i < m-1; i ++) {
			sb.append(String.valueOf(W[j][i]));
			sb.append("\t");
			}
			sb.append(String.valueOf(W[j][m-1]));
			sb.append("\n");
		}
		
		for (int i = 0; i < m-1; i ++) {
			sb.append(String.valueOf(W[n-1][i]));
			sb.append("\t");
		}
		
		sb.append(String.valueOf(W[n-1][m-1]));		
		String sb1 = sb.toString();
		writer.write(sb1);
		writer.close();
		fip.close();
	}
	
	public static void saveresults(String filename, double [] results) throws IOException{
		File f = new File(filename);
		// ����FileOutputStream����
		FileOutputStream fip = new FileOutputStream(f);
		// ����OutputStreamWriter����
		OutputStreamWriter writer = new OutputStreamWriter(fip,"UTF-8");
		//�����Ԥ������Ԫ�ظ���
		int n = results.length;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i ++) {
			sb.append(results[i]);
			sb.append("\n");			
		}
		String sb1 = sb.toString();
		writer.write(sb1);
		writer.close();
		fip.close();		
	}

}
