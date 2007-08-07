package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class NullBehavior extends Behavior {
  public NullBehavior(Sprite sprite) {
    super(sprite);
  }

  public void doFirstFrame() {
    // Purposely do not call super.doFirstFrame();
  }

  public boolean doFrame() {
    // Purposely do not call super.doFrame();
    return true;
  }

  public void doLastFrame() {
    // Purposely do not call super.doLastFrame();
  }
}
