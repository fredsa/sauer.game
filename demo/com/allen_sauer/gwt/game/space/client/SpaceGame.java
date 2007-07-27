package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.generator.KeyboardBulletGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class SpaceGame implements Game {
  private static final int MAX_BULLETS = 5;
  private static final int MAX_ROBOTS = 10;
  private static final double ROBOT_APPEARANCE_PROBABILITY = .05;
  private Image backgroundImage;
  private SpritePool bulletSpritePool;
  private SpritePool playerSpritePool;
  private SpritePool robotSpritePool;

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

    initPlayerFactoryAndPool();
    Sprite playerSprite = playerSpritePool.create();
    initBulletFactoryAndPool(playerSprite);
    initRobotFactoryAndPool();

    new CollisionDetector(bulletSpritePool, robotSpritePool);
    Engine.addFrameListener(this);
  }

  private void initBulletFactoryAndPool(final Sprite playerSprite) {
    SpriteFactory factory = new SpriteFactory() {
      public Sprite create() {
        return new BulletSprite(SpaceGame.this, playerSprite);
      }
    };

    bulletSpritePool = new SpritePool(factory, MAX_BULLETS);
    Engine.addSpritePool(bulletSpritePool);
    new KeyboardBulletGenerator(bulletSpritePool);
  }

  private void initPlayerFactoryAndPool() {
    SpriteFactory factory = new SpriteFactory() {
      public Sprite create() {
        return new SpaceShuttleSprite(SpaceGame.this);
      }
    };

    playerSpritePool = new SpritePool(factory, 1);
    Engine.addSpritePool(playerSpritePool);
    new IntervalGenerator(playerSpritePool, 1);
  }

  private void initRobotFactoryAndPool() {
    SpriteFactory factory = new SpriteFactory() {
      public Sprite create() {
        return new RobotSprite(SpaceGame.this);
      }
    };

    robotSpritePool = new SpritePool(factory, MAX_ROBOTS);
    Engine.addSpritePool(robotSpritePool);
    new IntervalGenerator(robotSpritePool, ROBOT_APPEARANCE_PROBABILITY);
  }
}
