(function(){
var $wnd = window;
var $doc = $wnd.document;
var $moduleName, $moduleBase;
var _, package_com_allen_sauer_gwt_game_client_behavior_ = 'com.allen_sauer.gwt.game.client.behavior.', package_com_allen_sauer_gwt_game_client_engine_ = 'com.allen_sauer.gwt.game.client.engine.', package_com_allen_sauer_gwt_game_client_generator_ = 'com.allen_sauer.gwt.game.client.generator.', package_com_allen_sauer_gwt_game_client_sprite_ = 'com.allen_sauer.gwt.game.client.sprite.', package_com_allen_sauer_gwt_game_space_client_ = 'com.allen_sauer.gwt.game.space.client.', package_com_allen_sauer_gwt_log_client_ = 'com.allen_sauer.gwt.log.client.', package_com_google_gwt_core_client_ = 'com.google.gwt.core.client.', package_com_google_gwt_lang_ = 'com.google.gwt.lang.', package_com_google_gwt_user_client_ = 'com.google.gwt.user.client.', package_com_google_gwt_user_client_impl_ = 'com.google.gwt.user.client.impl.', package_com_google_gwt_user_client_ui_ = 'com.google.gwt.user.client.ui.', package_com_google_gwt_user_client_ui_impl_ = 'com.google.gwt.user.client.ui.impl.', package_java_io_ = 'java.io.', package_java_lang_ = 'java.lang.', package_java_util_ = 'java.util.';
function nullMethod(){
}

function equals_2(other){
  return this === other;
}

function hashCode_3(){
  return identityHashCode(this);
}

function toString_6(){
  return this.typeName + '@' + this.hashCode();
}

function Object_0(){
}

_ = Object_0.prototype = {};
_.equals = equals_2;
_.hashCode = hashCode_3;
_.toString_0 = toString_6;
_.toString = function(){
  return this.toString_0();
}
;
_.typeName = package_java_lang_ + 'Object';
_.typeId = 0;
function $CursorKeyBehavior(this$static, sprite){
  this$static.sprite = sprite;
  return this$static;
}

function doFrame(){
  var x, y;
  x = this.sprite.x;
  y = this.sprite.y;
  if (isKeyDown(37)) {
    x -= this.xDelta;
    if (x < 0) {
      x = 0;
    }
  }
  if (isKeyDown(39)) {
    x += this.xDelta;
    if (x > this.xMax) {
      x = this.xMax;
    }
  }
  if (isKeyDown(38)) {
    y -= this.yDelta;
    if (y < 0) {
      y = 0;
    }
  }
  if (isKeyDown(40)) {
    y += this.yDelta;
    if (y > this.yMax) {
      y = this.yMax;
    }
  }
  $setX(this.sprite, x);
  $setY(this.sprite, y);
}

function init(){
  this.xMax = ($clinit_8() , clientWidth_0) - this.sprite.frameWidth;
  this.yMax = ($clinit_8() , clientHeight_0) - this.sprite.frameHeight;
  $setX(this.sprite, round_int(this.xMax / 2));
  $setY(this.sprite, round_int(this.yMax / 2));
  addFrameListener(this);
}

function CursorKeyBehavior(){
}

_ = CursorKeyBehavior.prototype = new Object_0();
_.doFrame = doFrame;
_.init = init;
_.typeName = package_com_allen_sauer_gwt_game_client_behavior_ + 'CursorKeyBehavior';
_.typeId = 1;
_.sprite = null;
_.xDelta = 3;
_.xMax = 0;
_.yDelta = 5;
_.yMax = 0;
function $ParatrooperBehavior(this$static, sprite){
  this$static.sprite = sprite;
  return this$static;
}

function doFrame_0(){
  var x, y;
  x = this.sprite.x;
  x += this.xSpeed;
  if (x < 0) {
    x = 0;
    this.xSpeed = nextInt(5) + 4;
  }
   else if (x > this.xMax) {
    x = this.xMax;
    this.xSpeed = -nextInt(5) - 4;
  }
  $setX(this.sprite, x);
  y = this.sprite.y;
  y += this.ySpeed;
  if (y < 0) {
    this.ySpeed = nextInt(3) + 2;
  }
   else if (y > this.yMax) {
    y = this.yMax;
    this.ySpeed = 0;
  }
   else if (y == this.yMax) {
    if (++this.walkFrames == 30) {
      $removeSelf(this.sprite);
    }
  }
  $setY(this.sprite, y);
}

function init_0(){
  this.xMax = ($clinit_8() , clientWidth_0) - this.sprite.frameWidth;
  this.yMax = ($clinit_8() , clientHeight_0) - this.sprite.frameHeight;
  this.walkFrames = 0;
  $setX(this.sprite, nextInt(this.xMax));
  $setY(this.sprite, -this.sprite.frameHeight);
  this.xSpeed = nextInt(5) + 3;
  this.ySpeed = nextInt(5) + 3;
  addFrameListener(this);
}

function ParatrooperBehavior(){
}

_ = ParatrooperBehavior.prototype = new Object_0();
_.doFrame = doFrame_0;
_.init = init_0;
_.typeName = package_com_allen_sauer_gwt_game_client_behavior_ + 'ParatrooperBehavior';
_.typeId = 2;
_.sprite = null;
_.walkFrames = 0;
_.xMax = 0;
_.xSpeed = 0;
_.yMax = 0;
_.ySpeed = 0;
function $clinit_8(){
  $clinit_8 = nullMethod;
  frameListeners = $ArrayList(new ArrayList());
  keyDown = initDims_0('[Z', [0], [(-1)], [255], false);
  newFrameListeners = $ArrayList(new ArrayList());
  spritePools = $ArrayList(new ArrayList());
}

function addFrameListener(listener){
  $clinit_8();
  $add_2(newFrameListeners, listener);
}

function addSpritePool(pool){
  $clinit_8();
  $add_2(spritePools, pool);
}

function doFrame_1(){
  $clinit_8();
  var iterator, listener;
  if (!$isEmpty(newFrameListeners)) {
    $addAll(frameListeners, newFrameListeners);
    newFrameListeners = $ArrayList(new ArrayList());
  }
  for (iterator = $iterator_0(frameListeners); $hasNext_0(iterator);) {
    listener = dynamicCast($next(iterator), 1);
    listener.doFrame();
  }
}

function init_1(game){
  $clinit_8();
  var enginePauseButton, focusPanel, keyboardListener;
  game_0 = game;
  enableScrolling(false);
  setClientSize(getClientWidth(), getClientHeight());
  $init_0(game);
  addWindowResizeListener(new Engine$1());
  engineTimer = $EngineTimer(new EngineTimer());
  $setPaused(engineTimer, false);
  enginePauseButton = $EnginePauseButton(new EnginePauseButton(), engineTimer);
  $add_0(get(), enginePauseButton, 10, 40);
  focusPanel = $FocusPanel(new FocusPanel());
  $add(get(), focusPanel);
  $setFocus(focusPanel, true);
  setStyleAttribute(focusPanel.getElement(), 'border', '1px solid green');
  $setPixelSize(focusPanel, 50, 50);
  keyboardListener = new Engine$2();
  $addKeyboardListener(focusPanel, keyboardListener);
}

function isKeyDown(keyCode){
  $clinit_8();
  return keyDown[keyCode & 255];
}

function setClientSize(clientWidth, clientHeight){
  $clinit_8();
  clientWidth_0 = clientWidth;
  clientHeight_0 = clientHeight;
}

var clientHeight_0 = 0, clientWidth_0 = 0, engineTimer = null, frameListeners, game_0 = null, keyDown, newFrameListeners, spritePools;
function onWindowResized(width, height){
  setClientSize(width, height);
  $clientResized(($clinit_8() , game_0), width, height);
}

function Engine$1(){
}

_ = Engine$1.prototype = new Object_0();
_.onWindowResized = onWindowResized;
_.typeName = package_com_allen_sauer_gwt_game_client_engine_ + 'Engine$1';
_.typeId = 3;
function onKeyDown_0(sender, keyCode, modifiers){
}

function onKeyPress_0(sender, keyCode, modifiers){
}

function onKeyUp_0(sender, keyCode, modifiers){
}

function KeyboardListenerAdapter(){
}

_ = KeyboardListenerAdapter.prototype = new Object_0();
_.onKeyDown = onKeyDown_0;
_.onKeyPress = onKeyPress_0;
_.onKeyUp = onKeyUp_0;
_.typeName = package_com_google_gwt_user_client_ui_ + 'KeyboardListenerAdapter';
_.typeId = 4;
function onKeyDown(sender, keyCode, modifiers){
  ($clinit_8() , keyDown)[keyCode & 255] = true;
  debug_1(charToString(keyCode) + ' down');
}

function onKeyPress(sender, keyCode, modifiers){
  debug_1(charToString(keyCode) + ' pre');
}

function onKeyUp(sender, keyCode, modifiers){
  ($clinit_8() , keyDown)[keyCode & 255] = false;
  debug_1(charToString(keyCode) + ' up');
}

function Engine$2(){
}

_ = Engine$2.prototype = new KeyboardListenerAdapter();
_.onKeyDown = onKeyDown;
_.onKeyPress = onKeyPress;
_.onKeyUp = onKeyUp;
_.typeName = package_com_allen_sauer_gwt_game_client_engine_ + 'Engine$2';
_.typeId = 5;
function $addStyleName(this$static, style){
  setStyleName(this$static.element, style, true);
}

function $getAbsoluteLeft_0(this$static){
  return getAbsoluteLeft(this$static.getElement());
}

function $getAbsoluteTop_0(this$static){
  return getAbsoluteTop(this$static.getElement());
}

function $replaceNode(this$static, node, newNode){
  var p = node.parentNode;
  if (!p) {
    return;
  }
  p.insertBefore(newNode, node);
  p.removeChild(node);
}

function $setElement_0(this$static, elem){
  if (this$static.element !== null) {
    $replaceNode(this$static, this$static.element, elem);
  }
  this$static.element = elem;
}

function $setHeight(this$static, height){
  setStyleAttribute(this$static.element, 'height', height);
}

function $setPixelSize(this$static, width, height){
  if (width >= 0) {
    $setWidth(this$static, width + 'px');
  }
  if (height >= 0) {
    $setHeight(this$static, height + 'px');
  }
}

function $setStyleName(this$static, style){
  resetStyleName(this$static.element, style);
}

function $setWidth(this$static, width){
  setStyleAttribute(this$static.element, 'width', width);
}

function $sinkEvents_0(this$static, eventBitsToAdd){
  sinkEvents(this$static.getElement(), eventBitsToAdd | getEventsSunk(this$static.getElement()));
}

function ensurePrimaryStyleName(elem){
  var className;
  className = $trim(getElementProperty(elem, 'className'));
  if ($equals_0('', className)) {
    className = 'gwt-nostyle';
    setElementProperty(elem, 'className', className);
  }
  return className;
}

function getElement_0(){
  return this.element;
}

function resetStyleName(elem, style){
  if (elem === null) {
    throw $RuntimeException_0(new RuntimeException(), 'Null widget handle. If you are creating a composite, ensure that initWidget() has been called.');
  }
  style = $trim(style);
  if ($length(style) == 0) {
    throw $IllegalArgumentException(new IllegalArgumentException(), 'Style names cannot be empty');
  }
  ensurePrimaryStyleName(elem);
  updatePrimaryAndDependentStyleNames(elem, style);
}

function setStyleName(elem, style, add){
  var begin, end, idx, last, lastPos, oldStyle;
  if (elem === null) {
    throw $RuntimeException_0(new RuntimeException(), 'Null widget handle. If you are creating a composite, ensure that initWidget() has been called.');
  }
  style = $trim(style);
  if ($length(style) == 0) {
    throw $IllegalArgumentException(new IllegalArgumentException(), 'Style names cannot be empty');
  }
  oldStyle = ensurePrimaryStyleName(elem);
  if (oldStyle === null) {
    idx = (-1);
    oldStyle = '';
  }
   else {
    idx = $indexOf_0(oldStyle, style);
  }
  while (idx != (-1)) {
    if (idx == 0 || $charAt(oldStyle, idx - 1) == 32) {
      last = idx + $length(style);
      lastPos = $length(oldStyle);
      if (last == lastPos || last < lastPos && $charAt(oldStyle, last) == 32) {
        break;
      }
    }
    idx = $indexOf_1(oldStyle, style, idx + 1);
  }
  if (add) {
    if (idx == (-1)) {
      if ($length(oldStyle) > 0) {
        oldStyle += ' ';
      }
      setElementProperty(elem, 'className', oldStyle + style);
    }
  }
   else {
    if (idx != (-1)) {
      if (idx == 0) {
        throw $IllegalArgumentException(new IllegalArgumentException(), 'Cannot remove base style name');
      }
      begin = $substring_0(oldStyle, 0, idx);
      end = $substring(oldStyle, idx + $length(style));
      setElementProperty(elem, 'className', begin + end);
    }
  }
}

function toString_5(){
  if (this.element === null) {
    return '(null handle)';
  }
  return toString_2(this.element);
}

function updatePrimaryAndDependentStyleNames(elem, newStyle){
  var className = elem.className;
  var spaceIdx = className.indexOf(' ');
  if (spaceIdx >= 0) {
    var oldStyle = className.substring(0, spaceIdx);
    var newClassName = '', curIdx = 0;
    while (true) {
      var idx = className.indexOf(oldStyle, curIdx);
      if (idx == -1) {
        newClassName += className.substring(curIdx);
        break;
      }
      newClassName += className.substring(curIdx, idx);
      newClassName += newStyle;
      curIdx = idx + oldStyle.length;
    }
    elem.className = newClassName;
  }
   else {
    elem.className = newStyle;
  }
}

function UIObject(){
}

_ = UIObject.prototype = new Object_0();
_.getElement = getElement_0;
_.toString_0 = toString_5;
_.typeName = package_com_google_gwt_user_client_ui_ + 'UIObject';
_.typeId = 0;
_.element = null;
function $removeFromParent(this$static){
  if (instanceOf(this$static.parent, 13)) {
    dynamicCast(this$static.parent, 13).remove_0(this$static);
  }
   else if (this$static.parent !== null) {
    throw $IllegalStateException_0(new IllegalStateException(), "This widget's parent does not implement HasWidgets");
  }
}

function $setElement_1(this$static, elem){
  if (this$static.isAttached()) {
    setEventListener(this$static.getElement(), null);
  }
  $setElement_0(this$static, elem);
  if (this$static.isAttached()) {
    setEventListener(elem, this$static);
  }
}

function $setParent(this$static, parent){
  var oldParent;
  oldParent = this$static.parent;
  this$static.parent = parent;
  if (parent === null) {
    if (oldParent !== null && oldParent.isAttached()) {
      this$static.onDetach();
    }
  }
   else if (parent.isAttached()) {
    this$static.onAttach();
  }
}

function doAttachChildren_0(){
}

function doDetachChildren_0(){
}

function isAttached_0(){
  return this.attached;
}

function onAttach_0(){
  if (this.isAttached()) {
    throw $IllegalStateException_0(new IllegalStateException(), "Should only call onAttach when the widget is detached from the browser's document");
  }
  this.attached = true;
  setEventListener(this.getElement(), this);
  this.doAttachChildren();
  this.onLoad();
}

function onBrowserEvent_5(event_0){
}

function onDetach_0(){
  if (!this.isAttached()) {
    throw $IllegalStateException_0(new IllegalStateException(), "Should only call onDetach when the widget is attached to the browser's document");
  }
  try {
    this.onUnload();
  }
   finally {
    this.doDetachChildren();
    setEventListener(this.getElement(), null);
    this.attached = false;
  }
}

function onLoad_0(){
}

function onUnload_0(){
}

function setElement_0(elem){
  $setElement_1(this, elem);
}

function Widget(){
}

_ = Widget.prototype = new UIObject();
_.doAttachChildren = doAttachChildren_0;
_.doDetachChildren = doDetachChildren_0;
_.isAttached = isAttached_0;
_.onAttach = onAttach_0;
_.onBrowserEvent = onBrowserEvent_5;
_.onDetach = onDetach_0;
_.onLoad = onLoad_0;
_.onUnload = onUnload_0;
_.setElement = setElement_0;
_.typeName = package_com_google_gwt_user_client_ui_ + 'Widget';
_.typeId = 6;
_.attached = false;
_.parent = null;
function $clinit_58(){
  $clinit_58 = nullMethod;
  impl_1 = ($clinit_104() , implWidget);
}

function $FocusWidget(this$static, elem){
  $clinit_58();
  $setElement(this$static, elem);
  return this$static;
}

function $onBrowserEvent(this$static, event_0){
  switch (eventGetType(event_0)) {
    case 1:
      if (this$static.clickListeners_0 !== null) {
        $fireClick(this$static.clickListeners_0, this$static);
      }

      break;
    case 4096:
    case 2048:
      break;
    case 128:
    case 512:
    case 256:
      break;
  }
}

function $setElement(this$static, elem){
  $setElement_1(this$static, elem);
  $sinkEvents_0(this$static, 7041);
}

function $setTabIndex(this$static, index){
  impl_1.setTabIndex(this$static.getElement(), index);
}

function addClickListener(listener){
  if (this.clickListeners_0 === null) {
    this.clickListeners_0 = $ClickListenerCollection(new ClickListenerCollection());
  }
  $add_2(this.clickListeners_0, listener);
}

function onBrowserEvent_0(event_0){
  $onBrowserEvent(this, event_0);
}

function setElement(elem){
  $setElement(this, elem);
}

function FocusWidget(){
}

_ = FocusWidget.prototype = new Widget();
_.addClickListener = addClickListener;
_.onBrowserEvent = onBrowserEvent_0;
_.setElement = setElement;
_.typeName = package_com_google_gwt_user_client_ui_ + 'FocusWidget';
_.typeId = 7;
_.clickListeners_0 = null;
var impl_1;
function $clinit_49(){
  $clinit_49 = nullMethod;
  $clinit_58();
}

function $ButtonBase(this$static, elem){
  $clinit_49();
  $FocusWidget(this$static, elem);
  return this$static;
}

function $setHTML(this$static, html){
  setInnerHTML(this$static.getElement(), html);
}

function ButtonBase(){
}

_ = ButtonBase.prototype = new FocusWidget();
_.typeName = package_com_google_gwt_user_client_ui_ + 'ButtonBase';
_.typeId = 8;
function $clinit_50(){
  $clinit_50 = nullMethod;
  $clinit_49();
}

function $Button(this$static){
  $clinit_50();
  $ButtonBase(this$static, createButton());
  adjustType(this$static.getElement());
  $setStyleName(this$static, 'gwt-Button');
  return this$static;
}

function $Button_0(this$static, html){
  $clinit_50();
  $Button(this$static);
  $setHTML(this$static, html);
  return this$static;
}

function adjustType(button){
  $clinit_50();
  if (button.type == 'submit') {
    try {
      button.setAttribute('type', 'button');
    }
     catch (e) {
    }
  }
}

function Button(){
}

_ = Button.prototype = new ButtonBase();
_.typeName = package_com_google_gwt_user_client_ui_ + 'Button';
_.typeId = 9;
function $clinit_6(){
  $clinit_6 = nullMethod;
  $clinit_50();
}

function $$init(this$static){
  this$static.gamePauseClickListener = $EnginePauseButton$GamePauseClickListener(new EnginePauseButton$GamePauseClickListener(), this$static);
}

function $EnginePauseButton(this$static, gameTimer){
  $clinit_6();
  $Button_0(this$static, 'Pause');
  $$init(this$static);
  this$static.gameTimer = gameTimer;
  setStyleAttribute(this$static.getElement(), 'width', '5em');
  this$static.addClickListener(this$static.gamePauseClickListener);
  return this$static;
}

function EnginePauseButton(){
}

_ = EnginePauseButton.prototype = new Button();
_.typeName = package_com_allen_sauer_gwt_game_client_engine_ + 'EnginePauseButton';
_.typeId = 10;
_.gameTimer = null;
function $EnginePauseButton$GamePauseClickListener(this$static, this$0){
  this$static.this$0 = this$0;
  return this$static;
}

function onClick(sender){
  $setPaused(this.this$0.gameTimer, !this.this$0.gameTimer.paused);
}

function EnginePauseButton$GamePauseClickListener(){
}

_ = EnginePauseButton$GamePauseClickListener.prototype = new Object_0();
_.onClick = onClick;
_.typeName = package_com_allen_sauer_gwt_game_client_engine_ + 'EnginePauseButton$GamePauseClickListener';
_.typeId = 11;
function $clinit_41(){
  $clinit_41 = nullMethod;
  timers = $ArrayList(new ArrayList());
  {
    hookWindowClosing();
  }
}

function $Timer(this$static){
  $clinit_41();
  return this$static;
}

function $cancel(this$static){
  if (this$static.isRepeating) {
    clearInterval(this$static.timerId);
  }
   else {
    clearTimeout(this$static.timerId);
  }
  $remove_6(timers, this$static);
}

function $fireAndCatch(this$static, handler){
  var $e0, e;
  try {
    $fireImpl(this$static);
  }
   catch ($e0) {
    $e0 = caught($e0);
    if (instanceOf($e0, 3)) {
      e = $e0;
      $onUncaughtException(handler, e);
    }
     else 
      throw $e0;
  }
}

function $fireImpl(this$static){
  if (!this$static.isRepeating) {
    $remove_6(timers, this$static);
  }
  this$static.run();
}

function $schedule(this$static, delayMillis){
  if (delayMillis <= 0) {
    throw $IllegalArgumentException(new IllegalArgumentException(), 'must be positive');
  }
  $cancel(this$static);
  this$static.isRepeating = false;
  this$static.timerId = createTimeout(this$static, delayMillis);
  $add_2(timers, this$static);
}

function $scheduleRepeating_0(this$static, periodMillis){
  if (periodMillis <= 0) {
    throw $IllegalArgumentException(new IllegalArgumentException(), 'must be positive');
  }
  $cancel(this$static);
  this$static.isRepeating = true;
  this$static.timerId = createInterval(this$static, periodMillis);
  $add_2(timers, this$static);
}

function clearInterval(id){
  $clinit_41();
  $wnd.clearInterval(id);
}

function clearTimeout(id){
  $clinit_41();
  $wnd.clearTimeout(id);
}

function createInterval(timer, period){
  $clinit_41();
  return $wnd.setInterval(function(){
    timer.fire();
  }
  , period);
}

function createTimeout(timer, delay){
  $clinit_41();
  return $wnd.setTimeout(function(){
    timer.fire();
  }
  , delay);
}

function fire(){
  var handler;
  handler = sUncaughtExceptionHandler;
  if (handler !== null) {
    $fireAndCatch(this, handler);
  }
   else {
    $fireImpl(this);
  }
}

function hookWindowClosing(){
  $clinit_41();
  addWindowCloseListener(new Timer$1());
}

function schedule_0(delayMillis){
  $schedule(this, delayMillis);
}

function Timer(){
}

_ = Timer.prototype = new Object_0();
_.fire = fire;
_.schedule = schedule_0;
_.typeName = package_com_google_gwt_user_client_ + 'Timer';
_.typeId = 12;
_.isRepeating = false;
_.timerId = 0;
var timers;
function $clinit_7(){
  $clinit_7 = nullMethod;
  $clinit_41();
}

function $$init_0(this$static){
  this$static.timerText = $HTML(new HTML());
}

function $EngineTimer(this$static){
  $clinit_7();
  $Timer(this$static);
  $$init_0(this$static);
  return this$static;
}

function $initMeasurements(this$static){
  $setHTML_0(this$static.timerText, '');
  $add_0(get(), this$static.timerText, 100, 10);
  this$static.lastTimestamp = 0;
}

function $measure(this$static){
  var frameRate, frameToFrame, timestamp;
  if (++this$static.count == 10) {
    timestamp = currentTimeMillis();
    this$static.count = 0;
    if (this$static.lastTimestamp != 0) {
      frameToFrame = round_long((timestamp - this$static.lastTimestamp) / 10);
      frameRate = round(1000.0 / frameToFrame);
      $setHTML_0(this$static.timerText, '10 frame avg = ' + frameToFrame + 'ms (' + frameRate + 'fps)');
    }
    this$static.lastTimestamp = timestamp;
  }
}

function $scheduleRepeating(this$static, periodMillis){
  $initMeasurements(this$static);
  $scheduleRepeating_0(this$static, periodMillis);
}

function $setPaused(this$static, paused){
  if (paused) {
    $cancel(this$static);
  }
   else if (this$static.paused) {
    $scheduleRepeating(this$static, 1);
  }
  this$static.paused = paused;
}

function run(){
  $measure(this);
  doFrame_1();
}

function schedule(delayMillis){
  $initMeasurements(this);
  $schedule(this, delayMillis);
}

function EngineTimer(){
}

_ = EngineTimer.prototype = new Timer();
_.run = run;
_.schedule = schedule;
_.typeName = package_com_allen_sauer_gwt_game_client_engine_ + 'EngineTimer';
_.typeId = 13;
_.count = 0;
_.lastTimestamp = 0;
_.paused = true;
function $IntervalGenerator(this$static, spritePool, probability){
  this$static.spritePool = spritePool;
  this$static.probability = probability;
  addFrameListener(this$static);
  return this$static;
}

function doFrame_2(){
  if (($clinit_13() , sprites).size < this.spritePool.maxSize && nextDouble() < this.probability) {
    $create(this.spritePool);
  }
}

function IntervalGenerator(){
}

_ = IntervalGenerator.prototype = new Object_0();
_.doFrame = doFrame_2;
_.typeName = package_com_allen_sauer_gwt_game_client_generator_ + 'IntervalGenerator';
_.typeId = 14;
_.probability = 0.0;
_.spritePool = null;
function $getElement(this$static){
  if (this$static.widget === null) {
    throw $IllegalStateException_0(new IllegalStateException(), 'initWidget() was never called in ' + getTypeName(this$static));
  }
  return this$static.element;
}

function $initWidget(this$static, widget){
  if (this$static.widget !== null) {
    throw $IllegalStateException_0(new IllegalStateException(), 'Composite.initWidget() may only be called once.');
  }
  $removeFromParent(widget);
  this$static.setElement(widget.getElement());
  this$static.widget = widget;
  $setParent(widget, this$static);
}

function getElement(){
  return $getElement(this);
}

function isAttached(){
  if (this.widget !== null) {
    return this.widget.isAttached();
  }
  return false;
}

function onAttach(){
  this.widget.onAttach();
  this.onLoad();
}

function onDetach(){
  try {
    this.onUnload();
  }
   finally {
    this.widget.onDetach();
  }
}

function Composite(){
}

_ = Composite.prototype = new Widget();
_.getElement = getElement;
_.isAttached = isAttached;
_.onAttach = onAttach;
_.onDetach = onDetach;
_.typeName = package_com_google_gwt_user_client_ui_ + 'Composite';
_.typeId = 15;
_.widget = null;
function $$init_1(this$static){
  this$static.panel = $AbsolutePanel(new AbsolutePanel());
}

function $Sprite(this$static, game, url, frames, frameWidth, frameHeight){
  $$init_1(this$static);
  this$static.frames = frames;
  this$static.frameWidth = frameWidth;
  this$static.frameHeight = frameHeight;
  $initWidget(this$static, this$static.panel);
  this$static.image = $Image(new Image_0(), url);
  $add_0(this$static.panel, this$static.image, 0, 0);
  $setPixelSize(this$static.image, frameWidth * frames, frameHeight);
  $setPixelSize(this$static.panel, frameWidth, frameHeight);
  setStyleAttribute($getElement(this$static), 'position', 'absolute');
  return this$static;
}

function $deinit(this$static){
  $remove(get(), this$static);
}

function $init(this$static){
  addFrameListener(this$static);
  $add(get(), this$static);
  this$static.behavior.init();
}

function $removeSelf(this$static){
  $destroy(this$static.spritePool, this$static);
}

function $setBehavior(this$static, behavior){
  this$static.behavior = behavior;
}

function $setFrame(this$static, frame){
  setStyleAttribute(this$static.image.getElement(), 'left', -frame * this$static.frameWidth + 'px');
}

function $setSpritePool(this$static, spritePool){
  this$static.spritePool = spritePool;
}

function $setX(this$static, x){
  this$static.x = x;
  setStyleAttribute($getElement(this$static), 'left', x + 'px');
}

function $setY(this$static, y){
  setStyleAttribute($getElement(this$static), 'top', y + 'px');
  this$static.y = y;
}

function doFrame_3(){
  if (++this.frame >= this.frames * 5) {
    this.frame = 0;
  }
  $setFrame(this, round_int(this.frame / 5));
}

function Sprite(){
}

_ = Sprite.prototype = new Composite();
_.doFrame = doFrame_3;
_.typeName = package_com_allen_sauer_gwt_game_client_sprite_ + 'Sprite';
_.typeId = 16;
_.behavior = null;
_.frame = 0;
_.frameHeight = 0;
_.frameWidth = 0;
_.frames = 0;
_.image = null;
_.spritePool = null;
_.x = 0;
_.y = 0;
function $clinit_13(){
  $clinit_13 = nullMethod;
  sprites = $ArrayList(new ArrayList());
}

function $SpritePool(this$static, spriteFactory, maxSize){
  $clinit_13();
  this$static.spriteFactory = spriteFactory;
  this$static.maxSize = maxSize;
  return this$static;
}

function $create(this$static){
  var sprite;
  sprite = this$static.spriteFactory.create();
  $setSpritePool(sprite, this$static);
  $init(sprite);
  $add_2(sprites, sprite);
  return sprite;
}

function $destroy(this$static, sprite){
  if (!$remove_6(sprites, sprite)) {
    throw $IllegalStateException_0(new IllegalStateException(), 'sprite not in pool');
  }
  $deinit(sprite);
}

function toString_0(){
  return 'SpritePool(' + sprites.size + ' of ' + this.maxSize + ')';
}

function SpritePool(){
}

_ = SpritePool.prototype = new Object_0();
_.toString_0 = toString_0;
_.typeName = package_com_allen_sauer_gwt_game_client_sprite_ + 'SpritePool';
_.typeId = 0;
_.maxSize = 0;
_.spriteFactory = null;
var sprites;
function $RobotSprite(this$static, game){
  $Sprite(this$static, game, 'images/robots-03-map-tr.gif', 6, 65, 90);
  $setBehavior(this$static, $ParatrooperBehavior(new ParatrooperBehavior(), this$static));
  return this$static;
}

function RobotSprite(){
}

_ = RobotSprite.prototype = new Sprite();
_.typeName = package_com_allen_sauer_gwt_game_space_client_ + 'RobotSprite';
_.typeId = 17;
function $onModuleLoad(this$static){
  var game;
  setUncaughtExceptionHandler(new LogUncaughtExceptionHandler());
  game = new SpaceGame();
  init_1(game);
}

function Space(){
}

_ = Space.prototype = new Object_0();
_.typeName = package_com_allen_sauer_gwt_game_space_client_ + 'Space';
_.typeId = 0;
function $clientResized(this$static, clientWidth, clientHeight){
  $setPixelSize(this$static.backgroundImage, clientWidth * 2, round_int(clientHeight / 2));
}

function $init_0(this$static){
  var timerText;
  this$static.backgroundImage = $Image(new Image_0(), 'images/nebula_13-fudged.jpg');
  $addStyleName(this$static.backgroundImage, 'backgroundImage');
  $add(get(), this$static.backgroundImage);
  timerText = $HTML_0(new HTML(), '');
  $addStyleName(timerText, 'timerText');
  $add_0(get(), timerText, 200, 0);
  $initPlayerFactory(this$static);
  $initRobotFactory(this$static);
}

function $initPlayerFactory(this$static){
  var factory, pool, spaceShuttleSprite;
  factory = $SpaceGame$1(new SpaceGame$1(), this$static);
  pool = $SpritePool(new SpritePool(), factory, 1);
  addSpritePool(pool);
  spaceShuttleSprite = $SpaceShuttleSprite(new SpaceShuttleSprite(), this$static);
  $IntervalGenerator(new IntervalGenerator(), pool, 0.05);
}

function $initRobotFactory(this$static){
  var factory, pool;
  factory = $SpaceGame$2(new SpaceGame$2(), this$static);
  pool = $SpritePool(new SpritePool(), factory, 10);
  addSpritePool(pool);
  $IntervalGenerator(new IntervalGenerator(), pool, 0.05);
}

function doFrame_4(){
}

function SpaceGame(){
}

_ = SpaceGame.prototype = new Object_0();
_.doFrame = doFrame_4;
_.typeName = package_com_allen_sauer_gwt_game_space_client_ + 'SpaceGame';
_.typeId = 18;
_.backgroundImage = null;
function $SpaceGame$1(this$static, this$0){
  this$static.this$0 = this$0;
  return this$static;
}

function create(){
  return $SpaceShuttleSprite(new SpaceShuttleSprite(), this.this$0);
}

function SpaceGame$1(){
}

_ = SpaceGame$1.prototype = new Object_0();
_.create = create;
_.typeName = package_com_allen_sauer_gwt_game_space_client_ + 'SpaceGame$1';
_.typeId = 0;
function $SpaceGame$2(this$static, this$0){
  this$static.this$0 = this$0;
  return this$static;
}

function create_0(){
  return $RobotSprite(new RobotSprite(), this.this$0);
}

function SpaceGame$2(){
}

_ = SpaceGame$2.prototype = new Object_0();
_.create = create_0;
_.typeName = package_com_allen_sauer_gwt_game_space_client_ + 'SpaceGame$2';
_.typeId = 0;
function $SpaceShuttleSprite(this$static, game){
  $Sprite(this$static, game, 'images/SpaceShuttle-sm-tr.gif', 1, 51, 113);
  $setBehavior(this$static, $CursorKeyBehavior(new CursorKeyBehavior(), this$static));
  return this$static;
}

function SpaceShuttleSprite(){
}

_ = SpaceShuttleSprite.prototype = new Sprite();
_.typeName = package_com_allen_sauer_gwt_game_space_client_ + 'SpaceShuttleSprite';
_.typeId = 19;
function debug_1(message){
  {
    log('[DEBUG ]', message, null);
  }
}

function fatal(message, e){
  {
    log('[FATAL ]', message, e);
  }
}

function log(levelPrefix, message, e){
  message = levelPrefix + $replaceAll(message, '\n', '\n' + levelPrefix);
  debug(message);
  if (e !== null) {
    debug_0(e);
  }
}

function $onUncaughtException(this$static, e){
  fatal('Uncaught Exception:', e);
}

function LogUncaughtExceptionHandler(){
}

_ = LogUncaughtExceptionHandler.prototype = new Object_0();
_.typeName = package_com_allen_sauer_gwt_log_client_ + 'LogUncaughtExceptionHandler';
_.typeId = 0;
function $clinit_24(){
  $clinit_24 = nullMethod;
  debugTable = $FlexTable(new FlexTable());
  {
    timer_0 = $LogUtil$1(new LogUtil$1());
  }
}

function debug(text){
  $clinit_24();
  var header;
  if (debugTextArea === null) {
    debugTextArea = $TextArea(new TextArea());
    $addStyleName(debugTable, 'log-panel');
    $setTabIndex(debugTextArea, (-1));
    $setWidth(debugTextArea, '700px');
    $setHeight(debugTextArea, '10em');
    setStyleAttribute(debugTextArea.getElement(), 'whiteSpace', 'pre');
    header = $Label_0(new Label(), 'LOG PANEL');
    $addStyleName(header, 'log-header');
    $setWidget(debugTable, 0, 0, header);
    $setWidget(debugTable, 1, 0, debugTextArea);
    $setHorizontalAlignment(debugTable.cellFormatter, 0, 0, ($clinit_68() , ALIGN_CENTER));
    $run(timer_0);
    $addMouseListener(header, $LogUtil$2(new LogUtil$2(), header));
  }
  text = $replaceAll(text, '\t', '    ');
  debugText = debugText + text + '\n';
  $clinit_126() , err_0;
  dirty = true;
}

function debug_0(ex){
  $clinit_24();
  var i, stackTraceElements, text;
  stackTraceElements = ex.stackTrace;
  text = $toString_1(ex) + '\n';
  for (i = 0; i < stackTraceElements.length_0; i++) {
    text = text + '  at ' + stackTraceElements[i] + '\n';
  }
  debug(text);
}

var debugTable, debugText = '', debugTextArea = null, dirty = false, timer_0 = null;
function $clinit_22(){
  $clinit_22 = nullMethod;
  $clinit_41();
}

function $LogUtil$1(this$static){
  $clinit_22();
  $Timer(this$static);
  return this$static;
}

function $run(this$static){
  if ($clinit_24() , dirty) {
    $clinit_24() , dirty = false;
    $setText_0(($clinit_24() , debugTextArea), ($clinit_24() , debugText));
    setElementPropertyInt(($clinit_24() , debugTextArea).getElement(), 'scrollTop', 1000000);
    if (!($clinit_24() , debugTable).isAttached()) {
      $add_0(get(), ($clinit_24() , debugTable), 5, 5);
    }
  }
  this$static.schedule(500);
}

function run_0(){
  $run(this);
}

function LogUtil$1(){
}

_ = LogUtil$1.prototype = new Timer();
_.run = run_0;
_.typeName = package_com_allen_sauer_gwt_log_client_ + 'LogUtil$1';
_.typeId = 20;
function onMouseDown_0(sender, x, y){
}

function onMouseEnter(sender){
}

function onMouseLeave(sender){
}

function onMouseMove_0(sender, x, y){
}

function onMouseUp_0(sender, x, y){
}

function MouseListenerAdapter(){
}

_ = MouseListenerAdapter.prototype = new Object_0();
_.onMouseDown = onMouseDown_0;
_.onMouseEnter = onMouseEnter;
_.onMouseLeave = onMouseLeave;
_.onMouseMove = onMouseMove_0;
_.onMouseUp = onMouseUp_0;
_.typeName = package_com_google_gwt_user_client_ui_ + 'MouseListenerAdapter';
_.typeId = 21;
function $LogUtil$2(this$static, val$header){
  this$static.val$header = val$header;
  return this$static;
}

function onMouseDown(sender, x, y){
  this.dragging = true;
  setCapture(this.val$header.getElement());
  this.dragStartX = x;
  this.dragStartY = y;
}

function onMouseMove(sender, x, y){
  var absX, absY;
  if (this.dragging) {
    absX = x + $getAbsoluteLeft_0(($clinit_24() , debugTable));
    absY = y + $getAbsoluteTop_0(($clinit_24() , debugTable));
    $setWidgetPosition(get(), ($clinit_24() , debugTable), absX - this.dragStartX, absY - this.dragStartY);
  }
}

function onMouseUp(sender, x, y){
  this.dragging = false;
  releaseCapture(this.val$header.getElement());
}

function LogUtil$2(){
}

_ = LogUtil$2.prototype = new MouseListenerAdapter();
_.onMouseDown = onMouseDown;
_.onMouseMove = onMouseMove;
_.onMouseUp = onMouseUp;
_.typeName = package_com_allen_sauer_gwt_log_client_ + 'LogUtil$2';
_.typeId = 22;
_.dragStartX = 0;
_.dragStartY = 0;
_.dragging = false;
function getTypeName(o){
  return o == null?null:o.typeName;
}

function setUncaughtExceptionHandler(handler){
  sUncaughtExceptionHandler = handler;
}

var sUncaughtExceptionHandler = null;
function getHashCode(o){
  return o == null?0:o.$H?o.$H:(o.$H = getNextHashId());
}

function getHashCode_0(o){
  return o == null?0:o.$H?o.$H:(o.$H = getNextHashId());
}

function getNextHashId(){
  return ++sNextHashId;
}

var sNextHashId = 0;
function $clinit_127(){
  $clinit_127 = nullMethod;
  NO_STACK_TRACE = initDims_0('[Ljava.lang.StackTraceElement;', [0], [0], [0], null);
}

function $$init_8(this$static){
  this$static.stackTrace = NO_STACK_TRACE;
}

function $Throwable(this$static){
  $clinit_127();
  $$init_8(this$static);
  return this$static;
}

function $Throwable_0(this$static, message){
  $clinit_127();
  $$init_8(this$static);
  this$static.message_0 = message;
  return this$static;
}

function $toString_1(this$static){
  var className, msg;
  className = getTypeName(this$static);
  msg = this$static.message_0;
  if (msg !== null) {
    return className + ': ' + msg;
  }
   else {
    return className;
  }
}

function toString_9(){
  return $toString_1(this);
}

function Throwable(){
}

_ = Throwable.prototype = new Object_0();
_.toString_0 = toString_9;
_.typeName = package_java_lang_ + 'Throwable';
_.typeId = 23;
_.message_0 = null;
var NO_STACK_TRACE;
function $clinit_113(){
  $clinit_113 = nullMethod;
  $clinit_127();
}

function $Exception(this$static){
  $clinit_113();
  $Throwable(this$static);
  return this$static;
}

function $Exception_0(this$static, message){
  $clinit_113();
  $Throwable_0(this$static, message);
  return this$static;
}

function Exception(){
}

_ = Exception.prototype = new Throwable();
_.typeName = package_java_lang_ + 'Exception';
_.typeId = 24;
function $clinit_123(){
  $clinit_123 = nullMethod;
  $clinit_113();
}

function $RuntimeException(this$static){
  $clinit_123();
  $Exception(this$static);
  return this$static;
}

function $RuntimeException_0(this$static, message){
  $clinit_123();
  $Exception_0(this$static, message);
  return this$static;
}

function RuntimeException(){
}

_ = RuntimeException.prototype = new Exception();
_.typeName = package_java_lang_ + 'RuntimeException';
_.typeId = 25;
function $clinit_30(){
  $clinit_30 = nullMethod;
  $clinit_123();
}

function $JavaScriptException(this$static, name, description){
  $clinit_30();
  $RuntimeException_0(this$static, 'JavaScript ' + name + ' exception: ' + description);
  return this$static;
}

function JavaScriptException(){
}

_ = JavaScriptException.prototype = new RuntimeException();
_.typeName = package_com_google_gwt_core_client_ + 'JavaScriptException';
_.typeId = 26;
function $equals(this$static, other){
  if (!instanceOf(other, 2)) {
    return false;
  }
  return equalsImpl(this$static, dynamicCast(other, 2));
}

function $hashCode(this$static){
  return getHashCode(this$static);
}

function createArray(){
  return [];
}

function createObject(){
  return {};
}

function equals(other){
  return $equals(this, other);
}

function equalsImpl(o, other){
  return o === other;
}

function hashCode_0(){
  return $hashCode(this);
}

function toString_1(){
  return toStringImpl(this);
}

function toStringImpl(o){
  if (o.toString)
    return o.toString();
  return '[object]';
}

function JavaScriptObject(){
}

_ = JavaScriptObject.prototype = new Object_0();
_.equals = equals;
_.hashCode = hashCode_0;
_.toString_0 = toString_1;
_.typeName = package_com_google_gwt_core_client_ + 'JavaScriptObject';
_.typeId = 27;
function $Array(this$static, length, typeId, queryId, typeName){
  this$static.length_0 = length;
  this$static.queryId = queryId;
  this$static.typeName = typeName;
  this$static.typeId = typeId;
  return this$static;
}

function _set(array, index, value){
  return array[index] = value;
}

function getIntValue(values, index){
  return values[index];
}

function getValueCount(values){
  return values.length;
}

function initDims_0(typeName, typeIdExprs, queryIdExprs, dimExprs, defaultValue){
  return initDims(typeName, typeIdExprs, queryIdExprs, dimExprs, 0, getValueCount(dimExprs), defaultValue);
}

function initDims(typeName, typeIdExprs, queryIdExprs, dimExprs, index, count, defaultValue){
  var i, length, result;
  if ((length = getIntValue(dimExprs, index)) < 0) {
    throw $NegativeArraySizeException(new NegativeArraySizeException());
  }
  result = $Array(new Array_0(), length, getIntValue(typeIdExprs, index), getIntValue(queryIdExprs, index), typeName);
  ++index;
  if (index < count) {
    typeName = $substring(typeName, 1);
    for (i = 0; i < length; ++i) {
      _set(result, i, initDims(typeName, typeIdExprs, queryIdExprs, dimExprs, index, count, defaultValue));
    }
  }
   else {
    for (i = 0; i < length; ++i) {
      _set(result, i, defaultValue);
    }
  }
  return result;
}

function setCheck(array, index, value){
  if (value !== null && array.queryId != 0 && !instanceOf(value, array.queryId)) {
    throw $ArrayStoreException(new ArrayStoreException());
  }
  return _set(array, index, value);
}

function Array_0(){
}

_ = Array_0.prototype = new Object_0();
_.typeName = package_com_google_gwt_lang_ + 'Array';
_.typeId = 0;
function canCast(srcId, dstId){
  if (!srcId)
    return false;
  return !(!typeIdArray[srcId][dstId]);
}

function charToString(x){
  return String.fromCharCode(x);
}

function dynamicCast(src, dstId){
  if (src != null)
    canCast(src.typeId, dstId) || throwClassCastException();
  return src;
}

function instanceOf(src, dstId){
  if (src == null)
    return false;
  return canCast(src.typeId, dstId);
}

function narrow_char(x){
  return x & 65535;
}

function round_int(x){
  if (x > ($clinit_117() , MAX_VALUE))
    return $clinit_117() , MAX_VALUE;
  if (x < ($clinit_117() , MIN_VALUE))
    return $clinit_117() , MIN_VALUE;
  return x >= 0?Math.floor(x):Math.ceil(x);
}

function round_long(x){
  if (x > ($clinit_118() , MAX_VALUE_0))
    return $clinit_118() , MAX_VALUE_0;
  if (x < ($clinit_118() , MIN_VALUE_0))
    return $clinit_118() , MIN_VALUE_0;
  return x >= 0?Math.floor(x):Math.ceil(x);
}

function throwClassCastException(){
  throw $ClassCastException(new ClassCastException());
}

function throwClassCastExceptionUnlessNull(o){
  if (o !== null) {
    throw $ClassCastException(new ClassCastException());
  }
  return o;
}

function wrapJSO(jso, seed){
  _ = seed.prototype;
  if (jso && !(jso.typeId >= _.typeId)) {
    var oldToString = jso.toString;
    for (var i in _) {
      jso[i] = _[i];
    }
    jso.toString = oldToString;
  }
  return jso;
}

var typeIdArray;
function caught(e){
  if (instanceOf(e, 3)) {
    return e;
  }
  return $JavaScriptException(new JavaScriptException(), javaScriptExceptionName(e), javaScriptExceptionDescription(e));
}

function javaScriptExceptionDescription(e){
  return e.message;
}

function javaScriptExceptionName(e){
  return e.name;
}

function $clinit_35(){
  $clinit_35 = nullMethod;
  sEventPreviewStack = $ArrayList(new ArrayList());
  {
    impl = new DOMImplOpera();
    $init_1(impl);
  }
}

function appendChild(parent, child){
  $clinit_35();
  $appendChild(impl, parent, child);
}

function compare(elem1, elem2){
  $clinit_35();
  return $compare(impl, elem1, elem2);
}

function createButton(){
  $clinit_35();
  return $createElement(impl, 'button');
}

function createDiv(){
  $clinit_35();
  return $createElement(impl, 'div');
}

function createImg(){
  $clinit_35();
  return $createElement(impl, 'img');
}

function createTBody(){
  $clinit_35();
  return $createElement(impl, 'tbody');
}

function createTR(){
  $clinit_35();
  return $createElement(impl, 'tr');
}

function createTable(){
  $clinit_35();
  return $createElement(impl, 'table');
}

function createTextArea(){
  $clinit_35();
  return $createElement(impl, 'textarea');
}

function dispatchEvent(evt, elem, listener){
  $clinit_35();
  var handler;
  handler = sUncaughtExceptionHandler;
  if (handler !== null) {
    dispatchEventAndCatch(evt, elem, listener, handler);
  }
   else {
    dispatchEventImpl(evt, elem, listener);
  }
}

function dispatchEventAndCatch(evt, elem, listener, handler){
  $clinit_35();
  var $e0, e;
  try {
    dispatchEventImpl(evt, elem, listener);
  }
   catch ($e0) {
    $e0 = caught($e0);
    if (instanceOf($e0, 3)) {
      e = $e0;
      $onUncaughtException(handler, e);
    }
     else 
      throw $e0;
  }
}

function dispatchEventImpl(evt, elem, listener){
  $clinit_35();
  if (elem === sCaptureElem) {
    if (eventGetType(evt) == 8192) {
      sCaptureElem = null;
    }
  }
  listener.onBrowserEvent(evt);
}

function eventCancelBubble(evt, cancel){
  $clinit_35();
  $eventCancelBubble(impl, evt, cancel);
}

function eventGetAltKey(evt){
  $clinit_35();
  return $eventGetAltKey(impl, evt);
}

function eventGetClientX(evt){
  $clinit_35();
  return $eventGetClientX(impl, evt);
}

function eventGetClientY(evt){
  $clinit_35();
  return $eventGetClientY(impl, evt);
}

function eventGetCtrlKey(evt){
  $clinit_35();
  return $eventGetCtrlKey(impl, evt);
}

function eventGetFromElement(evt){
  $clinit_35();
  return $eventGetFromElement(impl, evt);
}

function eventGetKeyCode(evt){
  $clinit_35();
  return $eventGetKeyCode(impl, evt);
}

function eventGetMetaKey(evt){
  $clinit_35();
  return $eventGetMetaKey(impl, evt);
}

function eventGetShiftKey(evt){
  $clinit_35();
  return $eventGetShiftKey(impl, evt);
}

function eventGetToElement(evt){
  $clinit_35();
  return $eventGetToElement(impl, evt);
}

function eventGetType(evt){
  $clinit_35();
  return $eventGetTypeInt(impl, evt);
}

function eventPreventDefault(evt){
  $clinit_35();
  $eventPreventDefault(impl, evt);
}

function eventToString(evt){
  $clinit_35();
  return $eventToString(impl, evt);
}

function getAbsoluteLeft(elem){
  $clinit_35();
  return $getAbsoluteLeft(impl, elem);
}

function getAbsoluteTop(elem){
  $clinit_35();
  return $getAbsoluteTop(impl, elem);
}

function getElementProperty(elem, prop){
  $clinit_35();
  return $getElementProperty(impl, elem, prop);
}

function getElementPropertyInt(elem, prop){
  $clinit_35();
  return $getElementPropertyInt(impl, elem, prop);
}

function getEventsSunk(elem){
  $clinit_35();
  return $getEventsSunk(impl, elem);
}

function getFirstChild(elem){
  $clinit_35();
  return $getFirstChild(impl, elem);
}

function getParent(elem){
  $clinit_35();
  return $getParent(impl, elem);
}

function insertChild(parent, child, index){
  $clinit_35();
  $insertChild(impl, parent, child, index);
}

function isOrHasChild(parent, child){
  $clinit_35();
  return $isOrHasChild(impl, parent, child);
}

function previewEvent(evt){
  $clinit_35();
  var preview, ret;
  ret = true;
  if (sEventPreviewStack.size > 0) {
    preview = throwClassCastExceptionUnlessNull($get(sEventPreviewStack, sEventPreviewStack.size - 1));
    if (!(ret = null.nullMethod())) {
      eventCancelBubble(evt, true);
      eventPreventDefault(evt);
    }
  }
  return ret;
}

function releaseCapture(elem){
  $clinit_35();
  if (sCaptureElem !== null && compare(elem, sCaptureElem)) {
    sCaptureElem = null;
  }
  $releaseCapture(impl, elem);
}

function removeChild(parent, child){
  $clinit_35();
  $removeChild(impl, parent, child);
}

function setCapture(elem){
  $clinit_35();
  sCaptureElem = elem;
  $setCapture(impl, elem);
}

function setElementProperty(elem, prop, value){
  $clinit_35();
  $setElementProperty(impl, elem, prop, value);
}

function setElementPropertyInt(elem, prop, value){
  $clinit_35();
  $setElementPropertyInt(impl, elem, prop, value);
}

function setEventListener(elem, listener){
  $clinit_35();
  $setEventListener(impl, elem, listener);
}

function setImgSrc(img, src){
  $clinit_35();
  $setImgSrc(impl, img, src);
}

function setInnerHTML(elem, html){
  $clinit_35();
  $setInnerHTML(impl, elem, html);
}

function setInnerText(elem, text){
  $clinit_35();
  $setInnerText(impl, elem, text);
}

function setStyleAttribute(elem, attr, value){
  $clinit_35();
  $setStyleAttribute(impl, elem, attr, value);
}

function sinkEvents(elem, eventBits){
  $clinit_35();
  $sinkEvents(impl, elem, eventBits);
}

function toString_2(elem){
  $clinit_35();
  return $toString(impl, elem);
}

function windowGetClientHeight(){
  $clinit_35();
  return $windowGetClientHeight(impl);
}

function windowGetClientWidth(){
  $clinit_35();
  return $windowGetClientWidth(impl);
}

var impl = null, sCaptureElem = null, sEventPreviewStack;
function equals_0(other){
  if (instanceOf(other, 4)) {
    return compare(this, dynamicCast(other, 4));
  }
  return $equals(wrapJSO(this, Element), other);
}

function hashCode_1(){
  return $hashCode(wrapJSO(this, Element));
}

function toString_3(){
  return toString_2(this);
}

function Element(){
}

_ = Element.prototype = new JavaScriptObject();
_.equals = equals_0;
_.hashCode = hashCode_1;
_.toString_0 = toString_3;
_.typeName = package_com_google_gwt_user_client_ + 'Element';
_.typeId = 28;
function equals_1(other){
  return $equals(wrapJSO(this, Event), other);
}

function hashCode_2(){
  return $hashCode(wrapJSO(this, Event));
}

function toString_4(){
  return eventToString(this);
}

function Event(){
}

_ = Event.prototype = new JavaScriptObject();
_.equals = equals_1;
_.hashCode = hashCode_2;
_.toString_0 = toString_4;
_.typeName = package_com_google_gwt_user_client_ + 'Event';
_.typeId = 29;
function nextDouble(){
  return Math.random();
}

function nextInt(upperBound){
  return ~(~Math.floor(Math.random() * upperBound));
}

function onWindowClosed(){
  while (($clinit_41() , timers).size > 0) {
    $cancel(dynamicCast($get(($clinit_41() , timers), 0), 5));
  }
}

function onWindowClosing(){
  return null;
}

function Timer$1(){
}

_ = Timer$1.prototype = new Object_0();
_.onWindowClosed = onWindowClosed;
_.onWindowClosing = onWindowClosing;
_.typeName = package_com_google_gwt_user_client_ + 'Timer$1';
_.typeId = 30;
function $clinit_44(){
  $clinit_44 = nullMethod;
  closingListeners = $ArrayList(new ArrayList());
  resizeListeners = $ArrayList(new ArrayList());
  {
    init_2();
  }
}

function addWindowCloseListener(listener){
  $clinit_44();
  $add_2(closingListeners, listener);
}

function addWindowResizeListener(listener){
  $clinit_44();
  $add_2(resizeListeners, listener);
}

function enableScrolling(enable){
  $clinit_44();
  $doc.body.style.overflow = enable?'auto':'hidden';
}

function fireClosedAndCatch(handler){
  $clinit_44();
  var $e0, e;
  try {
    fireClosedImpl();
  }
   catch ($e0) {
    $e0 = caught($e0);
    if (instanceOf($e0, 3)) {
      e = $e0;
      $onUncaughtException(handler, e);
    }
     else 
      throw $e0;
  }
}

function fireClosedImpl(){
  $clinit_44();
  var it, listener;
  for (it = $iterator_0(closingListeners); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 6);
    listener.onWindowClosed();
  }
}

function fireClosingAndCatch(handler){
  $clinit_44();
  var $e0, e;
  try {
    return fireClosingImpl();
  }
   catch ($e0) {
    $e0 = caught($e0);
    if (instanceOf($e0, 3)) {
      e = $e0;
      $onUncaughtException(handler, e);
      return null;
    }
     else 
      throw $e0;
  }
}

function fireClosingImpl(){
  $clinit_44();
  var it, listener, msg, ret;
  ret = null;
  for (it = $iterator_0(closingListeners); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 6);
    msg = listener.onWindowClosing();
    {
      ret = msg;
    }
  }
  return ret;
}

