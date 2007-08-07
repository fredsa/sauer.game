package com.allen_sauer.gwt.game.client.engine;

public interface FrameListener {
  void doFirstFrame();

  boolean doFrame();

  void doLastFrame();
}
