//
//  GooglePlusHelpers
//  GooglePlusIosExtension
//
//  Created by Aymeric Lamboley on 07/01/2015.
//  Copyright (c) 2015 DaVikingCode. All rights reserved.
//

#import <GooglePlus/GooglePlus.h>
#import "FlashRuntimeExtensions.h"

@class GTMOAuth2Authentication;

@interface GooglePlusHelpers : NSObject <GPPSignInDelegate, GPPShareDelegate> {
    
    FREContext ctx;
}

- (id) initWithContext:(FREContext) context;

- (void) loginWithKey:(NSString *) key andExtendedPermissions:(BOOL) extended;
- (void) disconnect;
- (BOOL) isAuthenticated;

- (void) shareText:(NSString *) text andURL:(NSString *) url withImage:(NSString *) imageURL;

- (NSString *) getUserMail;
- (NSString *) getUserID;
- (void) getAuth;
- (void) getUserInfo;

- (void) dispatchEvent:(NSString *) event withParams:(NSString * ) params;

@end
