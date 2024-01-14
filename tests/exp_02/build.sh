#/bin/sh

rm -rf tests/exp_02/class
mkdir tests/exp_02/class
java -jar pbatakjava.jar tests/exp_02/1/*.java tests/exp_02/2/*.java tests/exp_02/*.java -d tests/exp_02/class