package com.appsay.android.activity;

import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appsay.android.R;
import com.appsay.android.adapter.ApplicationAdapter;
import com.appsay.android.bean.AppInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppSayFragment extends ListFragment {

    private PackageManager packageManager = null;
    private List<AppInfo> applist = null;
    private ApplicationAdapter listadaptor = null;
    private Random random;

    @Override
    public void onCreate(Bundle savedInstanceState) {
//		System.out.println("OneFragment  onCreate");
        super.onCreate(savedInstanceState);
        packageManager = getActivity().getPackageManager();
        random = new Random();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//		System.out.println("OneFragment  onCreateView");
        return inflater.inflate(R.layout.one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new LoadApplications().execute();
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
    public void onDestroyView() {
//		System.out.println("OneFragment  onDestroyView");
        // TODO Auto-generated method stub
        super.onDestroyView();
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
                            getString(R.string.version, versionName), getString(R.string.size, "18M"), rate + "%", rate > 50 ? getString(R.string.rate_up) : getString(R.string.rate_down)));
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

    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            listadaptor = new ApplicationAdapter(getActivity(),
                    R.layout.snippet_list_row, applist);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), null,
                    "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
