package com.e.herosapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {

    private final static String  BASE_URL="http://10.0.2.2:3000/";
    private Button btnReg;
    private EditText etName,etDes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btnReg = findViewById(R.id.btnReg);
        etName = findViewById(R.id.etName);
        etDes = findViewById(R.id.etDesc);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }


    private void register(){




    }
}
