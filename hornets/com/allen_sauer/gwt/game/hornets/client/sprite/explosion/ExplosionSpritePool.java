package com.allen_sauer.gwt.game.hornets.client.sprite.explosion;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.hornets.client.HornetsGame;

public class ExplosionSpritePool extends SpritePool {
  private static final int MAX_EXPLOSIONS = 10;

  public ExplosionSpritePool(final HornetsGame game) {
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new ExplosionSprite(game);
      }
    };
    init(spriteFactory, MAX_EXPLOSIONS);
    Engine.addSpritePool(this);
    //    new IntervalGenerator(this);
  }
}