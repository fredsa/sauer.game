package com.allen_sauer.gwt.game.space.client.sprite.player;

import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.space.client.SpaceGame;
import com.allen_sauer.gwt.game.space.client.collision.RobotBulletCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.bullet.BulletSpritePool;

public class Player {
  private BulletSpritePool bulletSpritePool;
  private final SpaceGame game;
  private final int initialLives;
  private int lives;
  private final int playerNumber;
  private final PlayerSprite playerSprite;
  private RobotBulletCollisionDetector robotCollisionDetector;

  public Player(SpaceGame game, int playerNumber, PlayerSprite playerSprite, int initialLives) {
    this.game = game;
    this.playerNumber = playerNumber;
    this.playerSprite = playerSprite;
    this.initialLives = initialLives;

    assert game != null;
    assert playerNumber > 0;
    assert playerSprite != null;
    assert initialLives > 0;

    playerSprite.setPlayer(this);
    lives = initialLives;
    bulletSpritePool = new BulletSpritePool(game, playerSprite);
    robotCollisionDetector = new RobotBulletCollisionDetector(game.getRobotSpritePool(),
        bulletSpritePool, game.getExplosionSpritePool());
  }

  public void die() {
    if (--lives == 0) {
      playerSprite.markForRemoval();
    } else {
      playerSprite.doFirstFrame();
    }
    Sprite explosionSprite = game.getExplosionSpritePool().create();
    if (explosionSprite != null) {
      explosionSprite.setXY(playerSprite.getX(), playerSprite.getY());
    }

    game.playerDied(this);
  }

  public int getLives() {
    return lives;
  }

  public int getPlayerNumber() {
    return playerNumber;
  }
}
