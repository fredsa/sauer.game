/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.collision;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.hornetblast.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.hornetblast.client.sprite.player.PlayerSprite;

public class PlayerRobotCollisionDetector extends CollisionDetector {
  private final ExplosionSpritePool explosionSpritePool;

  public PlayerRobotCollisionDetector(Game game, SpritePool playerSpritePool, SpritePool robotSpritePool,
      ExplosionSpritePool explosionSpritePool) {
    super(game, playerSpritePool, robotSpritePool);
    this.explosionSpritePool = explosionSpritePool;
  }

  @Override
  protected void handleCollision(Sprite playerSprite, Sprite robotSprite) {
    Player player = ((PlayerSprite) playerSprite).getPlayer();
    if (player.hit()) {
      robotSprite.markForRemoval();
      if (!explosionSpritePool.exhausted()) {
        Sprite explosionSprite = explosionSpritePool.create();
        explosionSprite.setPosition(robotSprite.getX(), robotSprite.getY());
      }
    }
  }
}
