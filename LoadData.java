import java.util.*;
import java.io.*;
public class LoadData {
	//������������
		public static double[][] Loadfeature(String filename) throws IOException{
			File f = new File(filename);
			FileInputStream fip = new FileInputStream(f);
	        // ����FileInputStream����
	        InputStreamReader reader = new InputStreamReader(fip,"UTF-8");
	        // ����InputStreamReader����
	        StringBuffer sb = new StringBuffer();
	        while(reader.ready()) {
	        	sb.append((char) reader.read());
	        }
	        reader.close();
	        fip.close();
	        //�������������ת��Ϊ�ַ���
	        String sb1 = sb.toString();
	        //���н��ַ����ָ�,�����ά��������
	        String [] a = sb1.split("\n");
	        int n = a.length;
	        System.out.println("��ά��������Ϊ:" + n);
	        //�����ά��������
	        String [] a0 = a[0].split("\t");
	        int m = a0.length;
	        System.out.println("��ά��������Ϊ:" + m);
	        
	        double [][] feature = new double[n][m];
	        for (int i = 0; i < n; i ++) {
	        	String [] tmp = a[i].split("\t");
	        	for(int j = 0; j < m; j ++) {
	        		if (j == m-1) {
	        			feature[i][j] = (double) 1;
	        		}
	        		else {
	        			feature[i][j] = Double.parseDouble(tmp[j]);
	        		}       		
	        	}       	
	        }
	        return feature;		
		}
		//����������ǩ
		public static double[] LoadLabel(String filename) throws IOException{
			File f = new File(filename);
			FileInputStream fip = new FileInputStream(f);
	        // ����FileInputStream����
	        InputStreamReader reader = new InputStreamReader(fip,"UTF-8");
	        // ����InputStreamReader����,������д����ͬ
	        StringBuffer sb = new StringBuffer();
	        while(reader.ready()) {
	        	sb.append((char) reader.read());
	        }
	        reader.close();
	        fip.close();
	        //�������������ת��Ϊ�ַ���
	        String sb1 = sb.toString();
	        //���н��ַ����ָ�,�����ά��������
	        String [] a = sb1.split("\n");
	        int n = a.length;
	        System.out.println("��ά��������Ϊ:" + n);
	        //�����ά��������
	        String [] a0 = a[0].split("\t");
	        int m = a0.length;
	        System.out.println("��ά��������Ϊ:" + m);
	        
	        double [] Label = new double[n];
	        for (int i = 0; i < n; i ++) {
	        	String [] tmp = a[i].split("\t");
	        	Label[i] = Double.parseDouble(tmp[m-1]);        	
	        }
	        return Label;		
		}
		public static int LabelNum(double [] Label) {
			int n = Label.length;
			double [] LabelTmp = new double [n];
			System.arraycopy(Label, 0, LabelTmp, 0, n); 
			int labelNum = 1;
			Arrays.sort(LabelTmp);
			for(int i = 1; i < n; i ++) {
				if (LabelTmp[i] != LabelTmp[i-1]) {
					labelNum ++;
				}
			}
			return labelNum;
		}

}
