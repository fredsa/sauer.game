package com.allen_sauer.gwt.game.hornets.client;

import com.google.gwt.user.client.ui.Label;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.game.hornets.client.collision.PlayerRobotCollisionDetector;
import com.allen_sauer.gwt.game.hornets.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.HornetsPlayer;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.hornets.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.robot.Robot1SpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.robot.Robot2SpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.robot.Robot3SpritePool;
import com.allen_sauer.gwt.game.hornets.client.sprite.robot.Robot4SpritePool;
import com.allen_sauer.gwt.voices.client.SoundController;

public class HornetsGame extends Game {
  public static final int MAX_BULLETS = 5;
  public static final int MAX_ROBOTS = 2;
  public static final double ROBOT_APPEARANCE_PROBABILITY = .03;

  private static final int MAX_LIVES = 5;
  private static final int MAX_PLAYERS = 1;

  //  private Image backgroundImage;
  private ExplosionSpritePool explosionSpritePool;
  private HornetsPlayer[] player;
  private PlayerRobotCollisionDetector playerRobot1CollisionDetector;
  private PlayerRobotCollisionDetector playerRobot2CollisionDetector;
  private PlayerRobotCollisionDetector playerRobot3CollisionDetector;
  private PlayerRobotCollisionDetector playerRobot4CollisionDetector;
  private SpritePool playerSpritePool;
  private Label[] playerText;
  private SpritePool robot1SpritePool;
  private SpritePool robot2SpritePool;
  private SpritePool robot3SpritePool;
  private SpritePool robot4SpritePool;
  private SoundController soundController;

  public ExplosionSpritePool getExplosionSpritePool() {
    return explosionSpritePool;
  }

  public SpritePool getRobot1SpritePool() {
    return robot1SpritePool;
  }

  public SpritePool getRobot2SpritePool() {
    return robot2SpritePool;
  }

  public SpritePool getRobot3SpritePool() {
    return robot3SpritePool;
  }

  public SpritePool getRobot4SpritePool() {
    return robot4SpritePool;
  }

  public SoundController getSoundController() {
    return soundController;
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    soundController = new SoundController();
    soundController.setDefaultVolume(60);

    //    backgroundImage = new Image("images/hubble-deep-field-northern-detail-rw.jpg");
    //    backgroundImage.addStyleName("game-background-image");
    //    //    backgroundImage.setPixelSize(Engine.getClientWidth(), Engine.getClientHeight());
    //    Engine.background.add(backgroundImage, 0, 0);

    robot1SpritePool = new Robot1SpritePool(this);
    robot2SpritePool = new Robot2SpritePool(this);
    robot3SpritePool = new Robot3SpritePool(this);
    robot4SpritePool = new Robot4SpritePool(this);
    explosionSpritePool = new ExplosionSpritePool(this);

    playerSpritePool = new PlayerSpritePool(this, MAX_PLAYERS);

    player = new HornetsPlayer[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int playerNumber = i + 1;
      player[i] = new HornetsPlayer(this, playerNumber, (PlayerSprite) playerSpritePool.create(), MAX_LIVES);
    }
    playerRobot1CollisionDetector = new PlayerRobotCollisionDetector(this, playerSpritePool, robot1SpritePool, explosionSpritePool);
    playerRobot2CollisionDetector = new PlayerRobotCollisionDetector(this, playerSpritePool, robot2SpritePool, explosionSpritePool);
    playerRobot3CollisionDetector = new PlayerRobotCollisionDetector(this, playerSpritePool, robot3SpritePool, explosionSpritePool);
    playerRobot4CollisionDetector = new PlayerRobotCollisionDetector(this, playerSpritePool, robot4SpritePool, explosionSpritePool);

    initPlayerText();
  }

  public void playerDied(Player player) {
    updatePlayerText();
  }

  public void updatePlayerText() {
    int spacing = MAX_PLAYERS != 1 ? getClientWidth() / (MAX_PLAYERS - 1) : 0;
    int middle = getClientWidth() / 2;
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int lives = player[i].getLives();
      playerText[i].setText(player[i].getPlayerNumber() + "UP: " + lives + " " + (lives == 1 ? "Life" : "Lives") + " / "
          + player[i].getScore() + " points");
      int targetX = i * spacing;
      int x = targetX < middle ? targetX : targetX - playerText[i].getOffsetWidth();
      playfield.setWidgetPosition(playerText[i], x, 10);
    }
  }

  private void initPlayerText() {
    playerText = new Label[MAX_LIVES];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      playerText[i] = new Label();
      playerText[i].addStyleName("playerText");
      playfield.add(playerText[i], -500, -500);
    }
    updatePlayerText();
  }
}
