package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test{
    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws Exception{
        User user = new User("asdf","1234","abc","aaaa@aaa.com",new Date(), "fb",new Date());
        deleteAll();
        int rowCnt = insertUser(user);

        assertTrue(rowCnt == 1);
    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        User user = new User("asdf","1234","abc","aaaa@aaa.com",new Date(), "fb",new Date());

        User user2 = selectUser("asdf");

        assertTrue(user.getId().equals("asdf"));
    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();
        int rowCnt = deleteUser("asdf");

        assertTrue(rowCnt==0);

        User user = new User("asdf","1234","abc","aaaa@aaa.com",new Date(), "fb",new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt==1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt==1);

        assertTrue(selectUser(user.getId())==null);

    }

    @Test   // ȥ�� �����غ���
    public void updateUserTest() throws Exception {

        int rowCnt = updateUser("asdf");

        assertTrue(rowCnt==1);

    }

    // �Ű������� ���� ����� ������ user_info ���̺��� update�ϴ� �޼���
    public int updateUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "update user_info set pwd=1111 where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);

        return pstmt.executeUpdate();

    }

    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
//        int rowCnt = pstmt.executeUpdate();
//        return rowCnt;
        return pstmt.executeUpdate();
    }

    public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();   // select �ϋ� ���

        if(rs.next()){
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(rs.getDate(7));

            return user;
        }
        return null;
    }

    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info";

        PreparedStatement pstmt = conn.prepareStatement(sql);   // SQL Injection ����, �������, ? �� ��� ����
        pstmt.executeUpdate();
    }

    // ����� ������  user_info ���̺� �����ϴ� �޼���
    public int insertUser(User user) throws Exception{
        Connection conn = ds.getConnection();

        String sql = "insert into user_info values (?,?,?,?,?,?,now())";

        PreparedStatement pstmt = conn.prepareStatement(sql);   // SQL Injection ����, �������, ? �� ��� ����
        pstmt.setString(1 , user.getId());
        pstmt.setString(2,user.getPwd());
        pstmt.setString(3,user.getName());
        pstmt.setString(4,user.getEmail());
        pstmt.setDate(5,new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6,user.getSns());

        int rowCnt = pstmt.executeUpdate();  // ���ϰ��� int���̴�, sql ������ ��� ���� ����Ǿ������� ��ȯ�Ѵ�.
                                             // insert,update,delete���� ��� ����

        return rowCnt;
    }

    @Test
    public void main() throws Exception{
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // �����ͺ��̽��� ������ ��´�.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null); // test�� �����ߴ����� Ȯ��, ��ȣ ���� ���ǽ��� true�� ����, �ƴϸ� ����
    }
}