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
		initFragment(new HomeFragment());
	}

	private void initUI() {
		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawerlayout);

		NavigationView mNavigationView = (NavigationView)
			findViewById(R.id.activity_main_navigationview);

		Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
		});

		mNavigationView.setNavigationItemSelectedListener(this);
	}

	private void initFragment(Fragment fragment) {
		getSupportFragmentManager().beginTransaction()
			.replace(R.id.activity_main_container_framelayout, fragment)
			.commit();
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
				startActivity(new Intent(MainActivity.this, RevealFirstActivity.class));
				break;

			case R.id.drawer_cards:
				startActivity(new Intent(MainActivity.this, CardsActivity.class));
				break;
		}

		if (nextFragment != null)
			initFragment(nextFragment);

		menuItem.setChecked(true);
		mDrawerLayout.closeDrawers();
		return true;
	}
}
