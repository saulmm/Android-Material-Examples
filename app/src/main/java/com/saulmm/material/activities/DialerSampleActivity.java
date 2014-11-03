/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.saulmm.material.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.saulmm.material.R;
import com.saulmm.material.views.adapters.DialerPagerAdapter;
import com.saulmm.material.views.widgets.SlidingTabLayout;

public class DialerSampleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);

        configureToolbar();
        configurePager();
    }

    private void configureToolbar() {

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.activity_dialer_toolbar);
        mainToolbar.setTitleTextColor(getResources().getColor(R.color.theme_dialer_accent));
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Sliding");
    }

    private void configurePager() {

        ViewPager mViewPager = (ViewPager) findViewById(R.id.activity_dialer_pager);
        mViewPager.setAdapter(new DialerPagerAdapter(this));

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.activity_dialer_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}
