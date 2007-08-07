package com.allen_sauer.gwt.game.client.engine;

import java.util.ArrayList;
import java.util.Iterator;

public class FrameListenerCollection implements FrameListener {
  private final ArrayList frameListeners = new ArrayList();
  private final ArrayList newFrameListeners = new ArrayList();

  public void addFrameListener(FrameListener listener) {
    newFrameListeners.add(listener);
    // TODO verify this is not called twice
    listener.doFirstFrame();
  }

  public void doFirstFrame() {
    for (Iterator iterator = frameListeners.iterator(); iterator.hasNext();) {
      FrameListener listener = (FrameListener) iterator.next();
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
    for (Iterator iterator = frameListeners.iterator(); iterator.hasNext();) {
      FrameListener listener = (FrameListener) iterator.next();
      listener.doLastFrame();
    }
  }

  public int size() {
    return frameListeners.size();
  }

  private void doFrameListenerFrames() {
    for (Iterator iterator = frameListeners.iterator(); iterator.hasNext();) {
      FrameListener listener = (FrameListener) iterator.next();
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
