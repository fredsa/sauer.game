/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class VerticalBulletBehavior extends Behavior {
  private final Sprite playerSprite;

  public VerticalBulletBehavior(Sprite sprite, Sprite playerSprite) {
    super(sprite);
    this.playerSprite = playerSprite;
  }

  @Override
  public void doFirstFrame() {
    setYMin(-getSprite().getFrameInfo().frameHeight);
    // yMax = Engine.getClientHeight() - sprite.getFrameHeight();
    setX(playerSprite.getX() + 17);
    setY(playerSprite.getY() - 5);

    setYSpeed(-10);
    super.doFirstFrame();
  }

  @Override
  public boolean doFrame() {
    boolean again = true;
    if (getY() == getYMin()) {
      again = false;
    }
    return again && super.doFrame();
  }
}
