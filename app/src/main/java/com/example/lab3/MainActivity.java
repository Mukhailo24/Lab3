package com.example.lab3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements InputFragment.OnFragmentSendDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.deleteFile("state.txt");
    }

    @Override
    public void onSendData(String data) {
        OutputFragment fragment = (OutputFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fcv_output);
        if (fragment != null) {
            fragment.setOrderText(data);
        }
    }
}