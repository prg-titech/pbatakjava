#/bin/sh

rm -rf tests/error_04/class
mkdir tests/error_04/class
java -jar pbatakjava.jar tests/error_04/1/*.java tests/error_04/2/*.java tests/error_04/*.java -d tests/error_04/class
