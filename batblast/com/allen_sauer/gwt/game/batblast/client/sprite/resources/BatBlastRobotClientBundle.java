/**
 * Copyright 2008 Fred Sauer.
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface BatBlastRobotClientBundle extends ClientBundle {

  public static final BatBlastRobotClientBundle INSTANCE = GWT.create(BatBlastRobotClientBundle.class);

  ImageResource batman();

  ImageResource freeze2();

  ImageResource iceCube();

  ImageResource ivy();

  ImageResource joker();

  ImageResource mrFreezeTall();

  ImageResource robin();

  ImageResource skylerBaine();

  //  ImageResource skylerBaine();
  //
  //  ImageResource skylerBatman();
  //
  //  ImageResource skylerKillerCroc();
  //
  //  ImageResource skylerMrFreeze();
  //
  //  ImageResource skylerPenguin();
  //
  //  ImageResource skylerPoisinIvy();
  //
  //  ImageResource skylerTwoFace();
  //
  //  ImageResource skylerWorserMrFreeze();

}