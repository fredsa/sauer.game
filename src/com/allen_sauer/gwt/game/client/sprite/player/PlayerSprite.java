/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.sprite.player;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.BoundingBoxInfo;
import com.allen_sauer.gwt.game.client.sprite.Sprite;

public class PlayerSprite extends Sprite {
  private final int bulletStartOffsetX;
  private final int bulletStartOffsetY;

  public PlayerSprite(Game game, BoundingBoxInfo boundingBoxInfo, int bulletStartOffsetX,
      int bulletStartOffsetY) {
    super(game, boundingBoxInfo);
    this.bulletStartOffsetX = bulletStartOffsetX;
    this.bulletStartOffsetY = bulletStartOffsetY;
  }

  public int getBulletStartOffsetX() {
    return bulletStartOffsetX;
  }

  public int getBulletStartOffsetY() {
    return bulletStartOffsetY;
  }
}
