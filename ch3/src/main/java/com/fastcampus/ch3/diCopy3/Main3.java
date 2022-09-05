package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component class Car{}
@Component class SportsCar extends Car{}
@Component class Truck extends  Car{}
@Component class Engine{}

class AppContext{
    Map map; // ��ü ��¡��

    AppContext(){
        map = new HashMap();
        doComponentScan();
    }

    private void doComponentScan() {
        try {
            // 1. ��Ű�� ���� Ŭ���� ����� �����´�.
            // 2. �ݺ������� Ŭ������ �ϳ��� �о�ͼ� @Component�� �پ��ִ��� Ȯ��
            // 3. @Component�� �پ������� ��ü�� �����Ͽ� map�� ����
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for(ClassPath.ClassInfo classInfo:set){
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if(component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id,clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key){
        return map.get(key);
    }
    Object getBean(Class clazz){
        for(Object obj:map.values()){
            if(clazz.isInstance(obj)){
                return obj;
            }
        }
            return null;
    }
}

public class Main3 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        Car car = (Car)ac.getBean("car");   // byName���� ��ü�� �˻�
        Car car2 = (Car)ac.getBean(Car.class);  // byType���� ��ü�� �˻�
        Engine engine = (Engine)ac.getBean("engine");

        System.out.println("car 2 = "+car);
        System.out.println("engine = " + engine);
    }

}

