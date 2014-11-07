package cm.getuptogether.jason.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.androidannotations.annotations.EActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cm.getuptogether.R;
import cm.getuptogether.activity.BaseActivity;
import cm.getuptogether.jason.adapter.ArrayListAdapter.ListItemEventListener;
import cm.getuptogether.jason.adapter.group.GroupMemberAdapter;
import cm.getuptogether.jason.bean.GroupInfoBean;
import cm.getuptogether.jason.bean.NearByBean;
import cm.getuptogether.jason.param.GroupInfoParam;
import cm.getuptogether.request.StringRequest;
import cm.getuptogether.util.VolleyTools;

import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class GroupInfo4GroupUserActivity extends BaseActivity implements
		ListItemEventListener {
	private final String url ="http://114.215.178.134:8080/getup/action/Group_getGroupDetailInfo";
	private GroupMemberAdapter adapter;
	private VolleyTools tool;
	private GroupInfoParam value ;
	
	private GridView gridView_groupMembers;
	private TextView textView_alarmTime ;
	private ImageView imageView_groupImage ;
	private TextView textView_groupname;
	private TextView textView_current_member;
	private TextView textView_max_member;
	private Button button_paly_groupAudio; 
	private TextView textView_groupIntroduction;
	private TextView textView_groupArea;
	private TextView textView_groupRule;
	private ImageButton imageButton_group_chat;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		value= new GroupInfoParam(1);
		doNetworkRequest(value);
	}
	private void init() {
		setContentView(R.layout.activity_groupinfo_4_groupuser);
		gridView_groupMembers = (GridView) findViewById(R.id.gridView_groupMembers);
		textView_alarmTime = (TextView)findViewById(R.id.textView_alarmTime);
		imageView_groupImage = (ImageView)findViewById(R.id.imageView_groupImage);
		textView_groupname = (TextView)findViewById(R.id.textView_groupname);
		textView_current_member = (TextView)findViewById(R.id.textView_current_member);
		textView_max_member = (TextView) findViewById(R.id.textView_max_member);
		button_paly_groupAudio = (Button)findViewById(R.id.button_paly_groupAudio);
		textView_groupIntroduction =(TextView)findViewById(R.id.textView_groupIntroduction);
		textView_groupArea =(TextView)findViewById(R.id.textView_groupArea);
		textView_groupRule = (TextView)findViewById(R.id.textView_groupRule);
		imageButton_group_chat = (ImageButton)findViewById(R.id.imageButton_group_chat);
		tool = getVolleyTools();
		gridView_groupMembers.setAdapter(adapter = new GroupMemberAdapter(this,
				this, tool.getImageLoader()));
	}
	private void doNetworkRequest(Object value) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("param", getGson().toJson(value));
		System.out.println(getGson().toJson(value));
		tool.getQueue().add(new StringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String result) {
				if(result != null){
					System.out.println(result);
					onGetRrightResult(result);
					return ;
				}
				Toast.makeText(GroupInfo4GroupUserActivity.this, "获取群数据出错", Toast.LENGTH_SHORT).show();
				
			}

		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println(arg0.toString());
			}
		}, param));

	}

	private void onGetRrightResult(String result) {
		GroupInfoBean info = com.alibaba.fastjson.JSONObject.parseObject(
				result, GroupInfoBean.class);
			displayGroupInfo(info);
	}
	@Override
	public void onListItemEvent(int position, int flag) {

	}
	
	
	private void displayGroupInfo(GroupInfoBean info){
		/**
		 * private GridView gridView_groupMembers;
	private TextView textView_alarmTime ;
	private ImageView imageView_groupImage ;
	private TextView textView_groupname;
	private TextView textView_current_member;
	private TextView textView_max_member;
	private Button button_paly_groupAudio; 
	private TextView textView_groupIntroduction;
	private TextView textView_groupArea;
	private TextView textView_groupRule;
	private ImageButton imageButton_group_chat;
		 */
		adapter.addDate(info.getUsers());
		textView_alarmTime.setText(info.getGroup().getAlarmTime());
		textView_groupname.setText(info.getGroup().getGroupName());
		textView_current_member.setText(String.valueOf(info.getGroup().getCurrentMember()));
		textView_max_member.setText(String.valueOf(info.getGroup().getMaxMember()));
		textView_groupIntroduction.setText(info.getGroup().getSignature());
		//textView_groupArea.setText(info.getGroup().getGroupArea());
		//textView_groupRule.setText(info.getGroup().getGroupRule());
		
	}
}
