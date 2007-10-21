package com.allen_sauer.gwt.game.hornets.client;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.hornets.client.collision.PlayerRobotCollisionDetector;
import com.allen_sauer.gwt.game.hornets.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.HornetsPlayer;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.robot.Robot1SpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.robot.Robot2SpritePool;
import com.allen_sauer.gwt.voices.client.SoundController;

public class HornetsGame implements Game {
  public static final int MAX_BULLETS = 3;
  public static final int MAX_ROBOTS = 3;
  public static final double ROBOT_APPEARANCE_PROBABILITY = .05;

  private static final int MAX_LIVES = 5;
  private static final int MAX_PLAYERS = 1;

  private Image backgroundImage;
  private ExplosionSpritePool explosionSpritePool;
  private HornetsPlayer[] player;
  private PlayerRobotCollisionDetector playerRobot1CollisionDetector;
  private PlayerRobotCollisionDetector playerRobot2CollisionDetector;
  private PlayerSpritePool playerSpritePool;
  private Label[] playerText;
  private Robot1SpritePool robot1SpritePool;
  private Robot2SpritePool robot2SpritePool;
  private SoundController soundController;

  public void clientResized(int clientWidth, int clientHeight) {
    //    backgroundImage.setPixelSize(clientWidth, clientHeight);
  }

  public ExplosionSpritePool getExplosionSpritePool() {
    return explosionSpritePool;
  }

  public Robot1SpritePool getRobot1SpritePool() {
    return robot1SpritePool;
  }

  public Robot2SpritePool getRobot2SpritePool() {
    return robot2SpritePool;
  }

  public SoundController getSoundController() {
    return soundController;
  }

  public void init() {
    soundController = new SoundController();
    soundController.setDefaultVolume(10);

    //    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    //    backgroundImage.addStyleName("backgroundImage");
    //    //    backgroundImage.setPixelSize(Engine.getClientWidth(), Engine.getClientHeight());
    //    Engine.background.add(backgroundImage, 0, 0);

    robot1SpritePool = new Robot1SpritePool(this);
    robot2SpritePool = new Robot2SpritePool(this);
    explosionSpritePool = new ExplosionSpritePool(this);

    playerSpritePool = new PlayerSpritePool(this, MAX_PLAYERS);

    player = new HornetsPlayer[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int playerNumber = i + 1;
      player[i] = new HornetsPlayer(this, playerNumber, (PlayerSprite) playerSpritePool.create(), MAX_LIVES);
    }
    playerRobot1CollisionDetector = new PlayerRobotCollisionDetector(playerSpritePool, robot1SpritePool, explosionSpritePool);
    playerRobot2CollisionDetector = new PlayerRobotCollisionDetector(playerSpritePool, robot2SpritePool, explosionSpritePool);

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
