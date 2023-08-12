#/bin/sh

rm -rf tests/constraint_03/class
mkdir tests/constraint_03/class
java -jar pbatakjava.jar tests/constraint_03/1/*.java tests/constraint_03/2/*.java tests/constraint_03/*.java -d tests/constraint_03/class
