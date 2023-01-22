/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beadando1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * BuildGame is the base of the code.
 * It initialises the board, and the players from an input file.
 * @author Majki
 */
public class BuildGame {

    private final ArrayList<Field> board;
    private final ArrayList<Player> players;
    //------------------------------------------
    
    //konstruktor
    public BuildGame() {
        this.board = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    //------------------------------------------
    
    /**
     * reads the given text file line by line, and constructs the objects in the fileds
     * @param fileName
     * @throws FileNotFoundException
     * @throws InvalidInputException 
     */
    public void readFile(String fileName) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new FileReader(fileName));
        int numberOfFields = sc.nextInt();
        for (int i = 0; i < numberOfFields; i++) {
            Field field;
            switch (FieldType.valueOf(sc.next().toUpperCase())) {
                case PROPERTY:
                    field = new Field(0, FieldType.PROPERTY);
                    break;
                case SERVICE:
                    field = new Field(sc.nextInt(), FieldType.SERVICE);
                    break;
                case LUCK:
                    field = new Field(sc.nextInt(), FieldType.LUCK);
                    break;
                default:
                    throw new InvalidInputException();
            }
            this.board.add(field);
        }
        int numberOfPlayers = sc.nextInt();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player;
            String name = sc.next();
            switch (PlayerType.valueOf(sc.next().toUpperCase())) {
                case GREEDY:
                    player = new Greedy(name, board);
                    break;
                case STRATEGIC:
                    player = new Strategic(name, board);
                    break;
                case CAREFUL:
                    player = new Careful(name, board);
                    break;
                default:
                    throw new InvalidInputException();
            }
            this.players.add(player);
        }
    }
    //------------------------------------------
    
    //getterek
    public ArrayList<Field> getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    //------------------------------------------
    
    //eloadas szerint kotelezo:
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.board);
        hash = 97 * hash + Objects.hashCode(this.players);
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
        final BuildGame other = (BuildGame) obj;
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        return Objects.equals(this.players, other.players);
    }
    
    
}