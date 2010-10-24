/**
 * Copyright 2009 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.util.impl;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} default
 * cross-browser implementation.
 */
public abstract class DOMUtilImpl {
  public boolean allowFocusChangeCurrentTarget(NativeEvent nativeEvent) {
    return true;
  }

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#cancelAllDocumentSelections()
   */
  public native void cancelAllDocumentSelections()
  /*-{
    $doc.selection.empty();
  }-*/;

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getBorderLeft(Element)
   */
  public abstract int getBorderLeft(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getBorderTop(Element)
   */
  public abstract int getBorderTop(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getClientHeight(Element)
   */
  public abstract int getClientHeight(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getClientWidth(Element)
   */
  public abstract int getClientWidth(Element elem);

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getHorizontalBorders(Widget)
   */
  public final int getHorizontalBorders(Widget widget) {
    return widget.getOffsetWidth() - getClientWidth(widget.getElement());
  }

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getNodeName(Element)
   */
  public final native String getNodeName(Element elem)
  /*-{
    return elem.nodeName;
  }-*/;

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#getVerticalBorders(Widget)
   */
  public final int getVerticalBorders(Widget widget) {
    return widget.getOffsetHeight() - getClientHeight(widget.getElement());
  }

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#isOrContains(Element, Element)
   */
  public native boolean isOrContains(Element parent, Element child)
  /*-{
    return parent.contains(child);
  }-*/;

  public native boolean isWnd(Element elem)
  /*-{
    return elem === $wnd;
  }-*/;

  /**
   * @see com.allen_sauer.gwt.dragdrop.client.util.DOMUtil#setStatus(String)
   */
  public final native void setStatus(String text)
  /*-{
    $wnd.status = text;
  }-*/;
}
