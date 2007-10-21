package com.allen_sauer.gwt.game.space.client.sprite.player;

import com.allen_sauer.gwt.game.client.generator.KeyboardBulletGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.space.client.SpaceGame;
import com.allen_sauer.gwt.game.space.client.collision.RobotBulletCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.bullet.BulletSpritePool;

public class Player {
  private BulletSpritePool bulletSpritePool;
  private final SpaceGame game;
  private final int initialLives;
  private KeyboardBulletGenerator keyboardBulletGenerator;
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
    keyboardBulletGenerator = new KeyboardBulletGenerator(bulletSpritePool);
    robotCollisionDetector = new RobotBulletCollisionDetector(game.getRobotSpritePool(), bulletSpritePool,
        game.getExplosionSpritePool());
  }

  public void die() {
    if (!game.getExplosionSpritePool().exhausted()) {
      Sprite explosionSprite = game.getExplosionSpritePool().create();
      explosionSprite.setPosition(playerSprite.getX(), playerSprite.getY());
    }
    if (--lives == 0) {
      playerSprite.markForRemoval();
      keyboardBulletGenerator.markForRemoval();
    } else {
      playerSprite.doFirstFrame();
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