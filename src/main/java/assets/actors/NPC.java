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

    //wird irgendwann aufgerufen
    public void WhereToMove() {
        //npcs bewegen sich zufällig durch den Raum
        Random r = new Random();
        int länge = r.nextInt(100);
        int richtung = r.nextInt(4);

        
        //bewegung in positive x-Richtung
        if (richtung == 1) {
            if (room.isWalkable(x + 1, y)) {}
                walk();
            
        }

        //bewegung in negative x-Richtung
        if (richtung == 2) {
            if (room.isWalkable(x - 1, y)) {

            }
        }

        //bewegung in positive y-Richtung
        if (richtung == 3) {
            if (room.isWalkable(x, y + 1)) {

            }
        }

        //bewegung in negative y-Richtung
        if (richtung == 4) {
            if (room.isWalkable(x, y - 1)) {

            }
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
            NPC.generate();   //methode muss noch angelegt werden
        }
    }

}

