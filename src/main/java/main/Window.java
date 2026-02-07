package main;
import javax.swing.*;
import java.io.File;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.HashMap;


public class Window{
    
    //  Das Koordinaten System sieht einen Pixel als eine Koordinateneinheit
    //  0,0 ist oben links die Ecke
    //  x wird nach rechts größer, y nach unten 
    //  bei den Bildern ist die Koordinate auch oben links die Ecke
    //  Bildobjeckte werden bei der imageList mit Namen hinzugefügt 
    //  welche bilder über welchen gemalt werden wird hier geregelt
    //  muss aber vielleicht noch überarbeitet werdeb
    //  Bilddateien in "Resources" Ordner packen, dan einfach Dateiname bei addImage
    //  für Unterordner den namen und dan "/", bsp.: "playerImages/player.png"
    
    JFrame spielFenster;
    HashMap<String, JLabel> imageList = new HashMap<>();
    
    public Window( String title, int width, int height){
        this.spielFenster = new JFrame(title);
        //  für freie Objektanordnung
        this.spielFenster.setLayout(null);
        this.spielFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.spielFenster.setMinimumSize(new Dimension(width, height));
        //  mit skalieren nicht implementiert werden muss
        this.spielFenster.setResizable(false);
        this.spielFenster.setVisible(true);
        //  zentrieren(nimmt 1920:1080 Bildschirm an)
        this.spielFenster.setLocation((1920-width)/2, (1080-height)/2);
        //  startet Timer, der den Bildschirm alle 16 Millisekunden(~60mal pro Sekunde)
        //  aktualisiert
        Timer t = new Timer(16, (ActionEvent ae) -> {
            Window.refreshScreen(this);
        });
        t.start();
    }
    
    public static void addImage(String objectname, String filename, Window window, int x, int y) {
        //  findet Datei mit lokalem Dateipfad zur Bilddatei
        File image = new File("Resources/" + filename);
        //  absoluten Dateienpfad zu Datei ermitteln, Bild laden
        ImageIcon jFrameImage = new ImageIcon(image.getAbsolutePath());
        JLabel imageLabel = new JLabel(jFrameImage);
        //  Größe des JLabel(Objekt) auf Bild anpassen, Position setzen
        imageLabel.setBounds(x, y, jFrameImage.getIconWidth(), jFrameImage.getIconHeight());
        //  Objekt in Objektliste hinzufügen
        window.imageList.put(objectname, imageLabel);
        //  Objekt in Fenster hinzufügen
        window.spielFenster.getLayeredPane().add(imageLabel);
    }
    
    public static void removeImage(String objectname, Window window) {
        JLabel object = window.imageList.get(objectname);
        //  entfernt Objekt aus Objektliste, mit bei Fensteraktualisierung alles gut läuft
        window.imageList.remove(objectname);
        //  entfernt Objekt von Fenster
        window.spielFenster.getLayeredPane().remove(object);
    }
    
    public static void moveImage(String objectname, Window window, int x,int y) { 
        //  Objekt aus Objektliste von Objektname
        JLabel object = window.imageList.get(objectname);
        object.setBounds(
                object.getX() + x,
                object.getY() + y, 
                object.getWidth(),
                object.getHeight());
    }
    
    
    public static void movePlayer(String playername, Window window, int x, int y){
        //  iteriert durch alle Objektnamen
        for (String i : window.imageList.keySet()){
        //  alles außer Spieler wird in entgegengestzte Richtung bewegt
            if (!i.equals(playername)) {
                Window.moveImage(i, window, -x, -y);
            }
        }
    }
    
    public static void refreshScreen(Window window){
        //  iteriert durch alle Objekte
        for (JLabel i : window.imageList.values()) {
        //  je größer y, desto weiter unten -> vorne
        //  Höhe addiert, weil untere Kante gesucht ist
            int layer = i.getBounds().y + i.getBounds().height;
            window.spielFenster.getLayeredPane().setLayer(i, layer);
        }
        SwingUtilities.updateComponentTreeUI(window.spielFenster);
    }
    
    
    
}
