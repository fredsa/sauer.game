/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

public interface FrameListener {
  void doFirstFrame();

  boolean doFrame(double millis);

  void doLastFrame();
}
