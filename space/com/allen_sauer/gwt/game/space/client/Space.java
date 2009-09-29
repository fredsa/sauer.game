/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;
import com.allen_sauer.gwt.log.client.Log;

public class Space implements EntryPoint {
  public void onModuleLoad() {
    // set uncaught exception handler
    Log.setUncaughtExceptionHandler();

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

    SpaceGame game = new SpaceGame();
    DOMUtil.getRootPanel("game").add(game);

    game.setFocus(true);
  }
}