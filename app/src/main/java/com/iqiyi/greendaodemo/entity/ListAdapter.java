package com.iqiyi.greendaodemo.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iqiyi.greendaodemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanliu on 2016/9/30.
 * Email: alexnewtt@gmail.com
 */
public class ListAdapter extends BaseAdapter {
    private List<Note> lists = new ArrayList<Note>();

    private OnClickListener listener;
    private Context mContext;
    public ListAdapter(Context context, OnClickListener listener) {
        this.mContext = context;
        this.listener = listener;
    }

    public void setData(List<Note> lists) {
        this.lists.clear();
        if (lists != null) {
            this.lists = lists;
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        this.lists.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (lists == null) {
            return 0;
        }
        return lists.size();
    }

    @Override
    public Note getItem(int position) {
        if (position < 0) {
            return null;
        }
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_note_list, parent, false);
            h = new ViewHolder(convertView);
            convertView.setTag(h);
        }else{
            h = (ViewHolder) convertView.getTag();
        }
        h.update(getItem(position));

        return convertView;
    }

    class ViewHolder implements View.OnClickListener {
        RelativeLayout layoutBase;
        TextView textId;
        TextView textTitle;
        TextView textData;

        Note note;

        public ViewHolder(View convertView) {
            layoutBase = (RelativeLayout) convertView.findViewById(R.id.ll_base);
            textId = (TextView) convertView.findViewById(R.id.tv_id);
            textTitle = (TextView) convertView.findViewById(R.id.tv_title);
            textData = (TextView) convertView.findViewById(R.id.tv_data);

            layoutBase.setOnClickListener(this);
        }

        public void update(Note note) {
            this.note = note;
            textId.setText(note.getId() + "");
            textTitle.setText(note.getText());
            textData.setText(note.getDate() + "");
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_base:
                    if (listener != null) {
                        listener.onItemClick(note);
                    }
                    break;
            }
        }
    }

    public interface OnClickListener {
        void onItemClick(Note note);
    }
}
