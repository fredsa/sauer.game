package com.allen_sauer.gwt.game.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class Game implements EntryPoint {
  private int clientWidth;
  private int clientHeight;
  private Timer timer;
  private boolean paused = true;

  public void onModuleLoad() {
    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        clientResized(width, height);
      }
    });

    clientResized(Window.getClientWidth(), Window.getClientHeight());

    timer = new Timer() {
      public void run() {
        doFrame();
      }
    };

    RootPanel.get().add(new GamePauseButton(this), 10, 40);
  }

  private void clientResized(int clientWidth, int clientHeight) {
    this.clientWidth = clientWidth;
    this.clientHeight = clientHeight;
    handleClientResized(clientWidth, clientHeight);
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }

  protected abstract void doFrame();

  protected abstract void handleClientResized(int clientWidth, int clientHeight);

  public int getClientWidth() {
    return clientWidth;
  }

  public int getClientHeight() {
    return clientHeight;
  }

  public boolean isPaused() {
    return paused;
  }

  public void setPaused(boolean paused) {
    if (paused) {
      timer.cancel();
    } else if (this.paused) {
      timer.scheduleRepeating(1);
    }
    this.paused = paused;
  }
}
