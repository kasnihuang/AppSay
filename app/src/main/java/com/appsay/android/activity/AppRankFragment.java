package com.appsay.android.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsay.android.R;
import com.appsay.android.bean.RankInfo;
import com.appsay.android.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AppRankFragment extends Fragment {
    private static final String TAG = "AppRankFragment";
    private GridView mRankGridView;
    private List<RankInfo> mRankList;
    private AppRankAdapter mAppRankAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRankList = new ArrayList<>();
        mAppRankAdapter = new AppRankAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_rank_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTestData();
        mRankGridView = (GridView) view.findViewById(R.id.list_rank);
        mRankGridView.setAdapter(mAppRankAdapter);
        initTestData();
        mAppRankAdapter.setList(mRankList);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    private void initTestData() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.ic_launcher);
        RankInfo info = new RankInfo("社交应用版", bitmap, bitmap, bitmap);
        mRankList.add(info);
        mRankList.add(info);
    }

    private class AppRankAdapter extends BaseAdapter {
        private List<RankInfo> dataList;
        private LayoutInflater inflater;

        public AppRankAdapter(Context context) {
            dataList = new ArrayList<>();
            inflater = LayoutInflater.from(context);
        }

        public void setList(List<RankInfo> list) {
            this.dataList = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.app_rank_item, null);
                viewHolder.tvLabel = (TextView) convertView.findViewById(R.id.tv_rank_label);
                viewHolder.ivFavorite = (ImageView) convertView.findViewById(R.id.iv_favorite);
                viewHolder.ivAppOne = (ImageView) convertView.findViewById(R.id.iv_app_one);
                viewHolder.ivAppTwo = (ImageView) convertView.findViewById(R.id.iv_app_two);
                viewHolder.ivAppThree = (ImageView) convertView.findViewById(R.id.iv_app_three);
                viewHolder.mBtnMore = (Button) convertView.findViewById(R.id.btn_more);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final RankInfo info = dataList.get(position);
            if (info != null) {
                viewHolder.tvLabel.setText(info.getLabel());
                viewHolder.ivAppOne.setImageBitmap(info.getIconOne());
                viewHolder.ivAppTwo.setImageBitmap(info.getIconTwo());
                viewHolder.ivAppThree.setImageBitmap(info.getIconThree());
                viewHolder.mBtnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AppRankListActivity.class);
                        intent.putExtra(Constants.RANK_LIST_LABEL, info.getLabel());
                        startActivity(intent);
                    }
                });
            }
            return convertView;
        }

        private class ViewHolder {
            public TextView tvLabel;
            public ImageView ivFavorite;
            public ImageView ivAppOne;
            public ImageView ivAppTwo;
            public ImageView ivAppThree;
            public Button mBtnMore;
        }
    }
}
