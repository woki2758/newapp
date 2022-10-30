package org.example.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    public void onBackButtonClicked(View v)
    {
        Toast.makeText(getApplicationContext(), "종료버튼 누름", Toast.LENGTH_LONG).show();
        finish();
    }

}