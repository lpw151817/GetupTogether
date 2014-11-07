package cm.getuptogether.jason.adapter.alarm;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cm.getuptogether.R;
import cm.getuptogether.jason.adapter.ArrayListAdapter;
import cm.getuptogether.jason.bean.NearByBean;

import com.android.volley.toolbox.ImageLoader;

public class NearByAdapter extends
		ArrayListAdapter<NearByBean, NearByAdapter.Holder> {
	private int position;
	private Context context;
	private ImageLoader imageLoader;

	public NearByAdapter(Context context, ListItemEventListener listener,
			ImageLoader imageLoader) {
		super(context, listener);
		this.context = context;
		this.imageLoader = imageLoader;
	}

	class Holder {
		TextView tv_id;
		TextView tv_alarmTime;
		ImageView imageView_head;
		ImageView imageView_playAudio;
		ImageView imageView_join;
		TextView tv_distance;
		TextView tv_name;
		TextView tv_currentMember;
		TextView tv_maxMember;
		TextView tv_type;
	}

	@Override
	protected View initItemViewAt(int position) {
		return getLayoutInflater().inflate(R.layout.item_alarm_nearby, null);
	}

	@Override
	protected Holder initHolder(int position, View itemView) {
		this.position = position;
		Holder holder = new Holder();
		holder.tv_id = (TextView) itemView.findViewById(R.id.textView_id);
		holder.tv_alarmTime = (TextView) itemView
				.findViewById(R.id.textView_alarmTime);
		holder.tv_distance = (TextView) itemView
				.findViewById(R.id.textView_distance);
		holder.tv_name = (TextView) itemView.findViewById(R.id.textView_name);
		holder.tv_type = (TextView) itemView.findViewById(R.id.textView_type);
		holder.tv_currentMember = (TextView) itemView
				.findViewById(R.id.textView_current_member);
		holder.tv_maxMember = (TextView) itemView
				.findViewById(R.id.textView_max_member);
		holder.imageView_head = (ImageView) itemView
				.findViewById(R.id.imageView_headImage);
		holder.imageView_playAudio = (ImageView) itemView
				.findViewById(R.id.imageView_audio);
		holder.imageView_join = (ImageView) itemView.findViewById(R.id.imageView_join);
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(context, "itemView clicked", Toast.LENGTH_SHORT)
						.show();
			}
		});

		holder.imageView_head.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(context, "imageView_head clicked",
						Toast.LENGTH_SHORT).show();
			}
		});
		holder.imageView_playAudio
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						Toast.makeText(context, "Playing", Toast.LENGTH_SHORT).show();
					}
				});
		
		holder.imageView_join.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(context, "Joining", Toast.LENGTH_SHORT).show();
			}
		});
		return holder;

	}

	@Override
	protected void displayData(int position, Holder holder, NearByBean model) {
		if (imageLoader != null && model.getImageUrl() != null
				&& "".equals(model.getImageUrl())) {

		}
		holder.tv_alarmTime.setText(model.getAlarmTime());
		holder.tv_currentMember
				.setText(String.valueOf(model.getCurrentMember()));
		holder.tv_distance.setText(String.valueOf(model.getDistance()));
		holder.tv_id.setText(String.valueOf(model.getId()));
		holder.tv_maxMember.setText(String.valueOf(model.getMaxMember()));
		holder.tv_name.setText(model.getName());
		holder.tv_type.setText(String.valueOf(model.getType()));

	}
}
