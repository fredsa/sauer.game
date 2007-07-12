package com.allen_sauer.gwt.game.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import com.allen_sauer.gwt.log.client.Log;

public class Game implements EntryPoint {
  private Image backgroundImage;
  private int clientWidth;
  private int clientHeight;

  public void onModuleLoad() {
    backgroundImage = new Image("images/nebula_13-fudged.jpg");
    backgroundImage.addStyleName("backgroundImage");
    RootPanel.get().add(backgroundImage);
    initClientSize();

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        //Log.debug("resize event");
        handleClientResized(width, height);
      }
    });

    handleClientResized(clientWidth, clientHeight);
  }

  private void initClientSize() {
    clientWidth = Window.getClientWidth();
    clientHeight = Window.getClientHeight();
    if (clientWidth == 0 || clientHeight == 0) {
      //      Log.warn("Window.getClientWidth()=" + clientWidth + ", Window.getClientHeight()=" + clientHeight);
      clientWidth = 100;
      clientHeight = 100;
    }
  }

  private void handleClientResized(int width, int height) {
    //    Log.debug("handleClientResized(" + width + ", " + height + ")");
    clientWidth = width;
    clientHeight = height;
    backgroundImage.setPixelSize(width, height);
  }
}
