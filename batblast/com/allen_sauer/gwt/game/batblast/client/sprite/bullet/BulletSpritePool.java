/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.bullet;

import com.allen_sauer.gwt.game.batblast.client.BatGame;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.PlayerSprite;

public class BulletSpritePool extends SpritePool {
  private static int count;

  public BulletSpritePool(final BatGame game, final PlayerSprite playerSprite) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {

      public Sprite create() {
        switch (++count % 3) {
          case 0:
            return new BulletSprite(game, playerSprite);
          case 1:
            return new BulletSprite2(game, playerSprite);
          case 2:
            return new BulletSprite3(game, playerSprite);
          default:
            throw new UnsupportedOperationException();
        }
      }
    };
    init(spriteFactory, BatGame.MAX_BULLETS);
    game.addSpritePool(this);
  }
}
