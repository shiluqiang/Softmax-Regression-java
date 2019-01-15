
public class SRtrainGradientDescent {
	int paraNum; //权重参数的个数
    double rate; //学习率
    int samNum; //样本个数
    double [][] feature; //样本特征矩阵
    double [] Label;//样本标签
    int maxCycle; //最大迭代次数
    int labelNum; //标签个数
    
    //初始化构造器
    public SRtrainGradientDescent(double [][] feature, double [] Label, int paraNum,double rate, int samNum,int maxCycle,int labelNum) {
    	this.feature = feature;
    	this.Label = Label;
    	this.maxCycle = maxCycle;
    	this.paraNum = paraNum;
    	this.rate = rate;
    	this.samNum = samNum; 
    	this.labelNum = labelNum;
    }
    
    // 权值矩阵初始化
    public double [][] ParaInitialize(int paraNum,int labelNum) {
    	double [][] W = new double[paraNum][labelNum];
    	for (int i = 0; i < paraNum; i ++) {
    		for (int j = 0; j < labelNum; j ++) {
    			W[i][j] =  1.0;
    		}   		
    	}
    	return W;   	
    }
    
    //计算假设函数的分子部分
    public double [][] err(double[][] W, double [][] feature){
    	double [][] errMatrix = new double[feature.length][W[0].length];
    	for (int i = 0; i < feature.length; i ++) {
    		for (int j = 0; j < W[0].length; j ++) {
    			double tmp = 0;
    			for (int n = 0; n < W.length; n ++) {
    				tmp = tmp + feature[i][n] * W[n][j];
    			}
    			errMatrix[i][j] = Math.exp(tmp);
    		}
    	}
    	return errMatrix;
    }
    //计算假设函数的分母部分
    public double [] errSum(double [][] errMatrix) {
    	double [] errsum = new double[errMatrix.length];
    	for (int i = 0; i < errMatrix.length; i ++) {
    		double tmp = 0;
    		for (int j = 0; j < errMatrix[0].length; j ++) {
    			tmp = tmp - errMatrix[i][j];
    		}
    		errsum[i] = tmp;
    	}
    	return errsum;
    }
    
    //计算假设函数的负数矩阵
    public double [][] errFunction(double [][] errMatrix, double [] errsum){
    	double [][] errResult = new double [errMatrix.length][errMatrix[0].length];
    	for (int i = 0; i < errMatrix.length; i ++) {
    		for (int j = 0; j < errMatrix[0].length; j ++) {
    			errResult[i][j] = errMatrix[i][j] / errsum[i];
    		}
    	}
    	return errResult;
    }
    
    //计算预测损失函数值
    public double cost(double [] Label,double [][] errMatrix, double [] errsum,int samNum) {
    	double sum_cost = 0;
    	for(int i = 0; i < samNum; i ++) {
    		int m = (int) Label[i];
    		if ((errMatrix[i][m] / (- errsum[i])) > 0) {
    			sum_cost -= Math.log(errMatrix[i][m] / (- errsum[i]));
    		}
    		else {
    			sum_cost -= 0;
    		}
    	}
    	return sum_cost / samNum;
    }
    
    public double [][] Update(double [][] feature, double[] Label, int maxCycle, double rate,int paraNum,int labelNum, int samNum){
    	//初始化权重矩阵
    	double [][] weights = ParaInitialize(paraNum,labelNum);
    	// 循环迭代优化权重矩阵
    	for(int i = 0; i < maxCycle; i ++) {
    		//假设函数的分子部分
    		double [][] errMatrix = err(weights,feature);
    		//假设函数的分母部分的负数
    		double [] errsum = errSum(errMatrix);
    		if (i % 10 == 0) {
    			double cost = cost(Label,errMatrix,errsum,samNum);
    			System.out.println("第" + i + "次迭代的损失函数值为:" + cost);
    		}
    		//假设函数的负数矩阵
    		double [][] errResult = errFunction(errMatrix,errsum);
    		for (int j = 0; j < samNum; j ++) {
    			int m = (int) Label[j];
    			errResult[j][m] += 1; 
    		}    		
    		// 计算权重矩阵中每个权重参数的梯度方向
    		double [][] delt_weights = new double[paraNum][labelNum];
    		for (int iter1 = 0; iter1 < paraNum; iter1 ++) {
    			for (int iter2 = 0; iter2 < labelNum; iter2 ++) {
    				double tmp = 0;
    				for (int iter3 = 0; iter3 < samNum; iter3 ++) {
    					tmp = tmp + feature[iter3][iter1] * errResult[iter3][iter2];
    				}
    				delt_weights[iter1][iter2] = tmp / samNum;
    			}
    		}
    		
    		for (int iter1 = 0; iter1 < paraNum; iter1 ++) {
    			for (int iter2 = 0; iter2 < labelNum; iter2 ++) {
    				weights[iter1][iter2] = weights[iter1][iter2] + rate * delt_weights[iter1][iter2];
    			}    		}
  		
    	}
    	return weights; 
    }
    
    
}