function fireResizedAndCatch(handler){
  $clinit_44();
  var $e0, e;
  try {
    fireResizedImpl();
  }
   catch ($e0) {
    $e0 = caught($e0);
    if (instanceOf($e0, 3)) {
      e = $e0;
      $onUncaughtException(handler, e);
    }
     else 
      throw $e0;
  }
}

function fireResizedImpl(){
  $clinit_44();
  var it, listener;
  for (it = $iterator_0(resizeListeners); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 7);
    listener.onWindowResized(getClientWidth(), getClientHeight());
  }
}

function getClientHeight(){
  $clinit_44();
  return windowGetClientHeight();
}

function getClientWidth(){
  $clinit_44();
  return windowGetClientWidth();
}

function getScrollLeft(){
  $clinit_44();
  return $doc.documentElement.scrollLeft || $doc.body.scrollLeft;
}

function getScrollTop(){
  $clinit_44();
  return $doc.documentElement.scrollTop || $doc.body.scrollTop;
}

function init_2(){
  $clinit_44();
  __gwt_initHandlers(function(){
    onResize();
  }
  , function(){
    return onClosing();
  }
  , function(){
    onClosed();
    $wnd.onresize = null;
    $wnd.onbeforeclose = null;
    $wnd.onclose = null;
  }
  );
}

