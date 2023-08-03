#/bin/sh

rm -rf tests/constraint_01/class
mkdir tests/constraint_01/class
java -jar pbatakjava.jar tests/constraint_01/1/*.java tests/constraint_01/2/*.java tests/constraint_01/*.java -d tests/constraint_01/class/
