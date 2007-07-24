package com.allen_sauer.gwt.game.client.generator;

import com.allen_sauer.gwt.game.client.Keyboard;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class KeyboardBulletGenerator implements Generator {
  private static final int FRAMES_BETWEEN_BULLETS = 7;
  private int frameCounter;
  private final SpritePool spritePool;

  public KeyboardBulletGenerator(SpritePool spritePool) {
    this.spritePool = spritePool;
    Engine.addFrameListener(this);
  }

  public void deinitialize() {
  }

  public void doFrame() {
    if (++frameCounter > FRAMES_BETWEEN_BULLETS && !spritePool.exhausted() && Keyboard.isKeyDown(' ')) {
      spritePool.create();
      frameCounter = 0;
    }
  }

  public void initialize() {
    frameCounter = 0;
  }
}
