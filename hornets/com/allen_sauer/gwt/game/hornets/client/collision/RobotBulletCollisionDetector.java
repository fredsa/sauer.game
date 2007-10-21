package com.allen_sauer.gwt.game.hornets.client.collision;

import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class RobotBulletCollisionDetector extends CollisionDetector {
  private final SpritePool explosionSpritePool;

  public RobotBulletCollisionDetector(SpritePool robotSpritePool, SpritePool bulletSpritePool,
      SpritePool explosionSpritePool) {
    super(robotSpritePool, bulletSpritePool);
    this.explosionSpritePool = explosionSpritePool;
  }

  protected void handleCollision(Sprite robotSprite, Sprite bulletSprite) {
    if (!explosionSpritePool.exhausted()) {
      Sprite explosionSprite = explosionSpritePool.create();
      explosionSprite.setPosition(robotSprite.getX(), robotSprite.getY());
    }
    robotSprite.markForRemoval();
    bulletSprite.markForRemoval();
  }
}
