/**
 * Copyright 2007 Fred Sauer
 */
package com.allen_sauer.gwt.game.client;

import java.util.ArrayList;
import java.util.Iterator;

public class FrameListenerCollection implements FrameListener {
  private final ArrayList<FrameListener> frameListeners = new ArrayList<FrameListener>();
  private final ArrayList<FrameListener> newFrameListeners = new ArrayList<FrameListener>();

  public void addFrameListener(FrameListener listener) {
    newFrameListeners.add(listener);
    // TODO verify this is not called twice
    listener.doFirstFrame();
  }

  public void doFirstFrame() {
    for (FrameListener frameListener : frameListeners) {
      FrameListener listener = frameListener;
      // TODO verify this is not called twice
      listener.doFirstFrame();
    }
  }

  public boolean doFrame() {
    updateFrameListenerCollections();
    doFrameListenerFrames();
    return true;
  }

  public void doLastFrame() {
    for (FrameListener frameListener : frameListeners) {
      FrameListener listener = frameListener;
      listener.doLastFrame();
    }
  }

  public int size() {
    return frameListeners.size();
  }

  private void doFrameListenerFrames() {
    for (Iterator<FrameListener> iterator = frameListeners.iterator(); iterator.hasNext();) {
      FrameListener listener = iterator.next();
      boolean again = listener.doFrame();
      if (!again) {
        listener.doLastFrame();
        iterator.remove();
      }
    }
  }

  /**
   * Use additional collection to avoid ConcurrentModificationException
   */
  private void updateFrameListenerCollections() {
    if (frameListeners.addAll(newFrameListeners)) {
      newFrameListeners.clear();
    }
  }
}
