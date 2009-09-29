/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.sprite;

import com.allen_sauer.gwt.game.client.FrameListenerCollection;
import com.allen_sauer.gwt.game.client.Game;

import java.util.ArrayList;
import java.util.Iterator;

public class SpritePool {
  private FrameListenerCollection frameListenerCollection = new FrameListenerCollection();
  private boolean initialized = false;
  private int maxSprites;
  private ArrayList<Sprite> sprites;
  private int visibleSprites = 0;

  public SpritePool(Game game) {
    game.addSpriteFrameListener(frameListenerCollection);
  }

  public Sprite create() {
    assert initialized;
    assert visibleSprites < maxSprites;

    Sprite sprite = sprites.get(visibleSprites++);
    frameListenerCollection.addFrameListener(sprite);
    return sprite;
  }

  public void destroy(Sprite sprite) {
    assert initialized;
    assert sprite.getPoolIndex() < visibleSprites;
    assert sprite.getSpritePool() == this;

    if (sprite.getPoolIndex() < visibleSprites - 1) {
      swap(sprite.getPoolIndex(), visibleSprites - 1);
    }
    visibleSprites--;
    // frameListenerCollection.removeFrameListener(sprite);
  }

  public boolean exhausted() {
    assert initialized;
    return visibleSprites == maxSprites;
  }

  public FrameListenerCollection getFrameListenerCollection() {
    return frameListenerCollection;
  }

  public Iterator<Sprite> iterator() {
    assert initialized;
    return new Iterator<Sprite>() {
      private int i = 0;

      public boolean hasNext() {
        return i < visibleSprites;
      }

      public Sprite next() {
        return sprites.get(i++);
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public String toString() {
    assert initialized;
    return "SpritePool(" + visibleSprites + " of " + maxSprites + ")";
  }

  protected void init(SpriteFactory spriteFactory, int maxSprites) {
    this.maxSprites = maxSprites;
    sprites = new ArrayList<Sprite>(maxSprites);
    for (int i = 0; i < maxSprites; i++) {
      Sprite sprite = spriteFactory.create();
      sprite.setSpritePool(this);
      sprite.setPoolIndex(i);
      sprites.add(sprite);
    }
    initialized = true;
  }

  private void swap(int i, int j) {
    assert i != j;
    Sprite spriteI = sprites.get(i);
    Sprite spriteJ = sprites.get(j);
    sprites.set(i, spriteJ);
    sprites.set(j, spriteI);
    spriteI.setPoolIndex(j);
    spriteJ.setPoolIndex(i);
  }
}
