var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');
    
    

var Telephony = {
  getInfo: function(successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "Telephony", "getCellInfo", []);
  },
}

module.exports = Telephony;

    
