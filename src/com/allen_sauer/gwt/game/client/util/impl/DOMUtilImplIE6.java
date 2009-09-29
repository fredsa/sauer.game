/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.util.impl;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} implementation for
 * IE.
 */
public class DOMUtilImplIE6 extends DOMUtilImpl {
  @Override
  public boolean allowFocusChangeCurrentTarget(Event event) {
    return !isWnd(DOM.eventGetCurrentTarget(event));
  }

  @Override
  public native void cancelAllDocumentSelections()
  /*-{
    $doc.selection.empty();
  }-*/;

  @Override
  public native int getBorderLeft(Element elem)
  /*-{
    return elem.clientLeft;
  }-*/;

  @Override
  public native int getBorderTop(Element elem)
  /*-{
    return elem.clientTop;
  }-*/;

  @Override
  public native int getClientHeight(Element elem)
  /*-{
    return elem.clientHeight;
  }-*/;

  @Override
  public native int getClientWidth(Element elem)
  /*-{
    return elem.clientWidth;
  }-*/;

  @Override
  public native boolean isWnd(Element elem)
  /*-{
    return elem == $wnd;
  }-*/;
}