function onClosed(){
  $clinit_44();
  var handler;
  handler = sUncaughtExceptionHandler;
  if (handler !== null) {
    fireClosedAndCatch(handler);
  }
   else {
    fireClosedImpl();
  }
}

function onClosing(){
  $clinit_44();
  var handler;
  handler = sUncaughtExceptionHandler;
  if (handler !== null) {
    return fireClosingAndCatch(handler);
  }
   else {
    return fireClosingImpl();
  }
}

function onResize(){
  $clinit_44();
  var handler;
  handler = sUncaughtExceptionHandler;
  if (handler !== null) {
    fireResizedAndCatch(handler);
  }
   else {
    fireResizedImpl();
  }
}

var closingListeners, resizeListeners;
function $appendChild(this$static, parent, child){
  parent.appendChild(child);
}

function $createElement(this$static, tag){
  return $doc.createElement(tag);
}

function $eventCancelBubble(this$static, evt, cancel){
  evt.cancelBubble = cancel;
}

function $eventGetAltKey(this$static, evt){
  return evt.altKey;
}

function $eventGetClientX(this$static, evt){
  return evt.clientX;
}

function $eventGetClientY(this$static, evt){
  return evt.clientY;
}

function $eventGetCtrlKey(this$static, evt){
  return evt.ctrlKey;
}

