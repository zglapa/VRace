#!/bin/bash

JAVAFX="javafx-sdk-15.0.1/lib/"

java --module-path $JAVAFX --add-modules=javafx.controls --add-modules=javafx.media -cp out controller.Main
