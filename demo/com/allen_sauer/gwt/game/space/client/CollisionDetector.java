package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.DOM;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

import java.util.Iterator;

public class CollisionDetector implements FrameListener {
  private final SpritePool poolA;
  private final SpritePool poolB;

  public CollisionDetector(SpritePool poolA, SpritePool poolB) {
    this.poolA = poolA;
    this.poolB = poolB;
    Engine.addFrameListener(this);
  }

  public void doFirstFrame() {
    // TODO Replace auto-generated method stub
  }

  public void doFrame() {
    for (Iterator iteratorA = poolA.iterator(); iteratorA.hasNext();) {
      Sprite spriteA = (Sprite) iteratorA.next();
      DOM.setStyleAttribute(spriteA.getElement(), "backgroundColor", "");
    }
    for (Iterator iteratorB = poolB.iterator(); iteratorB.hasNext();) {
      Sprite spriteB = (Sprite) iteratorB.next();
      DOM.setStyleAttribute(spriteB.getElement(), "backgroundColor", "");
    }
    for (Iterator iteratorA = poolA.iterator(); iteratorA.hasNext();) {
      Sprite spriteA = (Sprite) iteratorA.next();
      for (Iterator iteratorB = poolB.iterator(); iteratorB.hasNext();) {
        Sprite spriteB = (Sprite) iteratorB.next();
        if (intersect(spriteA, spriteB)) {
          DOM.setStyleAttribute(spriteA.getElement(), "backgroundColor", "green");
          DOM.setStyleAttribute(spriteB.getElement(), "backgroundColor", "green");
        }
      }
    }
  }

  public void doLastFrame() {
    // TODO Replace auto-generated method stub
  }

  private boolean intersect(Sprite spriteA, Sprite spriteB) {
    if (spriteA.getRight() < spriteB.getX() || spriteA.getX() > spriteB.getRight() || spriteA.getBottom() < spriteB.getY()
        || spriteA.getY() > spriteB.getBottom()) {
      return false;
    }
    return true;
  }
}
