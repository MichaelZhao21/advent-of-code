JAVA = java
JAVAC = javac
SOURCES = $(wildcard *.java)
CLASSES = $(SOURCES:.java=.class)
DAY = 0

%.class: %.java
	$(JAVAC) $<

default: run

run: all
	$(JAVA) Day$(DAY)

all: $(CLASSES)

clean:
	rm -f *.class *~ *.bak
