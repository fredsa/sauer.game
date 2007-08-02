package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.ui.util.Page;

public class CursorKeyBehavior implements Behavior {

  private final Sprite sprite;
  private int xDelta = 7;
  private int xMax;
  private int yDelta = 7;
  private int yMax;

  public CursorKeyBehavior(Sprite sprite) {
    this.sprite = sprite;
  }

  public void doFirstFrame() {
    xMax = Engine.getClientWidth() - sprite.frameMetaData.frameWidth;
    yMax = Engine.getClientHeight() - sprite.frameMetaData.frameHeight;
    //    sprite.setXY(xMax / 2, yMax / 2);
    sprite.setXY(Random.nextInt(xMax), Random.nextInt(yMax));
  }

  public FrameListenerRetention doFrame() {
    int x = sprite.getX();
    int y = sprite.getY();

    if (Page.isKeyDown(Page.CODES.KEY_LEFT)) {
      x -= xDelta;
      if (x < 0) {
        x = 0;
      }
    }
    if (Page.isKeyDown(Page.CODES.KEY_RIGHT)) {
      x += xDelta;
      if (x > xMax) {
        x = xMax;
      }
    }

    if (Page.isKeyDown(Page.CODES.KEY_UP)) {
      y -= yDelta;
      if (y < 0) {
        y = 0;
      }
    }
    if (Page.isKeyDown(Page.CODES.KEY_DOWN)) {
      y += yDelta;
      if (y > yMax) {
        y = yMax;
      }
    }

    sprite.setXY(x, y);
    return LISTENER_CONTINUE;
  }

  public void doLastFrame() {
  }
}
