package com.allen_sauer.gwt.game.space.client.collision;

import com.allen_sauer.gwt.game.client.collision.CollisionDetector;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.space.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.Player;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.robot.RobotSpritePool;

public class PlayerCollisionDetector extends CollisionDetector {
  private final ExplosionSpritePool explosionSpritePool;

  public PlayerCollisionDetector(PlayerSpritePool playerSpritePool,
      RobotSpritePool robotSpritePool, ExplosionSpritePool explosionSpritePool) {
    super(playerSpritePool, robotSpritePool);
    this.explosionSpritePool = explosionSpritePool;
  }

  protected void handleCollision(Sprite playerSprite, Sprite robotSprite) {
    robotSprite.markForRemoval();
    Sprite explosionSprite = explosionSpritePool.create();
    if (explosionSprite != null) {
      explosionSprite.setXY(robotSprite.getX(), robotSprite.getY());
    }

    Player player = ((PlayerSprite) playerSprite).getPlayer();
    player.die();
  }
}
