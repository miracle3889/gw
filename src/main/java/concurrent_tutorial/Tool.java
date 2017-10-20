package concurrent_tutorial;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudabiao on 2017/3/9.
 */
public class Tool {

    public static Map<String,Object> clazz2Map(Class<?> clazz, Object object){
        Map<String,Object> map = new HashMap<String,Object>();
        clazz2Map(clazz,object,map);
        return map;
    }

    private static void clazz2Map(Class<?> clazz, Object object,Map<String,Object> result){
        if(result == null)
            throw new RuntimeException();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //重复的属性以第一次保存为主=>子类为准
            if(result.containsKey(field.getName()))
                continue;
            if(!field.isAccessible())
                field.setAccessible(true);
            try {
                result.put(field.getName(),field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if(interfaces!=null&&interfaces.length!=0)
            for (Class<?> anInterface : interfaces)
                clazz2Map(anInterface,object,result);
        Class<?> superClass = clazz.getSuperclass();
        if(superClass!=null)
            clazz2Map(superClass,object,result);
    }

    public static void main(String[] args) {
        System.out.println(clazz2Map(BigApple.class,new BigApple()));
    }
}
interface appleI{
    int apple_interface = Integer.MIN_VALUE;
}
class Apple implements appleI{
    int apple_i = 1;
}
class BigApple extends Apple {
    int Apple_j =2;
}