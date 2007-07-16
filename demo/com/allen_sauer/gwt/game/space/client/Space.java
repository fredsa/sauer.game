package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.core.client.EntryPoint;

import com.allen_sauer.gwt.game.client.Engine;

public class Space implements EntryPoint {

  public void onModuleLoad() {
    SpaceGame game = new SpaceGame();
    Engine.init(game);
  }
}
