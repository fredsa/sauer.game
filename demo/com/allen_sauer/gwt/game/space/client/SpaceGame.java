/*
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.game.client.Engine;
import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.Sprite;

public class SpaceGame implements Game {
  private static final int FRAMES = 6;
  private static final int SPRITE_FRAME_HEIGHT = 90;
  private static final int SPRITE_FRAME_WIDTH = 65;

  private Image backgroundImage;
  private int playfieldHeight;
  private int playfieldWidth;
  private Sprite robot[] = new Sprite[20];

  public void doFrame() {
    for (int i = 0; i < robot.length; i++) {
      robot[i].doMove();
    }
  }

  public int getPlayfieldHeight() {
    return playfieldHeight;
  }

  public int getPlayfieldWidth() {
    return playfieldWidth;
  }

  public void handleClientResized(int clientWidth, int clientHeight) {
    playfieldWidth = clientWidth;
    playfieldHeight = clientHeight;
    backgroundImage.setPixelSize(clientWidth, clientHeight);
  }

  public void init(Engine engine) {
    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    RootPanel.get().add(backgroundImage);

    final HTML timerText = new HTML("");
    timerText.addStyleName("timerText");
    RootPanel.get().add(timerText, 200, 0);

    for (int i = 0; i < robot.length; i++) {
      robot[i] = newSprite();
      RootPanel.get().add(robot[i]);
      for (int j = 0; j < i; j++) {
        robot[i].doMove();
      }
    }
  }

  private Sprite newSprite() {
    return new Sprite(this, "images/robots-03-map-tr.gif", FRAMES, SPRITE_FRAME_WIDTH, SPRITE_FRAME_HEIGHT);
  }
}
