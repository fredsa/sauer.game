@java -cp "%~dp0\src;%~dp0\demo;%GWT_LIBS%/gwt-log/gwt-log.jar;%GWT_LIBS%/gwt-voices/gwt-voices.jar;%GWT_PLATFORM%/gwt-user.jar;%GWT_PLATFORM%/gwt-dev-windows.jar" com.google.gwt.dev.GWTCompiler -out "%~dp0\www" %* com.allen_sauer.gwt.game.hornets.Hornets