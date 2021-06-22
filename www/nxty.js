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
    cordova.exec(successCallback, errorCallback, 'Telephony', 'getCellInfo',
        []);
  },
};

/**
 * Constants that describe the current radio access technology.
 *
 * See the full docs at
 * [developer.apple.com](https://developer.apple.com/documentation/coretelephony/cttelephonynetworkinfo/radio_access_technology_constants)
 */
phony.radioAccessTechnologyConstants = {
  /**
   * The General Packet Radio Service (GPRS) radio access technology.
   */
  CTRadioAccessTechnologyGPRS: 'CTRadioAccessTechnologyGPRS',
  /**
   * The Enhanced Data rates for GSM Evolution (EDGE) radio access technology.
   */
  CTRadioAccessTechnologyEdge: 'CTRadioAccessTechnologyEdge',
  /**
   * The Wideband Code Division Multiple Access (WCDMA) radio access technology.
   */
  CTRadioAccessTechnologyWCDMA: 'CTRadioAccessTechnologyWCDMA',
  /**
   * The High-Speed Downlink Packet Access (HSDPA) radio access technology.
   */
  CTRadioAccessTechnologyHSDPA: 'CTRadioAccessTechnologyHSDPA',
  /**
   * The High-Speed Uplink Packet Acess (HSUPA) radio access technology.
   */
  CTRadioAccessTechnologyHSUPA: 'CTRadioAccessTechnologyHSUPA',
  /**
   * The Code Division Multiple Access (CDMA) 1x radio access technology.
   */
  CTRadioAccessTechnologyCDMA1x: 'CTRadioAccessTechnologyCDMA1x',
  /**
   * The Code Division Multiple Access (CDMA) Evolution-Data Optimized (EV-DO)
   * Rev. 0 radio access technology.
   */
  CTRadioAccessTechnologyCDMAEVDORev0: 'CTRadioAccessTechnologyCDMAEVDORev0',
  /**
   * The Code Division Multiple Access (CDMA) Evolution-Data Optimized (EV-DO)
   * Rev. A radio access technology.
   */
  CTRadioAccessTechnologyCDMAEVDORevA: 'CTRadioAccessTechnologyCDMAEVDORevA',
  /**
   * The Code Division Multiple Access (CDMA) Evolution-Data Optimized (EV-DO)
   * Rev. B radio access technology.
   */
  CTRadioAccessTechnologyCDMAEVDORevB: 'CTRadioAccessTechnologyCDMAEVDORevB',
  /**
   * The Enhanced High Rate Packet Data (eHRPD) radio access technology.
   */
  CTRadioAccessTechnologyeHRPD: 'CTRadioAccessTechnologyeHRPD',
  /**
   * The Long-Term Evolution (LTE) radio access technology.
   */
  CTRadioAccessTechnologyLTE: 'CTRadioAccessTechnologyLTE',
  /**
   * The 5G New Radio Non-Standalone (NRNSA) radio access technology.
   */
  CTRadioAccessTechnologyNRNSA: 'CTRadioAccessTechnologyNRNSA',
  /**
   * The 5G New Radio (NR) radio access technology.
   */
  CTRadioAccessTechnologyNR: 'CTRadioAccessTechnologyNR',
};

/**
 * Get the radio access technology constant that the phone is currently
 * operating on.
 *
 * **iOS only**
 * @param {function} success - success callback
 * @param {function} error - error callback
 * @return {string} A radio access technology constant
 */
phony.getCurrentRadioAccessTechnology = (success, error) => {
  exec(getTechStringFromResult, error, 'Telephony',
      'getCurrentRadioAccessTechnology', []);

  /**
   * Get the Radio Access Technology Constant from the string.
   *
   * *Depending on the OS version a different string format is returned.*
   *
   * @param {string} res
   */
  function getTechStringFromResult(res) {
    const regex = /": "(.*)"/;

    if (res.length <= 3) {
      error('The cellular technology is unknown');
    } else if (res.charAt(0) === '[') {
      // iOS >= 12 uses
      // CTTelephonyNetworkInfo.serviceCurrentRadioAccessTechnology
      // which returns a dictionary containing a Radio Access Technology
      // Constant
      const captureGroup = regex.exec(res);
      success(captureGroup[1]);
    } else {
      // iOS < 12 uses
      // CTTelephonyNetworkInfo.currentRadioAccessTechnology
      // which returns a Radio Access Technology Constant
      success(res);
    }
  }
};

phony.getTelephonyNetworkInfo = (success, error) => {
  exec(processResult, error, 'a',
      'getTelephonyNetworkInfo', []);

  function processResult(res) {
    console.warn(`getTelephonyNetworkInfo - res:`, res);
    success(res);
  }
};

module.exports = phony;
