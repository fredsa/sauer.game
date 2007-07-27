package com.allen_sauer.gwt.game.client.sprite;

import com.allen_sauer.gwt.game.client.engine.Engine;

import java.util.ArrayList;
import java.util.Iterator;

public class SpritePool {
  private int maxSprites;
  private ArrayList sprites;
  private int visibleSprites = 0;

  public SpritePool(SpriteFactory spriteFactory, int maxSprites) {
    this.maxSprites = maxSprites;
    sprites = new ArrayList(maxSprites);
    for (int i = 0; i < maxSprites; i++) {
      Sprite sprite = spriteFactory.create();
      sprite.setSpritePool(this);
      sprite.setPoolIndex(i);
      sprites.add(sprite);
    }
  }

  public Sprite create() {
    assert visibleSprites < maxSprites;
    Sprite sprite = (Sprite) sprites.get(visibleSprites++);
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

  public Iterator iterator() {
    return new Iterator() {
      private int i = 0;

      public boolean hasNext() {
        return i < visibleSprites;
      }

      public Object next() {
        return sprites.get(i++);
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  public String toString() {
    return "SpritePool(" + visibleSprites + " of " + maxSprites + ")";
  }

  private void swap(int i, int j) {
    assert i != j;
    Sprite spriteI = (Sprite) sprites.get(i);
    Sprite spriteJ = (Sprite) sprites.get(j);
    sprites.set(i, spriteJ);
    sprites.set(j, spriteI);
    spriteI.setPoolIndex(j);
    spriteJ.setPoolIndex(i);
  }
}
