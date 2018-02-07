package bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by xudabiao on 2017/12/18.
 */
public class CarFact implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Car c = new Car();
        c.setId(1);
        c.setPrice(99);
        return c;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
