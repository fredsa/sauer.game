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
  private final int initialLives;
  private KeyboardBulletGenerator keyboardBulletGenerator;
  private int lives;
  private final int playerNumber;
  private final PlayerSprite playerSprite;
  private RobotBulletCollisionDetector robot1CollisionDetector;
  private RobotBulletCollisionDetector robot2CollisionDetector;
  private RobotBulletCollisionDetector robot3CollisionDetector;
  private RobotBulletCollisionDetector robot4CollisionDetector;

  public HornetsPlayer(HornetsGame game, int playerNumber, PlayerSprite playerSprite, int initialLives) {
    super(game);
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
    robot1CollisionDetector = new RobotBulletCollisionDetector(this, game.getRobot1SpritePool(), bulletSpritePool,
        game.getExplosionSpritePool());
    robot2CollisionDetector = new RobotBulletCollisionDetector(this, game.getRobot2SpritePool(), bulletSpritePool,
        game.getExplosionSpritePool());
    robot3CollisionDetector = new RobotBulletCollisionDetector(this, game.getRobot3SpritePool(), bulletSpritePool,
        game.getExplosionSpritePool());
    robot4CollisionDetector = new RobotBulletCollisionDetector(this, game.getRobot4SpritePool(), bulletSpritePool,
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
