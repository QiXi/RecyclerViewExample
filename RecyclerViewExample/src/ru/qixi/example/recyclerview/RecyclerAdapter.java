package ru.qixi.example.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener {

	private List<ViewModel>								mItems;
	private OnRecyclerViewItemClickListener<ViewModel>	mItemClickListener;
	private int											mItemLayout;


	public RecyclerAdapter(List<ViewModel> items, int itemLayout) {
		mItems = items;
		mItemLayout = itemLayout;
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
		v.setOnClickListener(this);
		return new ViewHolder(v);
	}


	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		ViewModel item = mItems.get(position);
		holder.text.setText(item.getText());
		holder.image.setImageResource(R.drawable.ic_launcher);
		holder.itemView.setTag(item);
	}


	@Override
	public int getItemCount() {
		return mItems.size();
	}


	@Override
	public void onClick(View view) {
		if (mItemClickListener != null) {
			ViewModel model = (ViewModel)view.getTag();
			mItemClickListener.onItemClick(view, model);
		}
	}


	public void add(ViewModel item, int position) {
		mItems.add(position, item);
		notifyItemInserted(position);
	}


	public void remove(ViewModel item) {
		int position = mItems.indexOf(item);
		mItems.remove(position);
		notifyItemRemoved(position);
	}


	public void setOnItemClickListener(OnRecyclerViewItemClickListener<ViewModel> listener) {
		mItemClickListener = listener;
	}


	public static class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView	image;
		public TextView		text;


		public ViewHolder(View itemView) {
			super(itemView);
			image = (ImageView)itemView.findViewById(R.id.image);
			text = (TextView)itemView.findViewById(R.id.text);
		}
	}
	
}
