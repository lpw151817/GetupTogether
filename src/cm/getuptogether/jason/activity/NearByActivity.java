package cm.getuptogether.jason.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import cm.getuptogether.R;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.jason.adapter.ArrayListAdapter.ListItemEventListener;
import cm.getuptogether.jason.adapter.alarm.NearByAdapter;
import cm.getuptogether.jason.bean.NearByBean;
import cm.getuptogether.jason.param.NearByParam;
import cm.getuptogether.request.StringRequest;
import cm.getuptogether.util.VolleyTools;

public class NearByActivity extends BaseActivity implements
		OnRefreshListener2<ListView>, OnItemClickListener,
		ListItemEventListener {
	private final String url = "http://114.215.178.134:8080/getup/action/Common_getNearByAlarms";
	private EditText editText_query;
	private Button button_search;
	private VolleyTools tool;
	private NearByAdapter adapter;
	private int page = 1;
	private String alarmTime = "0710";
	private double longitude = 114.368161  ;
	private double latitude = 30.547824 ;
	NearByParam value;
	private PullToRefreshListView ptrListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_alarm_nearby);
		editText_query = (EditText) findViewById(R.id.editText_query);
		button_search = (Button) findViewById(R.id.button_search);
		ptrListView = (PullToRefreshListView) findViewById(R.id.ptrListView);
		// 得到VolleyTools的实例，已定义在Application，从BaseActivity中取出
		tool = getVolleyTools();
		init();

	}

	void init() {

		value = new NearByParam(longitude, latitude, alarmTime, page);
		ptrListView.setAdapter(adapter = new NearByAdapter(this, this, tool
				.getImageLoader()));
		ptrListView.setMode(Mode.BOTH);
		ptrListView.setOnRefreshListener(this);
		ptrListView.setOnItemClickListener(this);

		button_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				alarmTime = editText_query.getText().toString();
				page = 1;
				Log.e("query", alarmTime);
				value.setAlarmTime(alarmTime);
				value.setPage(page);
				doNetworkRequest(value);

			}
		});
		// 数据请求
		// post
		doNetworkRequest(value);
	}

	private void doNetworkRequest(Object value) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("param", getGson().toJson(value));
		System.out.println(getGson().toJson(value));
		tool.getQueue().add(new StringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String result) {
				System.out.println(result);
				onGetRrightResult(result);
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println(arg0.toString());
			}
		}, param));

	}

	private void onGetRrightResult(String result) {
		List<NearByBean> nearbys = com.alibaba.fastjson.JSONArray.parseArray(
				result, NearByBean.class);
		if (page > 1) {
			if (nearbys.size() == 0) {
				Toast.makeText(NearByActivity.this, "没有更多数据",
						Toast.LENGTH_SHORT).show();
			} else {
				Log.e("onGetRightResult", "add Data!");
				adapter.addDate(nearbys);
			}
		} else {
			adapter.resetToList(nearbys);
		}
		ptrListView.onRefreshComplete();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Toast.makeText(NearByActivity.this, "Item Clicked ",
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		page = 1;
		value = new NearByParam(longitude, latitude, alarmTime, page);
		doNetworkRequest(value);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		page++;
		value = new NearByParam(longitude, latitude, alarmTime, page);
		doNetworkRequest(value);
	}

	@Override
	public void onListItemEvent(int position, int flag) {
		NearByBean bean = adapter.getDataAt(position);
		Toast.makeText(NearByActivity.this, String.valueOf(bean.getDistance()),
				Toast.LENGTH_SHORT).show();
	}
	
}
