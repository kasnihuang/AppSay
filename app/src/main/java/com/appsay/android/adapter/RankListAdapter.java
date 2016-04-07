package com.appsay.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsay.android.R;
import com.appsay.android.bean.AppInfo;

import java.util.List;

/**
 * Created by kasni.huang on 3/21/16.
 */
public class RankListAdapter extends ArrayAdapter<AppInfo> {
    private List<AppInfo> appsList = null;
    private Context context;
    private LayoutInflater layoutInflater;

    public RankListAdapter(Context context, int textViewResourceId,
                           List<AppInfo> appsList) {
        super(context, textViewResourceId, appsList);
        this.context = context;
        this.appsList = appsList;
    }

    @Override
    public int getCount() {
        return ((null != appsList) ? appsList.size() : 0);
    }

    @Override
    public AppInfo getItem(int position) {
        return ((null != appsList) ? appsList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.app_rank_list_item, null);
            viewHolder.iconView = (ImageView) convertView.findViewById(R.id.app_icon);
            viewHolder.appRank = (TextView) convertView.findViewById(R.id.tv_rank);
            viewHolder.appName = (TextView) convertView.findViewById(R.id.app_name);
            viewHolder.appScope = (TextView) convertView.findViewById(R.id.app_scope);
            viewHolder.appSize = (TextView) convertView.findViewById(R.id.tv_size);
            viewHolder.appRate = (TextView) convertView.findViewById(R.id.tv_rate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AppInfo data = appsList.get(position);
        if (null != data) {
            viewHolder.iconView.setImageDrawable(data.getAppIcon());
            viewHolder.appName.setText(data.getName());
            viewHolder.appScope.setText("4.7分");
            viewHolder.appSize.setText(data.getSize());
            viewHolder.appRate.setText(data.getRate());
            viewHolder.appRank.setText(String.valueOf(position + 1));
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView iconView;
        TextView appRank;
        TextView appName;
        TextView appScope;
        TextView appSize;
        TextView appRate;
        TextView appStatus;
    }
}
