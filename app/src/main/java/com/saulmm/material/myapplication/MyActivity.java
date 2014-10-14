package com.saulmm.material.myapplication;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Fab Button
        int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline fabOutLine = new Outline();
        fabOutLine.setOval(0, 0, fabSize, fabSize);

        // FabView
        View fabView = findViewById(R.id.fab_button);
        fabView.setOnClickListener(fabClickListener);
        fabView.setOutline(fabOutLine);
    }


    View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(MyActivity.this, "Hi",
                Toast.LENGTH_SHORT).show();
        }
    };
}
