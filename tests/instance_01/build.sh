#/bin/sh

rm -rf tests/instance_01/class
mkdir tests/instance_01/class
java -jar pbatakjava.jar tests/instance_01/1/*.java tests/instance_01/2/*.java tests/instance_01/*.java -d tests/instance_01/class/
