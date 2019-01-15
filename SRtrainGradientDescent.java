
public class SRtrainGradientDescent {
	int paraNum; //Ȩ�ز����ĸ���
    double rate; //ѧϰ��
    int samNum; //��������
    double [][] feature; //������������
    double [] Label;//������ǩ
    int maxCycle; //����������
    int labelNum; //��ǩ����
    
    //��ʼ��������
    public SRtrainGradientDescent(double [][] feature, double [] Label, int paraNum,double rate, int samNum,int maxCycle,int labelNum) {
    	this.feature = feature;
    	this.Label = Label;
    	this.maxCycle = maxCycle;
    	this.paraNum = paraNum;
    	this.rate = rate;
    	this.samNum = samNum; 
    	this.labelNum = labelNum;
    }
    
    // Ȩֵ�����ʼ��
    public double [][] ParaInitialize(int paraNum,int labelNum) {
    	double [][] W = new double[paraNum][labelNum];
    	for (int i = 0; i < paraNum; i ++) {
    		for (int j = 0; j < labelNum; j ++) {
    			W[i][j] =  1.0;
    		}   		
    	}
    	return W;   	
    }
    
    //������躯���ķ��Ӳ���
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
    //������躯���ķ�ĸ����
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
    
    //������躯���ĸ�������
    public double [][] errFunction(double [][] errMatrix, double [] errsum){
    	double [][] errResult = new double [errMatrix.length][errMatrix[0].length];
    	for (int i = 0; i < errMatrix.length; i ++) {
    		for (int j = 0; j < errMatrix[0].length; j ++) {
    			errResult[i][j] = errMatrix[i][j] / errsum[i];
    		}
    	}
    	return errResult;
    }
    
    //����Ԥ����ʧ����ֵ
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
    	//��ʼ��Ȩ�ؾ���
    	double [][] weights = ParaInitialize(paraNum,labelNum);
    	// ѭ�������Ż�Ȩ�ؾ���
    	for(int i = 0; i < maxCycle; i ++) {
    		//���躯���ķ��Ӳ���
    		double [][] errMatrix = err(weights,feature);
    		//���躯���ķ�ĸ���ֵĸ���
    		double [] errsum = errSum(errMatrix);
    		if (i % 10 == 0) {
    			double cost = cost(Label,errMatrix,errsum,samNum);
    			System.out.println("��" + i + "�ε�������ʧ����ֵΪ:" + cost);
    		}
    		//���躯���ĸ�������
    		double [][] errResult = errFunction(errMatrix,errsum);
    		for (int j = 0; j < samNum; j ++) {
    			int m = (int) Label[j];
    			errResult[j][m] += 1; 
    		}    		
    		// ����Ȩ�ؾ�����ÿ��Ȩ�ز������ݶȷ���
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
