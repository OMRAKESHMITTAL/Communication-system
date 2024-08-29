//Responsibilities:
//•	As the Player class, my primary responsibility is to manage the sending and receiving of messages between myself and my opponent. I handle this through a threaded execution, ensuring smooth communication.
//•	I am in charge of determining whether it's my turn to send a message or wait for my opponent's message. If it's my turn, I prompt the user to enter a message and then send it; if not, I wait until it's my turn.
//•	I keep track of the total number of messages exchanged using a shared messageCounter variable to ensure that we stop after 10 messages each.
//•	I store the last message received or sent in the temp variable to display it during communication.
//•	When it's my turn to send a message, I call allowSending () on my opponent, notifying them that they can now send their message. This synchronization helps in maintaining the order and flow of communication between both players.

package com.example;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Thread {
    String temp = "";
    private static final int MESSAGE_LIMIT = 20;
    private static final AtomicInteger messageCounter = new AtomicInteger(0);

    private final String name;
    private Player opponent;
    private final Scanner scanner = new Scanner(System.in);
    private final Object lock = new Object();
    private boolean canSendMessage;

    public Player(String name, boolean canSendMessage) {
        this.name = name;
        this.canSendMessage = canSendMessage;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public void run() {
        try {
            while (messageCounter.get() < MESSAGE_LIMIT) {
                synchronized (lock) {
                    if (canSendMessage) {
                        System.out.print(name + ", enter your message: ");
                        String userMessage = scanner.nextLine();
                        sendMessage(userMessage);
                        canSendMessage = false;
                        opponent.allowSending();
                    } else {
                        lock.wait(); // Wait until it's this player's turn to send a message
                    }
                }
            }
            System.out.println(name + " has completed sending messages.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        synchronized (lock) {
            if (messageCounter.get() < MESSAGE_LIMIT) {
                System.out.println(name + " sending message: " + temp + " " + message);
                System.out.println();
                opponent.receiveMessage(message);
                messageCounter.incrementAndGet();
                temp = message;
            }
        }
    }

    private void receiveMessage(String message) {
        synchronized (lock) {
            if (messageCounter.get() < MESSAGE_LIMIT) {
                System.out.println(name + " received message: " + temp + " " + message);
                temp = message;
            }
        }
    }

    public void allowSending() {
        synchronized (lock) {
            canSendMessage = true;
            lock.notify(); // Wake up the thread waiting to send a message
        }
    }
}
