package cm.getuptogether.jason.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 数组列表适配器模板类
 * 
 * @author Tianyu
 * 
 * @param <Model>
 *            与需要适配的ListView相关联的模型类
 * @param <Holder>
 *            与ListView列表Item项相关联的视图容器类
 */
public abstract class ArrayListAdapter<Model, Holder> extends BaseAdapter {

	/**
	 * 列表项事件的监听器接口 用于将列表项的事件响应回调转移到其他的类上
	 * 
	 * @author Tianyu
	 */
	public interface ListItemEventListener {
		/**
		 * 列表项相关联的事件回调
		 * 
		 * @param position
		 *            列表项位置
		 * @param flag
		 *            一些标志信息
		 */
		public void onListItemEvent(int position, int flag);
	}

	private Context mContext;
	public ArrayList<Model> mData;
	private ListItemEventListener mListener;

	public ArrayListAdapter(Context context) {
		mContext = context;
		mData = new ArrayList<Model>();
	}

	public ArrayListAdapter(Context context, ListItemEventListener listener) {
		this(context);
		mListener = listener;
	}

	protected void invokeItemEventListener(int position, int flag) {
		if (mListener != null) {
			mListener.onListItemEvent(position, flag);
		} else {
			throw new IllegalStateException(
					"onListItemEvent cannot be invoked, use another constructor.");
		}
	}

	protected LayoutInflater getLayoutInflater() {
		return LayoutInflater.from(mContext);
	}

	public void deleteDataAt(int position) {
		mData.remove(position);
	}

	public Model getDataAt(int position) {
		return mData.get(position);
	}

	public void appendList(List<Model> list) {
		mData.addAll(list);
		notifyDataSetChanged();
	}

	public void resetToList(List<Model> list) {
		mData.clear();
		addDate(list);
	}

	public void addDate(List<Model> list) {
		mData.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = initItemViewAt(position);
			holder = initHolder(position, convertView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		displayData(position, holder, mData.get(position));
		return convertView;
	}

	protected abstract View initItemViewAt(int position);

	protected abstract Holder initHolder(int position, View itemView);

	protected abstract void displayData(int position, Holder holder, Model model);
}
