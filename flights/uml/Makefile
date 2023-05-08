all: flights.tex uml-diagrams
	@mkdir -p build/
	tectonic -X compile \
		--synctex \
		--keep-logs \
		--keep-intermediates \
		--outdir build/ \
		flights.tex

uml-diagrams: uml.md
	plantuml -tlatex:nopreamble uml.md -o build

clean:
	rm -rf build/
