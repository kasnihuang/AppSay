package com.appsay.android.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.SearchView;

import com.appsay.android.R;
import com.appsay.android.adapter.RankListAdapter;
import com.appsay.android.bean.AppInfo;
import com.appsay.android.utils.Constants;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppRankListActivity extends Activity {

    private PackageManager packageManager = null;
    private List<AppInfo> applist = null;
    private RankListAdapter listadaptor = null;
    private Random random;
    private ListView mRankListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_rank_list_layout);
        Intent intent = getIntent();
        if (intent != null) {
            setTitle(intent.getStringExtra(Constants.RANK_LIST_LABEL));
        }
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        initViews();
        packageManager = getPackageManager();
        random = new Random();
        new LoadApplications().execute();
    }

    private void initViews() {
        mRankListView = (ListView) findViewById(R.id.list_rank);
    }

    @Override
    public void onPause() {
//		System.out.println("OneFragment  onPause");
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onResume() {
//		System.out.println("OneFragment  onResume");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onDestroy() {
//		System.out.println("OneFragment  onDestroy");
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onStop() {
//		System.out.println("OneFragment  onStop");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onStart() {
//		System.out.println("OneFragment  onStart");
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_rank_list; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rank_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                finish();
//                NavUtils.navigateUpFromSameTask(this);
                // overridePendingTransition(R.animator.anim_left, R.animator.anim_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<AppInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<AppInfo> applist = new ArrayList<>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    PackageInfo pkg = packageManager.getPackageInfo(info.packageName, 0);
                    String appName = pkg.applicationInfo.loadLabel(packageManager).toString();
                    String versionName = pkg.versionName;
                    int rate = random.nextInt(100);
                    applist.add(new AppInfo(info.loadIcon(packageManager), appName,
                            getString(R.string.version, versionName), "18M", rate + "%", rate > 50 ? getString(R.string.rate_up) : getString(R.string.rate_down)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }

    private String getAppVersion(String packageName) {
        String appVersion = "0";
        try {
            PackageInfo info = packageManager.getPackageInfo(packageName, 0);
            appVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return getString(R.string.version, appVersion);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadaptor = new RankListAdapter(AppRankListActivity.this,
                    R.layout.app_say_item, applist);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            mRankListView.setAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(AppRankListActivity.this, null,
                    "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
