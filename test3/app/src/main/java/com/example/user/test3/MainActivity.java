package com.example.user.test3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";

    public String message = "";
    public String message1="Відповідь не обрано \n";



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case -1:
                        message1="Не обрано \n";
                        break;
                    case R.id.radioButton:
                        message1="Так\n";
                        break;
                    case R.id.radioButton2:
                        message1="Ні\n";
                        break;
                    default:
                        break;
                }

            }
        });
    }
    public void saveText(View view){

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(message.getBytes());
            Toast.makeText(this, "Файл збережено", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openText(View view) {

        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.textView);

        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void OnClickButtonOK(View view) {
        message+=message1;
        EditText editText = (EditText) findViewById(R.id.editText);
        message+="Запитання: "+ editText.getText() + "\n";
        saveText(view);
        message+=" \n";
    }
    public void OnClickButtonOpen(View view) {
        openText(view);
    }
}
