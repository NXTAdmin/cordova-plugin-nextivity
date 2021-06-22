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

  @objc(getCTCarrier:)
  func getCTCarrier(command: CDVInvokedUrlCommand) {
    let carrier = CTCarrier();

    let data = [
      "allowsVOIP": carrier.allowsVOIP,
      // because carrierName is optional string param of CTCarrier set a default value ""
      "carrierName": carrier.carrierName ?? "",
      // because isoCountryCode is optional string param of CTCarrier set a default value ""
      "isoCountryCode": carrier.isoCountryCode ?? "",
      // because mobileCountryCode is optional string param of CTCarrier set a default value ""
      "mobileCountryCode": carrier.mobileCountryCode ?? "",
      // because mobileNetworkCode is optional string param of CTCarrier set a default value ""
      "mobileNetworkCode": carrier.mobileNetworkCode ?? "",
    ] as [String: Any]

    // Set the plugin result to succeed.
    let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: data);

    // Send the function result back to Cordova.
    self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
  }
}
