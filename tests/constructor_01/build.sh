#/bin/sh

rm -rf tests/constructor_01/class
mkdir tests/constructor_01/class
java -jar pbatakjava.jar tests/constructor_01/1/*.java tests/constructor_01/2/* -d tests/constructor_01/class
