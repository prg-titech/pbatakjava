#/bin/sh

# check if the copied methods are built with the correct return statements

rm -rf tests/method_05/class
mkdir tests/method_05/class
java -jar pbatakjava.jar tests/method_05/1/*.java tests/method_05/2/*.java -d tests/method_05/class
