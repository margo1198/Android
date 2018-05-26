package com.example.user.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private TextView textView, textView2;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        initOneFragment();
        initTwoFragment();
    }
    private void initOneFragment() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, oneFragment, OneFragment.TAG);
        transaction.commit();
    }
    private void initTwoFragment() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.container, twoFragment, TwoFragment.TAG);
        transaction.commit();


    }
    public void OnClickFragment(View view) {

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        switch (view.getId()) {
            case R.id.btnOk:
                int selectedAnswerId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedAnswerId);
                String strQuestion = editText.getText().toString();
                String strAnswer = (String) radioButton.getText();
                textView.setText(strQuestion);
                textView2.setText(strAnswer);
                break;
            case R.id.btnCancel:
                radioGroup.clearCheck();
                editText.setText("");
                textView.setText("");
                textView2.setText("");
                break;
        }

        }
    }