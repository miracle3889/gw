package bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by xudabiao on 2017/3/1.
 */
public abstract class Abs_Apple {
    static {
        System.out.println("static block in Abs_apple");
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> integerIterator = list.iterator();
        while(integerIterator.hasNext()){
            Integer i = integerIterator.next();
            if(i==2)
                integerIterator.remove();
        }
        System.out.println(list);
    }
}
