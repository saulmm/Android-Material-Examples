package com.saulmm.material.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.saulmm.material.R;
import com.saulmm.material.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements
	NavigationView.OnNavigationItemSelectedListener {

	private DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		initUI();
		initToolbar();
		initNavigationView();
		initFragment(new HomeFragment());
	}

	private void initFragment(Fragment fragment) {

		getSupportFragmentManager().beginTransaction()
			.replace(R.id.activity_main_container_framelayout, fragment)
			.commit();
	}

	private void initUI() {

		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawerlayout);
	}

	private void initToolbar() {

		Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
		toolbar.inflateMenu(R.menu.menu_home);
		toolbar.setNavigationIcon(R.drawable.ic_menu);
		setSupportActionBar(toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
					mDrawerLayout.closeDrawer(Gravity.START);
				} else {
					mDrawerLayout.openDrawer(Gravity.START);
				}
			}
		});
	}

	private void initNavigationView() {

		NavigationView mNavigationView = (NavigationView) findViewById(R.id.activity_main_navigationview);
		mNavigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			mDrawerLayout.openDrawer(Gravity.START);
			return true;
		}

		return false;
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {

		Fragment nextFragment = null;

		switch (menuItem.getItemId()) {

			case R.id.drawer_transitions:
				startActivity(new Intent(MainActivity.this, TransitionFirstActivity.class));
				break;

			case R.id.nav_elevation_sample:
				nextFragment = new ElevationFragment();
				break;

			case R.id.nav_circular_sample:
				startActivity(new Intent(MainActivity.this, ColorActivity.class));
				break;

			case R.id.nav_arrow:
				startActivity(new Intent(MainActivity.this, SlidingActivity.class));
				break;
		}

		if (nextFragment != null)
			initFragment(nextFragment);


		menuItem.setChecked(true);
		mDrawerLayout.closeDrawers();
		return true;
	}
}
