package com.krishna.internalstorageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button save,read;
    EditText mFileName,mFileContent;
    String fileName,fileContent,inputString;
    TextView showContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.btnSave);
        read = findViewById(R.id.btnRead);
        mFileName = findViewById(R.id.edtFileName);
        mFileContent = findViewById(R.id.edtContent);
        showContent = findViewById(R.id.show);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = mFileName.getText().toString().trim();
                fileContent = mFileContent.getText().toString().trim();

                FileOutputStream fos;
                try {
                    fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(fileContent.getBytes());
                    fos.close();
                    mFileName.setText("");
                    mFileContent.setText("");

                    Log.d("TAG","File Saved ....");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer stringBuffer = new StringBuffer();

                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));

                    while ((inputString = inputReader.readLine()) !=null){
                        stringBuffer.append(inputString +"\n");

                        Log.d("TAG","FileContent :"+stringBuffer);
                        showContent.setText(stringBuffer);

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        });
    }
}