function $eventGetKeyCode(this$static, evt){
  return evt.which || evt.keyCode;
}

function $eventGetMetaKey(this$static, evt){
  return !(!evt.metaKey);
}

function $eventGetShiftKey(this$static, evt){
  return evt.shiftKey;
}

function $eventGetTypeInt(this$static, evt){
  switch (evt.type) {
    case 'blur':
      return 4096;
    case 'change':
      return 1024;
    case 'click':
      return 1;
    case 'dblclick':
      return 2;
    case 'focus':
      return 2048;
    case 'keydown':
      return 128;
    case 'keypress':
      return 256;
    case 'keyup':
      return 512;
    case 'load':
      return 32768;
    case 'losecapture':
      return 8192;
    case 'mousedown':
      return 4;
    case 'mousemove':
      return 64;
    case 'mouseout':
      return 32;
    case 'mouseover':
      return 16;
    case 'mouseup':
      return 8;
    case 'scroll':
      return 16384;
    case 'error':
      return 65536;
    case 'mousewheel':
      return 131072;
    case 'DOMMouseScroll':
      return 131072;
  }
}

function $getElementProperty(this$static, elem, prop){
  var ret = elem[prop];
  return ret == null?null:String(ret);
}

function $getElementPropertyInt(this$static, elem, prop){
  var i = parseInt(elem[prop]);
  if (!i) {
    return 0;
  }
  return i;
}

function $getEventsSunk(this$static, elem){
  return elem.__eventBits || 0;
}

function $removeChild(this$static, parent, child){
  parent.removeChild(child);
}

function $setElementProperty(this$static, elem, prop, value){
  elem[prop] = value;
}

function $setElementPropertyInt(this$static, elem, prop, value){
  elem[prop] = value;
}

function $setEventListener(this$static, elem, listener){
  elem.__listener = listener;
}

function $setImgSrc(this$static, img, src){
  img.src = src;
}

function $setInnerHTML(this$static, elem, html){
  if (!html) {
    html = '';
  }
  elem.innerHTML = html;
}

function $setInnerText(this$static, elem, text){
  while (elem.firstChild) {
    elem.removeChild(elem.firstChild);
  }
  if (text != null) {
    elem.appendChild($doc.createTextNode(text));
  }
}

function $setStyleAttribute(this$static, elem, attr, value){
  elem.style[attr] = value;
}

function $toString(this$static, elem){
  return elem.outerHTML;
}

function $windowGetClientHeight(this$static){
  return $doc.body.clientHeight;
}

function $windowGetClientWidth(this$static){
  return $doc.body.clientWidth;
}

function DOMImpl(){
}

_ = DOMImpl.prototype = new Object_0();
_.typeName = package_com_google_gwt_user_client_impl_ + 'DOMImpl';
_.typeId = 0;
function $compare(this$static, elem1, elem2){
  return elem1 == elem2;
}

function $eventGetFromElement(this$static, evt){
  return evt.relatedTarget?evt.relatedTarget:null;
}

function $eventGetToElement(this$static, evt){
  return evt.relatedTarget || null;
}

function $eventPreventDefault(this$static, evt){
  evt.preventDefault();
}

function $eventToString(this$static, evt){
  return evt.toString();
}

function $getFirstChild(this$static, elem){
  var child = elem.firstChild;
  while (child && child.nodeType != 1)
    child = child.nextSibling;
  return child || null;
}

function $getParent(this$static, elem){
  var parent = elem.parentNode;
  if (parent == null) {
    return null;
  }
  if (parent.nodeType != 1)
    parent = null;
  return parent || null;
}

function $init_1(this$static){
  $wnd.__dispatchCapturedMouseEvent = function(evt){
    if ($wnd.__dispatchCapturedEvent(evt)) {
      var cap = $wnd.__captureElem;
      if (cap && cap.__listener) {
        dispatchEvent(evt, cap, cap.__listener);
        evt.stopPropagation();
      }
    }
  }
  ;
  $wnd.__dispatchCapturedEvent = function(evt){
    if (!previewEvent(evt)) {
      evt.stopPropagation();
      evt.preventDefault();
      return false;
    }
    return true;
  }
  ;
  $wnd.addEventListener('click', $wnd.__dispatchCapturedMouseEvent, true);
  $wnd.addEventListener('dblclick', $wnd.__dispatchCapturedMouseEvent, true);
  $wnd.addEventListener('mousedown', $wnd.__dispatchCapturedMouseEvent, true);
  $wnd.addEventListener('mouseup', $wnd.__dispatchCapturedMouseEvent, true);
  $wnd.addEventListener('mousemove', $wnd.__dispatchCapturedMouseEvent, true);
  $wnd.addEventListener('mousewheel', $wnd.__dispatchCapturedMouseEvent, true);
  $wnd.addEventListener('keydown', $wnd.__dispatchCapturedEvent, true);
  $wnd.addEventListener('keyup', $wnd.__dispatchCapturedEvent, true);
  $wnd.addEventListener('keypress', $wnd.__dispatchCapturedEvent, true);
  $wnd.__dispatchEvent = function(evt){
    if (this.__listener)
      dispatchEvent(evt, this, this.__listener);
  }
  ;
  $wnd.__captureElem = null;
}

function $insertChild(this$static, parent, toAdd, index){
  var count = 0, child = parent.firstChild, before = null;
  while (child) {
    if (child.nodeType == 1) {
      if (count == index) {
        before = child;
        break;
      }
      ++count;
    }
    child = child.nextSibling;
  }
  parent.insertBefore(toAdd, before);
}

function $isOrHasChild(this$static, parent, child){
  while (child) {
    if (parent == child) {
      return true;
    }
    child = child.parentNode;
    if (child && child.nodeType != 1) {
      child = null;
    }
  }
  return false;
}

function $releaseCapture(this$static, elem){
  if (elem == $wnd.__captureElem)
    $wnd.__captureElem = null;
}

function $setCapture(this$static, elem){
  $wnd.__captureElem = elem;
}

function $sinkEvents(this$static, elem, bits){
  elem.__eventBits = bits;
  elem.onclick = bits & 1?$wnd.__dispatchEvent:null;
  elem.ondblclick = bits & 2?$wnd.__dispatchEvent:null;
  elem.onmousedown = bits & 4?$wnd.__dispatchEvent:null;
  elem.onmouseup = bits & 8?$wnd.__dispatchEvent:null;
  elem.onmouseover = bits & 16?$wnd.__dispatchEvent:null;
  elem.onmouseout = bits & 32?$wnd.__dispatchEvent:null;
  elem.onmousemove = bits & 64?$wnd.__dispatchEvent:null;
  elem.onkeydown = bits & 128?$wnd.__dispatchEvent:null;
  elem.onkeypress = bits & 256?$wnd.__dispatchEvent:null;
  elem.onkeyup = bits & 512?$wnd.__dispatchEvent:null;
  elem.onchange = bits & 1024?$wnd.__dispatchEvent:null;
  elem.onfocus = bits & 2048?$wnd.__dispatchEvent:null;
  elem.onblur = bits & 4096?$wnd.__dispatchEvent:null;
  elem.onlosecapture = bits & 8192?$wnd.__dispatchEvent:null;
  elem.onscroll = bits & 16384?$wnd.__dispatchEvent:null;
  elem.onload = bits & 32768?$wnd.__dispatchEvent:null;
  elem.onerror = bits & 65536?$wnd.__dispatchEvent:null;
  elem.onmousewheel = bits & 131072?$wnd.__dispatchEvent:null;
}

function DOMImplStandard(){
}

_ = DOMImplStandard.prototype = new DOMImpl();
_.typeName = package_com_google_gwt_user_client_impl_ + 'DOMImplStandard';
_.typeId = 0;
function $getAbsoluteLeft(this$static, elem){
  var left = 0;
  var curr = elem.parentNode;
  while (curr != $doc.body) {
    if (curr.tagName != 'TR' && curr.tagName != 'TBODY') {
      left -= curr.scrollLeft;
    }
    curr = curr.parentNode;
  }
  while (elem) {
    left += elem.offsetLeft;
    elem = elem.offsetParent;
  }
  return left;
}

function $getAbsoluteTop(this$static, elem){
  var top = 0;
  var curr = elem.parentNode;
  while (curr != $doc.body) {
    if (curr.tagName != 'TR' && curr.tagName != 'TBODY') {
      top -= curr.scrollTop;
    }
    curr = curr.parentNode;
  }
  while (elem) {
    top += elem.offsetTop;
    elem = elem.offsetParent;
  }
  return top;
}

function DOMImplOpera(){
}

_ = DOMImplOpera.prototype = new DOMImplStandard();
_.typeName = package_com_google_gwt_user_client_impl_ + 'DOMImplOpera';
_.typeId = 0;
function $adopt(this$static, w, container){
  $removeFromParent(w);
  if (container !== null) {
    appendChild(container, w.getElement());
  }
  $setParent(w, this$static);
}

function $disown(this$static, w){
  var elem;
  if (w.parent !== this$static) {
    throw $IllegalArgumentException(new IllegalArgumentException(), 'w is not a child of this panel');
  }
  elem = w.getElement();
  $setParent(w, null);
  removeChild(getParent(elem), elem);
}

function disown_0(w){
  $disown(this, w);
}

function doAttachChildren(){
  var child, it;
  for (it = this.iterator(); it.hasNext();) {
    child = dynamicCast(it.next_0(), 9);
    child.onAttach();
  }
}

