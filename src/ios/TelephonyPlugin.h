#import <Cordova/CDVPlugin.h>

@interface Telephony : CDVPlugin

- (void)getCurrentRadioAccessTechnology:(CDVInvokedUrlCommand*)command;
- (void)getCarrierInfo:(CDVInvokedUrlCommand*)command;

@end
