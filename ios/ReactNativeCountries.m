//
//  ReactNativeCountries.m
//  ReactNativeCountries
//
//  Created by Talut TASGIRAN on 24.12.2018.
//

#import "ReactNativeCountries.h"


@interface ReactNativeCountries () {
  NSArray *cachedCountryNames;
  NSArray *cachedCountryNamesWithCodes;
}
  
@end


@implementation ReactNativeCountries

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}
+(BOOL)requiresMainQueueSetup {
    return NO;
}

RCT_EXPORT_MODULE();

-(void)dealloc {
  cachedCountryNames = nil;
  cachedCountryNamesWithCodes = nil;
}


-(NSArray *)countryCodes {
  return [NSLocale ISOCountryCodes];
}

-(NSArray *)countryNames {
  NSArray *countryCodesArray = [self countryCodes];

  NSMutableArray *countryNamesArray = [NSMutableArray new];

  for (NSString *code in countryCodesArray) {
    NSString *name = [[NSLocale currentLocale] localizedStringForCountryCode:code];
    [countryNamesArray addObject:name];
  }

  return [NSArray arrayWithArray:countryNamesArray];
}

-(NSArray *)countryNamesWithCodes {
  NSArray *countryCodesArray = [self countryCodes];

  NSMutableArray *dictArray = [NSMutableArray new];

  for (NSString *code in countryCodesArray) {
    NSString *name = [[NSLocale currentLocale] localizedStringForCountryCode:code];
    [dictArray addObject:[NSDictionary dictionaryWithObjectsAndKeys:code, @"code", name, @"name", nil]];
  }

  return [NSArray arrayWithArray:dictArray];
}


RCT_EXPORT_METHOD(getCountryCodes: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
  resolve([self countryCodes]);
}

RCT_EXPORT_METHOD(getCountryNames: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
  if (cachedCountryNames == nil) {
    cachedCountryNames = [self countryNames];
  }
  
  resolve(cachedCountryNames);
}

RCT_EXPORT_METHOD(getCountryNamesWithCodes: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
  if (cachedCountryNamesWithCodes == nil) {
    cachedCountryNamesWithCodes = [self countryNamesWithCodes];
  }
  
  resolve(cachedCountryNamesWithCodes);
}

@end
  