function doDetachChildren(){
  var child, it;
  for (it = this.iterator(); it.hasNext();) {
    child = dynamicCast(it.next_0(), 9);
    child.onDetach();
  }
}

function onLoad(){
}

function onUnload(){
}

function Panel(){
}

_ = Panel.prototype = new Widget();
_.disown = disown_0;
_.doAttachChildren = doAttachChildren;
_.doDetachChildren = doDetachChildren;
_.onLoad = onLoad;
_.onUnload = onUnload;
_.typeName = package_com_google_gwt_user_client_ui_ + 'Panel';
_.typeId = 31;
function $$init_2(this$static){
  this$static.children = $WidgetCollection(new WidgetCollection(), this$static);
}

function $ComplexPanel(this$static){
  $$init_2(this$static);
  return this$static;
}

function $add_1(this$static, w, container){
  return $insert(this$static, w, container, this$static.children.size);
}

function $getWidgetIndex(this$static, child){
  return $indexOf(this$static.children, child);
}

function $insert(this$static, w, container, beforeIndex){
  var idx;
  if (beforeIndex < 0 || beforeIndex > this$static.children.size) {
    throw $IndexOutOfBoundsException(new IndexOutOfBoundsException());
  }
  idx = $getWidgetIndex(this$static, w);
  if (idx == (-1)) {
    $removeFromParent(w);
  }
   else {
    $remove(this$static, w);
    if (idx < beforeIndex) {
      beforeIndex--;
    }
  }
  $adopt(this$static, w, container);
  $insert_0(this$static.children, w, beforeIndex);
  return beforeIndex;
}

function $remove(this$static, w){
  if (!$contains(this$static.children, w)) {
    return false;
  }
  this$static.disown(w);
  $remove_3(this$static.children, w);
  return true;
}

function iterator_0(){
  return $iterator(this.children);
}

function remove_0(w){
  return $remove(this, w);
}

function ComplexPanel(){
}

_ = ComplexPanel.prototype = new Panel();
_.iterator = iterator_0;
_.remove_0 = remove_0;
_.typeName = package_com_google_gwt_user_client_ui_ + 'ComplexPanel';
_.typeId = 32;
function $AbsolutePanel(this$static){
  $ComplexPanel(this$static);
  this$static.setElement(createDiv());
  setStyleAttribute(this$static.getElement(), 'position', 'relative');
  setStyleAttribute(this$static.getElement(), 'overflow', 'hidden');
  return this$static;
}

function $add(this$static, w){
  $add_1(this$static, w, this$static.getElement());
}

function $add_0(this$static, w, left, top){
  $removeFromParent(w);
  $setWidgetPositionImpl(this$static, w, left, top);
  $add(this$static, w);
}

function $checkWidgetParent(this$static, w){
  if (w.parent !== this$static) {
    throw $IllegalArgumentException(new IllegalArgumentException(), 'Widget must be a child of this panel.');
  }
}

function $setWidgetPosition(this$static, w, left, top){
  $checkWidgetParent(this$static, w);
  $setWidgetPositionImpl(this$static, w, left, top);
}

function $setWidgetPositionImpl(this$static, w, left, top){
  var h;
  h = w.getElement();
  if (left == (-1) && top == (-1)) {
    changeToStaticPositioning(h);
  }
   else {
    setStyleAttribute(h, 'position', 'absolute');
    setStyleAttribute(h, 'left', left + 'px');
    setStyleAttribute(h, 'top', top + 'px');
  }
}

function changeToStaticPositioning(elem){
  setStyleAttribute(elem, 'left', '');
  setStyleAttribute(elem, 'top', '');
  setStyleAttribute(elem, 'position', '');
}

function disown(w){
  $disown(this, w);
  changeToStaticPositioning(w.getElement());
}

function AbsolutePanel(){
}

_ = AbsolutePanel.prototype = new ComplexPanel();
_.disown = disown;
_.typeName = package_com_google_gwt_user_client_ui_ + 'AbsolutePanel';
_.typeId = 33;
function $advanceToFind(this$static, iter, o){
  var t;
  while (iter.hasNext()) {
    t = iter.next_0();
    if (o === null?t === null:o.equals(t)) {
      return iter;
    }
  }
  return null;
}

function add_0(o){
  throw $UnsupportedOperationException(new UnsupportedOperationException(), 'add');
}

function contains(o){
  var iter;
  iter = $advanceToFind(this, this.iterator(), o);
  return iter !== null;
}

function toString_10(){
  var comma, iter, sb;
  sb = $StringBuffer(new StringBuffer());
  comma = null;
  $append(sb, '[');
  iter = this.iterator();
  while (iter.hasNext()) {
    if (comma !== null) {
      $append(sb, comma);
    }
     else {
      comma = ', ';
    }
    $append(sb, valueOf(iter.next_0()));
  }
  $append(sb, ']');
  return $toString_0(sb);
}

function AbstractCollection(){
}

_ = AbstractCollection.prototype = new Object_0();
_.add_0 = add_0;
_.contains = contains;
_.toString_0 = toString_10;
_.typeName = package_java_util_ + 'AbstractCollection';
_.typeId = 0;
function $indexOutOfBounds(this$static, i){
  throw $IndexOutOfBoundsException_0(new IndexOutOfBoundsException(), 'Index: ' + i + ', Size: ' + this$static.size);
}

function $iterator_0(this$static){
  return $AbstractList$IteratorImpl(new AbstractList$IteratorImpl(), this$static);
}

function add_1(index, element){
  throw $UnsupportedOperationException(new UnsupportedOperationException(), 'add');
}

function add_2(obj){
  this.add(this.size_0(), obj);
  return true;
}

function equals_4(o){
  var elem, elemOther, iter, iterOther, other;
  if (o === this) {
    return true;
  }
  if (!instanceOf(o, 18)) {
    return false;
  }
  other = dynamicCast(o, 18);
  if (this.size_0() != other.size_0()) {
    return false;
  }
  iter = $iterator_0(this);
  iterOther = other.iterator();
  while ($hasNext_0(iter)) {
    elem = $next(iter);
    elemOther = $next(iterOther);
    if (!(elem === null?elemOther === null:elem.equals(elemOther))) {
      return false;
    }
  }
  return true;
}

function hashCode_5(){
  var coeff, iter, k, obj;
  k = 1;
  coeff = 31;
  iter = $iterator_0(this);
  while ($hasNext_0(iter)) {
    obj = $next(iter);
    k = 31 * k + (obj === null?0:obj.hashCode());
  }
  return k;
}

function iterator_3(){
  return $iterator_0(this);
}

function remove_3(index){
  throw $UnsupportedOperationException(new UnsupportedOperationException(), 'remove');
}

function AbstractList(){
}

_ = AbstractList.prototype = new AbstractCollection();
_.add = add_1;
_.add_0 = add_2;
_.equals = equals_4;
_.hashCode = hashCode_5;
_.iterator = iterator_3;
_.remove = remove_3;
_.typeName = package_java_util_ + 'AbstractList';
_.typeId = 34;
function $$init_9(this$static){
  {
    $clearImpl(this$static);
  }
}

function $ArrayList(this$static){
  $$init_9(this$static);
  return this$static;
}

function $add_2(this$static, o){
  setImpl(this$static.array, this$static.size++, o);
  return true;
}

function $addAll(this$static, c){
  var changed, iter;
  iter = $iterator_0(c);
  changed = iter.hasNext();
  while (iter.hasNext()) {
    setImpl(this$static.array, this$static.size++, iter.next_0());
  }
  return changed;
}

function $clearImpl(this$static){
  this$static.array = createArray();
  this$static.size = 0;
}

function $get(this$static, index){
  if (index < 0 || index >= this$static.size) {
    $indexOutOfBounds(this$static, index);
  }
  return getImpl(this$static.array, index);
}

function $indexOf_2(this$static, o){
  return $indexOf_3(this$static, o, 0);
}

function $indexOf_3(this$static, o, index){
  if (index < 0) {
    $indexOutOfBounds(this$static, index);
  }
  for (; index < this$static.size; ++index) {
    if (equals_7(o, getImpl(this$static.array, index))) {
      return index;
    }
  }
  return (-1);
}

function $isEmpty(this$static){
  return this$static.size == 0;
}

function $remove_5(this$static, index){
  var previous;
  previous = $get(this$static, index);
  removeRangeImpl(this$static.array, index, 1);
  --this$static.size;
  return previous;
}

function $remove_6(this$static, o){
  var i;
  i = $indexOf_2(this$static, o);
  if (i == (-1)) {
    return false;
  }
  $remove_5(this$static, i);
  return true;
}

function $set(this$static, index, o){
  var previous;
  previous = $get(this$static, index);
  setImpl(this$static.array, index, o);
  return previous;
}

function add_3(index, o){
  if (index < 0 || index > this.size) {
    $indexOutOfBounds(this, index);
  }
  addImpl(this.array, index, o);
  ++this.size;
}

function add_4(o){
  return $add_2(this, o);
}

function addImpl(array, index, o){
  array.splice(index, 0, o);
}

function contains_2(o){
  return $indexOf_2(this, o) != (-1);
}

function equals_7(a, b){
  return a === b || a !== null && a.equals(b);
}

function get_2(index){
  return $get(this, index);
}

function getImpl(array, index){
  return array[index];
}

function remove_4(index){
  return $remove_5(this, index);
}

function removeRangeImpl(array, index, count){
  array.splice(index, count);
}

function setImpl(array, index, o){
  array[index] = o;
}

function size_2(){
  return this.size;
}

function ArrayList(){
}

_ = ArrayList.prototype = new AbstractList();
_.add = add_3;
_.add_0 = add_4;
_.contains = contains_2;
_.get = get_2;
_.remove = remove_4;
_.size_0 = size_2;
_.typeName = package_java_util_ + 'ArrayList';
_.typeId = 35;
_.array = null;
_.size = 0;
function $ClickListenerCollection(this$static){
  $ArrayList(this$static);
  return this$static;
}

function $fireClick(this$static, sender){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 8);
    listener.onClick(sender);
  }
}

function ClickListenerCollection(){
}

_ = ClickListenerCollection.prototype = new ArrayList();
_.typeName = package_com_google_gwt_user_client_ui_ + 'ClickListenerCollection';
_.typeId = 36;
function $$init_5(this$static){
  this$static.widgetMap = $HTMLTable$WidgetMapper(new HTMLTable$WidgetMapper());
}

function $HTMLTable(this$static){
  $$init_5(this$static);
  this$static.tableElem = createTable();
  this$static.bodyElem = createTBody();
  appendChild(this$static.tableElem, this$static.bodyElem);
  this$static.setElement(this$static.tableElem);
  $sinkEvents_0(this$static, 1);
  return this$static;
}

function $checkRowBounds(this$static, row){
  var rowSize;
  rowSize = $getRowCount(this$static);
  if (row >= rowSize || row < 0) {
    throw $IndexOutOfBoundsException_0(new IndexOutOfBoundsException(), 'Row index: ' + row + ', Row size: ' + rowSize);
  }
}

function $cleanCell(this$static, row, column, clearInnerHTML){
  var td;
  td = $getRawElement(this$static.cellFormatter, row, column);
  $internalClearCell(this$static, td, clearInnerHTML);
  return td;
}

function $getDOMCellCount(this$static, tableBody, row){
  return tableBody.rows[row].cells.length;
}

function $getDOMRowCount(this$static){
  return $getDOMRowCount_0(this$static, this$static.bodyElem);
}

function $getDOMRowCount_0(this$static, elem){
  return elem.rows.length;
}

function $insertRow_0(this$static, beforeRow){
  var tr;
  if (beforeRow != $getRowCount(this$static)) {
    $checkRowBounds(this$static, beforeRow);
  }
  tr = createTR();
  insertChild(this$static.bodyElem, tr, beforeRow);
  return beforeRow;
}

function $internalClearCell(this$static, td, clearInnerHTML){
  var maybeChild, widget;
  maybeChild = getFirstChild(td);
  widget = null;
  if (maybeChild !== null) {
    widget = $getWidget(this$static.widgetMap, maybeChild);
  }
  if (widget !== null) {
    $remove_0(this$static, widget);
    return true;
  }
   else {
    if (clearInnerHTML) {
      setInnerHTML(td, '');
    }
    return false;
  }
}

function $remove_0(this$static, widget){
  if (widget.parent !== this$static) {
    return false;
  }
  $removeWidgetByElement(this$static.widgetMap, widget.getElement());
  this$static.disown(widget);
  return true;
}

function $setCellFormatter(this$static, cellFormatter){
  this$static.cellFormatter = cellFormatter;
}

function $setWidget(this$static, row, column, widget){
  var td;
  $prepareCell(this$static, row, column);
  if (widget !== null) {
    $removeFromParent(widget);
    td = $cleanCell(this$static, row, column, true);
    $putWidget(this$static.widgetMap, widget);
    $adopt(this$static, widget, td);
  }
}

function iterator_1(){
  return $widgetIterator(this.widgetMap);
}

function onBrowserEvent_1(event_0){
  switch (eventGetType(event_0)) {
    case 1:
      {
        break;
      }

    default:}
}

function remove_1(widget){
  return $remove_0(this, widget);
}

function HTMLTable(){
}

_ = HTMLTable.prototype = new Panel();
_.iterator = iterator_1;
_.onBrowserEvent = onBrowserEvent_1;
_.remove_0 = remove_1;
_.typeName = package_com_google_gwt_user_client_ui_ + 'HTMLTable';
_.typeId = 37;
_.bodyElem = null;
_.cellFormatter = null;
_.tableElem = null;
function $FlexTable(this$static){
  $HTMLTable(this$static);
  $setCellFormatter(this$static, $FlexTable$FlexCellFormatter(new FlexTable$FlexCellFormatter(), this$static));
  return this$static;
}

function $getCellCount(this$static, row){
  $checkRowBounds(this$static, row);
  return $getDOMCellCount(this$static, this$static.bodyElem, row);
}

function $getRowCount(this$static){
  return $getDOMRowCount(this$static);
}

function $insertRow(this$static, beforeRow){
  return $insertRow_0(this$static, beforeRow);
}

function $prepareCell(this$static, row, column){
  var cellCount, required;
  $prepareRow(this$static, row);
  if (column < 0) {
    throw $IndexOutOfBoundsException_0(new IndexOutOfBoundsException(), 'Cannot create a column with a negative index: ' + column);
  }
  cellCount = $getCellCount(this$static, row);
  required = column + 1 - cellCount;
  if (required > 0) {
    addCells(this$static.bodyElem, row, required);
  }
}

function $prepareRow(this$static, row){
  var i, rowCount;
  if (row < 0) {
    throw $IndexOutOfBoundsException_0(new IndexOutOfBoundsException(), 'Cannot create a row with a negative index: ' + row);
  }
  rowCount = $getRowCount(this$static);
  for (i = rowCount; i <= row; i++) {
    $insertRow(this$static, i);
  }
}

function addCells(table, row, num){
  var rowElem = table.rows[row];
  for (var i = 0; i < num; i++) {
    var cell = $doc.createElement('td');
    rowElem.appendChild(cell);
  }
}

function FlexTable(){
}

_ = FlexTable.prototype = new HTMLTable();
_.typeName = package_com_google_gwt_user_client_ui_ + 'FlexTable';
_.typeId = 38;
function $HTMLTable$CellFormatter(this$static, this$0){
  this$static.this$0 = this$0;
  return this$static;
}

function $getCellElement(this$static, table, row, col){
  var out = table.rows[row].cells[col];
  return out == null?null:out;
}

function $getRawElement(this$static, row, column){
  return $getCellElement(this$static, this$static.this$0.bodyElem, row, column);
}

function $setHorizontalAlignment(this$static, row, column, align){
  var elem;
  $prepareCell(this$static.this$0, row, column);
  elem = $getCellElement(this$static, this$static.this$0.bodyElem, row, column);
  setElementProperty(elem, 'align', align.textAlignString);
}

function HTMLTable$CellFormatter(){
}

_ = HTMLTable$CellFormatter.prototype = new Object_0();
_.typeName = package_com_google_gwt_user_client_ui_ + 'HTMLTable$CellFormatter';
_.typeId = 0;
function $FlexTable$FlexCellFormatter(this$static, this$0){
  $HTMLTable$CellFormatter(this$static, this$0);
  return this$static;
}

function FlexTable$FlexCellFormatter(){
}

_ = FlexTable$FlexCellFormatter.prototype = new HTMLTable$CellFormatter();
_.typeName = package_com_google_gwt_user_client_ui_ + 'FlexTable$FlexCellFormatter';
_.typeId = 0;
function $SimplePanel(this$static, elem){
  this$static.setElement(elem);
  return this$static;
}

function $remove_1(this$static, w){
  if (this$static.widget === w) {
    this$static.disown(w);
    this$static.widget = null;
    return true;
  }
  return false;
}

function iterator_2(){
  return $SimplePanel$1(new SimplePanel$1(), this);
}

function remove_2(w){
  return $remove_1(this, w);
}

function SimplePanel(){
}

