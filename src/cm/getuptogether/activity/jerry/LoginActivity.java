package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import cm.getuptogether.R;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.bean.jerry.ThirdPartyVerification;
import cm.getuptogether.dao.jerry.ThirdPartyVerificationDAO;

import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.ListFeedParam;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements com.renn.rennsdk.RennClient.LoginListener {

	private final String APP_ID = "272711";
	private final String API_KEY = "da5be33beb8b4aa69f7f99f0737fb3eb";
	private final String SECRET_KEY = "77dab849bcee456c8246607f4d588d07";
	private final String AUTHEN_THINGS = "read_user_feed status_update";
	/**
	 * 人人的权限 描述
	 * 
	 * read_user_blog 获取用户日志时需要用户授予的权限。 read_user_checkin 获取用户报到信息时需要用户授予的权限。
	 * read_user_feed 获取用户新鲜事时需要用户授予的权限。 read_user_guestbook 获取用户留言板时需要用户授予的权限。
	 * read_user_invitation 获取用户被邀请的状况时需要用户授予的权限。 read_user_like_history
	 * 获取用户喜欢的历史信息时需要用户授予的权限。 read_user_message 获取用户站内信时需要用户授予的权限。
	 * read_user_notification 获取用户已收到的通知时需要用户授予的权限。 read_user_photo
	 * 获取用户相册相关信息时需要用户授予的权限。 read_user_status 获取用户状态相关信息时需要用户授予的权限。
	 * read_user_album 获取用户相册相关信息时需要用户授予的权限。 read_user_comment
	 * 获取用户评论相关信息时需要用户授予的权限。 read_user_share 获取用户分享相关信息时需要用户授予的权限。
	 * read_user_request 获取用户好友请求、圈人请求等信息时需要用户授予的权限。
	 * 
	 * publish_blog 以用户身份发布日志时需要用户授予的权限。 publish_checkin 以用户身份发布报到时需要用户授予的权限。
	 * publish_feed 以用户身份发送新鲜事时需要用户授予的权限。 publish_share 以用户身份发送分享时需要用户授予的权限。。
	 * write_guestbook 以用户身份进行留言时需要用户授予的权限。 send_invitation 以用户身份发送邀请时需要用户授予的权限。
	 * send_request 以用户身份发送好友申请、圈人请求等时需要用户授予的权限。 send_message
	 * 以用户身份发送站内信时需要用户授予的权限。 send_notification
	 * 以用户身份发送通知（user_to_user）时需要用户授予的权限。 photo_upload 以用户身份上传照片时需要用户授予的权限。
	 * status_update 以用户身份发布状态时需要用户授予的权限。 create_album 以用户身份发布相册时需要用户授予的权限。
	 * publish_comment 以用户身份发布评论时需要用户授予的权限。 operate_like 以用户身份执行喜欢操作时需要用户授予的权限。
	 * 
	 * admin_page 以用户的身份，管理其可以管理的公共主页的权限。
	 */

	@ViewById(R.id.btn_renren)
	Button btn_renren;

	RennClient rennClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dao=new ThirdPartyVerificationDAO(this);
		
		
		
		
	}
	
	@Click
	void btn_renren() {
		// 初始化RennClient
		rennClient = RennClient.getInstance(this);// 获取实例
		rennClient.init(APP_ID, API_KEY, SECRET_KEY);// 设置应用程序信息
		// 设置权限范围
		rennClient.setScope(AUTHEN_THINGS);
		// 设置Token类型
		rennClient.setTokenType("bearer"); // 使用bearer token
		// 登录
		rennClient.login(this);
		// 通过为rennClient设置监听来处理登陆结果
		rennClient.setLoginListener(this);

	}

	// 人人登陆取消的回调
	@Override
	public void onLoginCanceled() {
		// TODO Auto-generated method stub
		System.out.println("cancel...");
	}

	ProgressDialog dialog;

	// 人人登陆成功的回调
	@Override
	public void onLoginSuccess() {
		// TODO Auto-generated method stub
		dialog = new ProgressDialog(this);
		dialog.show();
		System.out.println("success..." + rennClient.getAccessToken());

		ListFeedParam feedParam = new ListFeedParam();
		try {
			rennClient.getRennService().sendAsynRequest(feedParam, new CallBack() {

				@Override
				public void onSuccess(RennResponse arg0) {
					// TODO Auto-generated method stub
					System.out.println(arg0.toString());
					dialog.dismiss();
				}

				@Override
				public void onFailed(String arg0, String arg1) {
					// TODO Auto-generated method stub
					System.err.println("fffffffffffffffffffffffffffffffffffff");
				}
			});
		} catch (RennException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
