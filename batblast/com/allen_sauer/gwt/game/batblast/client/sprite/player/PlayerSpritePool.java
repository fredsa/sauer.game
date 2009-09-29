/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.player;

import com.allen_sauer.gwt.game.batblast.client.BatGame;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class PlayerSpritePool extends SpritePool {
  public PlayerSpritePool(final BatGame game, int maxPlayers) {
    super(game);
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new BatmanPlayerSprite(game);
      }
    };
    init(spriteFactory, maxPlayers);
    game.addSpritePool(this);
    //    new PlayerGenerator(this);
  }
}
