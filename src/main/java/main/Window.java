
package main;
import javax.swing.*;
import java.io.File;
import java.awt.Dimension;
import java.util.HashMap;


public class Window{
    
    //  Das Koordinaten System sieht einen Pixel als eine Koordinateneinheit
    //  0,0 ist oben links die Ecke
    //  x wird nach rechts groesser, y nach unten 
    //  bei den Bildern ist die Koordinate auch oben links die Ecke
    //  Bildobjeckte werden bei der imageList mit Namen hinzugefügt 
    //  welche bilder ueber welchen gemalt werden wird hier geregelt
    //  wird aber noch gemacht
    //  Bilddateien in Resources Ordner packen, dan einfach Dateiname bei addImage
    //  fuer Unterordner den namen und dan "/", bsp.: "playerImages/player.png"
    //  wenn man den Spieler bewegen will, dann bewegt man ja eigentlich alles 
    //  um alles außer den Spieler, der bleibt in der Mitte
    
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
        this.spielFenster.setLocation((1920-width)/2, (1080-height)/2);
    }
    
    public static void addImage(String Objectname, String dateiname, Window window, int x, int y) {
        File image = new File("Resources/" + dateiname);
        ImageIcon jFrameImage = new ImageIcon(image.getAbsolutePath());
        JLabel imageLabel = new JLabel(jFrameImage);
        imageLabel.setBounds(x, y, jFrameImage.getIconWidth(), jFrameImage.getIconHeight());
        window.imageList.put(Objectname, imageLabel);
        window.spielFenster.getLayeredPane().add(imageLabel);
        window.spielFenster.getLayeredPane().setLayer(imageLabel, y);
        SwingUtilities.updateComponentTreeUI(window.spielFenster);
    }
    
    public static void removeImage(String Objectname, Window window) {
        window.spielFenster.getLayeredPane().remove(window.imageList.get(Objectname));
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
    
    public static void refreshScreen(Window window){
        for (JLabel i : window.imageList.values()) {
            int layer = i.getBounds().y;
            window.spielFenster.getLayeredPane().setLayer(i, layer);
        }
        SwingUtilities.updateComponentTreeUI(window.spielFenster);
    }
    
    
    
}
