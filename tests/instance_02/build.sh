#/bin/sh

rm -rf tests/instance_02/class
mkdir tests/instance_02/class
java -jar pbatakjava.jar tests/instance_02/1/*.java tests/instance_02/2/*.java tests/instance_02/*.java -d tests/instance_02/class
