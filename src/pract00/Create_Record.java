/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pract00;

import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author yash1
 */
public class Create_Record {
    void create_records(String user,String password,String username,String Record_Name,int nof,Vector <String> field){
        try{
            Connection con;
            Statement stmt;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+username,user,password);
            stmt = con.createStatement();
            String q0 = "create table "+Record_Name+"(Student_Name varchar(40) not null";
            for(int i=0;i<nof;i++){
                q0+=", "+field.elementAt(i)+" int(3) ";
            }
            q0+=");";
            stmt.executeUpdate(q0);
            JOptionPane.showMessageDialog(null, "Record Created!");
            con.close();
        }
        catch(Exception e){
            System.out.println("Error\n"+e);
        }
    }
}
