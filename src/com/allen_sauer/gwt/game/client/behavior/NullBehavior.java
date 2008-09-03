/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class NullBehavior extends Behavior {
  public NullBehavior(Sprite sprite) {
    super(sprite);
  }

  @Override
  public void doFirstFrame() {
    // Purposely do not call super.doFirstFrame();
  }

  @Override
  public boolean doFrame(double millis) {
    // Purposely do not call super.doFrame();
    return true;
  }

  @Override
  public void doLastFrame() {
    // Purposely do not call super.doLastFrame();
  }
}
