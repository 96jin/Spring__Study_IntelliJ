package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car{}
class SportsCar extends Car{}
class Truck extends  Car{}
class Engine{}

class AppContext{
    Map map; // ��ü ��¡��

    AppContext(){
        map = new HashMap();
        try {
            Properties p  = new Properties();
            p.load(new FileReader("config.txt"));

            // Properties�� ����� ������ Map�� ����
            map = new HashMap(p);

            // �ݺ������� Ŭ���� �̸��� �� ��ü�� �����ؼ� �ٽ� map�� ����
            for(Object key: map.keySet()) {
                Class clazz = Class.forName((String)map.get(key));
                map.put(key, clazz.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Object getBean(String key){
        return map.get(key);
    }
}

public class Main2 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        Car car2 = (Car)ac.getBean("car");
        Engine engine = (Engine)ac.getBean("engine");

        System.out.println("car 2 = "+car2);
        System.out.println("engine = " + engine);
    }

}

