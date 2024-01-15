#/bin/sh

# check ranges for method calls

rm -rf tests/error_08/class
mkdir tests/error_08/class
java -jar pbatakjava.jar tests/error_08/1/*.java tests/error_08/2/*.java tests/error_08/3/*.java tests/error_08/*.java -d tests/error_08/class
