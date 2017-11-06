all:
	pi4j --compile RangeFinderDriver.java

run:
	sudo pi4j --run RangeFinderDriver

clean:
	rm -rf *.class
