all: deliverable.tex uml-diagrams
	@mkdir -p build/
	tectonic -X compile \
		--synctex \
		--keep-logs \
		--keep-intermediates \
		--outdir build/ \
		deliverable.tex

uml-diagrams:
	make -C uml

clean:
	make -C uml clean
	rm -rf build/
