package com.allen_sauer.gwt.game.client.sprite;

import java.util.ArrayList;
import java.util.Iterator;

public class SpritePool {
  private static ArrayList sprites = new ArrayList();
  private int maxSize;
  private final SpriteFactory spriteFactory;

  public SpritePool(SpriteFactory spriteFactory, int maxSize) {
    this.spriteFactory = spriteFactory;
    this.maxSize = maxSize;
  }

  public Sprite create() {
    Sprite sprite = spriteFactory.create();
    sprite.setSpritePool(this);
    sprite.init();
    sprites.add(sprite);
    return sprite;
  }

  public void destroy(Sprite sprite) {
    if (!sprites.remove(sprite)) {
      throw new IllegalStateException("sprite not in pool");
    }
    sprite.deinit();
  }

  public void doFrame() {
    for (Iterator iterator = sprites.iterator(); iterator.hasNext();) {
      Sprite sprite = (Sprite) iterator.next();
      sprite.doFrame();
    }
  }

  public int getMaxSize() {
    return maxSize;
  }

  public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
  }

  public int size() {
    return sprites.size();
  }

  public String toString() {
    return "SpritePool(" + size() + " of " + getMaxSize() + ")";
  }
}
