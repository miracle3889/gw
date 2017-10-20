import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

/**
 * Created by xudabiao on 2017/4/27.
 */
public class skipAll {
    public static void main(String[] args) {
        check();
    }

    private static void check(){
        try {
            File file = new File("C:\\Users\\apple\\Desktop\\not_matching.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            BigDecimal total = new BigDecimal("0");
            BigDecimal total2 = new BigDecimal("0");
            while (line!=null&&!line.trim().isEmpty()){
//                System.out.println(line);
                String[] sx = line.trim().split("\t");
                String d1 = sx[0].trim();
                String d2 = sx[1].trim();
                String ez = mul(d1,d2);
//                System.out.println(ez);
                String with_draw_tax =div(d1,"1.17",10);
                String part1 =mul(with_draw_tax,d2);
                String part2 =mulTax(with_draw_tax,d2);
                String comp = add(part1,part2);
//                System.out.println();
//                if(!comp.equals(ez))
                    System.out.println(ez+"|"+comp);
                line = br.readLine();
                total = total.add(new BigDecimal(ez));
                total2 = total2.add(new BigDecimal(ez));
            }
            System.out.println(total.toString());
            System.out.println(total2.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static String div(String v1, String v2, int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String mulTax(String v1,String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal b3 = new BigDecimal(".17");
        return b1.multiply(b2).multiply(b3).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String add(String v1,String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
}
