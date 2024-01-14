#/bin/sh

rm -rf tests/method_06/class
mkdir tests/method_06/class
java -jar pbatakjava.jar tests/method_06/1/*.java tests/method_06/2/*.java -d tests/method_06/class
