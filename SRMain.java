import java.io.IOException;

public class SRMain {
	public static void main(String[] args) throws IOException{
		// filename 
	    String filename = "SoftInput.txt";
	    // �������������ͱ�ǩ
	    double [][] feature = LoadData.Loadfeature(filename);
	    double [] Label = LoadData.LoadLabel(filename); 
	    int labelNum = LoadData.LabelNum(Label);
	    // ��������
	 	int samNum = feature.length;
	 	int paraNum = feature[0].length;
	 	double rate = 0.04;
	 	int maxCycle = 10000;
	 	// SRģ��ѵ��
	 	SRtrainGradientDescent SR = new SRtrainGradientDescent(feature,Label,paraNum,rate,samNum,maxCycle,labelNum);
	 	double [][] weights = SR.Update(feature, Label, maxCycle, rate, paraNum, labelNum, samNum);
	 	//����ģ��
	 	String model_path = "wrights.txt";
	 	SaveModelResults.savemodel(model_path, weights);
	 	//ģ�Ͳ���
	 	double [] results = SRTest.SRtest(labelNum, samNum, paraNum, feature, weights);
	 	String results_path = "results.txt";
	 	SaveModelResults.saveresults(results_path, results);

	}
	
}
