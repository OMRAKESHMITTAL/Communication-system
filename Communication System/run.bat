echo Choose an option:
echo 1. Run Multi-Process Player
echo 2. Run Single-Process Player
set /p choice=Enter your choice (1 or 2):
if "%choice%"=="1" (
    cd Multi-Process/src
    REM Start Player1 in the current terminal
    @echo off
    REM Compile the Java classes
    javac com/example/Player.java
    javac com/example/Player1.java
    javac com/example/Player2.java

    REM Start Player1 in the current terminal
    start cmd /k "java com.example.Player1"

    REM Open a new terminal and run Player2
    start cmd /k "java com.example.Player2"

) else if "%choice%"=="2" (
    cd Same-process
    REM Open a new terminal and run Player2
    @echo off
    REM Navigate to the src directory
    REM Compile the Java files
    javac -d out src/com/example/Main.java src/com/example/Player.java

    REM Run the Main class
    cd out
    start cmd /k "java com.example.Main"

    REM Pause to keep the command prompt open
    pause


) else (
    echo Invalid choice. Please run the script again and select either 1 or 2.
)