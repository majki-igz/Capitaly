/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.beadando1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Reads the file with BuildGame then cycles trough the number of players and calls plays() on each
 * upon the second player losing the while stops and print out the second element of the playersWhoLost arraylist
 * @author Majki
 */
public class Main {

    public static void main(String[] args) {
        BuildGame game = new BuildGame();
        try {
            if (args.length > 1) {
                game.readFile(args[1]);
            } else {
                game.readFile("input.txt");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }

        ArrayList<Player> playersWhoLost = new ArrayList<>();
        int round = 1;
        while (playersWhoLost.size() < 2){
            System.out.println("\nRound " + round);
            for (Player player : game.getPlayers()){
                player.plays(game.getBoard());
                if (!(player.isActive()) && !(playersWhoLost.contains(player))){
                    playersWhoLost.add(player);
                    System.out.println("\t " + player.getName() + " just lost.");
                } else if (player.isActive()){
                    System.out.println(player);
                }
            }
            round++;
        }
        System.out.println("\nResult: \nThe second player who lost is " + playersWhoLost.get(1).getName());
        
        /*
        game.getPlayers().get(0).plays(game.getBoard());
        game.getPlayers().get(0).plays(game.getBoard());
        game.getPlayers().get(0).plays(game.getBoard());
        game.getPlayers().get(0).plays(game.getBoard());
        System.out.println(game.getPlayers().get(0).getStandingOn());
        */
    }
}
