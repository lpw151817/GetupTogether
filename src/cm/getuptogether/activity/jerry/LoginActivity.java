package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.ProgressDialog;
import android.widget.Button;
import cm.getuptogether.R;
import cm.getuptogether.activity.BaseActivity;

import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.ListFeedParam;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements com.renn.rennsdk.RennClient.LoginListener {

	private final String APP_ID = "184321";
	private final String API_KEY = "a3f4a750bf574ed2a3fec412dc5ae1d5";
	private final String SECRET_KEY = "1bb78d82d8864366a2a104f08da4a85d";
	private final String AUTHEN_THINGS = "read_user_feed status_update";
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
	 * publish_blog ���û����ݷ�����־ʱ��Ҫ�û������Ȩ�ޡ� publish_checkin ���û����ݷ�������ʱ��Ҫ�û������Ȩ�ޡ�
	 * publish_feed ���û����ݷ���������ʱ��Ҫ�û������Ȩ�ޡ� publish_share ���û����ݷ��ͷ���ʱ��Ҫ�û������Ȩ�ޡ���
	 * write_guestbook ���û����ݽ�������ʱ��Ҫ�û������Ȩ�ޡ� send_invitation ���û����ݷ�������ʱ��Ҫ�û������Ȩ�ޡ�
	 * send_request ���û����ݷ��ͺ������롢Ȧ�������ʱ��Ҫ�û������Ȩ�ޡ� send_message
	 * ���û����ݷ���վ����ʱ��Ҫ�û������Ȩ�ޡ� send_notification
	 * ���û����ݷ���֪ͨ��user_to_user��ʱ��Ҫ�û������Ȩ�ޡ� photo_upload ���û������ϴ���Ƭʱ��Ҫ�û������Ȩ�ޡ�
	 * status_update ���û����ݷ���״̬ʱ��Ҫ�û������Ȩ�ޡ� create_album ���û����ݷ������ʱ��Ҫ�û������Ȩ�ޡ�
	 * publish_comment ���û����ݷ�������ʱ��Ҫ�û������Ȩ�ޡ� operate_like ���û�����ִ��ϲ������ʱ��Ҫ�û������Ȩ�ޡ�
	 * 
	 * admin_page ���û������ݣ���������Թ����Ĺ�����ҳ��Ȩ�ޡ�
	 */

	@ViewById(R.id.btn_renren)
	Button btn_renren;

	RennClient rennClient;

	@Click
	void btn_renren() {
		// ��ʼ��RennClient
		rennClient = RennClient.getInstance(this);// ��ȡʵ��
		rennClient.init(APP_ID, API_KEY, SECRET_KEY);// ����Ӧ�ó�����Ϣ
		// ����Ȩ�޷�Χ
		rennClient.setScope(AUTHEN_THINGS);
		// ����Token����
		rennClient.setTokenType("bearer"); // ʹ��bearer token
		// ��¼
		rennClient.login(this);
		// ͨ��ΪrennClient���ü�����������½���
		rennClient.setLoginListener(this);

	}

	// ���˵�½ȡ���Ļص�
	@Override
	public void onLoginCanceled() {
		// TODO Auto-generated method stub
		System.out.println("cancel...");
	}

	ProgressDialog dialog;

	// ���˵�½�ɹ��Ļص�
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