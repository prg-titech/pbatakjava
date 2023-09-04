#/bin/sh

rm -rf tests/error_06/class
mkdir tests/error_06/class
java -jar pbatakjava.jar tests/error_06/1/*.java tests/error_06/2/*.java tests/error_06/*.java -d tests/error_06/class
