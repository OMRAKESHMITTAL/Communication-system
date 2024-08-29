//Responsibilities:
//•	As Player2, I connect to Player1's server to participate in the chat. My job is to respond to each message that Player1 sends to me.
//•	I receive messages from Player1, display them, and then send my own response. This back-and-forth continues until we've exchanged 10 messages in total.
//•	Like Player1, I am responsible for closing the connection when our chat is complete, signalling the end of our conversation.


        package com.example;
import java.io.*;
import java.net.*;

public class Player2 {
    public static void main(String[] args) {
        String temp = "";
        try {
            // Connect to Player1 on localhost, port 5000
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to Player1.");

            Player player = new Player(socket);

            // Respond to messages from Player1
            while (player.getMessageCount() < 10) {
                if(player.getMessageCount()==0) {
                    String receivedMessage = player.receiveMessage();
                    System.out.println("Player2 received: " + receivedMessage);
                }else{
                    String receivedMessage = player.receiveMessage();
                    System.out.println("Your last message "+ temp+"| Player2 received: " + receivedMessage);
                }
                System.out.print("Player2 enter your message: ");
                BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
                String response = keyboardInput.readLine();
                temp = response;
                player.sendMessage(response);
            }

            // Close the connection after 10 messages
            player.closeConnection();
            System.out.println("Player2 has terminated the chat.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
