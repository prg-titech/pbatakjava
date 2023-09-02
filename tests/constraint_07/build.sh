#/bin/sh

rm -rf tests/constraint_07/class
mkdir tests/constraint_07/class
java -jar pbatakjava.jar tests/constraint_07/1/*.java tests/constraint_07/2/*.java tests/constraint_07/*.java -d tests/constraint_07/class