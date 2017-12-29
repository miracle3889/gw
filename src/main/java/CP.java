import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CP {

    static class Original {
        public void originalMethod(String s) {
            System.out.println(s);
        }
    }

    static class Handler implements MethodInterceptor {
        private final Original original;

        public Handler(Original original) {
            this.original = original;
        }

        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("BEFORE");
            method.invoke(original, args);
            System.out.println("AFTER");
            return null;
        }
    }

    public static void main(String[] args){
        Original original = new Original();
        MethodInterceptor handler = new Handler(original);
        Original f = (Original) Enhancer.create(Original.class,handler);
        System.out.println(f.getClass());
        System.out.println(f instanceof Original);
        f.originalMethod("Hallo");
    }
}
