//Responsibilities
//•	As the Player class, I act as the communication handler for both Player1 and Player2. My main task is to manage the socket connection and facilitate the sending and receiving of messages.
//•	I maintain a count of how many messages have been exchanged. For each message I receive, I append the message count before passing it back.
//•	I also handle the clean-up process by closing the socket connection when the chat ends, ensuring no further communication occurs.

package com.example;

import java.io.*;
import java.net.*;

public class Player {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private int messageCount;

    public Player(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.messageCount = 0;
    }

    /**
     * Sends a message to the other player.
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        messageCount++;
        output.println(message);
    }

    /**
     * Receives a message from the other player and appends the message count.
     * @return The received message with the message count appended.
     * @throws IOException If an input/output error occurs.
     */
    public String receiveMessage() throws IOException {
        String message = input.readLine();
        return message + " (msg#" + messageCount + ")";
    }

    /**
     * Closes the connection with the other player.
     * @throws IOException If an input/output error occurs.
     */
    public void closeConnection() throws IOException {
        socket.close();
    }

    /**
     * Returns the current message count.
     * @return The message count.
     */
    public int getMessageCount() {
        return messageCount;
    }
}
