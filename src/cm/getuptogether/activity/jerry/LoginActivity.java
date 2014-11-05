package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import cm.getuptogether.R;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.bean.jerry.ThirdPartyVerification;
import cm.getuptogether.dao.jerry.ThirdPartyVerificationDAO;
import cm.getuptogether.util.Contants;
import cm.getuptogether.util.RenrenUtils;

import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.GetUserParam;
import com.renn.rennsdk.param.ListFeedParam;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements com.renn.rennsdk.RennClient.LoginListener {

	// =======����========
	private final String RENREN_APP_ID = "272711";
	private final String RENREN_API_KEY = "da5be33beb8b4aa69f7f99f0737fb3eb";
	private final String RENREN_SECRET_KEY = "77dab849bcee456c8246607f4d588d07";
	private final String RENREN_AUTHEN_THINGS = "read_user_feed status_update";
	/**
	 * ���˵�Ȩ�� ����
	 * 
	 * read_user_blog ��ȡ�û���־ʱ��Ҫ�û������Ȩ�ޡ� read_user_checkin ��ȡ�û�������Ϣʱ��Ҫ�û������Ȩ�ޡ�
	 * read_user_feed ��ȡ�û�������ʱ��Ҫ�û������Ȩ�ޡ� read_user_guestbook ��ȡ�û����԰�ʱ��Ҫ�û������Ȩ�ޡ�
	 * read_user_invitation ��ȡ�û��������״��ʱ��Ҫ�û������Ȩ�ޡ� read_user_like_history
	 * ��ȡ�û�ϲ������ʷ��Ϣʱ��Ҫ�û������Ȩ�ޡ� read_user_message ��ȡ�û�վ����ʱ��Ҫ�û������Ȩ�ޡ�
	 * read_user_notification ��ȡ�û����յ���֪ͨʱ��Ҫ�û������Ȩ�ޡ� read_user_photo
	 * ��ȡ�û���������Ϣʱ��Ҫ�û������Ȩ�ޡ� read_user_status ��ȡ�û�״̬�����Ϣʱ��Ҫ�û������Ȩ�ޡ�
	 * read_user_album ��ȡ�û���������Ϣʱ��Ҫ�û������Ȩ�ޡ� read_user_comment
	 * ��ȡ�û����������Ϣʱ��Ҫ�û������Ȩ�ޡ� read_user_share ��ȡ�û����������Ϣʱ��Ҫ�û������Ȩ�ޡ�
	 * read_user_request ��ȡ�û���������Ȧ���������Ϣʱ��Ҫ�û������Ȩ�ޡ�
	 * 
	 * publish_blog ���û���ݷ�����־ʱ��Ҫ�û������Ȩ�ޡ� publish_checkin ���û���ݷ�������ʱ��Ҫ�û������Ȩ�ޡ�
	 * publish_feed ���û���ݷ���������ʱ��Ҫ�û������Ȩ�ޡ� publish_share ���û���ݷ��ͷ���ʱ��Ҫ�û������Ȩ�ޡ���
	 * write_guestbook ���û���ݽ�������ʱ��Ҫ�û������Ȩ�ޡ� send_invitation ���û���ݷ�������ʱ��Ҫ�û������Ȩ�ޡ�
	 * send_request ���û���ݷ��ͺ������롢Ȧ�������ʱ��Ҫ�û������Ȩ�ޡ� send_message
	 * ���û���ݷ���վ����ʱ��Ҫ�û������Ȩ�ޡ� send_notification
	 * ���û���ݷ���֪ͨ��user_to_user��ʱ��Ҫ�û������Ȩ�ޡ� photo_upload ���û�����ϴ���Ƭʱ��Ҫ�û������Ȩ�ޡ�
	 * status_update ���û���ݷ���״̬ʱ��Ҫ�û������Ȩ�ޡ� create_album ���û���ݷ������ʱ��Ҫ�û������Ȩ�ޡ�
	 * publish_comment ���û���ݷ�������ʱ��Ҫ�û������Ȩ�ޡ� operate_like ���û����ִ��ϲ������ʱ��Ҫ�û������Ȩ�ޡ�
	 * 
	 * admin_page ���û�����ݣ���������Թ���Ĺ�����ҳ��Ȩ�ޡ�
	 */

	@ViewById(R.id.btn_renren)
	Button btn_renren;

	RennClient rennClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dao = new ThirdPartyVerificationDAO(this);

		// ��ʼ��RennClient
		rennClient = RennClient.getInstance(this);// ��ȡʵ��
		rennClient.init(RENREN_APP_ID, RENREN_API_KEY, RENREN_SECRET_KEY);// ����Ӧ�ó�����Ϣ
		// ����Ȩ�޷�Χ
		rennClient.setScope(RENREN_AUTHEN_THINGS);
		// ����Token����
		rennClient.setTokenType("bearer"); // ʹ��bearer token

		if (rennClient.isLogin()) {
			getUserInfo();
		}

	}

	@Click
	void btn_renren() {
		// ͨ��ΪrennClient���ü����������½���
		rennClient.setLoginListener(this);
		// ��¼
		rennClient.login(this);

	}

	// ���˵�½ȡ���Ļص�
	@Override
	public void onLoginCanceled() {
		System.out.println("cancel...");
	}

	ProgressDialog dialog;

	// ���˵�½�ɹ��Ļص�
	@Override
	public void onLoginSuccess() {
		getUserInfo();
	}

	public void getUserInfo() {
		dialog = new ProgressDialog(this);
		dialog.show();

		GetUserParam userParam = new GetUserParam();
		try {
			rennClient.getRennService().sendAsynRequest(userParam, new CallBack() {

				@Override
				public void onSuccess(RennResponse arg0) {

					Intent intent = new Intent(LoginActivity.this, RegisterActivity_.class);
					intent.putExtra(Contants.INTENT_THRIDPART4REG_STRING, Contants.PREFIX_RENREN + arg0.toString());
					LoginActivity.this.startActivity(intent);
					dialog.dismiss();
				}

				@Override
				public void onFailed(String arg0, String arg1) {
					System.out.println("failed.................");
				}
			});
		} catch (RennException e) {
			e.printStackTrace();
		}
	}
}
