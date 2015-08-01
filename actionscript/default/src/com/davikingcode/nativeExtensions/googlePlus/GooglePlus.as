package com.davikingcode.nativeExtensions.googlePlus {

	import flash.display.BitmapData;
	import flash.events.EventDispatcher;
	import flash.external.ExtensionContext;

	public class GooglePlus extends EventDispatcher {

		private static var _instance:GooglePlus;

		public static function getInstance():GooglePlus {

			if (!_instance)
				_instance = new GooglePlus();

			return _instance;
		}

		public function GooglePlus() {

			if (_instance)
				throw new Error("GooglePlus is already initialized.");

			_instance = this;
		}

		/**
		* Login to the Google+ account user. On <i>iOS</i> If the user doesn't have the Google+ app, the login is done via the Google+ website. Then the 
		* user is redirected to its app if the login process is cancelled or completed. On <i>Android</i> it opens a popin.
		* @param iOSKey The client ID of the app from the Google APIs console <b>(for iOS only)</b>. Not considered on Android.
		* @param extended If true we can read user's mail, user's ID and know who you are on Google+.
		*/
		public function login(iOSKey:String, extended:Boolean = false):void {
		}

		/**
		* Disconnects the user from the app and revokes previous authentication. If the operation succeeds, the OAuth 2.0 token is also removed from keychain.
		* Dispatch <code>GooglePlusEvent.DISCONNECTED</code> on success.
		*/
		public function disconnect():void {
		}

		public function isAuthenticated():Boolean {
			return false;
		}

		/**
		* Share a prefilled text with an url and an image. Will dispatch a <i>GooglePlusEvent.POST_SHARED</i> on success or <i>GooglePlusEvent.POST_NOT_SHARED</i> if user canceled.
		* @param text Sets the text to prefill user's comment in the share dialog.
		* @param url The URL to share.
		* @param bitmapData A bitmapData you want to share.
		*/
		public function share(text:String, url:String = "", bitmapData:BitmapData = null):void {
		}

		/**
		* Grab the user mail, only works if you logged with extended set to true.
		*/
		public function getUserMail():String {
			return "";
		}

		/**
		* Get the user id, only works if you logged with extended set to true.
		*/
		public function getUserID():String {
			return "";
		}
		
		/**
		* Get the authentification token. Listen to <code>GooglePlusEvent.TOKEN</code> and read <code>informations</code>.
		*/
		public function getAuth():void {
		}

		/**
		* Get all the informations about the identified user. Listen to <code>GooglePlusEvent.USER_INFO</code> and read <code>informations</code> as a stringified JSON.
		*/
		public function getUserInfo():void {
		}

		/**
		* <b>Android only!</b> Print into the <a href="http://developer.android.com/tools/debugging/ddms.html">DDMS</a> or <a href="http://developer.android.com/tools/help/monitor.html">Device Monitor</a> the <b>SHA1 key</b> and the 
		* <b>package</b> that must be set inside the <a href="https://console.developers.google.com/project">Google+ console</a>.
		* @return a String that contains your <b>SHA1</b> key!
		*/
		public function debuggerHelper():String {
			return "";
		}
	}
}