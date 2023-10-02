#/bin/sh

rm -rf tests/hash_03/class
mkdir tests/hash_03/class
java -jar pbatakjava.jar tests/hash_03/1/*.java tests/hash_03/2/*.java tests/hash_03/*.java -d tests/hash_03/class
