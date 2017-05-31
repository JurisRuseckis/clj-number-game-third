# clj-number-game-third

to start to operate with this project you need to download and install leiningen here - https://leiningen.org/

afterwards navigate to project folder in terminal.
if you want to run project in development mode then run

	lein clean
	lein figwheel

if you want to compile project then run

	lein do clean, cljsbuild once min

and afterwards open this file in a browser 
 
	/project/resources/public/index.html 
	
## Overview

Partially recreated 2048 in clojurescript from

	https://github.com/gabrielecirulli/2048
	


## Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
"# clj-number-game-third" 
