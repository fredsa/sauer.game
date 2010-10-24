/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.games.client;

import com.allen_sauer.gwt.game.client.ui.util.DOMUtil;
import com.allen_sauer.gwt.game.hornetblast.client.HornetGame;
import com.allen_sauer.gwt.game.space.client.SpaceGame;
import com.allen_sauer.gwt.log.client.Log;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

public class Games implements EntryPoint {
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

    HornetGame hornetGame = new HornetGame();
    DOMUtil.getRootPanel("hornetblast").add(hornetGame);

    SpaceGame spaceGame = new SpaceGame();
    DOMUtil.getRootPanel("space").add(spaceGame);

    hornetGame.setFocus(true);
  }
}