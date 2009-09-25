/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.robot;

import com.allen_sauer.gwt.game.batblast.client.BatGame;
import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class KillerCrocSpritePool extends SpritePool {

  public KillerCrocSpritePool(final BatGame game) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new KillerCrocSprite(game);
      }
    };
    init(spriteFactory, BatGame.MAX_ROBOTS);
    game.addSpritePool(this);
    new IntervalGenerator(this, BatGame.ROBOT_APPEARANCE_PROBABILITY);
  }
}
