var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');
    
    

var phony = {

  // Telephony functions....
  // Call from js: phony.getCellInfo()
  // Call in Telephony.java: Telephony.getCellinfo().
  getCellInfo: function(successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "Telephony", "getCellInfo", []);
  },
}

module.exports = phony;
