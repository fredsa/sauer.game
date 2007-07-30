package com.allen_sauer.gwt.game.space.client.collision;

import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.space.client.sprite.BulletSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.RobotSpritePool;

public class RobotCollisionDetector extends CollisionDetector {
  public RobotCollisionDetector(RobotSpritePool poolA, BulletSpritePool poolB) {
    super(poolA, poolB);
  }

  protected void handleCollision(Sprite spriteA, Sprite spriteB) {
    // TODO Replace auto-generated method stub
  }
}
