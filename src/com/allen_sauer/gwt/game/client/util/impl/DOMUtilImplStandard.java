/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.util.impl;

import com.google.gwt.user.client.Element;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} implementation for
 * standard browsers.
 */
public abstract class DOMUtilImplStandard extends DOMUtilImpl {
  @Override
  public native void cancelAllDocumentSelections()
  /*-{
    try {
      $wnd.getSelection().removeAllRanges();
    } catch(e) { throw new Error("unselect exception:\n" + e); }
  }-*/;

  @Override
  public native int getBorderLeft(Element elem)
  /*-{
    try {
      var computedStyle = $doc.defaultView.getComputedStyle(elem, null);
      var borderLeftWidth = computedStyle.getPropertyValue("border-left-width");
      return borderLeftWidth.indexOf("px") == -1 ? 0 : parseInt(borderLeftWidth.substr(0, borderLeftWidth.length - 2));
    } catch(e) { throw new Error("getBorderLeft exception:\n" + e); }
  }-*/;

  @Override
  public native int getBorderTop(Element elem)
  /*-{
    try {
      var computedStyle = $doc.defaultView.getComputedStyle(elem, null);
      var borderTopWidth = computedStyle.getPropertyValue("border-top-width");
      return borderTopWidth.indexOf("px") == -1 ? 0 : parseInt(borderTopWidth.substr(0, borderTopWidth.length - 2));
    } catch(e) { throw new Error("getBorderTop: " + e); }
  }-*/;

  @Override
  public native int getClientHeight(Element elem)
  /*-{
    try {
      return elem.clientHeight;
    } catch(e) { throw new Error("getClientHeight exception:\n" + e); }
  }-*/;

  @Override
  public native int getClientWidth(Element elem)
  /*-{
    try {
      return elem.clientWidth;
    } catch(e) { throw new Error("getClientWidth exception:\n" + e); }
  }-*/;
}
