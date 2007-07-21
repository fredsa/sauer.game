package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.log.client.LogUncaughtExceptionHandler;

public class Space implements EntryPoint {

  public void onModuleLoad() {
    GWT.setUncaughtExceptionHandler(new LogUncaughtExceptionHandler());
    SpaceGame game = new SpaceGame();
    Engine.init(game);
  }
}
