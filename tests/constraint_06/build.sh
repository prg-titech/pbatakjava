#/bin/sh

rm -rf tests/constraint_06/class
mkdir tests/constraint_06/class
java -jar pbatakjava.jar tests/constraint_06/1/*.java tests/constraint_06/2/*.java tests/constraint_06/*.java -d tests/constraint_06/class
