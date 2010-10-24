/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.ui.util;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;

public final class Page {
  public static interface CODES {
  }

  private static final class PageHooks implements CloseHandler<Window> {
    public native void onClose(CloseEvent<Window> event)
    /*-{
      // clean up function reference
      $wnd.onfocus = null;
    }-*/;

    private native void init()
    /*-{
      $wnd.onfocus = function() {
        @com.allen_sauer.gwt.game.client.ui.util.Page::onWindowFocus()();
      }
    }-*/;

  }

  static {
    PageHooks pageHooks = new PageHooks();
    Window.addCloseHandler(pageHooks);
    pageHooks.init();
  }

  /**
   * Used by Engine to force static initialization of this class.
   */
  public static void forceStaticInit() {
  };

  @SuppressWarnings("unused")
  private static void onWindowFocus() {
    //    Log.debug("onWindowFocus()");
    //    Game.setFocus(true);
  }

  /**
   * Prevent class instantiation.
   */
  private Page() {
  }
}
