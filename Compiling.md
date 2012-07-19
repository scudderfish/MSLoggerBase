Building MSLogger
=================
MSLogger consists of two projects, this one and [Normaliser](https://github.com/scudderfish/Normaliser). You both to generate the application.

Checking out the code
---------------------
Check out both projects so that they have the same base directory eg.

<somedir>/MSLoggerBase
<somedir>/Normaliser
	
	cd Normaliser
	ant

You should see a bunch of output that ends similar to this

	compile_firmwares:
    	[javac] Compiling 55 source files to /Users/dgs/dev/Normaliser/gen_bin
		
		firmware:
      	[jar] Building jar: /Users/dgs/dev/MSLoggerBase/libs/firmwares.jar
		
		BUILD SUCCESSFUL
		Total time: 8 seconds
		
You can now build MSLoggerBase either through Eclipse or ant.
