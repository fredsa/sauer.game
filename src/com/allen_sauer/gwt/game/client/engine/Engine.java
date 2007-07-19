package com.allen_sauer.gwt.game.client.engine;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import com.allen_sauer.gwt.game.client.Game;
import com.allen_sauer.gwt.game.client.sprite.SpritePool;
import com.allen_sauer.gwt.log.client.Log;

import java.util.ArrayList;
import java.util.Iterator;

public class Engine {
  private static int clientHeight;
  private static int clientWidth;
  private static EngineTimer engineTimer;
  private static final ArrayList frameListeners = new ArrayList();
  private static Game game;
  private static boolean[] keyDown = new boolean[0xff];
  private static ArrayList newFrameListeners = new ArrayList();
  private static ArrayList spritePools = new ArrayList();

  public static void addFrameListener(FrameListener listener) {
    newFrameListeners.add(listener);
  }

  public static void addSpritePool(SpritePool pool) {
    spritePools.add(pool);
  }

  public static void doFrame() {
    // Avoid ConcurrentModificationException
    if (!newFrameListeners.isEmpty()) {
      frameListeners.addAll(newFrameListeners);
      newFrameListeners = new ArrayList();
    }

    game.doFrame();
    for (Iterator iterator = frameListeners.iterator(); iterator.hasNext();) {
      FrameListener listener = (FrameListener) iterator.next();
      listener.doFrame();
    }
  }

  public static int getClientHeight() {
    return clientHeight;
  }

  public static int getClientWidth() {
    return clientWidth;
  }
  
  public static void init(Game game) {
    Engine.game = game;
    setClientSize(Window.getClientWidth(), Window.getClientHeight());
    game.init();

    Window.addWindowResizeListener(new WindowResizeListener() {
      public void onWindowResized(int width, int height) {
        setClientSize(width, height);
        Engine.game.clientResized(width, height);
      }
    });

    engineTimer = new EngineTimer();
    engineTimer.setPaused(false);
    EnginePauseButton enginePauseButton = new EnginePauseButton(engineTimer);
    RootPanel.get().add(enginePauseButton, 10, 40);
    //    FocusImpl.getFocusImplForPanel().focus(RootPanel.getBodyElement());
    //    enginePauseButton.setFocus(true);

    FocusPanel focusPanel = new FocusPanel();
    RootPanel.get().add(focusPanel,0,0);
    focusPanel.setFocus(true);
    DOM.setStyleAttribute(focusPanel.getElement(), "border", "1px solid green");
    focusPanel.setPixelSize(50, 50);
    KeyboardListenerAdapter keyboardListener = new KeyboardListenerAdapter() {
      public void onKeyDown(Widget sender, char keyCode, int modifiers) {
        keyDown[keyCode & 0xff] = true;
        Log.debug(keyCode + " down");
      }

      public void onKeyPress(Widget sender, char keyCode, int modifiers) {
        Log.debug(keyCode + " pre");
      }

      public void onKeyUp(Widget sender, char keyCode, int modifiers) {
        keyDown[keyCode & 0xff] = false;
        Log.debug(keyCode + " up");
      }
    };
    focusPanel.addKeyboardListener(keyboardListener);
  }

  public static boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }

  private static void clientResized(int clientWidth, int clientHeight) {
    setClientSize(clientWidth, clientHeight);
    game.clientResized(clientWidth, clientHeight);
  }

  private static void setClientSize(int clientWidth, int clientHeight) {
    Engine.clientWidth = clientWidth;
    Engine.clientHeight = clientHeight;
    assert Window.getClientWidth() != 0;
    assert Window.getClientHeight() != 0;
  }

  private Engine() {
  }
}
