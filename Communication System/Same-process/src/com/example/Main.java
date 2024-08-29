//Responsibilities:
//•	As the Main class, I am responsible for orchestrating the start of the chat interaction between the two players. I create two Player instances, player1 and player2, and set them as opponents of each other.
//•	I ensure that the chat starts by invoking the start () method on both players, kicking off their conversation in separate threads. This sets the stage for the message exchange process to begin.


        package com.example;
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player1", true);
        Player player2 = new Player("Player2", false);

        player1.setOpponent(player2);
        player2.setOpponent(player1);

        player1.start();
        player2.start();
    }
}
