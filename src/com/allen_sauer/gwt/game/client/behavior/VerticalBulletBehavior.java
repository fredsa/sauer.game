/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.behavior;

import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;

public class VerticalBulletBehavior extends Behavior {
  private final Sprite bulletSprite;
  private final PlayerSprite playerSprite;

  public VerticalBulletBehavior(Sprite bulletSprite, PlayerSprite playerSprite) {
    super(bulletSprite);
    this.bulletSprite = bulletSprite;
    this.playerSprite = playerSprite;
  }

  @Override
  public void doFirstFrame() {
    setYMin(-getSprite().getFrameInfo().frameHeight);
    // yMax = Engine.getClientHeight() - sprite.getFrameHeight();
    setX(playerSprite.getX() + playerSprite.getBulletStartOffsetX()
        - bulletSprite.boundingBoxInfo.width / 2);
    setY(playerSprite.getY() + playerSprite.getBulletStartOffsetY()
        - bulletSprite.boundingBoxInfo.height / 2);

    setYSpeed(-0.5);
    super.doFirstFrame();
  }

  @Override
  public boolean doFrame(double millis) {
    boolean again = true;
    if (getY() == getYMin()) {
      again = false;
    }
    return again && super.doFrame(millis);
  }
}
