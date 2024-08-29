//Responsibilities:
//•	As Player1, I am responsible for initiating the chat by setting up a server socket and waiting for Player2 to connect to me.
//•	I handle the process of sending and receiving messages between Player2 and me. I ensure that I send a message first, then wait for a response, repeating this process until I've sent and received 10 messages.
//• Once the conversation is complete, I close the connection to signal that our chat has ended

package com.example;

import java.io.*;
import java.net.*;

public class Player1 {
    public static void main(String[] args) {
        String temp = "";
        try {
            // Create a server socket to listen on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Player1 is waiting for Player2 to connect...");

            // Accept the connection from Player2
            Socket socket = serverSocket.accept();
            System.out.println("Player2 connected.");

            Player player = new Player(socket);
            BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));

            // Initiator sends and receives messages
            while (player.getMessageCount() < 10) {
                System.out.print("Player1 enter your message: ");
                String message = keyboardInput.readLine();
                temp = message;
                player.sendMessage(message);

                String receivedMessage = player.receiveMessage();
                System.out.println("Your last message "+ temp+" |  Player1 received: "+receivedMessage);
            }

            // Close the connection after 10 messages
            player.closeConnection();
            serverSocket.close();
            System.out.println("Player1 has terminated the chat.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
