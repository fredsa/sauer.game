package com.allen_sauer.gwt.game.hornetblast.client.sprite.player;

import com.allen_sauer.gwt.game.client.Game.State;
import com.allen_sauer.gwt.game.client.generator.KeyboardBulletGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.hornetblast.client.HornetGame;
import com.allen_sauer.gwt.game.hornetblast.client.collision.RobotBulletCollisionDetector;
import com.allen_sauer.gwt.game.hornetblast.client.sprite.bullet.BulletSpritePool;

public class HornetPlayer extends Player {
  private static int frameCount;

  private static final int INVINSIBLE_FRAME_COUNT = 200;
  private BulletSpritePool bulletSpritePool;
  private final HornetGame game;
  private final int initialLives;
  private KeyboardBulletGenerator keyboardBulletGenerator;
  private int lives;
  private final int playerNumber;
  private PlayerSprite playerSprite;
  private final PlayerSpritePool playerSpritePool;

  public HornetPlayer(HornetGame game, int playerNumber, PlayerSpritePool playerSpritePool, int initialLives) {
    super(game);
    this.game = game;
    this.playerNumber = playerNumber;
    this.playerSpritePool = playerSpritePool;
    this.initialLives = initialLives;

    assert game != null;
    assert playerNumber > 0;
    assert playerSpritePool != null;
    assert initialLives > 0;
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
      game.setState(State.STATE_GAME_OVER);
    } else {
      playerSprite.doFirstFrame();
    }
    game.playerDied(this);
  }

  @Override
  public boolean doFrame() {
    super.doFrame();
    if (frameCount > 0) {
      frameCount--;
      playerSprite.setHidden(frameCount != 0 && frameCount / 4 % 2 == 0);
    }
    return true;
  }

  @Override
  public void doLastFrame() {
    // TODO Auto-generated method stub
    super.doLastFrame();
  }

  public int getLives() {
    return lives;
  }

  public int getPlayerNumber() {
    return playerNumber;
  }

  @Override
  public boolean hit() {
    if (frameCount == 0) {
      frameCount = INVINSIBLE_FRAME_COUNT;
      die();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void reset() {
    super.reset();
    lives = initialLives;
    if (playerSprite == null) {
      playerSprite = (PlayerSprite) playerSpritePool.create();
      playerSprite.setPlayer(this);
      bulletSpritePool = new BulletSpritePool(game, playerSprite);
      keyboardBulletGenerator = new KeyboardBulletGenerator(game, bulletSpritePool);
      new RobotBulletCollisionDetector(game, this, game.getRobot1SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
      new RobotBulletCollisionDetector(game, this, game.getRobot2SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
      new RobotBulletCollisionDetector(game, this, game.getRobot3SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
      new RobotBulletCollisionDetector(game, this, game.getRobot4SpritePool(), bulletSpritePool, game.getExplosionSpritePool());
    } else {
      frameCount = INVINSIBLE_FRAME_COUNT;
      assert playerSprite == (PlayerSprite) playerSpritePool.create();
    }
    game.getPlayingFrameListenerCollection().addFrameListener(playerSprite);
    game.getPlayingFrameListenerCollection().addFrameListener(keyboardBulletGenerator);
  }
}
