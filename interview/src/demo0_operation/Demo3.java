package demo0_operation;

/**
 * 需要理解栈操作
 */
public class Demo3 {

    public static void main(String[] args) {
        int i = 1;      //i=1
        i = i++;        //i=1
        int j = i++;    //i=2,j=1
        int k = i + ++i * i++; //i=4,j=1,k=11
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
