package com.saulmm.material.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import com.saulmm.material.R;

public class MainActivity extends AppCompatActivity implements
	NavigationView.OnNavigationItemSelectedListener {

	private DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		initUI();
		initToolbar();
		initNavigationView();

	}

	private void initUI() {

		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawerlayout);
	}

	private void initToolbar() {

		Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
		setSupportActionBar(toolbar);
	}

	private void initNavigationView() {

		NavigationView mNavigationView = (NavigationView) findViewById(R.id.activity_main_navigation_view);
		mNavigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {
			mDrawerLayout.openDrawer(Gravity.LEFT);
			return true;
		}

		return false;
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {

		menuItem.setChecked(true);
		mDrawerLayout.closeDrawers();
		return true;
	}
}
