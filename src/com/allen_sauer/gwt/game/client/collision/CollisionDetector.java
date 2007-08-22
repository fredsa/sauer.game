package com.allen_sauer.gwt.game.client.collision;

import com.google.gwt.user.client.DOM;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.engine.FrameListener;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

import java.util.Iterator;

public abstract class CollisionDetector implements FrameListener {
  private final SpritePool poolA;
  private final SpritePool poolB;

  public CollisionDetector(SpritePool poolA, SpritePool poolB) {
    this.poolA = poolA;
    this.poolB = poolB;
    Engine.addCollisionFrameListener(this);
  }

  public void doFirstFrame() {
    // TODO Replace auto-generated method stub
  }

  public boolean doFrame() {
    for (Iterator iteratorA = poolA.iterator(); iteratorA.hasNext();) {
      Sprite spriteA = (Sprite) iteratorA.next();
      if (Engine.DEBUG) {
        DOM.setStyleAttribute(spriteA.getElement(), "backgroundColor", "");
      }
    }
    for (Iterator iteratorB = poolB.iterator(); iteratorB.hasNext();) {
      Sprite spriteB = (Sprite) iteratorB.next();
      if (Engine.DEBUG) {
        DOM.setStyleAttribute(spriteB.getElement(), "backgroundColor", "");
      }
    }
    for (Iterator iteratorA = poolA.iterator(); iteratorA.hasNext();) {
      Sprite spriteA = (Sprite) iteratorA.next();
      for (Iterator iteratorB = poolB.iterator(); iteratorB.hasNext();) {
        Sprite spriteB = (Sprite) iteratorB.next();
        if (intersect(spriteA, spriteB)) {
          if (Engine.DEBUG) {
            DOM.setStyleAttribute(spriteA.getElement(), "backgroundColor", "green");
          }
          if (Engine.DEBUG) {
            DOM.setStyleAttribute(spriteB.getElement(), "backgroundColor", "green");
          }
          handleCollision(spriteA, spriteB);
        }
      }
    }
    return true;
  }

  public void doLastFrame() {
    // TODO Replace auto-generated method stub
  }

  protected abstract void handleCollision(Sprite spriteA, Sprite spriteB);

  private boolean intersect(Sprite spriteA, Sprite spriteB) {
    int aLeft = spriteA.getX() + spriteA.boundingBoxInfo.offsetLeft;
    int bLeft = spriteB.getX() + spriteB.boundingBoxInfo.offsetLeft;
    int aRight = aLeft + spriteA.boundingBoxInfo.width;
    int bRight = bLeft + spriteB.boundingBoxInfo.width;

    int aTop = spriteA.getY() + spriteA.boundingBoxInfo.offsetTop;
    int bTop = spriteB.getY() + spriteB.boundingBoxInfo.offsetTop;
    int aBottom = aTop + spriteA.boundingBoxInfo.height;
    int bBottom = bTop + spriteB.boundingBoxInfo.height;

    if (aRight < bLeft || aLeft > bRight || aBottom < bTop || aTop > bBottom) {
      return false;
    }
    return true;
  }
}
