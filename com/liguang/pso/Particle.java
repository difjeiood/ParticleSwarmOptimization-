package PSO简单算法实现.com.liguang.pso;

import java.util.Random;

/**
 * 粒子类
 */
public class Particle {
    //维数
    public int dimension = 2;

    //粒子位置
    public double[] X = new double[dimension];

    //局部最好位置
    public double[] pbest = new double[dimension];

    //粒子速度
    public double[] V = new double[dimension];

    //最大速度
    public double Vmax = 2;

    //适应值
    public double fitness;

    /*
    根据当前位置计算适应度值
     */
    public double calculateFitness() {
        //1.Ackley's function:
        //double newFitness = -20*Math.pow(Math.E,(-0.2*Math.sqrt(0.5*(X[0]*X[0]+X[1]*X[1]))))-Math.pow(Math.E,(0.5*(Math.cos(2*Math.PI*X[0])+Math.cos(2*Math.PI*X[1]))))+Math.E+20;

        //2.Sphere function  球曲面函数
        //double newFitness = X[0]*X[0]+X[1]*X[1];

        //3.Rosenbrock function  用来测试最优化算法性能的凸函数
        double newFitness = 100 * (Math.pow(X[1] - X[0] * X[0], 2 ) + Math.pow(X[0] -1 , 2));
        return newFitness;
    }

    /**
     * 初始化自己的位置和pbest
     */
    public void initialX() {
        for (int i = 0; i < dimension ; i++) {
            X[i] = new Random().nextInt(100);
            pbest[i] = X[i];
        }
    }

    /**
     * 初始化自己的速度
     */
    public void initialV() {
        for (int i = 0; i < dimension ; i++) {
            double tmp = new Random().nextDouble();
            V[i] = tmp*4 + (-2);
        }
    }

}
