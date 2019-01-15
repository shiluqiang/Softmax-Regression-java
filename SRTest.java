
public class SRTest {
	//�Ӿ����һ�����ҵ����Ԫ�ض�Ӧ��ָ��
	public static int MaxSearch(double [] array) {
		int  pointer = 0;
		double tmp = 0;
		for (int j = 0; j < array.length; j ++) {
			if (array[j] > tmp) {
				tmp = array[j];
				pointer = j;
			}
		}
		return pointer;
	}
	//����Ԥ����
	public static double [] SRtest(int labelNum,int samNum,int paraNum,double [][] feature,double [][] weights) {
		double [][] pre_results = new double [samNum][labelNum];
		for (int i = 0; i < samNum; i ++) {
			for (int j = 0; j < labelNum; j ++) {
				double tmp = 0;
				for (int n = 0; n < paraNum; n ++) {
					tmp += feature[i][n] * weights[n][j];
				}
				pre_results[i][j] = tmp;
			}
		}
		double [] results = new double [samNum];
		for (int m = 0; m < samNum; m ++) {
			results[m] = MaxSearch(pre_results[m]);
		}
		return results;
	}

}
