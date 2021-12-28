package com.fooddrop.myrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<String> mData;
    private List<Integer> mAge;
    private LayoutInflater mInflater;
    public static ItemClickListener mClickListener;

    // data is passed into the constructor
    HistoryAdapter(Context context, List<String> data, List<Integer> age){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mAge = age;
    }

    private String[] localDataSet;

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_history_recyclerview, parent,  false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.getTitle_tv().setText(localDataSet[position]);
        /*holder.getTitle_tv().setText("This is title");
        holder.getSubTitle_tv().setText("This is subtitle text-view");*/
        String animal = mData.get(position);
        String age = mAge.get(position).toString();
        holder.title_tv.setText(animal);
        holder.subTitle_tv.setText(age);
    }

    @Override
    public int getItemCount() {
        return mData.size(); //localDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView title_tv;
        private final TextView subTitle_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Define click listener for the ViewHolder's View
            title_tv = itemView.findViewById(R.id.tv_title_id);
            subTitle_tv = itemView.findViewById(R.id.tv_subTitle_id);
            itemView.setOnClickListener(this);
        }

        public TextView getTitle_tv(){
            return title_tv;
        }
        public TextView getSubTitle_tv(){
            return subTitle_tv;
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null){
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
    String getItem(int id){
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener){
        mClickListener = itemClickListener;
    }

    //parent activity will implement this method to respond to click event
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
