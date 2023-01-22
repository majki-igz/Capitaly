/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beadando1;

import java.util.ArrayList;

/**
 * GreedyPlayer extends from Player and overrides its doAction() method wich is called in the plays() method
 * @author Majki
 */
public class Greedy extends Player{
    //konstruktor
    public Greedy(String name, ArrayList<Field> board){
        super(PlayerType.GREEDY, name, board);
    }
    //------------------------------------------
    
    /**
     * the greedy playstyle buys everything when he/she has the chance
     * otherwise pays or gains money
     */
    @Override
    public void doAction() {
        switch (standingOn.getType()){
            case PROPERTY:
                //hazat epit ha az ove
                if (standingOn.getOwner() == this && founds >= 4000) {
                    buyHouse();
                }
                //megveszi
                if (!(standingOn.isOwned()) && founds >= 1000) {
                    buyProperty();
                }
                //fizet ha mase
                if (standingOn.getOwner() != null) {
                    paysOnProperty(standingOn.getOwner());
                }
                break;
            case SERVICE:
                paysOnService();
                break;
            case LUCK:
                gainsOnLuck();
                break;
        }
    }
}
