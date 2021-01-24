#!/bin/bash


JAVAFX="javafx-sdk-15.0.1/lib/"

javac  --module-path $JAVAFX --add-modules=javafx.controls  --add-modules=javafx.media -d out $(find src -name "*.java")
cp -r src/main/resources/* out/
