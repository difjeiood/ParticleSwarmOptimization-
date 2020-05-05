package PSO简单算法实现.com.liguang.pso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * PSO算法实现
 */
public class PSO {

    private static double[] gbest; //全局最优位置
    private static double gbest_fitness = Double.MAX_VALUE; //全局最优位置对应的fitness
    private static int particle_num = 20; //粒子数
    private static int N = 50;
    private static int c1, c2 = 2;
    private static double w = 1.4; //惯性因子
    private static List<Particle> particles = new ArrayList<>(); //粒子群


    /**
     * 初始化所有粒子
     */
    public static void initialParticles() {
        for (int i = 0; i < particle_num; i++) {
            Particle particle = new Particle();
            particle.initialX();
            particle.initialV();
            particle.fitness = particle.calculateFitness();
            particles.add(particle);
        }
    }

    /**
     * update gbest
     */
    public static void updateGbest() {
        double fitness = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < particle_num; i++) {
            if (particles.get(i).fitness < fitness) {
                index = i;
                fitness = particles.get(i).fitness;
            }
        }
        if (fitness < gbest_fitness) {
            gbest = particles.get(index).pbest.clone();
            gbest_fitness = fitness;
        }
    }

    /**
     * 更新每个粒子的速度
     */
    public static void updateV() {
        for (Particle particle : particles) {
            for (int i = 0; i < particle.dimension; i++) {
                double v = w * particle.V[i] + c1 * rand() * (particle.pbest[i] - particle.X[i]) + c2 * rand() * (gbest[i] - particle.X[i]);
                if (v > particle.Vmax)
                    v = particle.Vmax;
                else if (v < -particle.Vmax)
                    v = -particle.Vmax;
                particle.V[i] = v;//更新Vi
            }
        }
    }

    /**
     * 更新每个粒子的位置和pbest
     */
    public static void updateX() {
        for (Particle particle : particles) {
            for (int i = 0; i < particle.dimension; i++) {
                particle.X[i] = particle.X[i] + particle.V[i];
            }
            double newFitness = particle.calculateFitness();//新的适应值
            //如果新的适应值比原来的小则更新fitness和pbest
            if (newFitness < particle.fitness) {
                particle.pbest = particle.X.clone();
                particle.fitness = newFitness;
            }
        }
    }

    /**
     * 算法主要流程
     */
    public static void process() {
        int n = 0;
        initialParticles();
        updateGbest();
        while(n++<N) {
            updateV();
            updateX();
            updateGbest();
            System.out.println(n+".当前gbest:("+gbest[0]+","+gbest[1]+")  fitness="+gbest_fitness);
        }
    }

    /**
     * 返回一个0~1的随机数
     * @return
     */
    public static double rand() {
        return new Random().nextDouble();
    }

    public static void main(String[] args) {
        process();
    }

}
