/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.explosion;

import com.allen_sauer.gwt.game.batblast.client.BatGame;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class ExplosionSpritePool extends SpritePool {
  private static int count;

  private static final int MAX_EXPLOSIONS = 10;

  public ExplosionSpritePool(final BatGame game) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {

      public Sprite create() {
        switch (++count % 2) {
          case 0:
            return new IcedPlayer1Sprite(game);
          case 1:
            return new IcedPlayer2Sprite(game);
          default:
            throw new UnsupportedOperationException();
        }
      }
    };
    init(spriteFactory, MAX_EXPLOSIONS);
    game.addSpritePool(this);
    //    new IntervalGenerator(this);
  }
}
