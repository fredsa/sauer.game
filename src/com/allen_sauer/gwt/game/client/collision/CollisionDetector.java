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

  public FrameListenerRetention doFrame() {
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
    return LISTENER_CONTINUE;
  }

  public void doLastFrame() {
    // TODO Replace auto-generated method stub
  }

  protected abstract void handleCollision(Sprite spriteA, Sprite spriteB);

  private boolean intersect(Sprite spriteA, Sprite spriteB) {
    if (spriteA.getX() + spriteA.boundingBoxInfo.width < spriteB.getX()
        || spriteA.getX() > spriteB.getX() + spriteB.boundingBoxInfo.width
        || spriteA.getY() + spriteA.boundingBoxInfo.height < spriteB.getY()
        || spriteA.getY() > spriteB.getY() + spriteB.boundingBoxInfo.height) {
      return false;
    }
    return true;
  }
}
