/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

public interface Game {
  void clientResized(int clientWidth, int clientHeight);

  void doFrame();

  int getPlayfieldHeight();

  int getPlayfieldWidth();

  void init(Engine engine);
}
