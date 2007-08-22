package com.allen_sauer.gwt.game.client.ui.util;

import com.google.gwt.user.client.Element;

public final class FastDOM {
  public static final native void setElementPosition(Element elem, int x, int y)
  /*-{
    elem.style.left = x + "px";
    elem.style.top = y + "px";
  }-*/;

  public static final native void setElementX(Element elem, int x)
  /*-{
    elem.style.left = x + "px";
  }-*/;

  public static final native void setWindowStatus(String status)
  /*-{
    $wnd.status = status;
  }-*/;

  private FastDOM() {
  }
}
