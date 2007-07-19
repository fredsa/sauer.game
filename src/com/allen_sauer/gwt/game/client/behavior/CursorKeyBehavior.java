package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.user.client.ui.KeyboardListener;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.ui.util.Keyboard;

public class CursorKeyBehavior implements Behavior {

  private final Sprite sprite;
  private int xDelta = 7;
  private int xMax;
  private int yDelta = 7;
  private int yMax;

  public CursorKeyBehavior(Sprite sprite) {
    this.sprite = sprite;
  }

  public void doFrame() {
    int x = sprite.getX();
    int y = sprite.getY();

    if (Keyboard.isKeyDown(KeyboardListener.KEY_LEFT)) {
      x -= xDelta;
      if (x < 0) {
        x = 0;
      }
    }
    if (Keyboard.isKeyDown(KeyboardListener.KEY_RIGHT)) {
      x += xDelta;
      if (x > xMax) {
        x = xMax;
      }
    }

    if (Keyboard.isKeyDown(KeyboardListener.KEY_UP)) {
      y -= yDelta;
      if (y < 0) {
        y = 0;
      }
    }
    if (Keyboard.isKeyDown(KeyboardListener.KEY_DOWN)) {
      y += yDelta;
      if (y > yMax) {
        y = yMax;
      }
    }

    sprite.setX(x);
    sprite.setY(y);
  }

  public void init() {
    xMax = Engine.getClientWidth() - sprite.getFrameWidth();
    yMax = Engine.getClientHeight() - sprite.getFrameHeight();

    sprite.setX(xMax / 2);
    sprite.setY(yMax / 2);

    Engine.addFrameListener(this);
  }
}
