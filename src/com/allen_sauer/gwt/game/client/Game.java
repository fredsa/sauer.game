package com.allen_sauer.gwt.game.client;

import com.allen_sauer.gwt.game.client.engine.FrameListener;

public interface Game extends FrameListener {
  void clientResized(int clientWidth, int clientHeight);

  void doFrame();

  void init();
}
