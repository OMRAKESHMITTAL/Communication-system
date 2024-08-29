#!/bin/bash

echo "Choose an option:"
echo "1. Run Multi-Process Player"
echo "2. Run Single-Process Player"
read -p "Enter your choice (1 or 2): " choice

if [ "$choice" == "1" ]; then
    cd Multi-Process/src
    # Compile the Java classes
    javac com/example/Player.java
    javac com/example/Player1.java
    javac com/example/Player2.java

    # Start Player1 in the current terminal
    gnome-terminal -- bash -c "java com.example.Player1; exec bash"

    # Open a new terminal and run Player2
    gnome-terminal -- bash -c "java com.example.Player2; exec bash"

elif [ "$choice" == "2" ]; then
    cd Same-process/src
    # Compile the Java files
    javac com/example/Main.java 
    javac com/example/Player.java

    # Run the Main class
    gnome-terminal -- bash -c "java com.example.Main; exec bash"

else
    echo "Invalid choice. Please run the script again and select either 1 or 2."
fi
