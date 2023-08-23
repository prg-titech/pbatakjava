#/bin/sh

rm -rf tests/error_03/class
mkdir tests/error_03/class
java -jar pbatakjava.jar tests/error_03/1/*.java tests/error_03/2/*.java tests/error_03/*.java -d tests/error_03/class
