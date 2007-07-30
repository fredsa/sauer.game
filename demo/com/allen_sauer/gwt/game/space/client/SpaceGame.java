package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.space.client.collision.RobotCollisionDetector;
import com.allen_sauer.gwt.game.space.client.sprite.BulletSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.PlayerSpritePool;
import com.allen_sauer.gwt.game.space.client.sprite.RobotSpritePool;

public class SpaceGame implements Game {
  private Image backgroundImage;
  private BulletSpritePool bulletSpritePool;
  private PlayerSpritePool playerSpritePool;
  private RobotSpritePool robotSpritePool;

  public void clientResized(int clientWidth, int clientHeight) {
    //    backgroundImage.setPixelSize(clientWidth, clientHeight);
  }

  public void doFirstFrame() {
    // TODO Auto-generated method stub
  }

  public void doFrame() {
  }

  public void doLastFrame() {
    // TODO Auto-generated method stub
  }

  public void init() {
    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    //    backgroundImage.setPixelSize(Engine.getClientWidth(), Engine.getClientHeight());
    RootPanel.get().add(backgroundImage, 0, 0);

    final HTML timerText = new HTML("");
    timerText.addStyleName("timerText");
    RootPanel.get().add(timerText, 200, 0);

    playerSpritePool = new PlayerSpritePool(this);

    Sprite playerSprite = playerSpritePool.create();
    bulletSpritePool = new BulletSpritePool(this, playerSprite);

    robotSpritePool = new RobotSpritePool(this);

    new RobotCollisionDetector(robotSpritePool, bulletSpritePool);
    Engine.addFrameListener(this);
  }
}
