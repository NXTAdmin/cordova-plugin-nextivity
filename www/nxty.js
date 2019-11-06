var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');
    
    

var nxty = {

  // Telephony functions....
  // Call from js: nxty.getCellInfo()
  // Call in Telephony.java: Telephony.getCellinfo().
  getCellInfo: function(successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "Telephony", "getCellInfo", []);
  },
}

module.exports = nxty;

    
