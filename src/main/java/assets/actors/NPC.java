package assets.actors;

import assets.GameAsset;
import java.awt.Image;
import java.util.Random;
import java.util.Scanner;
import main.Main;

public class NPC extends GameAsset {

    String[] text;
    int healthpoints;
    String[] inventar;
    boolean alive;
    String[] greetings;
    String greeting;
    boolean quest;

    //npc der random begrüßung/beleidigung sagt, der, wenn angeschlagen, zurück angreift und zum enemy wird
    public NPC(int x, int y, Image img, String name, int healthpoints, String[] inventar) {
        super(x, y, img, name);
        Random r = new Random();
        this.greeting = greetings[r.nextInt(greetings.length)]; //unterschiedliche, random greetings werden generiert
        this.alive = true;
        this.healthpoints = healthpoints;
        this.inventar = inventar;
        this.quest = false;
    }

    //npc mit text, der zu questline gehört, kann noch getötet werden --> evtl ändern
    public NPC(int x, int y, Image img, String name, String[] text, int healthpoints, String[] inventar) {
        super(x, y, img, name);
        this.text = text;
        this.alive = true;
        this.healthpoints = healthpoints;
        this.inventar = inventar;
        this.quest = true;
    }

    //wird einmal am anfang aufgerufen
    public void WhereToMove() {
        //questNPCs bewegen sich nicht, damit man sie leicht wiederfinden kann
        if(quest != true)
        {break()}

        
        //npcs bewegen sich zufällig durch den Raum
        Random r = new Random();
        int length = r.nextInt(10);
        int richtung = r.nextInt(4);

        int xend; 
        int yend;

        int deltax;
        int deltay;

        
        //bewegung in positive x-Richtung
        if (richtung == 1) {
            xend=x+length;
            yend=y;
            deltax = 1;
            deltay=0;
            
        }

        //bewegung in negative x-Richtung
        if (richtung == 2) {
            xend=x-length;
            yend=y;
            deltax=-1;
            deltay=0;
            move(
        }

        //bewegung in positive y-Richtung
        if (richtung == 3) {
           yend = y+length;
            xend=x;
            deltay=1;
            deltax==;
        }

        //bewegung in negative y-Richtung
        if (richtung == 4) {
            yend = y-length;
            xend=x;
            deltay=-1;
            deltax=0;
        }
        //weitergeben zufälliger variablen an move-methode
        move(xend, yend, dektax, deltay);

    }


    //änderung der koordinaten entsorechend der zufälligen variablen
    public void move(int xend, int yend, int deltax, int deltay)
    {    if(x!= xend){
        x=x+deltax;
    }
         if(y!=yend){
         y=y + deltay;
    }

     //abbruch wenn zielkoordinate erreicht, zufällige wartezeit bis nächste bewegung
     if(y=yend && x = xend){

        Random r =new Random;
        wait(r.nextInt(1000));
        WhereToMove();
     }
    }

    //ausgabe des zugewiesenen textes über die konsole
    public void talk() {
        if (this.quest = false) {
            System.out.println(greeting);
        } else {
            for (int i = 0; i < text.length; i++) {
                System.out.println(text[i]);
                Scanner s = new Scanner(System.in);
                s.next(); //wenn enter gedrückt wird, wird nächster text ausgegeben
            }
        }
    }

    //wird bei jedem laden aufgerufen, prüft, ob ein npc genug schaden bekommen hat, damit er zum enemy wird
    public void toHostile() {
        if (healthpoints <= 0.8 * healthpoints) {
            Main.createEnemy(n);

        }
    }

    //bei tod enemy wird nach einiger zeit ein neuer gespawnt
    public void isAlive(NPC npc) {
        if (healthpoints == 0) {
            Rabdom r=new Random;
            wait(r.nextInt(1000);
            NPC.generate();   //methode muss noch angelegt werden
        }
    }

}

