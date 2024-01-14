#/bin/sh

# check if the copied methods are built with the correct return statements

rm -rf tests/error_07/class
mkdir tests/error_07/class
java -jar pbatakjava.jar tests/error_07/1/*.java tests/error_07/2/*.java tests/error_07/3/*.java tests/error_07/*.java   -d tests/error_07/class