_ = SimplePanel.prototype = new Panel();
_.iterator = iterator_2;
_.remove_0 = remove_2;
_.typeName = package_com_google_gwt_user_client_ui_ + 'SimplePanel';
_.typeId = 39;
_.widget = null;
function $clinit_57(){
  $clinit_57 = nullMethod;
  impl_0 = ($clinit_104() , implPanel);
}

function $FocusPanel(this$static){
  $clinit_57();
  $SimplePanel(this$static, $createFocusable(impl_0));
  $sinkEvents_0(this$static, 138237);
  return this$static;
}

function $addKeyboardListener(this$static, listener){
  if (this$static.keyboardListeners === null) {
    this$static.keyboardListeners = $KeyboardListenerCollection(new KeyboardListenerCollection());
  }
  $add_2(this$static.keyboardListeners, listener);
}

function $setFocus(this$static, focused){
  if (focused) {
    $focus(impl_0, this$static.getElement());
  }
   else {
    $blur(impl_0, this$static.getElement());
  }
}

function onBrowserEvent(event_0){
  switch (eventGetType(event_0)) {
    case 1:
      break;
    case 4:
    case 8:
    case 64:
    case 16:
    case 32:
      break;
    case 131072:
      break;
    case 4096:
    case 2048:
      break;
    case 128:
    case 512:
    case 256:
      if (this.keyboardListeners !== null) {
        $fireKeyboardEvent(this.keyboardListeners, this, event_0);
      }

      break;
  }
}

function FocusPanel(){
}

_ = FocusPanel.prototype = new SimplePanel();
_.onBrowserEvent = onBrowserEvent;
_.typeName = package_com_google_gwt_user_client_ui_ + 'FocusPanel';
_.typeId = 40;
_.keyboardListeners = null;
var impl_0;
function $Label(this$static){
  this$static.setElement(createDiv());
  $sinkEvents_0(this$static, 131197);
  $setStyleName(this$static, 'gwt-Label');
  return this$static;
}

function $Label_0(this$static, text){
  $Label(this$static);
  $setText(this$static, text);
  return this$static;
}

function $addMouseListener(this$static, listener){
  if (this$static.mouseListeners === null) {
    this$static.mouseListeners = $MouseListenerCollection(new MouseListenerCollection());
  }
  $add_2(this$static.mouseListeners, listener);
}

function $setText(this$static, text){
  setInnerText(this$static.getElement(), text);
}

function onBrowserEvent_3(event_0){
  switch (eventGetType(event_0)) {
    case 1:
      break;
    case 4:
    case 8:
    case 64:
    case 16:
    case 32:
      if (this.mouseListeners !== null) {
        $fireMouseEvent(this.mouseListeners, this, event_0);
      }

      break;
    case 131072:
      break;
  }
}

function Label(){
}

_ = Label.prototype = new Widget();
_.onBrowserEvent = onBrowserEvent_3;
_.typeName = package_com_google_gwt_user_client_ui_ + 'Label';
_.typeId = 41;
_.mouseListeners = null;
function $HTML(this$static){
  $Label(this$static);
  this$static.setElement(createDiv());
  $sinkEvents_0(this$static, 125);
  $setStyleName(this$static, 'gwt-HTML');
  return this$static;
}

function $HTML_0(this$static, html){
  $HTML(this$static);
  $setHTML_0(this$static, html);
  return this$static;
}

function $setHTML_0(this$static, html){
  setInnerHTML(this$static.getElement(), html);
}

function HTML(){
}

_ = HTML.prototype = new Label();
_.typeName = package_com_google_gwt_user_client_ui_ + 'HTML';
_.typeId = 42;
function $$init_3(this$static){
  {
    $findNext(this$static);
  }
}

function $HTMLTable$1(this$static, this$1){
  this$static.this$1 = this$1;
  $$init_3(this$static);
  return this$static;
}

function $findNext(this$static){
  while (++this$static.nextIndex < this$static.this$1.widgetList.size) {
    if ($get(this$static.this$1.widgetList, this$static.nextIndex) !== null) {
      return;
    }
  }
}

function $hasNext(this$static){
  return this$static.nextIndex < this$static.this$1.widgetList.size;
}

function hasNext(){
  return $hasNext(this);
}

function next_0(){
  var result;
  if (!$hasNext(this)) {
    throw $NoSuchElementException(new NoSuchElementException());
  }
  result = $get(this.this$1.widgetList, this.nextIndex);
  $findNext(this);
  return result;
}

function HTMLTable$1(){
}

_ = HTMLTable$1.prototype = new Object_0();
_.hasNext = hasNext;
_.next_0 = next_0;
_.typeName = package_com_google_gwt_user_client_ui_ + 'HTMLTable$1';
_.typeId = 0;
_.nextIndex = (-1);
function $$init_4(this$static){
  this$static.widgetList = $ArrayList(new ArrayList());
}

function $HTMLTable$WidgetMapper(this$static){
  $$init_4(this$static);
  return this$static;
}

function $getWidget(this$static, elem){
  var index;
  index = getWidgetIndex(elem);
  if (index < 0) {
    return null;
  }
  return dynamicCast($get(this$static.widgetList, index), 9);
}

function $putWidget(this$static, widget){
  var index;
  if (this$static.freeList === null) {
    index = this$static.widgetList.size;
    $add_2(this$static.widgetList, widget);
  }
   else {
    index = this$static.freeList.index;
    $set(this$static.widgetList, index, widget);
    this$static.freeList = this$static.freeList.next;
  }
  setWidgetIndex(widget.getElement(), index);
}

function $removeImpl(this$static, elem, index){
  clearWidgetIndex(elem);
  $set(this$static.widgetList, index, null);
  this$static.freeList = $HTMLTable$WidgetMapper$FreeNode(new HTMLTable$WidgetMapper$FreeNode(), index, this$static.freeList);
}

function $removeWidgetByElement(this$static, elem){
  var index;
  index = getWidgetIndex(elem);
  $removeImpl(this$static, elem, index);
}

function $widgetIterator(this$static){
  return $HTMLTable$1(new HTMLTable$1(), this$static);
}

function clearWidgetIndex(elem){
  elem['__widgetID'] = null;
}

function getWidgetIndex(elem){
  var index = elem['__widgetID'];
  return index == null?-1:index;
}

function setWidgetIndex(elem, index){
  elem['__widgetID'] = index;
}

function HTMLTable$WidgetMapper(){
}

_ = HTMLTable$WidgetMapper.prototype = new Object_0();
_.typeName = package_com_google_gwt_user_client_ui_ + 'HTMLTable$WidgetMapper';
_.typeId = 0;
_.freeList = null;
function $HTMLTable$WidgetMapper$FreeNode(this$static, index, next){
  this$static.index = index;
  this$static.next = next;
  return this$static;
}

function HTMLTable$WidgetMapper$FreeNode(){
}

_ = HTMLTable$WidgetMapper$FreeNode.prototype = new Object_0();
_.typeName = package_com_google_gwt_user_client_ui_ + 'HTMLTable$WidgetMapper$FreeNode';
_.typeId = 0;
_.index = 0;
_.next = null;
function $clinit_68(){
  $clinit_68 = nullMethod;
  ALIGN_CENTER = $HasHorizontalAlignment$HorizontalAlignmentConstant(new HasHorizontalAlignment$HorizontalAlignmentConstant(), 'center');
  $HasHorizontalAlignment$HorizontalAlignmentConstant(new HasHorizontalAlignment$HorizontalAlignmentConstant(), 'left');
  $HasHorizontalAlignment$HorizontalAlignmentConstant(new HasHorizontalAlignment$HorizontalAlignmentConstant(), 'right');
}

var ALIGN_CENTER;
function $HasHorizontalAlignment$HorizontalAlignmentConstant(this$static, textAlignString){
  this$static.textAlignString = textAlignString;
  return this$static;
}

function HasHorizontalAlignment$HorizontalAlignmentConstant(){
}

_ = HasHorizontalAlignment$HorizontalAlignmentConstant.prototype = new Object_0();
_.typeName = package_com_google_gwt_user_client_ui_ + 'HasHorizontalAlignment$HorizontalAlignmentConstant';
_.typeId = 0;
_.textAlignString = null;
function $clinit_75(){
  $clinit_75 = nullMethod;
  $HashMap(new HashMap());
}

function $Image(this$static, url){
  $clinit_75();
  $Image$UnclippedState_0(new Image$UnclippedState(), this$static, url);
  $setStyleName(this$static, 'gwt-Image');
  return this$static;
}

function onBrowserEvent_2(event_0){
  switch (eventGetType(event_0)) {
    case 1:
      {
        break;
      }

    case 4:
    case 8:
    case 64:
    case 16:
    case 32:
      {
        break;
      }

    case 131072:
      break;
    case 32768:
      {
        break;
      }

    case 65536:
      {
        break;
      }

  }
}

function Image_0(){
}

_ = Image_0.prototype = new Widget();
_.onBrowserEvent = onBrowserEvent_2;
_.typeName = package_com_google_gwt_user_client_ui_ + 'Image';
_.typeId = 43;
function Image$State(){
}

_ = Image$State.prototype = new Object_0();
_.typeName = package_com_google_gwt_user_client_ui_ + 'Image$State';
_.typeId = 0;
function $Image$UnclippedState(this$static, image){
  image.setElement(createImg());
  $sinkEvents_0(image, 229501);
  return this$static;
}

function $Image$UnclippedState_0(this$static, image, url){
  $Image$UnclippedState(this$static, image);
  $setUrl(this$static, image, url);
  return this$static;
}

function $setUrl(this$static, image, url){
  setImgSrc(image.getElement(), url);
}

function Image$UnclippedState(){
}

_ = Image$UnclippedState.prototype = new Image$State();
_.typeName = package_com_google_gwt_user_client_ui_ + 'Image$UnclippedState';
_.typeId = 0;
function $KeyboardListenerCollection(this$static){
  $ArrayList(this$static);
  return this$static;
}

function $fireKeyDown(this$static, sender, keyCode, modifiers){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 10);
    listener.onKeyDown(sender, keyCode, modifiers);
  }
}

function $fireKeyPress(this$static, sender, key, modifiers){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 10);
    listener.onKeyPress(sender, key, modifiers);
  }
}

function $fireKeyUp(this$static, sender, keyCode, modifiers){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 10);
    listener.onKeyUp(sender, keyCode, modifiers);
  }
}

function $fireKeyboardEvent(this$static, sender, event_0){
  var modifiers;
  modifiers = getKeyboardModifiers(event_0);
  switch (eventGetType(event_0)) {
    case 128:
      $fireKeyDown(this$static, sender, narrow_char(eventGetKeyCode(event_0)), modifiers);
      break;
    case 512:
      $fireKeyUp(this$static, sender, narrow_char(eventGetKeyCode(event_0)), modifiers);
      break;
    case 256:
      $fireKeyPress(this$static, sender, narrow_char(eventGetKeyCode(event_0)), modifiers);
      break;
  }
}

function getKeyboardModifiers(event_0){
  return (eventGetShiftKey(event_0)?1:0) | (eventGetMetaKey(event_0)?8:0) | (eventGetCtrlKey(event_0)?2:0) | (eventGetAltKey(event_0)?4:0);
}

function KeyboardListenerCollection(){
}

_ = KeyboardListenerCollection.prototype = new ArrayList();
_.typeName = package_com_google_gwt_user_client_ui_ + 'KeyboardListenerCollection';
_.typeId = 44;
function $MouseListenerCollection(this$static){
  $ArrayList(this$static);
  return this$static;
}

function $fireMouseDown(this$static, sender, x, y){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 11);
    listener.onMouseDown(sender, x, y);
  }
}

function $fireMouseEnter(this$static, sender){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 11);
    listener.onMouseEnter(sender);
  }
}

function $fireMouseEvent(this$static, sender, event_0){
  var from, senderElem, to, x, y;
  senderElem = sender.getElement();
  x = eventGetClientX(event_0) - getAbsoluteLeft(senderElem) + getElementPropertyInt(senderElem, 'scrollLeft') + getScrollLeft();
  y = eventGetClientY(event_0) - getAbsoluteTop(senderElem) + getElementPropertyInt(senderElem, 'scrollTop') + getScrollTop();
  switch (eventGetType(event_0)) {
    case 4:
      $fireMouseDown(this$static, sender, x, y);
      break;
    case 8:
      $fireMouseUp(this$static, sender, x, y);
      break;
    case 64:
      $fireMouseMove(this$static, sender, x, y);
      break;
    case 16:
      from = eventGetFromElement(event_0);
      if (!isOrHasChild(senderElem, from)) {
        $fireMouseEnter(this$static, sender);
      }

      break;
    case 32:
      to = eventGetToElement(event_0);
      if (!isOrHasChild(senderElem, to)) {
        $fireMouseLeave(this$static, sender);
      }

      break;
  }
}

function $fireMouseLeave(this$static, sender){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 11);
    listener.onMouseLeave(sender);
  }
}

function $fireMouseMove(this$static, sender, x, y){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 11);
    listener.onMouseMove(sender, x, y);
  }
}

function $fireMouseUp(this$static, sender, x, y){
  var it, listener;
  for (it = $iterator_0(this$static); $hasNext_0(it);) {
    listener = dynamicCast($next(it), 11);
    listener.onMouseUp(sender, x, y);
  }
}

function MouseListenerCollection(){
}

_ = MouseListenerCollection.prototype = new ArrayList();
_.typeName = package_com_google_gwt_user_client_ui_ + 'MouseListenerCollection';
_.typeId = 45;
function $clinit_86(){
  $clinit_86 = nullMethod;
  rootPanels = $HashMap(new HashMap());
}

function $RootPanel(this$static, elem){
  $clinit_86();
  $AbsolutePanel(this$static);
  if (elem === null) {
    elem = getBodyElement();
  }
  this$static.setElement(elem);
  this$static.onAttach();
  return this$static;
}

function get(){
  $clinit_86();
  return get_0(null);
}

function get_0(id){
  $clinit_86();
  var elem, gwt;
  gwt = dynamicCast($get_0(rootPanels, id), 12);
  if (gwt !== null) {
    return gwt;
  }
  elem = null;
  if (rootPanels.size == 0) {
    hookWindowClosing_0();
  }
  $put(rootPanels, id, gwt = $RootPanel(new RootPanel(), elem));
  return gwt;
}

function getBodyElement(){
  $clinit_86();
  return $doc.body;
}

function hookWindowClosing_0(){
  $clinit_86();
  addWindowCloseListener(new RootPanel$1());
}

function RootPanel(){
}

_ = RootPanel.prototype = new AbsolutePanel();
_.typeName = package_com_google_gwt_user_client_ui_ + 'RootPanel';
_.typeId = 46;
var rootPanels;
function onWindowClosed_0(){
  var gwt, it;
  for (it = $iterator_2($values(($clinit_86() , rootPanels))); $hasNext_2(it);) {
    gwt = dynamicCast($next_1(it), 12);
    if (gwt.isAttached()) {
      gwt.onDetach();
    }
  }
}

function onWindowClosing_0(){
  return null;
}

function RootPanel$1(){
}

_ = RootPanel$1.prototype = new Object_0();
_.onWindowClosed = onWindowClosed_0;
_.onWindowClosing = onWindowClosing_0;
_.typeName = package_com_google_gwt_user_client_ui_ + 'RootPanel$1';
_.typeId = 47;
function $$init_6(this$static){
  this$static.hasElement = false;
}

function $SimplePanel$1(this$static, this$0){
  this$static.this$0 = this$0;
  $$init_6(this$static);
  return this$static;
}

function hasNext_0(){
  return this.hasElement;
}

function next_1(){
  {
    throw $NoSuchElementException(new NoSuchElementException());
  }
  this.hasElement = false;
  return this.this$0.widget;
}

function SimplePanel$1(){
}

_ = SimplePanel$1.prototype = new Object_0();
_.hasNext = hasNext_0;
_.next_0 = next_1;
_.typeName = package_com_google_gwt_user_client_ui_ + 'SimplePanel$1';
_.typeId = 0;
function $clinit_98(){
  $clinit_98 = nullMethod;
  $clinit_58();
}

function $TextBoxBase(this$static, elem){
  $clinit_98();
  $FocusWidget(this$static, elem);
  $sinkEvents_0(this$static, 1024);
  return this$static;
}

function $setText_0(this$static, text){
  setElementProperty(this$static.getElement(), 'value', text !== null?text:'');
}

function addClickListener_0(listener){
  if (this.clickListeners === null) {
    this.clickListeners = $ClickListenerCollection(new ClickListenerCollection());
  }
  $add_2(this.clickListeners, listener);
}

function onBrowserEvent_4(event_0){
  var type;
  $onBrowserEvent(this, event_0);
  type = eventGetType(event_0);
  if (type == 1) {
    if (this.clickListeners !== null) {
      $fireClick(this.clickListeners, this);
    }
  }
   else {
  }
}

function TextBoxBase(){
}

_ = TextBoxBase.prototype = new FocusWidget();
_.addClickListener = addClickListener_0;
_.onBrowserEvent = onBrowserEvent_4;
_.typeName = package_com_google_gwt_user_client_ui_ + 'TextBoxBase';
_.typeId = 48;
_.clickListeners = null;
function $clinit_97(){
  $clinit_97 = nullMethod;
  $clinit_98();
}

