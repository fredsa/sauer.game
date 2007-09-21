package com.allen_sauer.gwt.game.client.ui.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowCloseListener;
import com.google.gwt.user.client.ui.KeyboardListener;

import com.allen_sauer.gwt.game.client.engine.Engine;

public final class Page {
  public static interface CODES extends KeyboardListener {
  }

  private static final class PageHooks implements WindowCloseListener {
    public native void onWindowClosed()
    /*-{
      $doc.onkeydown = null;
      $doc.onkeyup = null;
      
      $wnd.onfocus = null;
      $wnd.onblur = null;
    }-*/;

    public String onWindowClosing() {
      return null;
    }

    private native void init()
    /*-{
      $doc.onkeydown = function(evt) {
        @com.allen_sauer.gwt.game.client.ui.util.Page::onKeyDown(Lcom/google/gwt/user/client/Event;)(evt || $wnd.event);
      }
      $doc.onkeyup = function(evt) {
        @com.allen_sauer.gwt.game.client.ui.util.Page::onKeyUp(Lcom/google/gwt/user/client/Event;)(evt || $wnd.event);
      }
      
      $wnd.onfocus = function() {
        @com.allen_sauer.gwt.game.client.ui.util.Page::onWindowFocus()();
      }
      $wnd.onblur = function() {
        @com.allen_sauer.gwt.game.client.ui.util.Page::onWindowLostFocus()();
      }
      // try to force focus
      $wnd.focus();
      
      // simulate focus event for browsers not firing an initial focus event
      @com.allen_sauer.gwt.game.client.ui.util.Page::onWindowFocus()();
    }-*/;
  }

  private static boolean hasFocus = false;
  private static boolean[] keyDown = new boolean[0xff];

  static {
    PageHooks pageHooks = new PageHooks();
    Window.addWindowCloseListener(pageHooks);
    pageHooks.init();
  }

  static {
    surpressCompilerWarningHack();
  }

  public static void clearKeyDown(int keyCode) {
    keyDown[keyCode & 0xff] = false;
  }

  /**
   * Used by Engine to force static initialization of this class.
   */
  public static void forceStaticInit() {
  };

  public static boolean isKeyDown(int keyCode) {
    return keyDown[keyCode & 0xff];
  }

  private static void onKeyDown(Event event) {
    char keyCode = (char) DOM.eventGetKeyCode(event);
    // Log.debug(keyCode + " down");
    keyDown[keyCode & 0xff] = true;
    if (keyCode == 'P') {
      Engine.setPaused(!Engine.isPaused());
    }
  }

  private static void onKeyUp(Event event) {
    char keyCode = (char) DOM.eventGetKeyCode(event);
    // Log.debug(keyCode + " up");
    keyDown[keyCode & 0xff] = false;
  }

  private static void onWindowFocus() {
    if (!hasFocus) {
      hasFocus = true;
      // Log.debug("onWindowFocus()");
      Engine.setPaused(false);
    }
  }

  private static void onWindowLostFocus() {
    if (hasFocus) {
      hasFocus = false;
      // Log.debug("onWindowLostFocus()");
      Engine.setPaused(true);
    }
  }

  /**
   * This code will get optimized out at compile time until we can use
   * 
   * @SuppressWarnings("unused").
   */
  private static void surpressCompilerWarningHack() {
    if (GWT.isScript()) {
      if (!GWT.isScript()) {
        onKeyDown(null);
        onKeyUp(null);
        onWindowFocus();
        onWindowLostFocus();
      }
    }
  }

  /**
   * Prevent instantiation.
   */
  private Page() {
  }
}
