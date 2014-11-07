package cm.getuptogether.jason.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * �����б�������ģ����
 * 
 * @author Tianyu
 * 
 * @param <Model>
 *            ����Ҫ�����ListView�������ģ����
 * @param <Holder>
 *            ��ListView�б�Item�����������ͼ������
 */
public abstract class ArrayListAdapter<Model, Holder> extends BaseAdapter {

	/**
	 * �б����¼��ļ������ӿ� ���ڽ��б�����¼���Ӧ�ص�ת�Ƶ�����������
	 * 
	 * @author Tianyu
	 */
	public interface ListItemEventListener {
		/**
		 * �б�����������¼��ص�
		 * 
		 * @param position
		 *            �б���λ��
		 * @param flag
		 *            һЩ��־��Ϣ
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