function $TextArea(this$static){
  $clinit_97();
  $TextBoxBase(this$static, createTextArea());
  $setStyleName(this$static, 'gwt-TextArea');
  return this$static;
}

function TextArea(){
}

_ = TextArea.prototype = new TextBoxBase();
_.typeName = package_com_google_gwt_user_client_ui_ + 'TextArea';
_.typeId = 49;
function $WidgetCollection(this$static, parent){
  this$static.array = initDims_0('[Lcom.google.gwt.user.client.ui.Widget;', [0], [9], [4], null);
  return this$static;
}

function $contains(this$static, w){
  return $indexOf(this$static, w) != (-1);
}

function $indexOf(this$static, w){
  var i;
  for (i = 0; i < this$static.size; ++i) {
    if (this$static.array[i] === w) {
      return i;
    }
  }
  return (-1);
}

function $insert_0(this$static, w, beforeIndex){
  var i, newArray;
  if (beforeIndex < 0 || beforeIndex > this$static.size) {
    throw $IndexOutOfBoundsException(new IndexOutOfBoundsException());
  }
  if (this$static.size == this$static.array.length_0) {
    newArray = initDims_0('[Lcom.google.gwt.user.client.ui.Widget;', [0], [9], [this$static.array.length_0 * 2], null);
    for (i = 0; i < this$static.array.length_0; ++i) {
      setCheck(newArray, i, this$static.array[i]);
    }
    this$static.array = newArray;
  }
  ++this$static.size;
  for (i = this$static.size - 1; i > beforeIndex; --i) {
    setCheck(this$static.array, i, this$static.array[i - 1]);
  }
  setCheck(this$static.array, beforeIndex, w);
}

function $iterator(this$static){
  return $WidgetCollection$WidgetIterator(new WidgetCollection$WidgetIterator(), this$static);
}

function $remove_2(this$static, index){
  var i;
  if (index < 0 || index >= this$static.size) {
    throw $IndexOutOfBoundsException(new IndexOutOfBoundsException());
  }
  --this$static.size;
  for (i = index; i < this$static.size; ++i) {
    setCheck(this$static.array, i, this$static.array[i + 1]);
  }
  setCheck(this$static.array, this$static.size, null);
}

function $remove_3(this$static, w){
  var index;
  index = $indexOf(this$static, w);
  if (index == (-1)) {
    throw $NoSuchElementException(new NoSuchElementException());
  }
  $remove_2(this$static, index);
}

function WidgetCollection(){
}

_ = WidgetCollection.prototype = new Object_0();
_.typeName = package_com_google_gwt_user_client_ui_ + 'WidgetCollection';
_.typeId = 0;
_.array = null;
_.size = 0;
function $WidgetCollection$WidgetIterator(this$static, this$0){
  this$static.this$0 = this$0;
  return this$static;
}

function hasNext_1(){
  return this.index < this.this$0.size - 1;
}

function next_2(){
  if (this.index >= this.this$0.size) {
    throw $NoSuchElementException(new NoSuchElementException());
  }
  return this.this$0.array[++this.index];
}

function WidgetCollection$WidgetIterator(){
}

_ = WidgetCollection$WidgetIterator.prototype = new Object_0();
_.hasNext = hasNext_1;
_.next_0 = next_2;
_.typeName = package_com_google_gwt_user_client_ui_ + 'WidgetCollection$WidgetIterator';
_.typeId = 0;
_.index = (-1);
function $clinit_104(){
  $clinit_104 = nullMethod;
  implPanel = $FocusImplOld(new FocusImplOld());
  implWidget = implPanel !== null?$FocusImpl(new FocusImpl()):implPanel;
}

function $FocusImpl(this$static){
  $clinit_104();
  return this$static;
}

function setTabIndex_0(elem, index){
  elem.tabIndex = index;
}

function FocusImpl(){
}

_ = FocusImpl.prototype = new Object_0();
_.setTabIndex = setTabIndex_0;
_.typeName = package_com_google_gwt_user_client_ui_impl_ + 'FocusImpl';
_.typeId = 0;
var implPanel, implWidget;
function $clinit_103(){
  $clinit_103 = nullMethod;
  $clinit_104();
}

function $$init_7(this$static){
  this$static.blurHandler = $createBlurHandler(this$static);
  this$static.focusHandler = $createFocusHandler(this$static);
  this$static.mouseHandler = $createMouseHandler(this$static);
}

function $FocusImplOld(this$static){
  $clinit_103();
  $FocusImpl(this$static);
  $$init_7(this$static);
  return this$static;
}

function $blur(this$static, elem){
  elem.firstChild.blur();
}

function $createBlurHandler(this$static){
  return function(evt){
    if (this.parentNode.onblur) {
      this.parentNode.onblur(evt);
    }
  }
  ;
}

function $createFocusHandler(this$static){
  return function(evt){
    if (this.parentNode.onfocus) {
      this.parentNode.onfocus(evt);
    }
  }
  ;
}

function $createFocusable(this$static){
  var div = $doc.createElement('div');
  var input = this$static.createHiddenInput();
  input.addEventListener('blur', this$static.blurHandler, false);
  input.addEventListener('focus', this$static.focusHandler, false);
  div.addEventListener('mousedown', this$static.mouseHandler, false);
  div.appendChild(input);
  return div;
}

function $createMouseHandler(this$static){
  return function(){
    this.firstChild.focus();
  }
  ;
}

function $focus(this$static, elem){
  elem.firstChild.focus();
}

function createHiddenInput(){
  var input = $doc.createElement('input');
  input.type = 'text';
  input.style.width = input.style.height = 0;
  input.style.zIndex = -1;
  input.style.position = 'absolute';
  return input;
}

function setTabIndex(elem, index){
  elem.firstChild.tabIndex = index;
}

function FocusImplOld(){
}

_ = FocusImplOld.prototype = new FocusImpl();
_.createHiddenInput = createHiddenInput;
_.setTabIndex = setTabIndex;
_.typeName = package_com_google_gwt_user_client_ui_impl_ + 'FocusImplOld';
_.typeId = 0;
function OutputStream(){
}

_ = OutputStream.prototype = new Object_0();
_.typeName = package_java_io_ + 'OutputStream';
_.typeId = 0;
function FilterOutputStream(){
}

_ = FilterOutputStream.prototype = new OutputStream();
_.typeName = package_java_io_ + 'FilterOutputStream';
_.typeId = 0;
function PrintStream(){
}

_ = PrintStream.prototype = new FilterOutputStream();
_.typeName = package_java_io_ + 'PrintStream';
_.typeId = 0;
function $clinit_108(){
  $clinit_108 = nullMethod;
  $clinit_123();
}

function $ArrayStoreException(this$static){
  $clinit_108();
  $RuntimeException(this$static);
  return this$static;
}

function ArrayStoreException(){
}

_ = ArrayStoreException.prototype = new RuntimeException();
_.typeName = package_java_lang_ + 'ArrayStoreException';
_.typeId = 50;
function $clinit_110(){
  $clinit_110 = nullMethod;
  $clinit_123();
}

function $ClassCastException(this$static){
  $clinit_110();
  $RuntimeException(this$static);
  return this$static;
}

function ClassCastException(){
}

_ = ClassCastException.prototype = new RuntimeException();
_.typeName = package_java_lang_ + 'ClassCastException';
_.typeId = 51;
function $clinit_114(){
  $clinit_114 = nullMethod;
  $clinit_123();
}

function $IllegalArgumentException(this$static, message){
  $clinit_114();
  $RuntimeException_0(this$static, message);
  return this$static;
}

function IllegalArgumentException(){
}

_ = IllegalArgumentException.prototype = new RuntimeException();
_.typeName = package_java_lang_ + 'IllegalArgumentException';
_.typeId = 52;
function $clinit_115(){
  $clinit_115 = nullMethod;
  $clinit_123();
}

function $IllegalStateException(this$static){
  $clinit_115();
  $RuntimeException(this$static);
  return this$static;
}

function $IllegalStateException_0(this$static, s){
  $clinit_115();
  $RuntimeException_0(this$static, s);
  return this$static;
}

function IllegalStateException(){
}

_ = IllegalStateException.prototype = new RuntimeException();
_.typeName = package_java_lang_ + 'IllegalStateException';
_.typeId = 53;
function $clinit_116(){
  $clinit_116 = nullMethod;
  $clinit_123();
}

function $IndexOutOfBoundsException(this$static){
  $clinit_116();
  $RuntimeException(this$static);
  return this$static;
}

function $IndexOutOfBoundsException_0(this$static, message){
  $clinit_116();
  $RuntimeException_0(this$static, message);
  return this$static;
}

function IndexOutOfBoundsException(){
}

_ = IndexOutOfBoundsException.prototype = new RuntimeException();
_.typeName = package_java_lang_ + 'IndexOutOfBoundsException';
_.typeId = 54;
function $clinit_121(){
  $clinit_121 = nullMethod;
  {
    initNative();
  }
}

function initNative(){
  $clinit_121();
  floatRegex = /^[+-]?\d*\.?\d*(e[+-]?\d+)?$/i;
}

var floatRegex = null;
function $clinit_117(){
  $clinit_117 = nullMethod;
  $clinit_121();
}

var MAX_VALUE = 2147483647, MIN_VALUE = (-2147483648);
function $clinit_118(){
  $clinit_118 = nullMethod;
  $clinit_121();
}

var MAX_VALUE_0 = 9223372036854775807, MIN_VALUE_0 = (-9223372036854775808);
function round(x){
  return Math.round(x);
}

function $clinit_120(){
  $clinit_120 = nullMethod;
  $clinit_123();
}

function $NegativeArraySizeException(this$static){
  $clinit_120();
  $RuntimeException(this$static);
  return this$static;
}

function NegativeArraySizeException(){
}

_ = NegativeArraySizeException.prototype = new RuntimeException();
_.typeName = package_java_lang_ + 'NegativeArraySizeException';
_.typeId = 55;
function $charAt(this$static, index){
  return this$static.charCodeAt(index);
}

function $equals_0(this$static, other){
  if (!instanceOf(other, 17))
    return false;
  return __equals(this$static, other);
}

function $indexOf_0(this$static, str){
  return this$static.indexOf(str);
}

function $indexOf_1(this$static, str, startIndex){
  return this$static.indexOf(str, startIndex);
}

function $length(this$static){
  return this$static.length;
}

function $replaceAll(this$static, regex, replace){
  replace = __translateReplaceString(replace);
  return this$static.replace(RegExp(regex, 'g'), replace);
}

function $substring(this$static, beginIndex){
  return this$static.substr(beginIndex, this$static.length - beginIndex);
}

function $substring_0(this$static, beginIndex, endIndex){
  return this$static.substr(beginIndex, endIndex - beginIndex);
}

function $trim(this$static){
  var r1 = this$static.replace(/^(\s*)/, '');
  var r2 = r1.replace(/\s*$/, '');
  return r2;
}

function __equals(me, other){
  return String(me) == other;
}

function __translateReplaceString(replaceStr){
  var pos;
  pos = 0;
  while (0 <= (pos = $indexOf_1(replaceStr, '\\', pos))) {
    if ($charAt(replaceStr, pos + 1) == 36) {
      replaceStr = $substring_0(replaceStr, 0, pos) + '$' + $substring(replaceStr, ++pos);
    }
     else {
      replaceStr = $substring_0(replaceStr, 0, pos) + $substring(replaceStr, ++pos);
    }
  }
  return replaceStr;
}

function equals_3(other){
  return $equals_0(this, other);
}

function hashCode_4(){
  var hashCache = hashCache_0;
  if (!hashCache) {
    hashCache = hashCache_0 = {};
  }
  var key = ':' + this;
  var hashCode = hashCache[key];
  if (hashCode == null) {
    hashCode = 0;
    var n = this.length;
    var inc = n < 64?1:n / 32 | 0;
    for (var i = 0; i < n; i += inc) {
      hashCode <<= 1;
      hashCode += this.charCodeAt(i);
    }
    hashCode |= 0;
    hashCache[key] = hashCode;
  }
  return hashCode;
}

function toString_8(){
  return this;
}

function valueOf(x){
  return x !== null?x.toString_0():'null';
}

_ = String.prototype;
_.equals = equals_3;
_.hashCode = hashCode_4;
_.toString_0 = toString_8;
_.typeName = package_java_lang_ + 'String';
_.typeId = 56;
var hashCache_0 = null;
function $StringBuffer(this$static){
  $assign(this$static);
  return this$static;
}

function $append(this$static, toAppend){
  if (toAppend === null) {
    toAppend = 'null';
  }
  var last = this$static.js.length - 1;
  var lastLength = this$static.js[last].length;
  if (this$static.length > lastLength * lastLength) {
    this$static.js[last] = this$static.js[last] + toAppend;
  }
   else {
    this$static.js.push(toAppend);
  }
  this$static.length += toAppend.length;
  return this$static;
}

function $assign(this$static){
  $assign_0(this$static, '');
}

function $assign_0(this$static, s){
  this$static.js = [s];
  this$static.length = s.length;
}

function $toString_0(this$static){
  this$static.normalize();
  return this$static.js[0];
}

function normalize(){
  if (this.js.length > 1) {
    this.js = [this.js.join('')];
    this.length = this.js[0].length;
  }
}

function toString_7(){
  return $toString_0(this);
}

function StringBuffer(){
}

_ = StringBuffer.prototype = new Object_0();
_.normalize = normalize;
_.toString_0 = toString_7;
_.typeName = package_java_lang_ + 'StringBuffer';
_.typeId = 0;
function $clinit_126(){
  $clinit_126 = nullMethod;
  err_0 = new PrintStream();
}

function currentTimeMillis(){
  $clinit_126();
  return new Date().getTime();
}

function identityHashCode(o){
  $clinit_126();
  return getHashCode_0(o);
}

var err_0;
function $clinit_128(){
  $clinit_128 = nullMethod;
  $clinit_123();
}

function $UnsupportedOperationException(this$static, message){
  $clinit_128();
  $RuntimeException_0(this$static, message);
  return this$static;
}

function UnsupportedOperationException(){
}

_ = UnsupportedOperationException.prototype = new RuntimeException();
_.typeName = package_java_lang_ + 'UnsupportedOperationException';
_.typeId = 57;
function $AbstractList$IteratorImpl(this$static, this$0){
  this$static.this$0 = this$0;
  return this$static;
}

function $hasNext_0(this$static){
  return this$static.i < this$static.this$0.size_0();
}

function $next(this$static){
  if (!$hasNext_0(this$static)) {
    throw $NoSuchElementException(new NoSuchElementException());
  }
  return this$static.this$0.get(this$static.last = this$static.i++);
}

function $remove_4(this$static){
  if (this$static.last < 0) {
    throw $IllegalStateException(new IllegalStateException());
  }
  this$static.this$0.remove(this$static.last);
  this$static.i = this$static.last;
  this$static.last = (-1);
}

function hasNext_2(){
  return $hasNext_0(this);
}

function next_3(){
  return $next(this);
}

function AbstractList$IteratorImpl(){
}

_ = AbstractList$IteratorImpl.prototype = new Object_0();
_.hasNext = hasNext_2;
_.next_0 = next_3;
_.typeName = package_java_util_ + 'AbstractList$IteratorImpl';
_.typeId = 0;
_.i = 0;
_.last = (-1);
function $implFindEntry(this$static, key, remove){
  var entry, iter, k;
  for (iter = $iterator_3(this$static.entrySet()); $hasNext_3(iter);) {
    entry = $next_2(iter);
    k = entry.getKey();
    if (key === null?k === null:key.equals(k)) {
      if (remove) {
        $remove_7(iter);
      }
      return entry;
    }
  }
  return null;
}

function $keySet(this$static){
  var entrySet;
  entrySet = this$static.entrySet();
  return $AbstractMap$1(new AbstractMap$1(), this$static, entrySet);
}

function $values(this$static){
  var entrySet;
  entrySet = $entrySet(this$static);
  return $AbstractMap$3(new AbstractMap$3(), this$static, entrySet);
}

function containsKey(key){
  return $implFindEntry(this, key, false) !== null;
}

function equals_5(obj){
  var iter, key, keys, otherKeys, otherMap, otherValue, value;
  if (obj === this) {
    return true;
  }
  if (!instanceOf(obj, 19)) {
    return false;
  }
  otherMap = dynamicCast(obj, 19);
  keys = $keySet(this);
  otherKeys = otherMap.keySet();
  if (!$equals_1(keys, otherKeys)) {
    return false;
  }
  for (iter = $iterator_1(keys); $hasNext_1(iter);) {
    key = $next_0(iter);
    value = this.get_0(key);
    otherValue = otherMap.get_0(key);
    if (value === null?otherValue !== null:!value.equals(otherValue)) {
      return false;
    }
  }
  return true;
}

function get_1(key){
  var entry;
  entry = $implFindEntry(this, key, false);
  return entry === null?null:entry.getValue();
}

function hashCode_6(){
  var entry, hashCode, iter;
  hashCode = 0;
  for (iter = $iterator_3(this.entrySet()); $hasNext_3(iter);) {
    entry = $next_2(iter);
    hashCode += entry.hashCode();
  }
  return hashCode;
}

