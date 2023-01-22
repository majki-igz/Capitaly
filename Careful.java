/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beadando1;

import java.util.ArrayList;
/**
 * CarefulPlayer extends from Player and overrides its doAction() method wich is called in the plays() method
 * @author Majki
 */
public class Careful extends Player{

    public Careful(String name, ArrayList<Field> board){
        super(PlayerType.CAREFUL, name, board);
    }
    //------------------------------------------
    
    /**
     * the careful player buys things if he/she has the double of the expanse
     * otherwise pays or gains money
     */
    @Override
    public void doAction(){
        switch (standingOn.getType()){
            case PROPERTY:
                //hazat epit ha az ove
                if (standingOn.getOwner() == this && (founds/2) >= 4000) {
                    buyHouse();
                }
                //megveszi
                if (!(standingOn.isOwned()) && (founds/2) >= 1000) {
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
