package com.appsay.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsay.android.R;
import com.appsay.android.bean.AppInfo;

import java.util.List;

/**
 * Created by kasni.huang on 3/21/16.
 */
public class ApplicationAdapter extends ArrayAdapter<AppInfo> {
    private List<AppInfo> appsList = null;
    private Context context;
    private PackageManager packageManager;
    private LayoutInflater layoutInflater;

    public ApplicationAdapter(Context context, int textViewResourceId,
                              List<AppInfo> appsList) {
        super(context, textViewResourceId, appsList);
        this.context = context;
        this.appsList = appsList;
        packageManager = context.getPackageManager();
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
            convertView = layoutInflater.inflate(R.layout.snippet_list_row, null);
            viewHolder.iconView = (ImageView) convertView.findViewById(R.id.app_icon);
            viewHolder.appName = (TextView) convertView.findViewById(R.id.app_name);
            viewHolder.appVersion = (TextView) convertView.findViewById(R.id.app_version);
            viewHolder.appSize = (TextView) convertView.findViewById(R.id.tv_size);
            viewHolder.appRate = (TextView) convertView.findViewById(R.id.tv_rate);
            viewHolder.rateDes = (TextView) convertView.findViewById(R.id.tv_des);
            viewHolder.btnUp = (Button) convertView.findViewById(R.id.btn_up);
            viewHolder.btnDown = (Button) convertView.findViewById(R.id.btn_down);
            viewHolder.btnShare = (Button) convertView.findViewById(R.id.btn_share);
            viewHolder.btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareText();
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AppInfo data = appsList.get(position);
        if (null != data) {
            viewHolder.iconView.setImageDrawable(data.getAppIcon());
            viewHolder.appName.setText(data.getName());
            viewHolder.appVersion.setText(data.getVersion());
            viewHolder.appSize.setText(data.getSize());
            viewHolder.appRate.setText(data.getRate());
            viewHolder.rateDes.setText(data.getRateDes());
        }
        if (position == 0) {
            convertView.findViewById(R.id.rate_layout).setVisibility(View.GONE);
            convertView.findViewById(R.id.comment_layout).setVisibility(View.VISIBLE);
            viewHolder.btnShare.setVisibility(View.GONE);
        }
        return convertView;
    }

    //分享文字
    public void shareText() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my Share text.");
        shareIntent.setType("text/plain");

        //设置分享列表的标题，并且每次都显示分享列表
        context.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    private class ViewHolder {
        ImageView iconView;
        TextView appName;
        TextView appVersion;
        TextView appSize;
        TextView appRate;
        TextView rateDes;
        Button btnUp;
        Button btnDown;
        Button btnShare;
    }

    /*private String getAppSize() {

    }*/

};
