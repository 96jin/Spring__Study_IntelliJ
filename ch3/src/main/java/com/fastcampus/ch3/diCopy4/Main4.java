//package com.fastcampus.ch3.diCopy4;
//
//import com.google.common.reflect.ClassPath;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//@Component class Car{
//    @Resource
//    com.fastcampus.ch3.Engine engine;
//    @Resource
//    com.fastcampus.ch3.Door door;
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "engine=" + engine +
//                ", door=" + door +
//                '}';
//    }
//}
//@Component class SportsCar extends com.fastcampus.ch3.Car {}
//@Component class Truck extends com.fastcampus.ch3.Car {}
//@Component class Engine{}
//@Component class Door{}
//class AppContext{
//    Map map; // ��ü ��¡��
//
//    AppContext(){
//        map = new HashMap();
//        doComponentScan();
//        doAutowired();
//        doResource();
//    }
//
//    private void doResource() {
//        // map�� ����� ��ü�� iv�߿� @Resource�� �پ�������
//        // map���� iv�� �̸��� �´� ��ü�� ã�Ƽ� ����(��ü�� �ּҸ� iv�� ����)
//        try {
//            for(Object bean:map.values()){
//                for(Field fld : bean.getClass().getDeclaredFields()){
//                    if(fld.getAnnotation(Resource.class)!=null){   // byName
//                        fld.set(bean, getBean(fld.getType()));      // car.engine = obj;
//                    }
//                }
//            }
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void doAutowired() {
//        // map�� ����� ��ü�� iv�߿� @Autowired�� �پ�������
//        // map���� iv�� Ÿ�Կ� �´� ��ü�� ã�Ƽ� ����(��ü�� �ּҸ� iv�� ����)
//        try {
//            for(Object bean:map.values()){
//                for(Field fld : bean.getClass().getDeclaredFields()){
//                    if(fld.getAnnotation(Autowired.class)!=null){   // byType
//                        fld.set(bean, getBean(fld.getType()));      // car.engine = obj;
//                    }
//                }
//            }
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void doComponentScan() {
//        try {
//            // 1. ��Ű�� ���� Ŭ���� ����� �����´�.
//            // 2. �ݺ������� Ŭ������ �ϳ��� �о�ͼ� @Component�� �پ��ִ��� Ȯ��
//            // 3. @Component�� �پ������� ��ü�� �����Ͽ� map�� ����
//            ClassLoader classLoader = AppContext.class.getClassLoader();
//            ClassPath classPath = ClassPath.from(classLoader);
//
//            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");
//
//            for(ClassPath.ClassInfo classInfo:set){
//                Class clazz = classInfo.load();
//                Component component = (Component) clazz.getAnnotation(Component.class);
//                if(component != null){
//                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
//                    map.put(id,clazz.newInstance());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    Object getBean(String key){
//        return map.get(key);
//    }
//    Object getBean(Class clazz){
//        for(Object obj:map.values()){
//            if(clazz.isInstance(obj)){
//                return obj;
//            }
//        }
//            return null;
//    }
//}
//
//public class Main4 {
//    public static void main(String[] args) throws Exception {
//        AppContext ac = new AppContext();
//
//        com.fastcampus.ch3.Car car = (com.fastcampus.ch3.Car)ac.getBean("car");   // byName���� ��ü�� �˻�
//        com.fastcampus.ch3.Door door = (com.fastcampus.ch3.Door)ac.getBean(com.fastcampus.ch3.Door.class);  // byType���� ��ü�� �˻�
//        com.fastcampus.ch3.Engine engine = (com.fastcampus.ch3.Engine)ac.getBean("engine");
//
//        // �������� ��ü ����
////        car.engine = engine;
////        car.door = door;
//
//        System.out.println("car = "+car);
//        System.out.println("door = "+door);
//        System.out.println("engine = " + engine);
//    }
//
//}
//
