/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.collision;

import com.google.gwt.user.client.DOM;

import com.allen_sauer.gwt.game.client.FrameListener;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

import java.util.Iterator;

public abstract class CollisionDetector implements FrameListener {
  private final SpritePool poolA;
  private final SpritePool poolB;

  public CollisionDetector(Game game, SpritePool poolA, SpritePool poolB) {
    this.poolA = poolA;
    this.poolB = poolB;
    game.addCollisionFrameListener(this);
  }

  public void doFirstFrame() {
    // TODO Replace auto-generated method stub
  }

  public boolean doFrame(double millis) {
    for (Iterator<Sprite> iteratorA = poolA.iterator(); iteratorA.hasNext();) {
      Sprite spriteA = iteratorA.next();
      if (Game.DEBUG) {
        DOM.setStyleAttribute(spriteA.getElement(), "backgroundColor", "");
      }
    }
    for (Iterator<Sprite> iteratorB = poolB.iterator(); iteratorB.hasNext();) {
      Sprite spriteB = iteratorB.next();
      if (Game.DEBUG) {
        DOM.setStyleAttribute(spriteB.getElement(), "backgroundColor", "");
      }
    }
    for (Iterator<Sprite> iteratorA = poolA.iterator(); iteratorA.hasNext();) {
      Sprite spriteA = iteratorA.next();
      for (Iterator<Sprite> iteratorB = poolB.iterator(); iteratorB.hasNext();) {
        Sprite spriteB = iteratorB.next();
        if (intersect(spriteA, spriteB)) {
          if (Game.DEBUG) {
            DOM.setStyleAttribute(spriteA.getElement(), "backgroundColor", "green");
          }
          if (Game.DEBUG) {
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
    double aLeft = spriteA.getX() + spriteA.boundingBoxInfo.offsetLeft;
    double bLeft = spriteB.getX() + spriteB.boundingBoxInfo.offsetLeft;
    double aRight = aLeft + spriteA.boundingBoxInfo.width;
    double bRight = bLeft + spriteB.boundingBoxInfo.width;

    double aTop = spriteA.getY() + spriteA.boundingBoxInfo.offsetTop;
    double bTop = spriteB.getY() + spriteB.boundingBoxInfo.offsetTop;
    double aBottom = aTop + spriteA.boundingBoxInfo.height;
    double bBottom = bTop + spriteB.boundingBoxInfo.height;

    if (aRight < bLeft || aLeft > bRight || aBottom < bTop || aTop > bBottom) {
      return false;
    }
    return true;
  }
}
