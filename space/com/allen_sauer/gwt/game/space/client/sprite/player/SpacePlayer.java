package com.allen_sauer.gwt.game.space.client.sprite.player;

import com.allen_sauer.gwt.game.client.generator.KeyboardBulletGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.space.client.SpaceGame;
import com.allen_sauer.gwt.game.space.client.collision.RobotBulletCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.bullet.BulletSpritePool;

public class SpacePlayer extends Player {
  private BulletSpritePool bulletSpritePool;
  private final SpaceGame game;
  private KeyboardBulletGenerator keyboardBulletGenerator;
  private int lives;
  private final int playerNumber;
  private final PlayerSprite playerSprite;

  public SpacePlayer(SpaceGame game, int playerNumber, PlayerSprite playerSprite, int initialLives) {
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
    new RobotBulletCollisionDetector(game, game.getRobotSpritePool(), bulletSpritePool, game.getExplosionSpritePool());
  }

  @Override
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
