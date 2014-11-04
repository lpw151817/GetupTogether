package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import cm.getuptogether.R;
import cm.getuptogether.R.layout;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.request.StringRequest;
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

	@Click
	void bt_register() {
		if (et_username.getText().toString().length() == 0)
			showToast("«Î ‰»Î’À∫≈");
		else if (et_password.getText().toString().length() == 0)
			showToast("«Î ‰»Î√‹¬Î");
		else if (et_phone.getText().toString().length() == 0)
			showToast("«Î ‰»Î ÷ª˙∫≈");
		else {
			// ∑¢ÀÕ◊¢≤·«Î«Û.
			// getVolleyTools().getQueue().add()
		}
	}
}
