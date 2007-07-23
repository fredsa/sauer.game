package com.allen_sauer.gwt.game.client.sprite;

import com.allen_sauer.gwt.game.client.engine.Engine;

public class SpritePool {
  private int maxSprites;
  private Sprite[] sprites;
  private int visibleSprites = 0;

  public SpritePool(SpriteFactory spriteFactory, int maxSprites) {
    this.maxSprites = maxSprites;
    sprites = new Sprite[maxSprites];
    for (int i = 0; i < sprites.length; i++) {
      Sprite sprite = spriteFactory.create();
      sprite.setSpritePool(this);
      sprite.setPoolIndex(i);
      sprites[i] = sprite;
    }
  }

  public Sprite create() {
    assert visibleSprites < maxSprites;
    Sprite sprite = sprites[visibleSprites++];
    Engine.addFrameListener(sprite);
    return sprite;
  }

  public void destroy(Sprite sprite) {
    assert sprite.getPoolIndex() < visibleSprites;
    assert sprite.getSpritePool() == this;
    if (sprite.getPoolIndex() < visibleSprites - 1) {
      swap(sprite.getPoolIndex(), visibleSprites - 1);
    }
    visibleSprites--;
    Engine.removeFrameListener(sprite);
  }

  public boolean exhausted() {
    return visibleSprites == maxSprites;
  }

  public String toString() {
    return "SpritePool(" + visibleSprites + " of " + maxSprites + ")";
  }

  private void swap(int i, int j) {
    assert i != j;
    Sprite temp = sprites[i];
    sprites[i] = sprites[j];
    sprites[j] = temp;
    sprites[i].setPoolIndex(i);
    sprites[j].setPoolIndex(j);
  }
}
