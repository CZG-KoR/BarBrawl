
package main;
import javax.swing.*;
import java.io.File;
import java.awt.Dimension;
import java.util.HashMap;


public class Window{
    
    //  Das Koordinaten System sieht einen Pixel als eine Koordinateneinheit
    //  0,0 ist obe rechts die Ecke
    //  x wird nach rechts groesser, y nach unten 
    //  bei den Bildern ist die Koordinate auch oben rechts die Ecke
    
    JFrame spielFenster;
    HashMap<String, JLabel> imageList = new HashMap<>();
    
    public Window( String title, int width, int height){
        this.spielFenster = new JFrame(title);
        this.spielFenster.setLayout(null);
        this.spielFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.spielFenster.setMinimumSize(new Dimension(width, height));
        this.spielFenster.pack();
        this.spielFenster.setResizable(false);
        this.spielFenster.setVisible(true);

    }
    
    public static void addImage(String Objectname, String verzeichnis, Window window, int x, int y) {
        File image = new File(verzeichnis);
        ImageIcon jFrameImage = new ImageIcon(image.getAbsolutePath());
        JLabel imageLabel = new JLabel(jFrameImage);
        imageLabel.setBounds(x, y, jFrameImage.getIconWidth(), jFrameImage.getIconHeight());
        window.imageList.put(Objectname, imageLabel);
        window.spielFenster.add(imageLabel);
        SwingUtilities.updateComponentTreeUI(window.spielFenster);
    }
    
    public static void removeImage(String Objectname, Window window) {
        window.spielFenster.remove(window.imageList.get(Objectname));
        SwingUtilities.updateComponentTreeUI(window.spielFenster);
        window.imageList.remove(Objectname);
    }
    
    public static void moveImage(String Objectname, Window window, int x,int y) { 
        JLabel object = window.imageList.get(Objectname);
        object.setBounds(
                object.getX() + x,
                object.getY() + y, 
                object.getWidth(),
                object.getHeight());
    }
    
    public static void moveCamera(Window window, String playername,int x, int y){
        for (String objects : window.imageList.keySet()){
            if (!objects.equals(playername)) {
                Window.moveImage(objects, window, -x, -y);
            }
        }
    }
    
    
}
