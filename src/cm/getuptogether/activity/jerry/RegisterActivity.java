package cm.getuptogether.activity.jerry;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import cm.getuptogether.R;
import cm.getuptogether.R.layout;
import cm.getuptogether.activity.BaseActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
	@ViewsById
	EditText et_username, et_password, et_phone;
	@ViewById
	Button bt_register;

	@Click
	void bt_register() {
//		if(et_username.getText().toString().length()==0)
	}

}
