#/bin/sh

rm -rf tests/constraint_05/class
mkdir tests/constraint_05/class
java -jar pbatakjava.jar tests/constraint_05/1/*.java tests/constraint_05/2/*.java tests/constraint_05/*.java
