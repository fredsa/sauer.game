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
  public void onModuleLoad() {
    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        //Log.debug("resize event");
        handleClientResized(width, height);
      }
    });

    handleClientResized(Window.getClientWidth(), Window.getClientHeight());
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }

  protected void handleClientResized(int clientWidth, int clientHeight) {
    //    Log.debug("handleClientResized(" + clientWidth + ", " + clientHeight + ")");
  }
}
