#/bin/sh

rm -rf tests/inherit_01/class
mkdir tests/inherit_01/class
java -jar pbatakjava.jar tests/inherit_01/1/*.java tests/inherit_01/2/*.java tests/inherit_01/*.java -d tests/inherit_01/class
