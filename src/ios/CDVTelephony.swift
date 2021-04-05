@objc(Telephony) class Telephony : CDVPlugin {
  // MARK: Properties
  var pluginResult = CDVPluginResult(status: CDVCommandStatus_ERROR)

  @objc(getCellInfo:) func add(_ command: CDVInvokedUrlCommand) {
    var pluginResult = CDVPluginResult(status: CDVCommandStatus_ERROR)

    pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs:
    "success")
    }

    self.commandDelegate!.send(pluginResult, callbackId: command.callbackId)
  }
}
