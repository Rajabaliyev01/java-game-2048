#!/bin/bash
cd "$(dirname "$0")"
javac -d bin src/game2048/model/*.java src/game2048/ui/*.java src/game2048/Main.java
java -cp bin game2048.Main
