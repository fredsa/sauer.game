/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.sprite.bullet;

import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.hornetblast.client.HornetGame;

public class BulletSpritePool extends SpritePool {
  public BulletSpritePool(final HornetGame game, final PlayerSprite playerSprite) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new BulletSprite(game, playerSprite);
      }
    };
    init(spriteFactory, HornetGame.MAX_BULLETS);
    game.addSpritePool(this);
  }
}
