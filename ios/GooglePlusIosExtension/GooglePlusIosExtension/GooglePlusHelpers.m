//
//  GooglePlusHelpers.m
//  GooglePlusIosExtension
//
//  Created by Aymeric Lamboley on 07/01/2015.
//  Copyright (c) 2015 DaVikingCode. All rights reserved.
//

#import "GooglePlusHelpers.h"
#import "GoogleOpenSource/GTLPlusConstants.h"
#import "GoogleOpenSource/GTLQueryPlus.h"
#import "GoogleOpenSource/GTLPlusPerson.h"
#import "GoogleOpenSource/GTLServicePlus.h"

@interface GooglePlusHelpers ()

@end

@implementation GooglePlusHelpers

- (id) initWithContext:(FREContext) context {
    
    if (self = [super init])
        ctx = context;
    
    return self;
}

- (void) loginWithKey:(NSString *) key andExtendedPermissions:(BOOL) extended {
    
    GPPSignIn *signIn = [GPPSignIn sharedInstance];
    
    signIn.clientID = key;
    
    signIn.scopes = [[NSArray alloc] initWithObjects:kGTLAuthScopePlusLogin, nil];
    
    if (extended) {
        signIn.shouldFetchGoogleUserEmail = TRUE;
        signIn.shouldFetchGooglePlusUser = TRUE;
        signIn.shouldFetchGoogleUserID = TRUE;
    }
    
    [signIn setDelegate:self];
    [[GPPShare sharedInstance] setDelegate:self];
        
    if (![signIn trySilentAuthentication])
        [signIn authenticate];
}

- (void)finishedWithAuth:(GTMOAuth2Authentication *)auth error:(NSError *)error {
    
    if (error)
        [self dispatchEvent:@"LOGIN_FAILED" withParams:@""];
        
    else if ([[GPPSignIn sharedInstance] authentication])
        [self dispatchEvent:@"LOGIN_SUCCESSED" withParams:@""];
}

- (void) shareText:(NSString *) text andURL:(NSString *) url withImage:(NSString *) imageUrl {
    
    id<GPPNativeShareBuilder> shareBuilder = [[GPPShare sharedInstance] nativeShareDialog];
    
    [shareBuilder setPrefillText:[text stringByAppendingString:url]];
    
    NSURL *fileURL = [NSURL fileURLWithPath: [[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:imageUrl]];
    
    [shareBuilder attachImageData:[NSData dataWithContentsOfURL:fileURL]];
    
    [shareBuilder open];
}

- (void)finishedSharingWithError:(NSError *)error {
    
    if (error) {
        NSLog(@"%@", error);
        [self dispatchEvent:@"POST_NOT_SHARED" withParams:@""];
        
    } else
        [self dispatchEvent:@"POST_SHARED" withParams:@""];
}

- (void) disconnect {
    
    [[GPPSignIn sharedInstance] disconnect];
}

- (BOOL) isAuthenticated {
    
    return [[GPPSignIn sharedInstance] authentication] ? TRUE : FALSE;
}

- (void)didDisconnectWithError:(NSError *)error {
    
    if (error)
        NSLog(@"Received error %@", error);
        
    else
        [self dispatchEvent:@"DISCONNECTED" withParams:@""];
}

- (NSString *) getUserMail {
    
    return [GPPSignIn sharedInstance].userEmail;
}

- (NSString *) getUserID {
    
    return  [GPPSignIn sharedInstance].userID;
}

- (void) getUserInfo {
    
    GTLQueryPlus* query = [GTLQueryPlus queryForPeopleGetWithUserId:@"me"];
    
    [[[GPPSignIn sharedInstance] plusService] executeQuery:query completionHandler:^(GTLServiceTicket *ticket,
                                                                                     GTLPlusPerson *person,
                                                                                     NSError *error) {
        
        if (error) {
            
        } else {
            
            NSLog(@"%@", person.displayName);
        }
    }];
}

- (void) dispatchEvent:(NSString *) event withParams:(NSString * ) params {
    
    const uint8_t* par = (const uint8_t*) [params UTF8String];
    const uint8_t* evt = (const uint8_t*) [event UTF8String];
    
    FREDispatchStatusEventAsync(ctx, evt, par);
}

@end
