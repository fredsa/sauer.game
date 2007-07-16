package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Engine;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.IntervalGenerator;
import com.allen_sauer.gwt.game.client.Sprite;
import com.allen_sauer.gwt.game.client.SpriteFactory;
import com.allen_sauer.gwt.game.client.SpritePool;

public class SpaceGame implements Game {
  private static final int MAX_ROBOTS = 10;
  private static final double ROBOT_APPEARANCE_PROBABILITY = .1;
  private Image backgroundImage;
  private IntervalGenerator intervalGenerator;

  public void clientResized(int clientWidth, int clientHeight) {
    backgroundImage.setPixelSize(clientWidth, clientHeight);
  }

  public void doFrame() {
    intervalGenerator.doFrame();
  }

  public void init() {
    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    RootPanel.get().add(backgroundImage);

    final HTML timerText = new HTML("");
    timerText.addStyleName("timerText");
    RootPanel.get().add(timerText, 200, 0);

    SpriteFactory spriteFactory = new SpriteFactory() {
      public Sprite create() {
        return new RobotSprite(SpaceGame.this);
      }
    };
    SpritePool spritePool = new SpritePool(spriteFactory, MAX_ROBOTS);
    Engine.addSpritePool(spritePool);
    intervalGenerator = new IntervalGenerator(spritePool, ROBOT_APPEARANCE_PROBABILITY);
  }
}
