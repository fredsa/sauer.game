/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.hornetblast.client.collision;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.Player;

public class RobotBulletCollisionDetector extends CollisionDetector {
  private final SpritePool explosionSpritePool;
  private final Player player;

  public RobotBulletCollisionDetector(Game game, Player player, SpritePool robotSpritePool,
      SpritePool bulletSpritePool, SpritePool explosionSpritePool) {
    super(game, robotSpritePool, bulletSpritePool);
    this.player = player;
    this.explosionSpritePool = explosionSpritePool;
  }

  @Override
  protected void handleCollision(Sprite robotSprite, Sprite bulletSprite) {
    player.incrementScore(10);
    if (!explosionSpritePool.exhausted()) {
      Sprite explosionSprite = explosionSpritePool.create();
      explosionSprite.setPosition(robotSprite.getX(), robotSprite.getY());
    }
    robotSprite.markForRemoval();
    bulletSprite.markForRemoval();
  }
}
