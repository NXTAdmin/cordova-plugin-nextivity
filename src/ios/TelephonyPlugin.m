#import "TelephonyPlugin.h"
#import <CoreTelephony/CTTelephonyNetworkInfo.h>
#import <CoreTelephony/CTCarrier.h>

@implementation Telephony

-(void) getCurrentRadioAccessTechnology:(CDVInvokedUrlCommand*)command
{
    // Run in background thread
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult;

        /*
         * Always assume that the plugin will fail.
         * Set the plugin result to fail.
         */
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"The cellular technology is unknown"];
        NSString* tech;
        NSString* NO_TECH = @"none";
        CTTelephonyNetworkInfo* networkInfo = [CTTelephonyNetworkInfo new];

        tech = NO_TECH;

        if (@available(iOS 12.0, *)) {
            for (NSString* key in networkInfo.serviceCurrentRadioAccessTechnology){
                tech = networkInfo.serviceCurrentRadioAccessTechnology[key];
            }
        } else {
            // Fallback on earlier versions iOS 7.0-12.0
            tech = networkInfo.currentRadioAccessTechnology ? networkInfo.currentRadioAccessTechnology : NO_TECH;
        }

        if (tech != NO_TECH) {
            // Set the plugin result to succeed.
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:tech];
        }

        // Send the function result back to Cordova.
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
  }

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
                if (networkInfo.serviceSubscriberCellularProviders[key].mobileNetworkCode != nil){
                    carrier = networkInfo.serviceSubscriberCellularProviders[key];
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
