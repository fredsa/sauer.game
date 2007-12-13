/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client.collision;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.space.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.SpacePlayer;
import com.allen_sauer.gwt.game.space.client.sprite.player.SpacePlayerSprite;
import com.allen_sauer.gwt.game.space.client.sprite.robot.RobotSpritePool;

public class PlayerRobotCollisionDetector extends CollisionDetector {
  private final ExplosionSpritePool explosionSpritePool;

  public PlayerRobotCollisionDetector(Game game, PlayerSpritePool playerSpritePool, RobotSpritePool robotSpritePool,
      ExplosionSpritePool explosionSpritePool) {
    super(game, playerSpritePool, robotSpritePool);
    this.explosionSpritePool = explosionSpritePool;
  }

  @Override
  protected void handleCollision(Sprite playerSprite, Sprite robotSprite) {
    robotSprite.markForRemoval();
    if (!explosionSpritePool.exhausted()) {
      Sprite explosionSprite = explosionSpritePool.create();
      explosionSprite.setPosition(robotSprite.getX(), robotSprite.getY());
    }

    SpacePlayer player = ((SpacePlayerSprite) playerSprite).getPlayer();
    player.die();
  }
}
