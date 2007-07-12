package com.allen_sauer.gwt.game.space.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.Sprite;

public class Space extends Game {
  private static final int FRAMES = 6;
  private static final int FRAMES_TO_AVERAGE = 50;
  private static final int SPRITE_COUNT = 20;
  private static final int SPRITE_FRAME_HEIGHT = 90;
  private static final int SPRITE_FRAME_WIDTH = 65;
  private static final int SUBDIVISIONS = 50;
  private static final double TWO_PI = 2 * Math.PI;

  private Image backgroundImage;
  private boolean running = false;
  private int[] sine;
  private Sprite[] sprites;
  private int[] spriteX;
  private Timer timer;
  private Sprite robot;

  public Space() {
    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    RootPanel.get().add(backgroundImage);

    sprites = new Sprite[SPRITE_COUNT];
    spriteX = new int[SPRITE_COUNT];
    for (int i = 0; i < sprites.length; i++) {
      sprites[i] = newSprite();
    }

    sine = new int[SUBDIVISIONS];

    final HTML timerText = new HTML("");
    timerText.addStyleName("timerText");
    RootPanel.get().add(timerText, 200, 0);

    timer = new Timer() {
      private int count = 0;
      private int i = 0;
      private long lastTimestamp;
      private long maxFrameToFrame;
      private long minFrameRate;

      public void run() {
        long timestamp = System.currentTimeMillis();
        if (++count == FRAMES_TO_AVERAGE) {
          count = 0;
          if (lastTimestamp != 0) {
            long frameToFrame = (timestamp - lastTimestamp) / FRAMES_TO_AVERAGE;
            long frameRate = Math.round(1000D / frameToFrame);
            maxFrameToFrame = Math.max(maxFrameToFrame, frameToFrame);
            minFrameRate = Math.min(minFrameRate, frameRate);
            timerText.setHTML("After " + FRAMES_TO_AVERAGE + " frames, average frame-to-frame time: " + frameToFrame + "ms (max: "
                + maxFrameToFrame + "ms)" + "<br>" + "Frame rate = " + frameRate + "fps" + " (min: " + minFrameRate + "fps)");
          }
          lastTimestamp = timestamp;
        }
        for (int j = 0; j < sprites.length; j++) {
          int index = (i + j) % sine.length;
          int frame = (i + j) % FRAMES;
          RootPanel.get().setWidgetPosition(sprites[j], spriteX[j], sine[index]);
          sprites[j].setFrame(frame);
        }
        if (i < sine.length) {
          i++;
        } else {
          i = 0;
        }
      }

      public void scheduleRepeating(int periodMillis) {
        lastTimestamp = 0;
        maxFrameToFrame = 0;
        minFrameRate = Long.MAX_VALUE;
        super.scheduleRepeating(periodMillis);
      }
    };

    final Button button = new Button("start/stop");
    button.setWidth("5em");
    RootPanel.get().add(button, 10, 10);

    button.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        stopStart();
      }
    });
    robot = newSprite();
    RootPanel.get().add(robot);
  }

  private Sprite newSprite() {
    return new Sprite(this, "images/robots-03-map-tr.gif", FRAMES, SPRITE_FRAME_WIDTH, SPRITE_FRAME_HEIGHT);
  }

  public void stopStart() {
    if (running) {
      timer.cancel();
    } else {
      timer.scheduleRepeating(1);
    }
    running = !running;
  }

  
  protected void doFrame() {
    robot.doMove();
  }

  protected void handleClientResized(int clientWidth, int clientHeight) {
    backgroundImage.setPixelSize(clientWidth, clientHeight);

    for (int i = 0; i < sprites.length; i++) {
      spriteX[i] = Math.round((float) i / (sprites.length - 1) * (clientWidth - SPRITE_FRAME_WIDTH));
      RootPanel.get().add(sprites[i], -500, -500);
    }

    int amplitude = (clientHeight - SPRITE_FRAME_HEIGHT) / 2;
    for (int i = 0; i < sine.length; i++) {
      double radians = TWO_PI / SUBDIVISIONS * i;
      sine[i] = (int) (Math.round(amplitude * Math.sin(radians)) + amplitude);
    }

  }
}
