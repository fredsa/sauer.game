package com.allen_sauer.gwt.game.hornets.client.sprite.robot;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.hornets.client.HornetsGame;

public class RobotSpritePool extends SpritePool {

  public RobotSpritePool(final HornetsGame game) {
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new RobotSprite(game);
      }
    };
    init(spriteFactory, HornetsGame.MAX_ROBOTS);
    Engine.addSpritePool(this);
    new IntervalGenerator(this, HornetsGame.ROBOT_APPEARANCE_PROBABILITY);
  }
}
