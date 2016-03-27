package com.appsay.android.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appsay.android.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends FragmentActivity implements SmartTabLayout.TabProvider {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_old);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//		toolbar.setTitle(demo.titleResId);
//		setSupportActionBar(toolbar);
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//		ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
//		tab.addView(LayoutInflater.from(this).inflate(demo.layoutResId, tab, false));

		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
		final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
		viewPagerTab.setCustomTabView(this);

//		FragmentPagerItems pages = new FragmentPagerItems(this);
//		for (int titleResId : demo.tabs()) {
//			pages.add(FragmentPagerItem.of(getString(titleResId), DemoFragment.class));
//		}

		FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
				getSupportFragmentManager(), FragmentPagerItems.with(this)
				.add(R.string.app_name, AppSayFragment.class)
				.add(R.string.app_list, AppRankFragment.class)
				.create());


//		FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
//				getSupportFragmentManager(), pages);

		viewPager.setAdapter(adapter);
		viewPagerTab.setViewPager(viewPager);

		viewPagerTab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				super.onPageSelected(position);
				View tab = viewPagerTab.getTabAt(position);
//				View mark = tab.findViewById(R.id.custom_tab_notification_mark);
//				mark.setVisibility(View.GONE);
			}
		});
	}

	@Override
	public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
		LayoutInflater inflater = LayoutInflater.from(container.getContext());
		Resources res = container.getContext().getResources();
		View tab = inflater.inflate(R.layout.title, container, false);
		TextView textView = (TextView) tab.findViewById(R.id.tv_tab_title);
		switch (position) {
			case 0:
				textView.setText(R.string.app_name);
				break;
			case 1:
				textView.setText(R.string.app_list);
//			default:
//				throw new IllegalStateException("Invalid position: " + position);
		}
		return tab;
	}

/*	private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
		ArrayList<PInfo> res = new ArrayList<PInfo>();
		List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && (p.versionName == null)) {
				continue;
			}
			PInfo newInfo = new PInfo();
			newInfo.appname = p.applicationInfo.loadLabel(getPackageManager())
					.toString();
			newInfo.pname = p.packageName;
			newInfo.versionName = p.versionName;
			newInfo.versionCode = p.versionCode;
			newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
			res.add(newInfo);
		}
		return res;
	}*/

	/*private void setupTab(SmartTabLayout layout) {
		final LayoutInflater inflater = LayoutInflater.from(this);
		final Resources res = this.getResources();

		layout.setCustomTabView(new SmartTabLayout.TabProvider() {
			@Override
			public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
				ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon2, container,
						false);
				switch (position) {
					case 0:
						icon.setImageDrawable(res.getDrawable(R.drawable.ic_home_white_24dp));
						break;
					case 1:
						icon.setImageDrawable(res.getDrawable(R.drawable.ic_search_white_24dp));
						break;
					case 2:
						icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_white_24dp));
						break;
					case 3:
						icon.setImageDrawable(res.getDrawable(R.drawable.ic_flash_on_white_24dp));
						break;
					default:
						throw new IllegalStateException("Invalid position: " + position);
				}
				return icon;
			}
		});
	}*/

}
