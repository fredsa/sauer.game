package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.space.client.collision.PlayerRobotCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.SpacePlayer;
import com.allen_sauer.gwt.game.space.client.sprite.robot.RobotSpritePool;
import com.allen_sauer.gwt.voices.client.SoundController;

public class SpaceGame implements Game {
  public static final int MAX_BULLETS = 3;
  public static final int MAX_ROBOTS = 10;
  public static final double ROBOT_APPEARANCE_PROBABILITY = .05;

  private static final int MAX_LIVES = 5;
  private static final int MAX_PLAYERS = 1;

  private Image backgroundImage;
  private ExplosionSpritePool explosionSpritePool;
  private SpacePlayer[] player;
  private PlayerRobotCollisionDetector playerRobotCollisionDetector;
  private PlayerSpritePool playerSpritePool;
  private Label[] playerText;
  private RobotSpritePool robotSpritePool;
  private SoundController soundController;

  public void clientResized(int clientWidth, int clientHeight) {
    //    backgroundImage.setPixelSize(clientWidth, clientHeight);
  }

  public ExplosionSpritePool getExplosionSpritePool() {
    return explosionSpritePool;
  }

  public RobotSpritePool getRobotSpritePool() {
    return robotSpritePool;
  }

  public SoundController getSoundController() {
    return soundController;
  }

  public void init() {
    soundController = new SoundController();
    soundController.setDefaultVolume(10);

    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    //    backgroundImage.setPixelSize(Engine.getClientWidth(), Engine.getClientHeight());
    Engine.background.add(backgroundImage, 0, 0);

    robotSpritePool = new RobotSpritePool(this);
    explosionSpritePool = new ExplosionSpritePool(this);

    playerSpritePool = new PlayerSpritePool(this, MAX_PLAYERS);

    player = new SpacePlayer[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int playerNumber = i + 1;
      player[i] = new SpacePlayer(this, playerNumber, (PlayerSprite) playerSpritePool.create(), MAX_LIVES);
    }
    playerRobotCollisionDetector = new PlayerRobotCollisionDetector(playerSpritePool, robotSpritePool, explosionSpritePool);

    initPlayerText();
  }

  public void playerDied(Player player) {
    updatePlayerText();
  }

  private void initPlayerText() {
    playerText = new Label[MAX_LIVES];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      playerText[i] = new Label();
      playerText[i].addStyleName("playerText");
      Engine.playfield.add(playerText[i], -500, -500);
    }
    updatePlayerText();
  }

  private void updatePlayerText() {
    int spacing = MAX_PLAYERS != 1 ? Engine.getClientWidth() / (MAX_PLAYERS - 1) : 0;
    int middle = Engine.getClientWidth() / 2;
    for (int i = 0; i < MAX_PLAYERS; i++) {
      playerText[i].setText(player[i].getPlayerNumber() + "UP: " + player[i].getLives());
      int targetX = i * spacing;
      int x = targetX < middle ? targetX : targetX - playerText[i].getOffsetWidth();
      Engine.playfield.setWidgetPosition(playerText[i], x, 10);
    }
  }
}
