/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client.collision;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.space.client.sprite.bullet.BulletSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.robot.RobotSpritePool;

public class RobotBulletCollisionDetector extends CollisionDetector {
  private final ExplosionSpritePool explosionSpritePool;

  public RobotBulletCollisionDetector(Game game, RobotSpritePool robotSpritePool, BulletSpritePool bulletSpritePool,
      ExplosionSpritePool explosionSpritePool) {
    super(game, robotSpritePool, bulletSpritePool);
    this.explosionSpritePool = explosionSpritePool;
  }

  @Override
  protected void handleCollision(Sprite robotSprite, Sprite bulletSprite) {
    if (!explosionSpritePool.exhausted()) {
      Sprite explosionSprite = explosionSpritePool.create();
      explosionSprite.setPosition(robotSprite.getX(), robotSprite.getY());
    }
    robotSprite.markForRemoval();
    bulletSprite.markForRemoval();
  }
}
