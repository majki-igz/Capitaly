/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beadando1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Player class is responcible for every players basic actions, and has a plays method wich represents 1 round
 * It stroes the datas of a player such as:
 * name, strategy, founds, active, wich filed is the player standing on (index of the board), the type of the filed the player is standing on, and owned properties
 * @author Majki
 */
public abstract class Player {
    protected String name;
    protected PlayerType type;
    protected int founds = 10000;
    protected boolean active;
    protected int standingOnField; 
    protected Field standingOn;
    protected ArrayList<Field> owned;
 
    //konstruktor
    protected Player(PlayerType type, String name, ArrayList<Field> board) {
        this.name = name;
        this.owned = new ArrayList<>();
        this.standingOn = board.get(0);
        this.standingOnField = 0;
        this.type = type;
        this.active = true;

    }
    //------------------------------------------

    //a korokert felelos fuggvenyek:
    /**
     * each individual playerstyle will be defined in this function
     */
    public abstract void doAction();

    /**
     * this function is responcible for the rounds of each player, rolls the dice and moves the player
     * then depending on the players playstyle does the according action.
     * @param board 
     */
    protected void plays(ArrayList<Field> board) {
        if (active) {
            Random rand = new Random();
            int diceRoll = rand.nextInt(6) + 1;
            System.out.println(this.name + " rolled: " + diceRoll);
            
            this.standingOnField += diceRoll;
            this.standingOnField = this.standingOnField % board.size();
            
            this.standingOn = board.get(standingOnField);

            System.out.println("\t and is now stadning on: " + this.standingOn.getType());
            
            doAction();
        }

    }
    //------------------------------------------

    //akciok:
    /**
     * takes the given value from the buyer, and adds it to their owned properties
     * also setting the fileds owner to the buyer
     */
    protected void buyProperty() {
        this.founds -= 1000;
        this.owned.add(this.standingOn);
        this.standingOn.setOwner(this);
    }

    /**
     * takes the given value from the buyer, and adds it on the property the owner is standing on
     */
    protected void buyHouse() {
        this.founds -= 4000;
        this.standingOn.setHouse(true);
    }

    /**
     * checks if the property has a hosue already, then removes the according amount
     * if the player who pays runs out of money, gives the owner his/her remaining money
     * loses all of his/her properties, and gets set to inactive
     * @param owner 
     */
    protected void paysOnProperty(Player owner) {
        if (this.standingOn.hasHouse()) {
            this.founds -= 2000;
            owner.setFounds(owner.getFounds() + 2000);
        } else {
            this.founds -= 500;
            owner.setFounds(owner.getFounds() + 500);
        }
        //kiesik
        if (this.founds < 0) {
            owner.setFounds(owner.getFounds() + this.founds); // a megmaradt penzet kifizeti a tulajdonosnak, a maradék az elveszik
            this.active = false;
            for (Field property : this.owned) {
                property.setOwner(null);
                property.setHouse(false);
            }
            this.owned.clear();
        }
    }
    
    /**
     * removes the according amount
     * if the player runs out of moeny, he/she loses all owned properties and gets set to inactive
     */
    protected void paysOnService() {
        this.founds -= this.standingOn.getAmount();
        //kiesik
        if (this.founds < 0) {
            this.active = false;
            for (Field property : this.owned) {
                property.setOwner(null);
                property.setHouse(false);
            }
            this.owned.clear();
        }
    }
    
    /**
     * gets the according amount added to the founds
     */
    protected void gainsOnLuck() {
        this.founds += this.standingOn.getAmount();
    }
    //------------------------------------------
       
    //toString:
    /**
     * toString method to make the code visible while running
     * @return 
     */
    @Override
    public String toString() {
        return /*"Player{"
                + "name=" + name 
                +*/ "\t Founds: " + founds
                + ", Owns: " + owned
               /* + ", standingOn=" + standingOn
                + ", standingOnField=" + standingOnField
                + ", active=" + active
                + ", type=" + type 
                + '}'*/;
    }
    //------------------------------------------
    
    //setterek,getterek
    public String getName(){
        return this.name;
    }
    
    public int getFounds() {
        return founds;
    }

    public void setFounds(int founds) {
        this.founds = founds;
    }

    public ArrayList<Field> getOwned() {
        return owned;
    }

    public void setOwned(ArrayList<Field> owned) {
        this.owned = owned;
    }

    public Field getStandingOn() {
        return standingOn;
    }

    public int getStandingOnField() {
        return standingOnField;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PlayerType getType() {
        return type;
    }
    //------------------------------------------
    
    //kotelezo az eloadas szerint:
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.founds;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return this.founds == other.founds;
    }
}
