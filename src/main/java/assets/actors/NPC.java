
package assets.actors;

import assets.GameAsset;
import java.awt.Image;
import java.util.Random;
import java.util.Scanner;

public class NPC extends GameAsset{
    
    String[] text;
    int healthpoints;
    String[] inventar;
    boolean alive;
    String[] greetings;
    String greeting;
    boolean quest;
    
    //npc der random begrüßung/beleidigung sagt, der aber getötet werden kann und evtl was dropt
    public NPC(int x, int y, Image img, String name, int healthpoints, String[] inventar) {
        super(x, y, img, name)
        Random r = new Random();
        this.greetings = r.nextInt(greetings.length()); //unterschiedliche, random greetings werden generiert
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

    public void move() {
    }
    
    public String talk() {
        if this.quest = false
            {System.out.println(greeting);
            }else{
            for(int i = 0; i < text.length(); i++)
                {System.put.println(text[i]);
                 Scanner s = new Scanner(System.in);
                 s.next(); //wenn enter gedrückt wird, wird nächster text ausgegeben
                }
        }
    }
 
}
