package com.allen_sauer.gwt.game.space.client.sprite;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.space.client.SpaceGame;

public class PlayerSpritePool extends SpritePool {
  private static final int MAX_PLAYERS = 1;

  public PlayerSpritePool(final SpaceGame game) {
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new SpaceShuttleSprite(game);
      }
    };
    init(spriteFactory, MAX_PLAYERS);
    Engine.addSpritePool(this);
    new IntervalGenerator(this, 1);
  }
}
