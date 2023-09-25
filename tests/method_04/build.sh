#/bin/sh

rm -rf tests/method_04/class
mkdir tests/method_04/class
java -jar pbatakjava.jar tests/method_04/1/*.java tests/method_04/2/*.java -d tests/method_04/class
