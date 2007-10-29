package com.allen_sauer.gwt.game.hornets.client.sprite.player;

import com.allen_sauer.gwt.game.client.generator.KeyboardBulletGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.hornets.client.HornetsGame;
import com.allen_sauer.gwt.game.hornets.client.collision.RobotBulletCollisionDetector;
import com.allen_sauer.gwt.game.hornets.client.sprite.bullet.BulletSpritePool;

public class HornetsPlayer extends Player {
  private BulletSpritePool bulletSpritePool;
  private final HornetsGame game;
  private KeyboardBulletGenerator keyboardBulletGenerator;
  private int lives;
  private final int playerNumber;
  private final PlayerSprite playerSprite;

  public HornetsPlayer(HornetsGame game, int playerNumber, PlayerSprite playerSprite, int initialLives) {
    super(game);
    this.game = game;
    this.playerNumber = playerNumber;
    this.playerSprite = playerSprite;

    assert game != null;
    assert playerNumber > 0;
    assert playerSprite != null;
    assert initialLives > 0;

    playerSprite.setPlayer(this);
    lives = initialLives;
    bulletSpritePool = new BulletSpritePool(game, playerSprite);
    keyboardBulletGenerator = new KeyboardBulletGenerator(game, bulletSpritePool);
    new RobotBulletCollisionDetector(game, this, game.getRobot1SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
    new RobotBulletCollisionDetector(game, this, game.getRobot2SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
    new RobotBulletCollisionDetector(game, this, game.getRobot3SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
    new RobotBulletCollisionDetector(game, this, game.getRobot4SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
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