function keySet(){
  return $keySet(this);
}

function toString_11(){
  var comma, entry, iter, s;
  s = '{';
  comma = false;
  for (iter = $iterator_3(this.entrySet()); $hasNext_3(iter);) {
    entry = $next_2(iter);
    if (comma) {
      s += ', ';
    }
     else {
      comma = true;
    }
    s += valueOf(entry.getKey());
    s += '=';
    s += valueOf(entry.getValue());
  }
  return s + '}';
}

function AbstractMap(){
}

_ = AbstractMap.prototype = new Object_0();
_.containsKey = containsKey;
_.equals = equals_5;
_.get_0 = get_1;
_.hashCode = hashCode_6;
_.keySet = keySet;
_.toString_0 = toString_11;
_.typeName = package_java_util_ + 'AbstractMap';
_.typeId = 58;
function $equals_1(this$static, o){
  var iter, other, otherItem;
  if (o === this$static) {
    return true;
  }
  if (!instanceOf(o, 20)) {
    return false;
  }
  other = dynamicCast(o, 20);
  if (other.size_0() != this$static.size_0()) {
    return false;
  }
  for (iter = other.iterator(); iter.hasNext();) {
    otherItem = iter.next_0();
    if (!this$static.contains(otherItem)) {
      return false;
    }
  }
  return true;
}

function equals_6(o){
  return $equals_1(this, o);
}

function hashCode_7(){
  var hashCode, iter, next;
  hashCode = 0;
  for (iter = this.iterator(); iter.hasNext();) {
    next = iter.next_0();
    if (next !== null) {
      hashCode += next.hashCode();
    }
  }
  return hashCode;
}

function AbstractSet(){
}

_ = AbstractSet.prototype = new AbstractCollection();
_.equals = equals_6;
_.hashCode = hashCode_7;
_.typeName = package_java_util_ + 'AbstractSet';
_.typeId = 59;
function $AbstractMap$1(this$static, this$0, val$entrySet){
  this$static.this$0 = this$0;
  this$static.val$entrySet = val$entrySet;
  return this$static;
}

function $iterator_1(this$static){
  var outerIter;
  outerIter = $iterator_3(this$static.val$entrySet);
  return $AbstractMap$2(new AbstractMap$2(), this$static, outerIter);
}

function contains_0(key){
  return this.this$0.containsKey(key);
}

function iterator_4(){
  return $iterator_1(this);
}

function size_0(){
  return this.val$entrySet.this$0.size;
}

function AbstractMap$1(){
}

_ = AbstractMap$1.prototype = new AbstractSet();
_.contains = contains_0;
_.iterator = iterator_4;
_.size_0 = size_0;
_.typeName = package_java_util_ + 'AbstractMap$1';
_.typeId = 60;
function $AbstractMap$2(this$static, this$1, val$outerIter){
  this$static.val$outerIter = val$outerIter;
  return this$static;
}

function $hasNext_1(this$static){
  return this$static.val$outerIter.hasNext();
}

function $next_0(this$static){
  var entry;
  entry = this$static.val$outerIter.next_0();
  return entry.getKey();
}

function hasNext_3(){
  return $hasNext_1(this);
}

function next_4(){
  return $next_0(this);
}

function AbstractMap$2(){
}

_ = AbstractMap$2.prototype = new Object_0();
_.hasNext = hasNext_3;
_.next_0 = next_4;
_.typeName = package_java_util_ + 'AbstractMap$2';
_.typeId = 0;
function $AbstractMap$3(this$static, this$0, val$entrySet){
  this$static.this$0 = this$0;
  this$static.val$entrySet = val$entrySet;
  return this$static;
}

function $iterator_2(this$static){
  var outerIter;
  outerIter = $iterator_3(this$static.val$entrySet);
  return $AbstractMap$4(new AbstractMap$4(), this$static, outerIter);
}

function contains_1(value){
  return $containsValue(this.this$0, value);
}

function iterator_5(){
  return $iterator_2(this);
}

function size_1(){
  return this.val$entrySet.this$0.size;
}

function AbstractMap$3(){
}

_ = AbstractMap$3.prototype = new AbstractCollection();
_.contains = contains_1;
_.iterator = iterator_5;
_.size_0 = size_1;
_.typeName = package_java_util_ + 'AbstractMap$3';
_.typeId = 0;
function $AbstractMap$4(this$static, this$1, val$outerIter){
  this$static.val$outerIter = val$outerIter;
  return this$static;
}

function $hasNext_2(this$static){
  return this$static.val$outerIter.hasNext();
}

function $next_1(this$static){
  var value;
  value = this$static.val$outerIter.next_0().getValue();
  return value;
}

function hasNext_4(){
  return $hasNext_2(this);
}

function next_5(){
  return $next_1(this);
}

function AbstractMap$4(){
}

_ = AbstractMap$4.prototype = new Object_0();
_.hasNext = hasNext_4;
_.next_0 = next_5;
_.typeName = package_java_util_ + 'AbstractMap$4';
_.typeId = 0;
function $clinit_144(){
  $clinit_144 = nullMethod;
  UNDEFINED = createUndefinedValue();
}

function $$init_10(this$static){
  {
    $clearImpl_0(this$static);
  }
}

function $HashMap(this$static){
  $clinit_144();
  $$init_10(this$static);
  return this$static;
}

function $clearImpl_0(this$static){
  this$static.hashCodeMap = createArray();
  this$static.stringMap = createObject();
  this$static.nullSlot = wrapJSO(UNDEFINED, JavaScriptObject);
  this$static.size = 0;
}

function $containsKey(this$static, key){
  if (instanceOf(key, 17)) {
    return getStringValue(this$static.stringMap, dynamicCast(key, 17)) !== UNDEFINED;
  }
   else if (key === null) {
    return this$static.nullSlot !== UNDEFINED;
  }
   else {
    return getHashValue(this$static.hashCodeMap, key, key.hashCode()) !== UNDEFINED;
  }
}

function $containsValue(this$static, value){
  if (this$static.nullSlot !== UNDEFINED && equalsWithNullCheck(this$static.nullSlot, value)) {
    return true;
  }
   else if (containsStringValue(this$static.stringMap, value)) {
    return true;
  }
   else if (containsHashValue(this$static.hashCodeMap, value)) {
    return true;
  }
  return false;
}

function $entrySet(this$static){
  return $HashMap$EntrySet(new HashMap$EntrySet(), this$static);
}

function $get_0(this$static, key){
  var result;
  if (instanceOf(key, 17)) {
    result = getStringValue(this$static.stringMap, dynamicCast(key, 17));
  }
   else if (key === null) {
    result = this$static.nullSlot;
  }
   else {
    result = getHashValue(this$static.hashCodeMap, key, key.hashCode());
  }
  return result === UNDEFINED?null:result;
}

function $put(this$static, key, value){
  var previous;
  {
    previous = this$static.nullSlot;
    this$static.nullSlot = value;
  }
  if (previous === UNDEFINED) {
    ++this$static.size;
    return null;
  }
   else {
    return previous;
  }
}

function $remove_8(this$static, key){
  var previous;
  if (instanceOf(key, 17)) {
    previous = removeStringValue(this$static.stringMap, dynamicCast(key, 17));
  }
   else if (key === null) {
    previous = this$static.nullSlot;
    this$static.nullSlot = wrapJSO(UNDEFINED, JavaScriptObject);
  }
   else {
    previous = removeHashValue(this$static.hashCodeMap, key, key.hashCode());
  }
  if (previous === UNDEFINED) {
    return null;
  }
   else {
    --this$static.size;
    return previous;
  }
}

function addAllHashEntries(hashCodeMap, dest){
  $clinit_144();
  for (var hashCode in hashCodeMap) {
    if (hashCode == parseInt(hashCode)) {
      var array = hashCodeMap[hashCode];
      for (var i = 0, c = array.length; i < c; ++i) {
        dest.add_0(array[i]);
      }
    }
  }
}

function addAllStringEntries(stringMap, dest){
  $clinit_144();
  for (var key in stringMap) {
    if (key.charCodeAt(0) == 58) {
      var value = stringMap[key];
      var entry = create_1(key.substring(1), value);
      dest.add_0(entry);
    }
  }
}

function containsHashValue(hashCodeMap, value){
  $clinit_144();
  for (var hashCode in hashCodeMap) {
    if (hashCode == parseInt(hashCode)) {
      var array = hashCodeMap[hashCode];
      for (var i = 0, c = array.length; i < c; ++i) {
        var entry = array[i];
        var entryValue = entry.getValue();
        if (equalsWithNullCheck(value, entryValue)) {
          return true;
        }
      }
    }
  }
  return false;
}

function containsKey_0(key){
  return $containsKey(this, key);
}

function containsStringValue(stringMap, value){
  $clinit_144();
  for (var key in stringMap) {
    if (key.charCodeAt(0) == 58) {
      var entryValue = stringMap[key];
      if (equalsWithNullCheck(value, entryValue)) {
        return true;
      }
    }
  }
  return false;
}

function createUndefinedValue(){
  $clinit_144();
}

function entrySet_0(){
  return $entrySet(this);
}

function equalsWithNullCheck(a, b){
  $clinit_144();
  if (a === b) {
    return true;
  }
   else if (a === null) {
    return false;
  }
   else {
    return a.equals(b);
  }
}

function get_3(key){
  return $get_0(this, key);
}

function getHashValue(hashCodeMap, key, hashCode){
  $clinit_144();
  var array = hashCodeMap[hashCode];
  if (array) {
    for (var i = 0, c = array.length; i < c; ++i) {
      var entry = array[i];
      var entryKey = entry.getKey();
      if (equalsWithNullCheck(key, entryKey)) {
        return entry.getValue();
      }
    }
  }
}

function getStringValue(stringMap, key){
  $clinit_144();
  return stringMap[':' + key];
}

function removeHashValue(hashCodeMap, key, hashCode){
  $clinit_144();
  var array = hashCodeMap[hashCode];
  if (array) {
    for (var i = 0, c = array.length; i < c; ++i) {
      var entry = array[i];
      var entryKey = entry.getKey();
      if (equalsWithNullCheck(key, entryKey)) {
        if (array.length == 1) {
          delete hashCodeMap[hashCode];
        }
         else {
          array.splice(i, 1);
        }
        return entry.getValue();
      }
    }
  }
}

function removeStringValue(stringMap, key){
  $clinit_144();
  key = ':' + key;
  var result = stringMap[key];
  delete stringMap[key];
  return result;
}

function HashMap(){
}

_ = HashMap.prototype = new AbstractMap();
_.containsKey = containsKey_0;
_.entrySet = entrySet_0;
_.get_0 = get_3;
_.typeName = package_java_util_ + 'HashMap';
_.typeId = 61;
_.hashCodeMap = null;
_.nullSlot = null;
_.size = 0;
_.stringMap = null;
var UNDEFINED;
function $HashMap$EntryImpl(this$static, key, value){
  this$static.key = key;
  this$static.value = value;
  return this$static;
}

function create_1(key, value){
  return $HashMap$EntryImpl(new HashMap$EntryImpl(), key, value);
}

function equals_8(other){
  var entry;
  if (instanceOf(other, 21)) {
    entry = dynamicCast(other, 21);
    if (equalsWithNullCheck(this.key, entry.getKey()) && equalsWithNullCheck(this.value, entry.getValue())) {
      return true;
    }
  }
  return false;
}

function getKey(){
  return this.key;
}

function getValue(){
  return this.value;
}

function hashCode_8(){
  var keyHash, valueHash;
  keyHash = 0;
  valueHash = 0;
  if (this.key !== null) {
    keyHash = this.key.hashCode();
  }
  if (this.value !== null) {
    valueHash = this.value.hashCode();
  }
  return keyHash ^ valueHash;
}

function toString_12(){
  return this.key + '=' + this.value;
}

function HashMap$EntryImpl(){
}

_ = HashMap$EntryImpl.prototype = new Object_0();
_.equals = equals_8;
_.getKey = getKey;
_.getValue = getValue;
_.hashCode = hashCode_8;
_.toString_0 = toString_12;
_.typeName = package_java_util_ + 'HashMap$EntryImpl';
_.typeId = 62;
_.key = null;
_.value = null;
function $HashMap$EntrySet(this$static, this$0){
  this$static.this$0 = this$0;
  return this$static;
}

function $iterator_3(this$static){
  return $HashMap$EntrySetIterator(new HashMap$EntrySetIterator(), this$static.this$0);
}

function contains_3(o){
  var entry, key, value;
  if (instanceOf(o, 21)) {
    entry = dynamicCast(o, 21);
    key = entry.getKey();
    if ($containsKey(this.this$0, key)) {
      value = $get_0(this.this$0, key);
      return equalsWithNullCheck(entry.getValue(), value);
    }
  }
  return false;
}

function iterator_6(){
  return $iterator_3(this);
}

function size_3(){
  return this.this$0.size;
}

function HashMap$EntrySet(){
}

_ = HashMap$EntrySet.prototype = new AbstractSet();
_.contains = contains_3;
_.iterator = iterator_6;
_.size_0 = size_3;
_.typeName = package_java_util_ + 'HashMap$EntrySet';
_.typeId = 63;
function $HashMap$EntrySetIterator(this$static, this$0){
  var list;
  this$static.this$0 = this$0;
  list = $ArrayList(new ArrayList());
  if (this$static.this$0.nullSlot !== ($clinit_144() , UNDEFINED)) {
    $add_2(list, $HashMap$EntryImpl(new HashMap$EntryImpl(), null, this$static.this$0.nullSlot));
  }
  addAllStringEntries(this$static.this$0.stringMap, list);
  addAllHashEntries(this$static.this$0.hashCodeMap, list);
  this$static.iter = $iterator_0(list);
  return this$static;
}

function $hasNext_3(this$static){
  return $hasNext_0(this$static.iter);
}

function $next_2(this$static){
  return this$static.last = dynamicCast($next(this$static.iter), 21);
}

function $remove_7(this$static){
  if (this$static.last === null) {
    throw $IllegalStateException_0(new IllegalStateException(), 'Must call next() before remove().');
  }
   else {
    $remove_4(this$static.iter);
    $remove_8(this$static.this$0, this$static.last.getKey());
    this$static.last = null;
  }
}

function hasNext_5(){
  return $hasNext_3(this);
}

function next_6(){
  return $next_2(this);
}

function HashMap$EntrySetIterator(){
}

_ = HashMap$EntrySetIterator.prototype = new Object_0();
_.hasNext = hasNext_5;
_.next_0 = next_6;
_.typeName = package_java_util_ + 'HashMap$EntrySetIterator';
_.typeId = 0;
_.iter = null;
_.last = null;
function $clinit_149(){
  $clinit_149 = nullMethod;
  $clinit_123();
}

function $NoSuchElementException(this$static){
  $clinit_149();
  $RuntimeException(this$static);
  return this$static;
}

function NoSuchElementException(){
}

_ = NoSuchElementException.prototype = new RuntimeException();
_.typeName = package_java_util_ + 'NoSuchElementException';
_.typeId = 64;
function init_3(){
  $onModuleLoad(new Space());
}

function gwtOnLoad(errFn, modName, modBase){
  $moduleName = modName;
  $moduleBase = modBase;
  if (errFn)
    try {
      init_3();
    }
     catch (e) {
      errFn(modName);
    }
   else {
    init_3();
  }
}

var typeIdArray = [{}, {1:1}, {1:1}, {7:1}, {10:1}, {10:1}, {9:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {8:1}, {5:1}, {5:1}, {1:1}, {9:1, 14:1, 15:1, 16:1}, {1:1, 9:1, 14:1, 15:1, 16:1}, {1:1, 9:1, 14:1, 15:1, 16:1}, {1:1}, {1:1, 9:1, 14:1, 15:1, 16:1}, {5:1}, {11:1}, {11:1}, {3:1}, {3:1}, {3:1}, {3:1}, {2:1}, {2:1, 4:1}, {2:1}, {6:1}, {9:1, 13:1, 14:1, 15:1, 16:1}, {9:1, 13:1, 14:1, 15:1, 16:1}, {9:1, 13:1, 14:1, 15:1, 16:1}, {18:1}, {18:1}, {18:1}, {9:1, 13:1, 14:1, 15:1, 16:1}, {9:1, 13:1, 14:1, 15:1, 16:1}, {9:1, 13:1, 14:1, 15:1, 16:1}, {9:1, 13:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {18:1}, {18:1}, {9:1, 12:1, 13:1, 14:1, 15:1, 16:1}, {6:1}, {9:1, 14:1, 15:1, 16:1}, {9:1, 14:1, 15:1, 16:1}, {3:1}, {3:1}, {3:1}, {3:1}, {3:1}, {3:1}, {17:1}, {3:1}, {19:1}, {20:1}, {20:1}, {19:1}, {21:1}, {20:1}, {3:1}];

if (com_allen_sauer_gwt_game_space_Space) {
  var __gwt_initHandlers = com_allen_sauer_gwt_game_space_Space.__gwt_initHandlers;  com_allen_sauer_gwt_game_space_Space.onScriptLoad(gwtOnLoad);
}
})();