#import "TelephonyPlugin.h"
//#import <CoreTelephony/CTTelephonyNetworkInfo.h>

@implementation GetCarrierInfo

- (void)getCarrierInfo:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult;

//    CTTelephonyNetworkInfo* networkInfo = [CTTelephonyNetworkInfo new];
//    CTCarrier* carrier = networkInfo.subscriberCellularProvider;

        NSDictionary *data = @{
                @"carrierName" : @"",
                @"isoCountryCode" : @"",
                @"mobileCountryCode" : @"",
                @"mobileNetworkCode" : @"",
        };

//    NSDictionary *data = @{
//            carrierName : carrier?.carrierName ?? "",
//            isoCountryCode : carrier?.isoCountryCode ?? "",
//            mobileCountryCode : carrier?.mobileCountryCode ?? "",
//            mobileNetworkCode : carrier?.mobileNetworkCode ?? "",
//
//    }


    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:data];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
