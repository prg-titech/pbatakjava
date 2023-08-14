#/bin/sh

rm -rf tests/constraint_04/class
mkdir tests/constraint_04/class
java -jar pbatakjava.jar tests/constraint_04/1/*.java tests/constraint_04/2/*.java tests/constraint_04/*.java -d tests/constraint_04/class
