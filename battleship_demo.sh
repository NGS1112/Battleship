#!/bin/bash

javac -d . src/battleship/*.java

echo "Enter game size [ (s)mall, (m)edium, (l)arge ] or custom file name:"
read input
echo

case $input in

	s | small)
		datafile="4-4-0-0-2.txt"
		;;
	m | medium)
		datafile="6-6-1-2-2.txt"
		;;
	l | large)
		datafile="8-8-2-4-4.txt"
		;;
	*)
		datafile=$input
		;;
esac

java battleship.Battleship $datafile