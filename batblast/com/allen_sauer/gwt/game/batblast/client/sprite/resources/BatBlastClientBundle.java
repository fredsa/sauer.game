/**
 * Copyright 2008 Fred Sauer.
 */
package com.allen_sauer.gwt.game.batblast.client.sprite.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface BatBlastClientBundle extends ClientBundle {

  public static final BatBlastClientBundle INSTANCE = GWT.create(BatBlastClientBundle.class);

  ImageResource batman();

  //  @Source("62363__fons__zap_2.mp3")
  //  DataResource fonsZap2();

  ImageResource freeze2();

  //  @Source("21915__Halleck__neck_crack_pure.mp3")
  //  DataResource halleckNeckCrackPure();

  ImageResource iceCube();

  ImageResource iceCube2();

  ImageResource iceCube3();

  ImageResource iced();

  ImageResource icedPlayer1();

  ImageResource icedPlayer2();

  ImageResource ivy();

  ImageResource joker();

  ImageResource mrFreezeTall();

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

  ImageResource robin();

  ImageResource skylerBaine();

  //  @Source("16064__Traveler__Cork3.mp3")
  //  DataResource travelerCork3();
}