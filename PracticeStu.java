package com.mystudy.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PracticeStu {

    public static void main(String[] args) {

        // 1. 기기 연결

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("서버연결");
        } catch (ClassNotFoundException e) {
            System.out.println("서버실패");
            e.printStackTrace();
        }

        // 2. 서버연결
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.222", "mystudy", "mystudypw");
        } catch (SQLException e) {

            e.printStackTrace();
        }
        // 3. 정보 보내기
        ResultSet rs = null;
        Statement sm = null;

        try {
            sm = conn.createStatement();
            // 스트링을 스테이트먼트에 담아서 결과물에 넣는다.
            // 즉, 스테이트먼트는 인풋임.

            String sql = "SELECT ID,NAME,KOR,ENG,MATH,TOT,AVG FROM STUDENT ORDER BY NAME";

            rs = sm.executeQuery(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        // 4. 데이터 받아오기
        
         System.out.println("데이터 입력중");
         try {
            while (rs.next()) {
                 String str = "";
                 str += rs.getNString("ID")+ "\t";
                 str += rs.getNString("NAME")+ "\t";
                 str += rs.getInt("KOR")+ "\t";
                 str += rs.getInt("ENG")+ "\t";
                 str += rs.getInt("MATH")+ "\t";
                 str += rs.getInt("TOT")+ "\t";
                 str += rs.getDouble("AVG");
                 
                 System.out.println(str);
             }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        
        // 5. 클로즈처리
         
         if (conn != null) {try {
            conn.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }}
         
         if (rs != null) {try {
            rs.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }}
         
         if (sm != null) {try {
            sm.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }}
         
    }
}
