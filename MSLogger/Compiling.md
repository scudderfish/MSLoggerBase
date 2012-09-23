Building MSLogger
=================
MSLogger consists of two projects, this one and [Normaliser](https://github.com/scudderfish/Normaliser). You need both to generate the application.

Checking out the code
---------------------
Check out both projects so that they have the same base directory eg.

	somedir/MSLoggerBase
	somedir/Normaliser
	
	cd Normaliser
	ant

You should see output that ends similar to this

	compile_firmwares:
    	[javac] Compiling 55 source files to /Users/dgs/dev/Normaliser/gen_bin
		
		firmware:
      	[jar] Building jar: /Users/dgs/dev/MSLoggerBase/libs/firmwares.jar
		
		BUILD SUCCESSFUL
		Total time: 8 seconds
		
You can now build MSLoggerBase either through Eclipse or ant.

Philosophy
----------
Mobile phones, whilst powerful, still lag behind laptops and desktops in CPU grunt.  Parsing INI files and evaluating the expressions
during runtime is expensive.  This approach precompiles the INI into executable Java code, which is then optimised by the JIT
compiler on the device resulting in the fastest possible execution time.  I expect most end users to not change the configuration
of their INI file and would be happy with the defaults we ship.  If you do want to change an INI file, just modify the
one in Normaliser and rebuild the application.