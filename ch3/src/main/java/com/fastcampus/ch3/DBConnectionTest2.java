package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

public class DBConnectionTest2 {
    public static void main(String[] args) throws Exception {

        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // �����ͺ��̽��� ������ ��´�.

        System.out.println("conn = " + conn);
//        assertTrue(conn!=null);
    }
}

