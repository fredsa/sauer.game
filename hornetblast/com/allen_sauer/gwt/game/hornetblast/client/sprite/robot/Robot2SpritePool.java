/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.sprite.robot;

import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.hornetblast.client.HornetGame;

public class Robot2SpritePool extends SpritePool {

  public Robot2SpritePool(final HornetGame game) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new Robot2Sprite(game);
      }
    };
    init(spriteFactory, HornetGame.MAX_ROBOTS);
    game.addSpritePool(this);
    new IntervalGenerator(this, HornetGame.ROBOT_APPEARANCE_PROBABILITY);
  }
}
