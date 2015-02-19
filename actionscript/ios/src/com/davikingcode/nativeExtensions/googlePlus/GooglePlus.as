package com.davikingcode.nativeExtensions.googlePlus {

	import flash.display.BitmapData;
	import flash.display.JPEGEncoderOptions;
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.utils.ByteArray;

	public class GooglePlus extends EventDispatcher {

		private static var _instance:GooglePlus;

		public var extensionContext:ExtensionContext;

		private var _file:File;

		public static function getInstance():GooglePlus {

			if (!_instance)
				_instance = new GooglePlus();

			return _instance;
		}

		public function GooglePlus() {

			if (_instance)
				throw new Error("GooglePlus is already initialized.");

			_instance = this;

			extensionContext = ExtensionContext.createExtensionContext("com.davikingcode.nativeExtensions.GooglePlus", null);

			if (!extensionContext)
				throw new Error("GooglePlus native extension is not supported on this platform.");

			extensionContext.addEventListener(StatusEvent.STATUS, _onStatus);
		}

		private function _onStatus(sEvt:StatusEvent):void {

			switch (sEvt.code) {

				case GooglePlusEvent.LOGIN_FAILED:
					dispatchEvent(new GooglePlusEvent(GooglePlusEvent.LOGIN_FAILED));
					break;

				case GooglePlusEvent.LOGIN_SUCCESSED:
					dispatchEvent(new GooglePlusEvent(GooglePlusEvent.LOGIN_SUCCESSED));
					break;

				case GooglePlusEvent.POST_SHARED:
					dispatchEvent(new GooglePlusEvent(GooglePlusEvent.POST_SHARED));
					_deleteSharedImage();
					break;

				case GooglePlusEvent.POST_NOT_SHARED:
					dispatchEvent(new GooglePlusEvent(GooglePlusEvent.POST_NOT_SHARED));
					_deleteSharedImage();
					break;

				case GooglePlusEvent.DISCONNECTED:
					dispatchEvent(new GooglePlusEvent(GooglePlusEvent.DISCONNECTED));
					break;
			}
		}

		public function login(iOSKey:String, extended:Boolean = false):void {

			extensionContext.call("login", iOSKey, extended);
		}

		public function disconnect():void {

			extensionContext.call("disconnect");
		}

		public function isAuthenticated():Boolean {

			return extensionContext.call("isAuthenticated") as Boolean;
		}

		public function share(text:String, url:String = "", bitmapData:BitmapData = null):void {

			var image:String = "image" + new Date().time + ".jpg";

			if (bitmapData) {

				_file = File.documentsDirectory.resolvePath(image);
				var stream:FileStream = new FileStream();
				stream.open(_file, FileMode.WRITE);
				var bytes:ByteArray = bitmapData.encode(bitmapData.rect, new JPEGEncoderOptions());
				stream.writeBytes(bytes, 0, bytes.bytesAvailable);
				stream.close();
			}

			extensionContext.call("share", text, " " + url, image);
		}

		public function getUserMail():String {

			return extensionContext.call("getUserMail") as String;
		}

		public function getUserID():String {
			
			return extensionContext.call("getUserID") as String;
		}

		public function debuggerHelper():String {
			return "";
		}

		private function _deleteSharedImage():void {

			if (_file) {
				_file.deleteFileAsync();
				_file = null;
			}
		}

	}
}