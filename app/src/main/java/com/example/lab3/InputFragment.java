package com.example.lab3;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.FileOutputStream;
import java.io.IOException;
public class InputFragment extends Fragment {

    private final static String FILE_NAME = "state.txt";

    interface OnFragmentSendDataListener {
        void onSendData(String data);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        Button btnOk = view.findViewById(R.id.btnOk);
        Button btnOpen = view.findViewById(R.id.btnOpen);
        RadioGroup colorRadioGroup = view.findViewById(R.id.rg_colors);
        RadioGroup priceRadioGroup = view.findViewById(R.id.rg_price);

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int colorId = colorRadioGroup.getCheckedRadioButtonId();
                int priceId = priceRadioGroup.getCheckedRadioButtonId();
                if (colorId == -1 || priceId == -1) {
                    Toast.makeText(getContext(),
                            "Вкажіть дані", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton priceRadioButton = priceRadioGroup.findViewById(priceId);
                    RadioButton colorRadioButton = colorRadioGroup.findViewById(colorId);
                    String price = priceRadioButton.getText().toString();
                    String color = colorRadioButton.getText().toString();
                    String data = "Ваше замовлення: колір - " + color + ", діапазон цін - " + price;
                    saveText(data);
                    fragmentSendDataListener.onSendData(data);
                }
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowFileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // сохранение файла
    public void saveText(String orderData) {

        FileOutputStream fos = null;
        String rightData = orderData + "\n";
        try {
            fos = getContext().openFileOutput(FILE_NAME, Context.MODE_APPEND);
            fos.write(rightData.getBytes());
            Toast.makeText(getContext(), "Файл збережений", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}