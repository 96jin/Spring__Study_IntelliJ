//package com.fastcampus.ch3;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
////@Component
//class Engine{}
//@Component class TurboEngine extends Engine{}
//@Component class SuperEngine extends Engine{}
//@Component class Door{}
//@Component
//class Car{
//    @Value("red") String color;
//    @Value("100") int oil;
////    @Autowired  // byType으로 검색
////    @Qualifier("superEngine")   // @Component 안되어있으면 @Qualifier("이름") 추가 해주면 가능
//    @Resource(name = "superEngine") // byName 으로 검색, @Autowired 와 @Qualifier 대신에 @Resource 하나만으로 사용가능
//    Engine engine;  // byType으로 먼저 검색, 타입이 여러개면 이름으로 검색 = engine, superEngine, turboEngine
//    @Autowired
//    Door[] doors;
//    public Car(){}  // 기본생성자를 꼭 만들어주자
//    public Car(String color, int oil, Engine engine, Door[] doors) {
//        this.color = color;
//        this.oil = oil;
//        this.engine = engine;
//        this.doors = doors;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//public class SpringDiTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
////        Car car = (Car)ac.getBean("car"); 아래와 같은 문장
//        Car car = ac.getBean("car",Car.class);  // byName
//        Car car2 = ac.getBean(Car.class);   // byType
//        Engine engine = (Engine)ac.getBean("superEngine");  //byName으로 검색해야한다. why? engine클래스가 3개있어서 오류발생
//        Door door = (Door)ac.getBean("door");
//
//        // config.xml 에서 property 설정가능
////        car.setColor("red");
////        car.setOil(100);
////        car.setEngine(engine);
////        car.setDoors(new Door[]{ac.getBean("door",Door.class), (Door)ac.getBean("door")});
//        System.out.println("car = "+car);
//        System.out.println("engine = "+engine);
//
//    }
//}
