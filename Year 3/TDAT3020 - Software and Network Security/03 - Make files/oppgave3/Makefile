CC=gcc
CFLAGS=-O2
DEPS=fargeskrift.h

.PHONY: all clean install

.SUFFIXES:


all: fargedemo fargetest noe_fargerikt

%.o: %.c $(DEPS)
	$(CC) -c $(CFLAGS) $< -o $@ 

fargedemo: fargedemo.o fargeskrift.o
	$(CC) -o fargedemo fargedemo.o fargeskrift.o

fargetest: fargetest.o fargeskrift.o
	$(CC) -o fargetest fargetest.o fargeskrift.o

noe_fargerikt: noe_fargerikt.o fargeskrift.o
	$(CC) -o noe_fargerikt noe_fargerikt.o fargeskrift.o

clean:
	rm -f *.o fargedemo fargetest noe_fargerikt

install: fargedemo fargetest noe_fargerikt
	cp fargedemo fargetest noe_fargerikt /usr/local/bin/


