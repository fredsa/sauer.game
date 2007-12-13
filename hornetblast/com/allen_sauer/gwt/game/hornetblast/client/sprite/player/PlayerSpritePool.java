/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.sprite.player;

import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.hornetblast.client.HornetGame;

public class PlayerSpritePool extends SpritePool {
  public PlayerSpritePool(final HornetGame game, int maxPlayers) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new PlayerSprite(game);
      }
    };
    init(spriteFactory, maxPlayers);
    game.addSpritePool(this);
    //    new PlayerGenerator(this);
  }
}
