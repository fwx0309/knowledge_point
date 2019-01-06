package demo2_iteration;

/**
 * n步台阶，每次走一走，或走两步，一共有多少种走法
 */
public class Iteration_ways {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(f(41));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static Integer f(int n){
        if(n==1 || n==2){
            return Integer.valueOf(n);
        }
        return f(n-1)+f(n-2);
    }
}
