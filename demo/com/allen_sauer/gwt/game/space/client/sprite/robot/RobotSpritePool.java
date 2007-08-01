package com.allen_sauer.gwt.game.space.client.sprite.robot;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.space.client.SpaceGame;

public class RobotSpritePool extends SpritePool {
  private static final int MAX_ROBOTS = 10;
  private static final double ROBOT_APPEARANCE_PROBABILITY = .05;

  public RobotSpritePool(final SpaceGame game) {
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new RobotSprite(game);
      }
    };
    init(spriteFactory, MAX_ROBOTS);
    Engine.addSpritePool(this);
    new IntervalGenerator(this, ROBOT_APPEARANCE_PROBABILITY);
  }
}
