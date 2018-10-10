#!/bin/sh

##############################################################################
##
##  Create jar file of the application.
##  Which you can find on ${projectDir}/build/libs/movietracking-${version}.jar
##
##############################################################################

./gradlew clean build bootJar
