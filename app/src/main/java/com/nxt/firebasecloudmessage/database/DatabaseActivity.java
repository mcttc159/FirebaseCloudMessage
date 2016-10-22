package com.nxt.firebasecloudmessage.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nxt.firebasecloudmessage.R;

/**
 * Created by NXT on 21/10/2016.
 */

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_realtime_database);

        Button buttonReadWrite = (Button) findViewById(R.id.buttonReadWriteData);
        buttonReadWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseActivity.this, ReadWriteDataActivity.class));
            }
        });

        Button buttonListData = (Button) findViewById(R.id.buttonListData);
        buttonListData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseActivity.this, ListDataActivity.class));
            }
        });
    }
}
