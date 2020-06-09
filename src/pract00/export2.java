/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pract00;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author yash1
 */
public class export2 {
    void export(String text,String filename) throws IOException{
           File file1 = new File("Read.text");
           if(file1.exists()){
               file1.delete();
           }
           file1.createNewFile();
           file1.deleteOnExit();
           FileWriter myWriter = new FileWriter("Read.text");
            myWriter.write(text);
            myWriter.close();

        /*
           Because font metrics is based on a graphics context, we need to create
           a small, temporary image so we can ascertain the width and height
           of the final image
         */
        int width = 1000;
        int height = 1000;

        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Times New Roman", Font.PLAIN, 48);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

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
        //fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.white);
        File file = new File("Read.text");
        

        BufferedReader br=null;
        int nextLinePosition=100;
        int fontSize = 48;
        int total_width = img.getWidth();
        try {
            br = new BufferedReader(new FileReader(file));

            String line;
            int h=100,z=0,net_width=0;
            while ((line = br.readLine()) != null) {
               int actual_width = fm.stringWidth(line);
               if((total_width-actual_width-h)>0){
                   net_width = total_width-actual_width-h;
               }
               else{
                   net_width = total_width-actual_width;
               }
                g2d.drawString(line, net_width, nextLinePosition);
                z++;
                //g2d.drawChars(ar, 0, ar.length, 1, nextLinePosition);
               nextLinePosition = nextLinePosition + fontSize;
               if(z<=4){
                   h=140;
               }
               else{
                    h=290;
               }
            }
        br.close();
        } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(null,ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }


        
        try {
            ImageIO.write(img, "png", new File(filename+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        g2d.dispose();
    
    }
}
