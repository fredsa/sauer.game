package com.allen_sauer.gwt.game.client.engine;

public interface FrameListener {
  void deinitialize();

  void doFrame();

  void initialize();
}
