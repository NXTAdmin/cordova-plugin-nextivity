import CoreTelephony;

/*
* Notes: The @objc shows that this class & function should be exposed to Cordova.
*/
@objc(Telephony) class Telephony: CDVPlugin {
  @objc(getCurrentRadioAccessTechnology:)
  func getCurrentRadioAccessTechnology(command: CDVInvokedUrlCommand) {
    /*
     * Always assume that the plugin will fail.
     * Set the plugin result to fail.
     */
    var pluginResult = CDVPluginResult(status: CDVCommandStatus_ERROR, messageAs: "The cellular technology is unknown");
    var tech: String;
    let telephony = CTTelephonyNetworkInfo();
    let NO_TECH = "none";

    if #available(iOS 12.0, *) {
      let EMPTY_RETURN = "[:]";

      tech = telephony.serviceCurrentRadioAccessTechnology?.description ?? NO_TECH;

      if tech == EMPTY_RETURN {
        tech = NO_TECH;
      }
    } else {
      // Fallback on earlier versions iOS 7.0-12.0
      tech = telephony.currentRadioAccessTechnology ?? NO_TECH;
    }

    if tech != NO_TECH {
      // Set the plugin result to succeed.
      pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: tech);
    }

    // Send the function result back to Cordova.
    self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
  }

  @objc(getCarrierInfo:)
  func getCarrierInfo(command: CDVInvokedUrlCommand) {
    var carrier: CTCarrier?

    let networkInfo = CTTelephonyNetworkInfo()

    if #available(iOS 12.0, *) {
        if let key = networkInfo.serviceSubscriberCellularProviders?.first(where: {$0.value.carrierName != nil})?.key {
            carrier = networkInfo.serviceSubscriberCellularProviders?[key]
        }
    } else {
        carrier = networkInfo.subscriberCellularProvider;
    };

    let data = [
        "carrierName": carrier?.carrierName ?? "",
        "isoCountryCode": carrier?.isoCountryCode ?? "",
        "mobileCountryCode": carrier?.mobileCountryCode ?? "",
        "mobileNetworkCode": carrier?.mobileNetworkCode ?? "",
    ]

    // Set the plugin result to succeed.
    let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: data);

    // Send the function result back to Cordova.
    self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
  }
}
