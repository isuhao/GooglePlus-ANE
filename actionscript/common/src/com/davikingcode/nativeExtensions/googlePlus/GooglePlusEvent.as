package com.davikingcode.nativeExtensions.googlePlus {

	import flash.events.Event;

	public class GooglePlusEvent extends Event {

		static public const LOGIN_FAILED:String = "LOGIN_FAILED";
		static public const LOGIN_SUCCESSED:String = "LOGIN_SUCCESSED";

		static public const POST_SHARED:String = "POST_SHARED";
		static public const POST_NOT_SHARED:String = "POST_NOT_SHARED";

		static public const DISCONNECTED:String = "DISCONNECTED";

		static public const TOKEN:String = "TOKEN";

		static public const USER_INFO:String = "USER_INFO";

		private var _informations:String;

		public function GooglePlusEvent(type:String, informations:String = "", bubbles:Boolean = false, cancelable:Boolean = false) {
			super(type, bubbles, cancelable);

			_informations = informations;
		}

		public function get informations():String {

			return _informations;
		}
	}
}
