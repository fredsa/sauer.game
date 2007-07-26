package com.allen_sauer.gwt.game.client.behavior;

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

  public void deinitialize() {
  }

  public void doFrame() {
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
  }

  public void initialize() {
    xMax = Engine.getClientWidth() - sprite.getFrameWidth();
    yMax = Engine.getClientHeight() - sprite.getFrameHeight();
    sprite.setXY(xMax / 2, yMax / 2);
  }
}
