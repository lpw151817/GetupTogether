package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import cm.getuptogether.R;
import cm.getuptogether.R.layout;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.request.StringRequest;
import cm.getuptogether.util.Contants;
import cm.getuptogether.util.ThirdLoginUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
	@ViewById
	EditText et_username;
	@ViewById
	EditText et_password;
	@ViewById
	EditText et_phone;
	@ViewById
	Button bt_register;

	@AfterViews
	void ini() {
		String thiredPartResponse = getIntent().getExtras().getString(Contants.INTENT_THRIDPART4REG_STRING, "");

		if (thiredPartResponse != null && thiredPartResponse != "") {
			// 如果是用人人登陆的
			if (thiredPartResponse.startsWith(Contants.PREFIX_RENREN)) {
				et_username.setText(ThirdLoginUtils.renren_getUsername(thiredPartResponse));
			} else if (thiredPartResponse.startsWith(Contants.PREFIX_QQ)) {
				et_username.setText(ThirdLoginUtils.qq_getNickName(thiredPartResponse));
			}
		}
	}

	@Click
	void bt_register() {
		if (et_username.getText().toString().length() == 0)
			showToast("请输入账号");
		else if (et_password.getText().toString().length() == 0)
			showToast("请输入密码");
		else if (et_phone.getText().toString().length() == 0)
			showToast("请输入手机号");
		else {
			// 发送注册请求.
			// getVolleyTools().getQueue().add()
		}
	}
}
