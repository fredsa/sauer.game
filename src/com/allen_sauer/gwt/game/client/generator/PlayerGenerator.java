package com.allen_sauer.gwt.game.client.generator;

import com.allen_sauer.gwt.game.client.sprite.SpritePool;

// TODO drop class
public class PlayerGenerator implements Generator {
  private final SpritePool spritePool;

  public PlayerGenerator(SpritePool spritePool) {
    this.spritePool = spritePool;
    spritePool.getFrameListenerCollection().addFrameListener(this);
  }

  public void doFirstFrame() {
  }

  public boolean doFrame() {
    if (!spritePool.exhausted()) {
      spritePool.create();
    }
    return true;
  }

  public void doLastFrame() {
  }
}
