package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.space.client.collision.PlayerCollisionDetector;
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
  private PlayerCollisionDetector playerCollisionDetector;
  private Player[] players;
  private PlayerSpritePool playerSpritePool;
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
    playerCollisionDetector = new PlayerCollisionDetector(playerSpritePool, robotSpritePool,
        explosionSpritePool);

    players = new Player[MAX_PLAYERS];
    for (int i = 0; i < MAX_PLAYERS; i++) {
      players[i] = new Player(this, (PlayerSprite) playerSpritePool.create(), MAX_LIVES);
    }
  }
}
