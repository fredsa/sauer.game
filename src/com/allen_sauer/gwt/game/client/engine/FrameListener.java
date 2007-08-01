package com.allen_sauer.gwt.game.client.engine;

public interface FrameListener {
  public class FrameListenerRetention {

    public final boolean retention;

    private FrameListenerRetention(boolean retention) {
      this.retention = retention;
    }
  }

  public static final FrameListenerRetention LISTENER_CONTINUE = new FrameListenerRetention(true);
  public static final FrameListenerRetention LISTENER_REMOVE = new FrameListenerRetention(false);

  void doFirstFrame();

  FrameListenerRetention doFrame();

  void doLastFrame();
}
