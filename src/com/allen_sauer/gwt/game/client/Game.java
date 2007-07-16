package com.allen_sauer.gwt.game.client;

public interface Game {
  void clientResized(int clientWidth, int clientHeight);

  void doFrame();

  void init();
}
