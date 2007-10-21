package com.allen_sauer.gwt.game.hornets.client.collision;

import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.hornets.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.HornetsPlayer;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.robot.RobotSpritePool;

public class PlayerRobotCollisionDetector extends CollisionDetector {
  private final ExplosionSpritePool explosionSpritePool;

  public PlayerRobotCollisionDetector(PlayerSpritePool playerSpritePool, RobotSpritePool robotSpritePool,
      ExplosionSpritePool explosionSpritePool) {
    super(playerSpritePool, robotSpritePool);
    this.explosionSpritePool = explosionSpritePool;
  }

  protected void handleCollision(Sprite playerSprite, Sprite robotSprite) {
    robotSprite.markForRemoval();
    if (!explosionSpritePool.exhausted()) {
      Sprite explosionSprite = explosionSpritePool.create();
      explosionSprite.setPosition(robotSprite.getX(), robotSprite.getY());
    }

    HornetsPlayer player = ((PlayerSprite) playerSprite).getPlayer();
    player.die();
  }
}
