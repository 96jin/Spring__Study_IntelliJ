package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

@Component
@Scope("prototype")
class Door {}
@Component class Engine {}
@Component class TurboEngine extends Engine {}
@Component class SuperEngine extends Engine {}

@Component
class Car {
    @Value("red") String color;
    @Value("100") int oil;
//    @Autowired
    Engine engine;
//    @Autowired
    Door[] doors;

//    @Autowired
    public Car(@Value("red") String color, @Value("100") int oil, Engine engine, Door[] doors) {
        this.color = color;
        this.oil = oil;
        this.engine = engine;
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}

@Component
@PropertySource("setting.properties")
class SysInfo{
    @Value("#{systemProperties['user.timezone']}")
    String timeZone;
    @Value("#{systemEnvironment['APPDATA']}")
    String currDir;
    @Value("${autosaveDir}")
    String autosaveDir;
    @Value("${autosaveInterval}")
    int autosaveInterval;
    @Value("${autosave}")
    boolean autosave;

    @Override
    public String toString() {
        return "SysInfo{" +
                "timeZone='" + timeZone + '\'' +
                ", currDir='" + currDir + '\'' +
                ", autosaveDir='" + autosaveDir + '\'' +
                ", autosaveInterval=" + autosaveInterval +
                ", autosave=" + autosave +
                '}';
    }
}

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//      Car car = ac.getBean("car", Car.class); // Ÿ���� �����ϸ� ����ȯ ���ص���. �Ʒ��� ����� ����
        Car car  = (Car) ac.getBean("car"); // �̸����� �� �˻�
        Car car2 = (Car) ac.getBean(Car.class);   // Ÿ������ �� �˻�
        System.out.println("car = " + car);

        Map<String,String> env = System.getenv();
        System.out.println("System.getenv() = "+env);

        Properties prop = System.getProperties();
        System.out.println("System.getProperties() = "+prop);

        System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
    }
}