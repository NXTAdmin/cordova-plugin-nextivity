#import "TelephonyPlugin.h"
#import <Cordova/CDVPlugin.h>

@implementation GetCarrierInfo

- (NSDictionary)getCarrierInfo:(CDVInvokedUrlCommand*)command
{
    CTTelephonyNetworkInfo* networkInfo = [CTTelephonyNetworkInfo new];
    CTCarrier* carrier = networkInfo.subscriberCellularProvider;
    
    return @{
        "carrierName": carrier?.carrierName ?? "",
        "isoCountryCode": carrier?.isoCountryCode ?? "",
        "mobileCountryCode": carrier?.mobileCountryCode ?? "",
        "mobileNetworkCode": carrier?.mobileNetworkCode ?? "",
    }
}

@end
