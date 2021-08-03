#import <Cordova/CDVPlugin.h>

@interface Telephony : CDVPlugin

 - (void)getCarrierInfo:(CDVInvokedUrlCommand*)command;

@end
