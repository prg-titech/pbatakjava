#/bin/sh

rm -rf tests/error_02/class
mkdir tests/error_02/class
java -jar pbatakjava.jar tests/error_02/1/*.java tests/error_02/2/*.java tests/error_02/*.java -d tests/error_02/class
