/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client.sprite.bullet;

import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.hornetblast.client.sprite.bullet.BulletSprite;
import com.allen_sauer.gwt.game.space.client.SpaceGame;

public class BulletSpritePool extends SpritePool {
  public BulletSpritePool(final SpaceGame game, final PlayerSprite playerSprite) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new BulletSprite(game, playerSprite);
      }
    };
    init(spriteFactory, SpaceGame.MAX_BULLETS);
    game.addSpritePool(this);
  }
}
