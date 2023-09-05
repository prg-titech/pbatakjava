#/bin/sh

rm -rf tests/subtype_01/class
mkdir tests/subtype_01/class
java -jar pbatakjava.jar tests/subtype_01/1/*.java tests/subtype_01/2/*.java tests/subtype_01/*.java -d tests/subtype_01/class
