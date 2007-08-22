package com.allen_sauer.gwt.game.client.behavior;

import com.google.gwt.user.client.Random;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.ui.util.Page;

public class CursorKeyBehavior extends Behavior {
  private int xDelta = 7;
  private int yDelta = 7;

  public CursorKeyBehavior(Sprite sprite) {
    super(sprite);
  }

  public void doFirstFrame() {
    setXMax(Engine.getClientWidth() - getSprite().getFrameInfo().frameWidth);
    setYMax(Engine.getClientHeight() - getSprite().getFrameInfo().frameHeight);
    //    sprite.setXY(xMax / 2, yMax / 2);
    setX(Random.nextInt(getXMax()));
    setY(Random.nextInt(getYMax()));
    super.doFirstFrame();
  }

  public boolean doFrame() {
    if (Page.isKeyDown(Page.CODES.KEY_LEFT)) {
      setXSpeed(-xDelta);
    } else if (Page.isKeyDown(Page.CODES.KEY_RIGHT)) {
      setXSpeed(xDelta);
    } else {
      setXSpeed(0);
    }

    if (Page.isKeyDown(Page.CODES.KEY_UP)) {
      setYSpeed(-yDelta);
    } else if (Page.isKeyDown(Page.CODES.KEY_DOWN)) {
      setYSpeed(yDelta);
    } else {
      setYSpeed(0);
    }

    return super.doFrame();
  }
}
