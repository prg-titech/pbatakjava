#/bin/sh

rm -rf tests/exp_01/class
mkdir tests/exp_01/class
java -jar pbatakjava.jar tests/exp_01/1/*.java tests/exp_01/2/*.java tests/exp_01/*.java -d tests/exp_01/class