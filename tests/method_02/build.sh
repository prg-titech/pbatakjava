#/bin/sh

rm -rf tests/method_02/class
mkdir tests/method_02/class
java -jar pbatakjava.jar tests/method_02/1/*.java tests/method_02/2/*.java tests/method_02/*.java -d tests/method_02/class
