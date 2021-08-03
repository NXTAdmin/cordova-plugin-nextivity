#import "TelephonyPlugin.h"
#import <CoreTelephony/CTTelephonyNetworkInfo.h>
#import <CoreTelephony/CTCarrier.h>

@implementation Telephony

- (void)getCarrierInfo:(CDVInvokedUrlCommand*)command
{
    // Run in background thread
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult;
        CTCarrier* carrier;
        CTTelephonyNetworkInfo* networkInfo = [CTTelephonyNetworkInfo new];
        NSDictionary* data = @{
            @"carrierName" : @"",
            @"isoCountryCode" : @"",
            @"mobileCountryCode" : @"",
            @"mobileNetworkCode" : @"",
        };

        if (@available(iOS 12.0, *)) {
            // Find the active provider
            for (NSString* key in networkInfo.serviceSubscriberCellularProviders){
                if (networkInfo.serviceSubscriberCellularProviders[key] != nil){
                    carrier = networkInfo.serviceSubscriberCellularProviders[key];
                    break;
                }
            }
        } else {
            carrier = networkInfo.subscriberCellularProvider;
        };

        if (carrier){
            data = @{
                @"carrierName" : carrier.carrierName ? carrier.carrierName : @"",
                @"isoCountryCode" : carrier.isoCountryCode ? carrier.isoCountryCode : @"",
                @"mobileCountryCode" : carrier.mobileCountryCode ? carrier.mobileCountryCode : @"",
                @"mobileNetworkCode" : carrier.mobileNetworkCode ? carrier.mobileNetworkCode : @"",
            };
        }


        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:data];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

@end
