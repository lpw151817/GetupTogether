package cm.getuptogether.jason.adapter.group;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import cm.getuptogether.R;
import cm.getuptogether.jason.adapter.ArrayListAdapter;
import cm.getuptogether.jason.adapter.group.*;
import cm.getuptogether.jason.adapter.ArrayListAdapter.ListItemEventListener;
import cm.getuptogether.jason.bean.NearByBean;
import cm.getuptogether.jason.bean.SimpleUserBean;
import cm.getuptogether.jason.bean.UserBean;

public class GroupMemberAdapter extends
		ArrayListAdapter<SimpleUserBean, GroupMemberAdapter.Holder> {
	class Holder {
		ImageView imageView_head;
		TextView tv_userId;
		TextView tv_username;
	}

	private int position;
	private Context context;
	private ImageLoader imageLoader;

	public GroupMemberAdapter(Context context, ListItemEventListener listener,
			ImageLoader imageLoader) {
		super(context, listener);
		this.context = context;
		this.imageLoader = imageLoader;
	}

	@Override
	protected View initItemViewAt(int position) {
		return getLayoutInflater().inflate(R.layout.grid_item_group_member,
				null);
	}

	@Override
	protected Holder initHolder(int position, View itemView) {
		this.position = position;
		Holder holder = new Holder();
		holder.imageView_head = (ImageView) itemView
				.findViewById(R.id.imageView_headImage);
		holder.tv_userId = (TextView) itemView
				.findViewById(R.id.textView_userId);
		holder.tv_username = (TextView) itemView
				.findViewById(R.id.textView_username);
		holder.imageView_head.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

			}
		});
		return holder;

	}


	@Override
	protected void displayData(int position, Holder holder, SimpleUserBean model) {
		if (model.getImageUrl() != null && !"".equals(model.getImageUrl())) {

		}
		holder.tv_userId.setText(String.valueOf(model.getUserId()));
		holder.tv_username.setText(model.getNickname());

	}

}
