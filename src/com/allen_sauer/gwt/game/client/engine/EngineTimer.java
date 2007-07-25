package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

final class EngineTimer extends Timer {
  private static final int FRAMES_TO_AVERAGE = 10;
  private int count = 0;
  private long lastTimestamp;
  private boolean paused = true;
  private HTML timerText = new HTML();

  EngineTimer() {
    DOM.setStyleAttribute(timerText.getElement(), "color", "#ABCDEF");
  }

  public boolean isPaused() {
    return paused;
  }

  public void measure() {
    if (++count == FRAMES_TO_AVERAGE) {
      long timestamp = System.currentTimeMillis();
      count = 0;
      if (lastTimestamp != 0) {
        long frameToFrame = (timestamp - lastTimestamp) / FRAMES_TO_AVERAGE;
        long frameRate = Math.round(1000D / frameToFrame);
        timerText.setHTML(FRAMES_TO_AVERAGE + " buffer avg = " + frameToFrame + "ms (" + frameRate + "fps), " + Engine.info());
      }
      lastTimestamp = timestamp;
    }
  }

  public void run() {
    measure();
    Engine.doFrame();
  }

  public void schedule(int delayMillis) {
    initMeasurements();
    super.schedule(delayMillis);
  }

  public void scheduleRepeating(int periodMillis) {
    initMeasurements();
    super.scheduleRepeating(periodMillis);
  }

  public void setPaused(boolean paused) {
    if (paused) {
      cancel();
    } else if (this.paused) {
      scheduleRepeating(100);
    }
    this.paused = paused;
  }

  private void initMeasurements() {
    timerText.setHTML("");
    RootPanel.get().add(timerText, 100, 10);
    lastTimestamp = 0;
  }
}