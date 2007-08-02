package com.allen_sauer.gwt.game.space.client.sprite.player;

import com.allen_sauer.gwt.game.space.client.SpaceGame;
import com.allen_sauer.gwt.game.space.client.collision.RobotBulletCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.bullet.BulletSpritePool;

public class Player {
  private BulletSpritePool bulletSpritePool;
  private final int maxLives;
  private final PlayerSprite playerSprite;
  private RobotBulletCollisionDetector robotCollisionDetector;

  public Player(SpaceGame game, PlayerSprite playerSprite, int maxLives) {
    assert maxLives > 0;

    this.playerSprite = playerSprite;
    this.maxLives = maxLives;

    bulletSpritePool = new BulletSpritePool(game, playerSprite);
    robotCollisionDetector = new RobotBulletCollisionDetector(game.getRobotSpritePool(),
        bulletSpritePool, game.getExplosionSpritePool());
  }
}
