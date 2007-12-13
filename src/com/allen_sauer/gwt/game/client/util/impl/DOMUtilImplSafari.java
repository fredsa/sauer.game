/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client.util.impl;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * {@link com.allen_sauer.gwt.dragdrop.client.util.DOMUtil} implementation for
 * Webkit/Safari.
 */
public class DOMUtilImplSafari extends DOMUtilImplStandard {
  @Override
  public native void cancelAllDocumentSelections()
  /*-{
    try {
      $wnd.getSelection().collapse();
    } catch(e) { throw new Error("unselect exception:\n" + e); }
  }-*/;

  @Override
  public native int getBorderLeft(Element elem)
  /*-{
    var computedStyle = $doc.defaultView.getComputedStyle(elem, null);
    if (computedStyle != null) {
      var borderLeftWidth = computedStyle.getPropertyValue("border-left-width");
      return borderLeftWidth.indexOf("px") == -1 ? 0 : parseInt(borderLeftWidth.substr(0, borderLeftWidth.length - 2));
    } else {
      // When elem is hidden
      return 0;
    }
  }-*/;

  @Override
  public native int getBorderTop(Element elem)
  /*-{
    var computedStyle = $doc.defaultView.getComputedStyle(elem, null);
    if (computedStyle != null) {
      var borderTopWidth = computedStyle.getPropertyValue("border-top-width");
      return borderTopWidth.indexOf("px") == -1 ? 0 : parseInt(borderTopWidth.substr(0, borderTopWidth.length - 2));
    } else {
      // When elem is hidden
      return 0;
    }
  }-*/;

  @Override
  public native int getClientHeight(Element elem)
  /*-{
    return elem.clientHeight || 0;
  }-*/;

  @Override
  public native int getClientWidth(Element elem)
  /*-{
    return elem.clientWidth || 0;
  }-*/;

  @Override
  public boolean isOrContains(Element parent, Element child) {
    // While Safari 1.3.2 / Safari 2.0.4 support the 'contains' method on DOM
    // elements, the method does not appear to return valid results in all cases.
    // Revert to a DOM walk from DOM.isOrHasChild instead.
    return DOM.isOrHasChild(parent, child);
  }

  @Override
  public native boolean isWnd(Element elem)
  /*-{
    return elem == $wnd;
  }-*/;
}
