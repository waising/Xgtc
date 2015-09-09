package com.xgtongcheng.xgtc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xgtongcheng.xgtc.R;
import com.xgtongcheng.xgtc.adapter.model.MenuModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wwx on 2015/9/9.
 */
public class MenuAdapter extends BaseAdapter{

    private Context context;
    private List<MenuModel> list;

    public MenuAdapter(Context context, List<MenuModel> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.menu_list_item, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(list.get(position).getImageView());
        holder.textView.setText(list.get(position).getText());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item_imageview) ImageView imageView;
        @Bind(R.id.item_textview) TextView textView;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
