/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

public interface Game {
  void doFrame();

  int getPlayfieldHeight();

  int getPlayfieldWidth();

  void handleClientResized(int clientWidth, int clientHeight);

  void init(Engine engine);
}
