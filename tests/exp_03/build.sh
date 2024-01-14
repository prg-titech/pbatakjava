#/bin/sh

rm -rf tests/exp_03/class
mkdir tests/exp_03/class
java -jar pbatakjava.jar tests/exp_03/1/*.java tests/exp_03/2/*.java tests/exp_03/*.java -d tests/exp_03/class