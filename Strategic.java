/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beadando1;

import java.util.ArrayList;

/**
 * StrategicPlayer extends from Player and overrides its doAction() method wich is called in the plays() method
 * It has a field to help in the implementation of the doAction()
 * @author Majki
 */
public class Strategic extends Player {

    private int turnsToBuy = 0;
    //------------------------------------------
    
    //konstruktor:
    public Strategic(String name, ArrayList<Field> board) {
        super(PlayerType.STRATEGIC, name, board);
    }
    //------------------------------------------
    
    /**
     * the strategic player gives up on every second option to buy things
     * otherwise pays or gains money
     */
    @Override
    public void doAction() {
        switch (standingOn.getType()) {
            case PROPERTY:
                //hazat epit ha az ove ------------
                if (standingOn.getOwner() == this && founds >= 4000) {
                    if (turnsToBuy == 0) {
                        buyHouse();
                        this.turnsToBuy = 1;
                    } else {
                        turnsToBuy--;
                    }
                }
                //megveszi ------------------------
                if (!(standingOn.isOwned()) && founds >= 1000) {
                    if (turnsToBuy == 0) {
                        buyProperty();
                        this.turnsToBuy = 1;
                    } else {
                        turnsToBuy--;
                    }
                }
                
                //fizet ha mase -------------------
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
