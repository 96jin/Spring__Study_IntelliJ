package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
    @Autowired    Engine engine;
    @Autowired    Door[] doors;

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

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//      Car car = ac.getBean("car", Car.class); // Ÿ���� �����ϸ� ����ȯ ���ص���. �Ʒ��� ����� ����
        Car car  = (Car) ac.getBean("car"); // �̸����� �� �˻�
        Car car2 = (Car) ac.getBean(Car.class);   // Ÿ������ �� �˻�
        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);

        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames())); // ���ǵ� ���� �̸��� �迭�� ��ȯ
        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount()); // ���ǵ� ���� ������ ��ȯ

        System.out.println("ac.containsBeanDefinition(\"car\") = " + ac.containsBeanDefinition("car"));  // true ���� ���ǰ� ���ԵǾ� �ִ��� Ȯ��
        System.out.println("ac.containsBean(\"car\") = " + ac.containsBean("car")); // true ���� ���ԵǾ� �ִ��� Ȯ��

        System.out.println("ac.getType(\"car\") = " + ac.getType("car")); // ���� �̸����� Ÿ���� �˾Ƴ� �� ����.
        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car")); // true ���� �̱������� Ȯ��
        System.out.println("ac.isPrototype(\"car\") = " + ac.isPrototype("car")); // false ���� ������Ÿ������ Ȯ��
        System.out.println("ac.isPrototype(\"door\") = " + ac.isPrototype("door")); // true
        System.out.println("ac.isTypeMatch(\"car\", Car.class) = " + ac.isTypeMatch("car", Car.class)); // "car"��� �̸��� ���� Ÿ���� Car���� Ȯ��
        System.out.println("ac.findAnnotationOnBean(\"car\", Component.class) = " + ac.findAnnotationOnBean("car", Component.class)); // �� car�� @Component�� �پ������� ��ȯ
        System.out.println("ac.getBeanNamesForAnnotation(Component.class) = " + Arrays.toString(ac.getBeanNamesForAnnotation(Component.class))); // @Component�� ���� ���� �̸��� �迭�� ��ȯ
        System.out.println("ac.getBeanNamesForType(Engine.class) = " + Arrays.toString(ac.getBeanNamesForType(Engine.class))); // Engine �Ǵ� �� �ڼ� Ÿ���� ���� �̸��� �迭�� ��ȯ
    }
}