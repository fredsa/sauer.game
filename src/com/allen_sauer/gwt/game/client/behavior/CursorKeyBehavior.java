/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.ui.util.Page;

public class CursorKeyBehavior extends Behavior {
  private final Sprite sprite;
  private int xDelta = 4;
  private int yDelta = 4;

  public CursorKeyBehavior(Sprite sprite) {
    super(sprite);
    this.sprite = sprite;
  }

  @Override
  public void doFirstFrame() {
    clientResized();
    //  setX(Random.nextInt(getXMax()));
    //  setY(Random.nextInt(getYMax()));
    setX(getXMax() / 2);
    setY(getYMax());
    super.doFirstFrame();
  }

  @Override
  public boolean doFrame() {
    if (sprite.getGame().input.isKeyDown(Page.CODES.KEY_LEFT)) {
      setXSpeed(-xDelta);
    } else if (sprite.getGame().input.isKeyDown(Page.CODES.KEY_RIGHT)) {
      setXSpeed(xDelta);
    } else {
      setXSpeed(0);
    }

    if (sprite.getGame().input.isKeyDown(Page.CODES.KEY_UP)) {
      setYSpeed(-yDelta);
    } else if (sprite.getGame().input.isKeyDown(Page.CODES.KEY_DOWN)) {
      setYSpeed(yDelta);
    } else {
      setYSpeed(0);
    }

    return super.doFrame();
  }
}
