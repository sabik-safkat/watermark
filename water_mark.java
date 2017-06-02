import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class DemoJFileChooser extends JPanel
   implements ActionListener {
   JButton go;
   
   JFileChooser chooser;
   String choosertitle;
   
  public DemoJFileChooser() {
    go = new JButton("Find file");
    go.addActionListener(this);
    add(go);
   }

  public void actionPerformed(ActionEvent e) {
    int result;
        
    chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle(choosertitle);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(true);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): " 
         +  chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  chooser.getSelectedFile());
		 
			File origFile = new File(chooser.getSelectedFile().getName());
		  
            ImageIcon icon = new ImageIcon(origFile.getPath());
 
            // create BufferedImage object of same width and height as of original image
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),
                        icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
 
            // create graphics object and add original image to it
            Graphics graphics = bufferedImage.getGraphics();
            graphics.drawImage(icon.getImage(), 0, 0, null);
 
            // set font for the watermark text
            graphics.setFont(new Font("Arial", Font.BOLD, 30));
 
            String watermark = "watermark";
 
            // add the watermark text
            graphics.drawString(watermark, 0, icon.getIconHeight() / 2);
            //graphics.dispose();
 
            File newFile = new File(chooser.getSelectedFile().getName());
		
            try {
                  ImageIO.write(bufferedImage, "jpg", newFile);
            } catch (IOException x) {
                  x.printStackTrace();
            }
 
            System.out.println(newFile.getPath() + " created successfully!");
			ImageIcon background = new ImageIcon(chooser.getSelectedFile().getName());
			JLabel label = new JLabel();
			label.setBounds(0, 0, 500, 500);
			label.setIcon(background);

			JPanel panel = new JPanel();
			panel.setVisible(true);
			panel.setLayout(null);
			panel.add(label);
      }
    else {
      System.out.println("No Selection ");
      }
     }
   
  public Dimension getPreferredSize(){
    return new Dimension(600, 600);
    }
    
  public static void main(String s[]) {
    JFrame frame = new JFrame("");
    DemoJFileChooser panel = new DemoJFileChooser();
    frame.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
          }
        }
      );
    frame.getContentPane().add(panel,"Center");
    frame.setSize(panel.getPreferredSize());
    frame.setVisible(true);
    }
}