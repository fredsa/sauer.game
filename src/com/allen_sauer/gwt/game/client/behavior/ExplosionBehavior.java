package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class ExplosionBehavior implements Behavior {
  private int frameCount;
  private final Sprite sprite;

  public ExplosionBehavior(Sprite sprite) {
    this.sprite = sprite;
  }

  public void doFirstFrame() {
    frameCount = 0;
    //    sprite.setXY(x, y);
  }

  public FrameListenerRetention doFrame() {
    if (++frameCount == sprite.getVirtualFrameCount()) {
      return LISTENER_REMOVE;
    }
    return LISTENER_CONTINUE;
  }

  public void doLastFrame() {
  }
}
