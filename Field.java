/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beadando1;

import java.util.Objects;

/**
 * Filed class is the type of the field with the data of all field as fields.
 * If its a property it stores its owner and if it has a house or not.
 * if its other than property it srotes an amount of wich the field pays, or takes
 * @author Majki
 */
public class Field {
    //property
    private Player owner = null;
    private boolean house = false;
    
    //service, luck
    private final int amount;
    
    //field
    private final FieldType type;
    //------------------------------------------
    
    //constructor
    public Field(int amount, FieldType type) {
        this.amount = amount;
        this.type = type;
    }
    //------------------------------------------
    
    //property methods
    public void setHouse(boolean hasHouse) {
        this.house = hasHouse;
    }

    public boolean hasHouse(){
        return this.house;
    }
    
    public void setOwner(Player player){
        this.owner = player;
    }
    
    public boolean isOwned(){
        return this.owner != null;
    }
    //------------------------------------------
    
    //service, luck
    public int getAmount() {
        return amount;
    }
    //------------------------------------------
    
    //field
    public FieldType getType() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }
    //------------------------------------------
    
    //toString
    @Override
    public String toString() {
        return  //"Field{" 
                //+ "owner=" + owner 
                /*+*/ "house=" + house ;
                //+ ", amount=" + amount 
                //+ ", type=" + type 
                //+ '}';
    }
    //------------------------------------------
    
    //kotelezo az eloadas szerint:
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.owner);
        hash = 89 * hash + (this.house ? 1 : 0);
        hash = 89 * hash + this.amount;
        hash = 89 * hash + Objects.hashCode(this.type);
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
        final Field other = (Field) obj;
        if (this.house != other.house) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return this.type == other.type;
    }
}
