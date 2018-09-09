.PHONY: build rebuild touch icons

build: icons

rebuild: touch icons

touch:
	touch *.svg

icons: \
	../client/src/main/res/drawable-ldpi/icon.png \
	../client/src/main/res/drawable-mdpi/icon.png \
	../client/src/main/res/drawable-hdpi/icon.png \
	../client/src/main/res/drawable-xhdpi/icon.png \
	../client/src/main/res/drawable-xxhdpi/icon.png \
	../client/src/main/res/drawable-xxxhdpi/icon.png \
	../client/src/main/res/drawable-ldpi-v11/mask.png \
	../client/src/main/res/drawable-mdpi-v11/mask.png \
	../client/src/main/res/drawable-hdpi-v11/mask.png \
	../client/src/main/res/drawable-xhdpi-v11/mask.png \
	../client/src/main/res/drawable-xxhdpi-v11/mask.png \
	../client/src/main/res/drawable-xxxhdpi-v11/mask.png \
	../addon/script.notifikator/resources/icon.png

../client/src/main/res/drawable-ldpi/icon.png: icon.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 36x36 -depth 8 $^ $@
	pngout -c6 -k0 -q -force $@

../client/src/main/res/drawable-mdpi/icon.png: icon.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 48x48 -depth 8 $^ $@
	pngout -c6 -k0 -q -force $@

../client/src/main/res/drawable-hdpi/icon.png: icon.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 72x72 -depth 8 $^ $@
	pngout -c6 -k0 -q -force $@

../client/src/main/res/drawable-xhdpi/icon.png: icon.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 96x96 -depth 8 $^ $@
	pngout -c6 -k0 -q -force $@

../client/src/main/res/drawable-xxhdpi/icon.png: icon.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 144x144 -depth 8 $^ $@
	pngout -c6 -k0 -q -force $@

../client/src/main/res/drawable-xxxhdpi/icon.png: icon.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 192x192 -depth 8 $^ $@
	pngout -c6 -k0 -q -force $@

../client/src/main/res/drawable-ldpi-v11/mask.png: mask.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 18x18 -depth 8 $^ $@
	pngout -c4 -k0 -q -force $@

../client/src/main/res/drawable-mdpi-v11/mask.png: mask.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 24x24 -depth 8 $^ $@
	pngout -c4 -k0 -q -force $@

../client/src/main/res/drawable-hdpi-v11/mask.png: mask.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 36x36 -depth 8 $^ $@
	pngout -c4 -k0 -q -force $@

../client/src/main/res/drawable-xhdpi-v11/mask.png: mask.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 48x48 -depth 8 $^ $@
	pngout -c4 -k0 -q -force $@

../client/src/main/res/drawable-xxhdpi-v11/mask.png: mask.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 72x72 -depth 8 $^ $@
	pngout -c4 -k0 -q -force $@

../client/src/main/res/drawable-xxxhdpi-v11/mask.png: mask.svg
	mkdir -p $(dir $@)
	convert -background transparent -resize 96x96 -depth 8 $^ $@
	pngout -c4 -k0 -q -force $@

../addon/script.notifikator/resources/icon.png: kodi.svg
	mkdir -p $(dir $@)
	convert -background black -resize 512x512 -depth 8 $^ $@
	pngout -c6 -k0 -q -force $@
