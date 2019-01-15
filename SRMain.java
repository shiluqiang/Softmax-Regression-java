import java.io.IOException;

public class SRMain {
	public static void main(String[] args) throws IOException{
		// filename 
	    String filename = "SoftInput.txt";
	    // 导入样本特征和标签
	    double [][] feature = LoadData.Loadfeature(filename);
	    double [] Label = LoadData.LoadLabel(filename); 
	    int labelNum = LoadData.LabelNum(Label);
	    // 参数设置
	 	int samNum = feature.length;
	 	int paraNum = feature[0].length;
	 	double rate = 0.04;
	 	int maxCycle = 10000;
	 	// SR模型训练
	 	SRtrainGradientDescent SR = new SRtrainGradientDescent(feature,Label,paraNum,rate,samNum,maxCycle,labelNum);
	 	double [][] weights = SR.Update(feature, Label, maxCycle, rate, paraNum, labelNum, samNum);
	 	//保存模型
	 	String model_path = "wrights.txt";
	 	SaveModelResults.savemodel(model_path, weights);
	 	//模型测试
	 	double [] results = SRTest.SRtest(labelNum, samNum, paraNum, feature, weights);
	 	String results_path = "results.txt";
	 	SaveModelResults.saveresults(results_path, results);

	}
	
}
