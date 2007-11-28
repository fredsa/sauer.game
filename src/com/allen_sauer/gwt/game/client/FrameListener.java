package com.allen_sauer.gwt.game.client;

public interface FrameListener {
  void doFirstFrame();

  boolean doFrame();

  void doLastFrame();
}