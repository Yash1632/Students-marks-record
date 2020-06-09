/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pract00;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author yash1
 */
public class export {
    void exports(String text,String name){
       

        /*
           Because font metrics is based on a graphics context, we need to create
           a small, temporary image so we can ascertain the width and height
           of the final image
         */
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Arial", Font.PLAIN, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        try {
            ImageIO.write(img, "png", new File(name+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    void data_exp(String user,String password,String username,String record_name){
        try{
            Connection con;
            Statement stmt;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+username,user,password);
            stmt = con.createStatement();
            String q = "select * from "+record_name+";";
            ResultSet rs = stmt.executeQuery(q);
            ResultSetMetaData rsmd = rs.getMetaData();
            int column = rsmd.getColumnCount();
            String s1;
            String text_to_print;
            while(rs.next()){
                text_to_print = rs.getString("Student_Name")+"         (Result Of Peridoic Exam)\n\n\n"+"Subject          Marks(Out Of 25)";
                for(int i=2;i<=column;i++){
                    s1 = rsmd.getColumnName(i);
                    int n = s1.length();
                    text_to_print += "\n\n"+s1;
                    for(int j=0;j<(23+7-n);j++){
                        text_to_print+=" ";
                    }
                    text_to_print+=rs.getInt(i);
                }
//                System.out.println(text_to_print);
                export2 exp = new export2();
                exp.export(text_to_print,rs.getString("Student_Name"));
            }
            JOptionPane.showMessageDialog(null,"Records Exported");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!");
        }
    }
}
