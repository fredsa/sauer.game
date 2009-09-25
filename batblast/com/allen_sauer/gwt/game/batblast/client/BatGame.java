/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.batblast.client;

import com.google.gwt.event.dom.client.KeyCodes;

import com.allen_sauer.gwt.game.batblast.client.collision.PlayerRobotCollisionDetector;
import com.allen_sauer.gwt.game.batblast.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.batblast.client.sprite.player.BatmanPlayer;
import com.allen_sauer.gwt.game.batblast.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.batblast.client.sprite.robot.MrFreezeSpritePool;
import com.allen_sauer.gwt.game.batblast.client.sprite.robot.BaineSpritePool;
import com.allen_sauer.gwt.game.batblast.client.sprite.robot.KillerCrocSpritePool;
import com.allen_sauer.gwt.game.batblast.client.sprite.robot.PenguinSpritePool;
import com.allen_sauer.gwt.game.batblast.client.ui.BatLabel;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.game.client.sprite.player.Player;
import com.allen_sauer.gwt.voices.client.SoundController;

public class BatGame extends Game {
  public static final int MAX_BULLETS = 5;
  public static final int MAX_ROBOTS = 2;
  public static final double ROBOT_APPEARANCE_PROBABILITY = .03;

  private static final int MAX_LIVES = 3;
  private static final int MAX_PLAYERS = 1;
  //  private Image backgroundImage;
  private ExplosionSpritePool explosionSpritePool;
  private boolean gameInProgress = false;
  private BatGameOverPanel gameOverPanel = new BatGameOverPanel(this);
  private BatPausedPanel pausedPanel = new BatPausedPanel(this);
  private BatmanPlayer[] player;
  private PlayerSpritePool playerSpritePool;
  private BatLabel[] playerText;
  private SpritePool robot1SpritePool;
  private SpritePool robot2SpritePool;
  private SpritePool robot3SpritePool;
  private SpritePool robot4SpritePool;
  private SoundController soundController;

  private BatSuspendedPanel suspendedPanel = new BatSuspendedPanel(this);

  public BatGame() {
    addStyleName("batblast");
  }

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

  @Override
  public SoundController getSoundController() {
    return soundController;
  }

  @Override
  public void playerDied(Player player) {
    updatePlayerText();
  }

  @Override
  public State setState(State state) {
    // TODO panels should listen for state changes instead of this mess 

    // cleanup old state
    State oldState = super.setState(state);
    if (oldState == State.STATE_GAME_OVER) {
      gameOverPanel.removeFromParent();
    } else if (oldState == State.STATE_PAUSED_BY_USER) {
      pausedPanel.removeFromParent();
    } else if (oldState == State.STATE_SUSPENDED) {
      suspendedPanel.removeFromParent();
    }

    // setup new state
    if (state == State.STATE_GAME_OVER) {
      overlay.add(gameOverPanel);
      gameInProgress = false;
    } else if (state == State.STATE_PAUSED_BY_USER) {
      overlay.add(pausedPanel);
    } else if (state == State.STATE_SUSPENDED) {
      overlay.add(suspendedPanel);
    } else if (state == State.STATE_PLAYING && !gameInProgress) {
      for (int i = 0; i < MAX_PLAYERS; i++) {
        player[i].reset();
      }
      updatePlayerText();
      gameInProgress = true;
    }
    return oldState;
  }

  @Override
  public void updatePlayerText() {
    int spacing = MAX_PLAYERS != 1 ? getPlayfieldWidth() / (MAX_PLAYERS - 1) : 0;
    int middle = getPlayfieldWidth() / 2;
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int lives = player[i].getLives();
      playerText[i].setHTML(player[i].getPlayerNumber() + "UP: " + lives + " "
          + (lives == 1 ? "Life" : "Lives") + " / " + player[i].getScore() + " points");
      int targetX = i * spacing;
      int x = targetX < middle ? targetX : targetX - playerText[i].getOffsetWidth();
      getPlayfield().setWidgetPosition(playerText[i], x, 10);
    }
  }

  @Override
  protected void onLoad() {
    super.onLoad();
    soundController = new SoundController();
    soundController.setDefaultVolume(60);

    robot1SpritePool = new MrFreezeSpritePool(this);
    robot2SpritePool = new BaineSpritePool(this);
    robot3SpritePool = new KillerCrocSpritePool(this);
    robot4SpritePool = new PenguinSpritePool(this);
    explosionSpritePool = new ExplosionSpritePool(this);

    playerSpritePool = new PlayerSpritePool(this, MAX_PLAYERS);

    player = new BatmanPlayer[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int playerNumber = i + 1;
      player[i] = new BatmanPlayer(this, playerNumber, playerSpritePool, MAX_LIVES);
    }

    new PlayerRobotCollisionDetector(this, playerSpritePool, robot1SpritePool, explosionSpritePool);
    new PlayerRobotCollisionDetector(this, playerSpritePool, robot2SpritePool, explosionSpritePool);
    new PlayerRobotCollisionDetector(this, playerSpritePool, robot3SpritePool, explosionSpritePool);
    new PlayerRobotCollisionDetector(this, playerSpritePool, robot4SpritePool, explosionSpritePool);

    initPlayerText();

    input.registerKey(' ');
    input.registerKey('P');
    input.registerKey(KeyCodes.KEY_UP);
    input.registerKey(KeyCodes.KEY_LEFT);
    input.registerKey(KeyCodes.KEY_RIGHT);
    input.registerKey(KeyCodes.KEY_DOWN);

    setState(State.STATE_PAUSED_BY_USER);
  }

  private void initPlayerText() {
    playerText = new BatLabel[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      playerText[i] = new BatLabel();
      playerText[i].addStyleName("playerText");
      getPlayfield().add(playerText[i], -500, -500);
    }
    updatePlayerText();
  }
}
