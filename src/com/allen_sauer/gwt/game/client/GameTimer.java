/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.core.client.Duration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;

public final class GameTimer extends Timer {
  private static int framesToAverage = 2;
  private static int framesToAverage2 = 1;
  private static final int TIMER_INTERVAL_MILLIS = 1;
  private int count = 0;
  private int count2 = 0;
  private float frameToFrame;
  private float frameToFrame2;
  private final Game game;
  private double lastTimestamp;
  private boolean paused = true;
  private final HTML timerText = new HTML();

  public GameTimer(Game game) {
    this.game = game;
    timerText.addStyleName("timerText");
    scheduleRepeating(TIMER_INTERVAL_MILLIS);
  }

  public boolean isPaused() {
    return paused;
  }

  public void measure() {
    if (++count == framesToAverage) {
      double timestamp = Duration.currentTimeMillis();
      double elapsed = timestamp - lastTimestamp;
      if (elapsed < 5) {
        framesToAverage++;
      } else {
        count = 0;
        if (lastTimestamp != 0) {
          frameToFrame = (float) (elapsed / framesToAverage);
          if (++count2 == framesToAverage2) {
            if (frameToFrame2 < 300) {
              framesToAverage2++;
            } else {
              count2 = 0;
              int frameRate = Math.round(1000F / frameToFrame2 * framesToAverage2);
              timerText.setHTML(framesToAverage * framesToAverage2 + " frames rendered in "
                  + Math.round(frameToFrame2) + "ms (" + frameRate + "fps)");
              frameToFrame2 = 0;
            }
          }
          frameToFrame2 += frameToFrame;
        }
        lastTimestamp = timestamp;
      }
    }
  }

  @Override
  public void run() {
    measure();
    game.getCurrentStateFrameListenerCollection().doFrame(frameToFrame);
  }

  @Override
  public void schedule(int delayMillis) {
    initMeasurements();
    super.schedule(delayMillis);
  }

  @Override
  public void scheduleRepeating(int periodMillis) {
    initMeasurements();
    super.scheduleRepeating(periodMillis);
  }

  public void setPaused(boolean paused) {
    if (paused) {
      cancel();
    } else if (this.paused) {
      scheduleRepeating(TIMER_INTERVAL_MILLIS);
    }
    this.paused = paused;
  }

  private void initMeasurements() {
    timerText.setHTML("");
    game.getPlayfield().add(timerText);
    lastTimestamp = 0;
  }
}