#/bin/sh

rm -rf tests/method_01/class
mkdir tests/method_01/class
java -jar pbatakjava.jar tests/method_01/1/*.java tests/method_01/2/*.java tests/method_01/*.java -d tests/method_01/class
