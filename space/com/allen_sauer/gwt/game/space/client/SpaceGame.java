/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.space.client.collision.PlayerRobotCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.SpacePlayer;
import com.allen_sauer.gwt.game.space.client.sprite.player.SpacePlayerSprite;
import com.allen_sauer.gwt.game.space.client.sprite.robot.RobotSpritePool;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class SpaceGame extends Game {
  public static final int MAX_BULLETS = 3;
  private static final int MAX_LIVES = 5;
  private static final int MAX_PLAYERS = 1;
  public static final int MAX_ROBOTS = 10;

  public static final double ROBOT_APPEARANCE_PROBABILITY = .05;
  private Image backgroundImage;

  private ExplosionSpritePool explosionSpritePool;
  private SpacePlayer[] player;
  private PlayerSpritePool playerSpritePool;
  private Label[] playerText;
  private RobotSpritePool robotSpritePool;
  private SoundController soundController;

  public SpaceGame() {
    addStyleName("space");
  }

  public ExplosionSpritePool getExplosionSpritePool() {
    return explosionSpritePool;
  }

  public RobotSpritePool getRobotSpritePool() {
    return robotSpritePool;
  }

  @Override
  public SoundController getSoundController() {
    return soundController;
  }

  private void initPlayerText() {
    playerText = new Label[MAX_LIVES];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      playerText[i] = new Label();
      playerText[i].addStyleName("playerText");
      getPlayfield().add(playerText[i], -500, -500);
    }
    updatePlayerText();
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    soundController = new SoundController();
    soundController.setDefaultVolume(10);

    backgroundImage = new Image("space-images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("game-background-image");
    backgroundImage.setSize("100%", "100%");
    background.add(backgroundImage, 0, 0);

    robotSpritePool = new RobotSpritePool(this);
    explosionSpritePool = new ExplosionSpritePool(this);

    playerSpritePool = new PlayerSpritePool(this, MAX_PLAYERS);

    player = new SpacePlayer[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int playerNumber = i + 1;
      player[i] = new SpacePlayer(this, playerNumber,
          (SpacePlayerSprite) playerSpritePool.create(), MAX_LIVES);
    }
    new PlayerRobotCollisionDetector(this, playerSpritePool, robotSpritePool, explosionSpritePool);

    initPlayerText();
  }

  @Override
  public void playerDied(Player player) {
    updatePlayerText();
  }

  @Override
  public void updatePlayerText() {
    int spacing = MAX_PLAYERS != 1 ? getPlayfieldWidth() / (MAX_PLAYERS - 1) : 0;
    int middle = getPlayfieldWidth() / 2;
    for (int i = 0; i < MAX_PLAYERS; i++) {
      playerText[i].setText(player[i].getPlayerNumber() + "UP: " + player[i].getLives());
      int targetX = i * spacing;
      int x = targetX < middle ? targetX : targetX - playerText[i].getOffsetWidth();
      getPlayfield().setWidgetPosition(playerText[i], x, 10);
    }
  }
}
