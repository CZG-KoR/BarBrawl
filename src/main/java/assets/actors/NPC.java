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
    boolean quest, isMoving;

    //npc der random begrüßung/beleidigung sagt, der, wenn angeschlagen, zurück angreift und zum enemy wird
    public NPC(int x, int y, Image img, String name, int healthpoints, String[] inventar) {
        super(x, y, img, name);
        Random r = new Random();
        this.greeting = greetings[r.nextInt(greetings.length)]; //unterschiedliche, random greetings werden generiert
        this.alive = true;
        this.healthpoints = healthpoints;
        this.inventar = inventar;
        this.quest = false;
        this.isMoving = false;
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
        if (quest != true) {
        } else {

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
                xend = this.getX() + length;
                yend = this.getY();
                deltax = 1;
                deltay = 0;

            }

            //bewegung in negative x-Richtung
            if (richtung == 2) {
                xend = this.getX() - length;
                yend = this.getY();
                deltax = -1;
                deltay = 0;
            }

            //bewegung in positive y-Richtung
            if (richtung == 3) {
                yend = this.getY() + length;
                xend = this.getX();
                deltay = 1;
                deltax = 0;
            }

            //bewegung in negative y-Richtung
            if (richtung == 4) {
                yend = this.getY() - length;
                xend = this.getX();
                deltay = -1;
                deltax = 0;
            }

            //stellt sicher, dass charakter sich bewegt
            isMoving = true;

        }
    }

    //wird so: move(xend, yend, dektax, deltay); aufgerufen, wenn isMoving true ist
    //änderung der koordinaten entsprechend der zufälligen variablen
    public void move(int xend, int yend, int deltax, int deltay) {

        //abbruch wenn zielkoordinate erreicht
        if (this.getY() == yend && this.getX() == xend) {
            stopMoving();
        }

        //abbruch wenn nächstes feld ein hinderniss ist
        if (room.isWalkable(this.getX() + deltax, this.getY() + deltay)) {
            stopMoving();
        }

        if (this.getX() != xend) {
            this.setX(this.getX() + deltax);
        }
        if (this.getY() != yend) {
            this.setY(this.getY() + deltay);
        }

    }

    public void stopMoving() {
        //abbruch wenn zielkoordinate erreicht, zufällige wartezeit bis nächste bewegung

        isMoving = false;

        Random r = new Random();
        wait(r.nextInt(1000));
        WhereToMove();

    }

    //ausgabe des zugewiesenen textes über die konsole
    public void talk() {
        if (this.quest = false) { //wenn kein quest-guy wird random greeting ausgegeben
            System.out.println(greeting);
        } else { //ansonsten wird quest-text ausgegeben
            for (int i = 0; i < text.length; i++) {
                System.out.println(text[i]);
                Scanner s = new Scanner(System.in);
                s.next(); //wenn enter gedrückt wird, wird nächster text ausgegeben
            }
        }
    }

    //wird bei jedem laden aufgerufen, prüft, ob ein npc genug schaden bekommen hat, damit er zum enemy wird
    public GameAsset toHostile() {
        if (healthpoints <= 0.8 * healthpoints) {
            return Main.createEnemy(this); //this = wir selber, also der aktuelle NPC, dessen healthpoints gecheckt werden

        }
        return this;
    }

    //bei tod enemy wird nach einiger zeit ein neuer gespawnt
    public boolean isAlive(NPC npc) {
        if (healthpoints == 0) {
            //damit er nicht mehr gezeichnet wird
            alive = false;

            //items droppen
            for (int i = 0; i < inventar.length; i++) {
                Random r = new Random();
                if (r.nextInt(100) <= 20) { //npc droppt das item
                    System.out.println(inventar[i]); //an dieser stelle noch etwas anderes hinzufügen --> item wird inventar des spielers hinzugefügt      
                }
            }

            //damit irgendwann ein neuer NPC spawnt
            Random r = new Random();
            //das passiert übergeordnet: muss ich mich nicht drum kümmern
            //wait(r.nextInt(1000));
            //generate();   //leere methode
            return false;
        }
        return true;
    }

}
