
public class Main {

    public  static double R=10;
    public static double eMax=10.0;
    public static int  L=1;
    public static double omega=316.0;
    public static int beta=5;
    public static double omega0=316.23;


    public static double f1(double q2){
        return q2;
    }


     public static double eds(double t){

        return eMax*Math.cos(omega*t);
     }
    public static double f2(double t, double q1, double q2){

        return ((eMax*Math.cos(omega*t))/L - 2*beta*q2-Math.pow(omega,2)*q1);
    }

    public static void main(String[] args) {

        double h, a, b;
        int N;
        double[]  var = new double[2];

        a = 0;
        b = 0.5;
        N=2000;
        var [0] = 0;
        var [1] = 0;

        double[] t = new double[N];
        double[] ur = new double[N];
        double[][] w = new double[N+1][2];

        h = (b-a)/N; //the step size

        t[1] = a;
        w[0][0] = var[0];
        w[0][1] = var[1];

        double k11, k21, k31, k41;
        double k12, k22, k32, k42;

        for (int i=0; i<N-1;i++) {

            k11 = h * f1(w[i][1]);
            k12 = h * f2(t[i], w[i][0], w[i][1]);

            k21 = h * f1(w[i][1]+0.5 * k12);
            k22 = h * f2(t[i] + h / 2,w[i][0]+0.5 * k11, w[i][1]+0.5 * k12);

            k31 = h * f1(w[i][1]+0.5 * k22);
            k32 = h * f2(t[i] + h / 2, w[i][0]+0.5 * k21, w[i][1]+0.5 * k22);

            k41 = h * f1(w[i][1]+k32);
            k42 = h * f2(t[i] + h, w[i][0]+k31, w[i][1]+k32);

            ur[i]=R*w[i][1];
            w[i+1][0] =w[i][0]+(k11 + 2 * k21 + 2 * k31 + k41) / 6;
            w[i+1][1] =w[i][1]+(k12 + 2 * k22 + 2 * k32 + k42) / 6;
            t[i + 1] = a + i * h;

            System.out.print(String.format("%.5f ", t[i+1]));//время
            System.out.print(String.format("%.5f ", w[i+1][0]));// заряд
            System.out.print(String.format("%.5f ", w[i+1][1]));// ток
            System.out.print(String.format("%.5f ", ur[i]));
            System.out.print(String.format("%.5f ",  eds(t[i+1])));
            System.out.println();
        }
    }
}
