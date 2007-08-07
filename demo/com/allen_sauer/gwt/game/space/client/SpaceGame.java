package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.space.client.collision.PlayerRobotCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.explosion.ExplosionSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.player.Player;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSprite;
import com.allen_sauer.gwt.game.space.client.sprite.player.PlayerSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.robot.RobotSpritePool;

public class SpaceGame implements Game {
  private static final int MAX_LIVES = 3;
  private static final int MAX_PLAYERS = 3;
  private Image backgroundImage;
  private ExplosionSpritePool explosionSpritePool;
  private Player[] player;
  private PlayerRobotCollisionDetector playerRobotCollisionDetector;
  private PlayerSpritePool playerSpritePool;
  private Label[] playerText;
  private RobotSpritePool robotSpritePool;

  public void clientResized(int clientWidth, int clientHeight) {
    //    backgroundImage.setPixelSize(clientWidth, clientHeight);
  }

  public ExplosionSpritePool getExplosionSpritePool() {
    return explosionSpritePool;
  }

  public RobotSpritePool getRobotSpritePool() {
    return robotSpritePool;
  }

  public void init() {
    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    //    backgroundImage.setPixelSize(Engine.getClientWidth(), Engine.getClientHeight());
    RootPanel.get().add(backgroundImage, 0, 0);

    final HTML timerText = new HTML("");
    timerText.addStyleName("timerText");
    RootPanel.get().add(timerText, 200, 0);

    robotSpritePool = new RobotSpritePool(this);
    explosionSpritePool = new ExplosionSpritePool(this);

    playerSpritePool = new PlayerSpritePool(this, MAX_PLAYERS);

    player = new Player[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      int playerNumber = i + 1;
      player[i] = new Player(this, playerNumber, (PlayerSprite) playerSpritePool.create(),
          MAX_LIVES);
    }
    playerRobotCollisionDetector = new PlayerRobotCollisionDetector(playerSpritePool,
        robotSpritePool, explosionSpritePool);

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
    int spacing = Engine.getClientWidth() / (MAX_PLAYERS - 1);
    int middle = Engine.getClientWidth() / 2;
    for (int i = 0; i < MAX_PLAYERS; i++) {
      playerText[i].setText(player[i].getPlayerNumber() + "UP: " + player[i].getLives());
      int targetX = i * spacing;
      int x = targetX < middle ? targetX : targetX - playerText[i].getOffsetWidth();
      Engine.playfield.setWidgetPosition(playerText[i], x, 10);
    }
  }
}
