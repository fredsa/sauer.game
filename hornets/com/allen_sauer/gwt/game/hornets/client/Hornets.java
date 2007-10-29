package com.allen_sauer.gwt.game.hornets.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;
import com.allen_sauer.gwt.log.client.LogUncaughtExceptionHandler;

public class Hornets implements EntryPoint {
  public void onModuleLoad() {
    // set uncaught exception handler
    GWT.setUncaughtExceptionHandler(new LogUncaughtExceptionHandler());

    // use deferred command to catch initialization exceptions
    DeferredCommand.addCommand(new Command() {
      public void execute() {
        onModuleLoad2();
      }
    });
  }

  public void onModuleLoad2() {
    //    Log.moveTo(0, 110);
    //    Log.setPixelSize(800, 300);

    HornetsGame game1 = new HornetsGame();
    DOMUtil.getRootPanel("game1").add(game1);

    HornetsGame game2 = new HornetsGame();
    DOMUtil.getRootPanel("game2").add(game2);
  }
}