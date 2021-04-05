/*
* Notes: The @objc shows that this class & function should be exposed to Cordova.
*/
@objc(Telephony) class Telephony : CDVPlugin {
  @objc(getCellInfo:) // Declare your function name.
  func getCellInfo(command: CDVInvokedUrlCommand) { // write the function code.
    /*
     * Always assume that the plugin will fail.
     * Even if in this example, it can't.
     */
    // Set the plugin result to fail.
    var pluginResult = CDVPluginResult (status: CDVCommandStatus_ERROR, messageAs: "The Plugin Failed");
    // Set the plugin result to succeed.
        pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "The plugin succeeded");
    // Send the function result back to Cordova.
    self.commandDelegate!.send(pluginResult, callbackId: command.callbackId);
  }
}
