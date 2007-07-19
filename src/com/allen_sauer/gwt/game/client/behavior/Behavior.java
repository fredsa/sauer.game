package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.engine.FrameListener;

public interface Behavior extends FrameListener {
  void doFrame();

  void init();
}
