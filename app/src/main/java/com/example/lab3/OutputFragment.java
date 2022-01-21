package com.example.lab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class OutputFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_output, container, false);
    }

    public void setOrderText(String data) {
        TextView tvOutputOrder = getView().findViewById(R.id.tv_output_order);
        tvOutputOrder.setText(data);
    }
}