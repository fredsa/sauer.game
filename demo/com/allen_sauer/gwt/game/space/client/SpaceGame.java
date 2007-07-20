package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.engine.Engine;
import com.allen_sauer.gwt.game.client.generator.IntervalGenerator;
import com.allen_sauer.gwt.game.client.sprite.Sprite;
import com.allen_sauer.gwt.game.client.sprite.SpriteFactory;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;

public class SpaceGame implements Game {
  private static final int MAX_ROBOTS = 10;
  private static final double ROBOT_APPEARANCE_PROBABILITY = 5;
  private Image backgroundImage;

  public void clientResized(int clientWidth, int clientHeight) {
    backgroundImage.setPixelSize(clientWidth, clientHeight);
  }

  public void doFrame() {
  }

  public void init() {
    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    backgroundImage.setPixelSize(Engine.getClientWidth(), Engine.getClientHeight());
    RootPanel.get().add(backgroundImage);

    final HTML timerText = new HTML("");
    timerText.addStyleName("timerText");
    RootPanel.get().add(timerText, 200, 0);

    initPlayerFactory();
    initRobotFactory();
  }

  private void initPlayerFactory() {
    SpriteFactory factory = new SpriteFactory() {
      public Sprite create() {
        return new SpaceShuttleSprite(SpaceGame.this);
      }
    };

    SpritePool pool = new SpritePool(factory, 1);
    Engine.addSpritePool(pool);
    SpaceShuttleSprite spaceShuttleSprite = new SpaceShuttleSprite(this);
    new IntervalGenerator(pool, 1);
  }

  private void initRobotFactory() {
    SpriteFactory factory = new SpriteFactory() {
      public Sprite create() {
        return new RobotSprite(SpaceGame.this);
      }
    };

    SpritePool pool = new SpritePool(factory, MAX_ROBOTS);
    Engine.addSpritePool(pool);
    new IntervalGenerator(pool, ROBOT_APPEARANCE_PROBABILITY);
  }
}
