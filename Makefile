all:
	pi4j --compile HC_SR04.java

run:
	sudo pi4j --run HC_SR04 

clean:
	rm *.class
