/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.util.impl;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Element;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} implementation for
 * IE.
 */
public class DOMUtilImplIE6 extends DOMUtilImpl {
  @Override
  public boolean allowFocusChangeCurrentTarget(NativeEvent nativeEvent) {
    return !isWnd((Element) nativeEvent.getCurrentEventTarget().cast());
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
