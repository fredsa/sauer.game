package com.allen_sauer.gwt.game.hornets.client.sprite.player;

import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.hornets.client.HornetsGame;

public class PlayerSpritePool extends SpritePool {
  public PlayerSpritePool(final HornetsGame game, int maxPlayers) {
    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new PlayerSprite(game);
      }
    };
    init(spriteFactory, maxPlayers);
    Engine.addSpritePool(this);
    //    new PlayerGenerator(this);
  }
}
