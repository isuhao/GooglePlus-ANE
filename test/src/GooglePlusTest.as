package {

	import com.davikingcode.nativeExtensions.googlePlus.GooglePlus;
	import com.davikingcode.nativeExtensions.googlePlus.GooglePlusEvent;

	import flash.display.Sprite;
	import flash.events.MouseEvent;
	import flash.text.TextField;
	import flash.text.TextFormat;

	/**
	 * @author Aymeric
	 */
	public class GooglePlusTest extends Sprite {
		
		[Embed(source="logo.jpg")]
		private const _logoBitmap:Class;
		
		private var _googlePlus:GooglePlus;
		private const _KEY:String = "103213343972-viosupja0di6kact26le0mvf720ab726.apps.googleusercontent.com";
		
		private var _txtGoogleLogin:TextField;
		private var _btnGoogleLogin:Sprite;
		private var _txtPostShared:TextField;
		private var _btnPostShared:Sprite;

		public function GooglePlusTest() {
			
			_googlePlus = new GooglePlus();
			
			_googlePlus.addEventListener(GooglePlusEvent.LOGIN_FAILED, _loginFailed);
			_googlePlus.addEventListener(GooglePlusEvent.LOGIN_SUCCESSED, _loginSuccessed);
			_googlePlus.addEventListener(GooglePlusEvent.POST_NOT_SHARED, _postNotShared);
			_googlePlus.addEventListener(GooglePlusEvent.POST_SHARED, _postShared);
			_googlePlus.addEventListener(GooglePlusEvent.DISCONNECTED, _disconnected);
			_googlePlus.addEventListener(GooglePlusEvent.TOKEN, _token);
			
			_txtGoogleLogin = new TextField();
			_txtGoogleLogin.defaultTextFormat = new TextFormat("Arial", 20);
			_txtGoogleLogin.width = stage.stageWidth;
			_txtGoogleLogin.text = "Logged in Google+ ? False.";
			_txtGoogleLogin.mouseEnabled = false;
			addChild(_txtGoogleLogin);
			
			_btnGoogleLogin = new Sprite();
			_btnGoogleLogin.graphics.beginFill(0xcccccc);
			_btnGoogleLogin.graphics.drawRect(0, 0, 350, 200);
			_btnGoogleLogin.graphics.endFill();
			_btnGoogleLogin.x = stage.stageWidth / 2;
			addChild(_btnGoogleLogin);
			
			_txtPostShared = new TextField();
			_txtPostShared.defaultTextFormat = new TextFormat("Arial", 20);
			_txtPostShared.width = stage.stageWidth;
			_txtPostShared.text = "Share post ?";
			_txtPostShared.y = 250;
			_txtPostShared.mouseEnabled = false;
			addChild(_txtPostShared);
			
			_btnPostShared = new Sprite();
			_btnPostShared.graphics.beginFill(0xcccccc);
			_btnPostShared.graphics.drawRect(0, 0, 350, 200);
			_btnPostShared.graphics.endFill();
			_btnPostShared.x = stage.stageWidth / 2;
			_btnPostShared.y = 250;
			addChild(_btnPostShared);

			_btnGoogleLogin.addEventListener(MouseEvent.CLICK, _login);
			_btnPostShared.addEventListener(MouseEvent.CLICK, _shareRandomPost);
		}

		private function _login(mEvt:MouseEvent):void {
			
			if (!_googlePlus.isAuthenticated())
				_googlePlus.login(_KEY);
			else
				_googlePlus.disconnect();
		}
		
		private function _shareRandomPost(mEvt:MouseEvent):void {
			
			if (_googlePlus.isAuthenticated())
				_googlePlus.share("An awesome ANE for Instagram", "http://github.com/DaVikingCode/Instagram-ANE", new _logoBitmap().bitmapData);
		}

		private function _loginSuccessed(gpEvt:GooglePlusEvent):void {
			
			trace("login successed");
			
			_btnGoogleLogin.graphics.beginFill(0x00FF00);
			_btnGoogleLogin.graphics.drawRect(0, 0, 350, 200);
			_btnGoogleLogin.graphics.endFill();
			
			_txtGoogleLogin.text = "Logged in Google+ ? True.";
			
			//_googlePlus.getAuth();
		}

		private function _loginFailed(gpEvt:GooglePlusEvent):void {
			
			trace("login failed");
			
			_txtGoogleLogin.text = "Logged in Google+ ? Failed.";
		}
		
		private function _postShared(gpEvt:GooglePlusEvent):void {
			
			trace("post shared");
			
			_txtPostShared.text = "Post Shared";
		}

		private function _postNotShared(gpEvt:GooglePlusEvent):void {
			
			trace("post not shared");
			
			_txtPostShared.text = "Post NOT Shared";
		}
		
		private function _disconnected(gpEvt:GooglePlusEvent):void {
			
			trace("disconnected");
			
			_btnGoogleLogin.graphics.beginFill(0xFF0000);
			_btnGoogleLogin.graphics.drawRect(0, 0, 350, 200);
			_btnGoogleLogin.graphics.endFill();
			
			_txtGoogleLogin.text = "Logged in Google+ ? False.";
		}
		
		private function _token(gpEvt:GooglePlusEvent):void {
			
			trace("token: " + gpEvt.informations);
		}
	}
}
