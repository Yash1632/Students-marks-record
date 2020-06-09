/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pract00;
import java.sql.*;
/**
 *
 * @author yash1
 */


public class Pract {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Connection con;
            Statement stmt;
            String user = "root",password = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/",user,password);
            stmt = con.createStatement();
            String query1 = "show databases;";
            ResultSet r0 = stmt.executeQuery(query1);
            boolean initial_database = false;
            while (r0.next()){
                if(r0.getString("Database").equals("usrrecords")){
                    initial_database = true;
                    stmt.executeUpdate("use usrrecords;");
                    break;
                }
            }
            if(initial_database == false){
                prereq p = new prereq();
                p.setVisible(true);
                String q2 = "create database usrrecords;";//query_2
                stmt.executeUpdate(q2);
                stmt.executeUpdate("use usrrecords;");
                String q3 = "create table login(\nName varchar(50) not null,\nUsername varchar(20) primary key,\npassword varchar(60) not null);";
                stmt.executeUpdate(q3);
                p.setVisible(false);
                p.dispose();
            }
            con.close();
            login l = new login(user,password);
            l.setVisible(true);
        }
        catch(Exception e){
            System.out.println("error!\n"+e);
        }
    }
    
}
