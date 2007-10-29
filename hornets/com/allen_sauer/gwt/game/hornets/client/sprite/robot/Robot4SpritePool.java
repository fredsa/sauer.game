package com.allen_sauer.gwt.game.hornets.client.sprite.robot;

import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.hornets.client.HornetsGame;

public class Robot4SpritePool extends SpritePool {

  public Robot4SpritePool(final HornetsGame game) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new Robot4Sprite(game);
      }
    };
    init(spriteFactory, HornetsGame.MAX_ROBOTS);
    game.addSpritePool(this);
    new IntervalGenerator(this, HornetsGame.ROBOT_APPEARANCE_PROBABILITY);
  }
}
