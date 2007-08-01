package com.allen_sauer.gwt.game.space.client.sprite.bullet;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.generator.KeyboardBulletGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.space.client.SpaceGame;

public class BulletSpritePool extends SpritePool {
  private static final int MAX_BULLETS = 5;

  public BulletSpritePool(final SpaceGame game, final Sprite playerSprite) {
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new BulletSprite(game, playerSprite);
      }
    };
    init(spriteFactory, MAX_BULLETS);
    Engine.addSpritePool(this);
    new KeyboardBulletGenerator(this);
  }
}
