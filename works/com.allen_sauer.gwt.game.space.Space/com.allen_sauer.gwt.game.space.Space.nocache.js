function com_allen_sauer_gwt_game_space_Space(){
  var $wnd = window, $doc = document, external = $wnd.external, scriptsDone, loadDone, bodyDone, base = '', metaProps = {}, values = [], providers = [], answers = [], onLoadErrorFunc, propertyErrorFunc;
  if (!$wnd.__gwt_stylesLoaded) {
    $wnd.__gwt_stylesLoaded = {};
  }
  if (!$wnd.__gwt_scriptsLoaded) {
    $wnd.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    try {
      return external && (external.gwtOnLoad && $wnd.location.search.indexOf('gwt.hybrid') == -1);
    }
     catch (e) {
      return false;
    }
  }

  function maybeStartModule(){
    if (scriptsDone && (loadDone && bodyDone)) {
      var iframe = $doc.getElementById('com.allen_sauer.gwt.game.space.Space');
      var frameWnd = iframe.contentWindow;
      frameWnd.__gwt_initHandlers = com_allen_sauer_gwt_game_space_Space.__gwt_initHandlers;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name){
          return computePropValue(name);
        }
        ;
      }
      com_allen_sauer_gwt_game_space_Space = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, 'com.allen_sauer.gwt.game.space.Space', base);
    }
  }

  function computeScriptBase(){
    var thisScript, markerScript = $doc.getElementById('__gwt_js_marker_com.allen_sauer.gwt.game.space.Space');
    if (markerScript) {
      thisScript = markerScript.nextSibling;
    }
     else {
      $doc.write('<script id="__gwt_marker_com.allen_sauer.gwt.game.space.Space"><\/script>');
      markerScript = $doc.getElementById('__gwt_marker_com.allen_sauer.gwt.game.space.Space');
      if (markerScript) {
        thisScript = markerScript.previousSibling;
      }
    }
    function getDirectoryOfFile(path){
      var eq = path.lastIndexOf('/');
      return eq >= 0?path.substring(0, eq + 1):'';
    }

    ;
    if (thisScript && thisScript.src) {
      base = getDirectoryOfFile(thisScript.src);
    }
    if (base == '') {
      var baseElements = $doc.getElementsByTagName('base');
      if (baseElements.length > 0) {
        base = baseElements[baseElements.length - 1].href;
      }
       else {
        base = getDirectoryOfFile($doc.location.href);
      }
    }
     else if (base.match(/^\w+:\/\//)) {
    }
     else {
      var img = $doc.createElement('img');
      img.src = base + 'clear.cache.gif';
      base = getDirectoryOfFile(img.src);
    }
    if (markerScript) {
      markerScript.parentNode.removeChild(markerScript);
    }
  }

  function processMetas(){
    var metas = document.getElementsByTagName('meta');
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name = meta.getAttribute('name'), content;
      if (name) {
        if (name == 'gwt:property') {
          content = meta.getAttribute('content');
          if (content) {
            var value, eq = content.indexOf('=');
            if (eq >= 0) {
              name = content.substring(0, eq);
              value = content.substring(eq + 1);
            }
             else {
              name = content;
              value = '';
            }
            metaProps[name] = value;
          }
        }
         else if (name == 'gwt:onPropertyErrorFn') {
          content = meta.getAttribute('content');
          if (content) {
            try {
              propertyErrorFunc = eval(content);
            }
             catch (e) {
              alert('Bad handler "' + content + '" for "gwt:onPropertyErrorFn"');
            }
          }
        }
         else if (name == 'gwt:onLoadErrorFn') {
          content = meta.getAttribute('content');
          if (content) {
            try {
              onLoadErrorFunc = eval(content);
            }
             catch (e) {
              alert('Bad handler "' + content + '" for "gwt:onLoadErrorFn"');
            }
          }
        }
      }
    }
  }

  function __gwt_isKnownPropertyValue(propName, propValue){
    return propValue in values[propName];
  }

  function __gwt_getMetaProperty(name){
    var value = metaProps[name];
    return value == null?null:value;
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  providers['user.agent'] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (ua.indexOf('opera') != -1) {
      return 'opera';
    }
     else if (ua.indexOf('webkit') != -1) {
      return 'safari';
    }
     else if (ua.indexOf('msie') != -1) {
      var result = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3) {
        if (makeVersion(result) >= 6000) {
          return 'ie6';
        }
      }
    }
     else if (ua.indexOf('gecko') != -1) {
      var result = /rv:([0-9]+)\.([0-9]+)/.exec(ua);
      if (result && result.length == 3) {
        if (makeVersion(result) >= 1008)
          return 'gecko1_8';
      }
      return 'gecko';
    }
    return 'unknown';
  }
  ;
  values['user.agent'] = {'gecko':0, 'gecko1_8':1, 'ie6':2, 'opera':3, 'safari':4};
  com_allen_sauer_gwt_game_space_Space.onInjectionDone = function(){
    scriptsDone = true;
    maybeStartModule();
  }
  ;
  com_allen_sauer_gwt_game_space_Space.onScriptLoad = function(){
    loadDone = true;
    maybeStartModule();
  }
  ;
  computeScriptBase();
  processMetas();
  var strongName;
  if (isHostedMode()) {
    strongName = 'hosted.html?com_allen_sauer_gwt_game_space_Space';
  }
   else {
    try {
      unflattenKeylistIntoAnswers(['ie6'], '19570E8DAB24FC4EC8E75D00EDE87235');
      unflattenKeylistIntoAnswers(['opera'], '35CFE6FF64D8E223BCECE952C2D5052E');
      unflattenKeylistIntoAnswers(['gecko'], '4897F3B42F6B9DD3F322AC1A8BB8FF0F');
      unflattenKeylistIntoAnswers(['gecko1_8'], '60A84AF7460FB4F3CC59DB8DD24977EF');
      unflattenKeylistIntoAnswers(['safari'], 'EC43CC3DBC3000E669DA8DD1FE6E3336');
      strongName = answers[computePropValue('user.agent')];
    }
     catch (e) {
      return;
    }
    strongName += '.cache.html';
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      maybeStartModule();
      if ($doc.removeEventListener) {
        $doc.removeEventListener('DOMContentLoaded', onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  var scriptInjected;
  function maybeInjectFrame(){
    if (!scriptInjected && $doc.body) {
      scriptInjected = true;
      var iframe = $doc.createElement('iframe');
      iframe.id = 'com.allen_sauer.gwt.game.space.Space';
      iframe.src = base + strongName;
      iframe.style.cssText = 'position:absolute;width:0;height:0;border:none';
      $doc.body.appendChild(iframe);
    }
  }

  if ($doc.addEventListener) {
    $doc.addEventListener('DOMContentLoaded', function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    maybeInjectFrame();
    if (/loaded|complete/.test($doc.readyState)) {
      onBodyDone();
    }
  }
  , 50);
  if (!__gwt_stylesLoaded['Log.css']) {
    __gwt_stylesLoaded['Log.css'] = true;
    document.write('<link rel="stylesheet" href="' + base + 'Log.css">');
  }
  if (!__gwt_stylesLoaded['Game.css']) {
    __gwt_stylesLoaded['Game.css'] = true;
    document.write('<link rel="stylesheet" href="' + base + 'Game.css">');
  }
  if (!__gwt_stylesLoaded['Space.css']) {
    __gwt_stylesLoaded['Space.css'] = true;
    document.write('<link rel="stylesheet" href="' + base + 'Space.css">');
  }
  $doc.write("<script>com_allen_sauer_gwt_game_space_Space.onInjectionDone('com.allen_sauer.gwt.game.space.Space')<\/script>");
}

com_allen_sauer_gwt_game_space_Space.__gwt_initHandlers = function(resize, beforeunload, unload){
  var $wnd = window, oldOnResize = $wnd.onresize, oldOnBeforeUnload = $wnd.onbeforeunload, oldOnUnload = $wnd.onunload;
  $wnd.onresize = function(evt){
    try {
      resize();
    }
     finally {
      oldOnResize && oldOnResize(evt);
    }
  }
  ;
  $wnd.onbeforeunload = function(evt){
    var ret, oldRet;
    try {
      ret = beforeunload();
    }
     finally {
      oldRet = oldOnBeforeUnload && oldOnBeforeUnload(evt);
    }
    if (ret != null) {
      return ret;
    }
    if (oldRet != null) {
      return oldRet;
    }
  }
  ;
  $wnd.onunload = function(evt){
    try {
      unload();
    }
     finally {
      oldOnUnload && oldOnUnload(evt);
    }
  }
  ;
}
;
com_allen_sauer_gwt_game_space_Space();
