#/bin/sh

rm -rf tests/instance_03/class
mkdir tests/instance_03/class
java -jar pbatakjava.jar tests/instance_03/1/*.java tests/instance_03/2/*.java tests/instance_03/*.java -d tests/instance_03/class
