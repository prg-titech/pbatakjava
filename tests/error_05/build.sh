#/bin/sh

rm -rf tests/error_05/class
mkdir tests/error_05/class
java -jar pbatakjava.jar tests/error_05/1/*.java tests/error_05/2/*.java tests/error_05/*.java -d tests/error_05/class