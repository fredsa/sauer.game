/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import com.google.gwt.core.client.Duration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;

public final class GameTimer extends Timer {
  private static final int FRAMES_TO_AVERAGE = 10;
  private static final int TIMER_INTERVAL_MILLIS = 1;
  private int count = 0;
  private final Game game;
  private double lastTimestamp;
  private boolean paused = true;
  private final HTML timerText = new HTML();
  private double frameToFrame;

  public GameTimer(Game game) {
    this.game = game;
    timerText.addStyleName("timerText");
    scheduleRepeating(TIMER_INTERVAL_MILLIS);
  }

  public boolean isPaused() {
    return paused;
  }

  public void measure() {
    if (++count == FRAMES_TO_AVERAGE) {
      double timestamp = Duration.currentTimeMillis();
      count = 0;
      if (lastTimestamp != 0) {
        frameToFrame = (timestamp - lastTimestamp) / FRAMES_TO_AVERAGE;
        int frameRate = Math.round(1000F / (float) frameToFrame);
        timerText.setHTML(FRAMES_TO_AVERAGE + " frame avg = " + frameToFrame + "ms (" + frameRate
            + "fps)");
      }
      lastTimestamp = timestamp;
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
    game.playfield.add(timerText);
    lastTimestamp = 0;
  }
}