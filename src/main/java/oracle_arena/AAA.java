package oracle_arena;

/**
 * Created by xudabiao on 2017/8/9.
 */
public class AAA {
    public AAA(Integer i){}
    public static void main(String[] args) {
        try {
            System.out.println(AAA.class.getConstructor(int.class).getDeclaringClass()==AAA.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
