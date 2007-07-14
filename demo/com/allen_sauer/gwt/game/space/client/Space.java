/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.core.client.EntryPoint;

import com.allen_sauer.gwt.game.client.Engine;

public class Space implements EntryPoint {

  public void onModuleLoad() {
    Engine engine = new Engine();
    SpaceGame game = new SpaceGame();
    engine.init(game);
  }
}
