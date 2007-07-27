package com.allen_sauer.gwt.game.client.generator;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.ui.util.Page;

public class KeyboardBulletGenerator implements Generator {
  private static final int FRAMES_BETWEEN_BULLETS = 7;
  private int frameCounter;
  private final SpritePool spritePool;

  public KeyboardBulletGenerator(SpritePool spritePool) {
    this.spritePool = spritePool;
    Engine.addFrameListener(this);
  }

  public void doFirstFrame() {
    frameCounter = 0;
  }

  public void doFrame() {
    if (++frameCounter > FRAMES_BETWEEN_BULLETS && !spritePool.exhausted() && Page.isKeyDown(' ')) {
      spritePool.create();
      frameCounter = 0;
    }
  }

  public void doLastFrame() {
  }
}
