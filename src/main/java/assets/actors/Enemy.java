/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assets.actors;

import assets.GameAsset;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author administrator
 */
public class Enemy extends GameAsset {

    int healthpoints, damage, attack;
    boolean alive;
    String[] inventar;

    public Enemy(int x, int y, Image img, String name) {
        super(x, y, img, name);
        this.damage = 10;
        this.attack = 10;
        this.alive = true;
        this.inventar = inventar;
    }

    public Enemy(int x, int y, Image img, String name, int damage, int attack) {
        super(x, y, img, name);
        this.damage = damage;
        this.attack = attack;
        this.alive = true;
        this.inventar = inventar;
    }

    public int getHealth() {
        return healthpoints;
    }

    public void setHealth(int health) {
        this.healthpoints = healthpoints;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttack() {
        return attack;
    }

    //bei tod enemy wird nach einiger zeit ein neuer gespawnt
    public void isAlive(Enemy ene) {
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
            //generate();  
        }
    }

}

