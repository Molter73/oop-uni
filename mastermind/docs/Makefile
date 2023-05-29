all: latex javadoc

javadoc:
	javadoc -d build/javadoc \
		-sourcepath ../src/main/java/ \
		-subpackages mastermind

latex: mastermind.tex uml-diagrams output-dir
	tectonic -X compile \
		--synctex \
		--keep-logs \
		--keep-intermediates \
		--outdir build/ \
		mastermind.tex

output-dir:
	@mkdir -p build/

uml-diagrams: uml.md
	plantuml -tlatex:nopreamble uml.md -o build

clean:
	rm -rf build/
