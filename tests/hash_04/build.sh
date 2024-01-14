#/bin/sh

rm -rf tests/hash_04/class
mkdir tests/hash_04/class
java -jar pbatakjava.jar tests/hash_04/1/*.java tests/hash_04/2/*.java tests/hash_04/*.java -d tests/hash_04/class
