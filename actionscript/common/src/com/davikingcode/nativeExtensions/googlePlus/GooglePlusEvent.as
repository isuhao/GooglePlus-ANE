package com.davikingcode.nativeExtensions.googlePlus {

	import flash.events.Event;

	public class GooglePlusEvent extends Event {

		static public const LOGIN_FAILED:String = "LOGIN_FAILED";
		static public const LOGIN_SUCCESSED:String = "LOGIN_SUCCESSED";

		static public const POST_SHARED:String = "POST_SHARED";
		static public const POST_NOT_SHARED:String = "POST_NOT_SHARED";

		static public const DISCONNECTED:String = "DISCONNECTED";

		public function GooglePlusEvent(type:String, bubbles:Boolean = false, cancelable:Boolean = false) {
			super(type, bubbles, cancelable);
		}
	}
}
