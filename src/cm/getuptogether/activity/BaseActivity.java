package cm.getuptogether.activity;

import cm.getuptogether.Application;
import cm.getuptogether.dao.BaseDAO;
import cm.getuptogether.util.VolleyTools;

import com.google.gson.Gson;

import android.app.Activity;

public class BaseActivity extends Activity {
	// 用于本地数据库存储
	protected BaseDAO dao;

	@Override
	protected void onStop() {
		super.onStop();
		getVolleyTools().getQueue().cancelAll(this);
	}

	public VolleyTools getVolleyTools() {
		return Application.getTools();
	}

	public Gson getGson() {
		return Application.getGson();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 释放掉dao
		if (dao != null) {
			dao.release();// 释放dao中的databasehelper
			dao = null;
		}

	}
}
