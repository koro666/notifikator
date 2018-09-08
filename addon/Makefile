.PHONY: build rebuild clean

build: script.notifikator.zip

rebuild: clean build

clean:
	rm -f *.zip

script.notifikator.zip: script.notifikator/*
	zip -9 $@ $^
