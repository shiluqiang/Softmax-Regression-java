import java.io.*;

public class SaveModelResults {
	public static void savemodel(String filename, double [][] W) throws IOException{
		File f = new File(filename);
		// 构建FileOutputStream对象
		FileOutputStream fip = new FileOutputStream(f);
		// 构建OutputStreamWriter对象
		OutputStreamWriter writer = new OutputStreamWriter(fip,"UTF-8");
		//计算模型矩阵的元素个数
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
		// 构建FileOutputStream对象
		FileOutputStream fip = new FileOutputStream(f);
		// 构建OutputStreamWriter对象
		OutputStreamWriter writer = new OutputStreamWriter(fip,"UTF-8");
		//计算的预测结果中元素个数
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
