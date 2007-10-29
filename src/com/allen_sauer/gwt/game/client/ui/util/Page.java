package com.allen_sauer.gwt.game.client.ui.util;

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
      $wnd.onfocus = null;
    }-*/;

    public String onWindowClosing() {
      return null;
    }

    private native void init()
    /*-{
      $wnd.onfocus = function() {
        @com.allen_sauer.gwt.game.client.ui.util.Page::onWindowFocus()();
      }
    }-*/;
  }

  static {
    PageHooks pageHooks = new PageHooks();
    Window.addWindowCloseListener(pageHooks);
    pageHooks.init();
  }

  /**
   * Used by Engine to force static initialization of this class.
   */
  public static void forceStaticInit() {
  };

  @SuppressWarnings("unused")
  private static void onWindowFocus() {
    //    Log.debug("onWindowFocus() active = " + FastDOM.getActiveElement());
    Engine.playfield.setFocus(true);
  }

  /**
   * Prevent class instantiation.
   */
  private Page() {
  }
}
