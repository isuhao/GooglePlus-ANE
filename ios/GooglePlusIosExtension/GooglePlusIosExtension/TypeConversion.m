//
//  TypeConversion.m
//  GooglePlusIosExtension
//
//  Created by Aymeric Lamboley on 26/01/2015.
//  Copyright (c) 2015 DaVikingCode. All rights reserved.
//

#import "TypeConversion.h"

@implementation TypeConversion


- (FREResult) FREGetObject:(FREObject)object asString:(NSString**)value {
    
    FREResult result;
    uint32_t length = 0;
    const uint8_t* tempValue = NULL;
    
    result = FREGetObjectAsUTF8(object, &length, &tempValue);
    
    if (result != FRE_OK)
        return result;
    
    *value = [NSString stringWithUTF8String:(char *) tempValue];
    return FRE_OK;
}

- (FREResult) FREGetString:(NSString*) string asObject:(FREObject*) asString {
    
    if (string == nil)
        return FRE_INVALID_ARGUMENT;
    
    const char* utf8String = string.UTF8String;
    unsigned long length = strlen(utf8String);
    
    return FRENewObjectFromUTF8(length + 1, (uint8_t*) utf8String, asString);
}

+ (NSString *) ConvertNSDictionaryToJSONString:(NSDictionary *) dictionary {
    
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dictionary options:0 error:&error];
    
    NSString *JSONString;
    
    if (!jsonData)
        JSONString = [[NSString alloc] init];
    else {
        JSONString = [[NSString alloc] initWithBytes:[jsonData bytes] length:[jsonData length] encoding:NSUTF8StringEncoding];
        JSONString = [JSONString stringByReplacingOccurrencesOfString:@"\\/" withString:@"/"];
    }
    
    return JSONString;
}

@end
