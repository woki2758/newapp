package org.example.hello;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v)
    {
     //   Toast.makeText(getApplicationContext(), "시작버튼 누름", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), NewActivity.class);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode, intent);
        if(requestCode == REQUEST_CODE_SCAN) {
            Toast toast = Toast.makeText(getBaseContext(), "onactivity result called with code : " + resultCode, Toast.LENGTH_LONG);
            toast.show();
            if(resultCode == Activity.RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");
                contentsText.append("\nSCAN Result format : " + formatName);
                contentsText.append("\nSCAN Result : " + contents);
                if(contents != null && contents.indexOf("http://") >= 0 ) {
                    int startIndex = contents.substring(startIndex);
                    showDialog(DIALOG_SHOW_URL);
                }
            } else {
                contentsText.append("\nSCAN Failed.");
            }
        }
    }

    protected Dialog onCreatedDialog(int id) {
        AlertDialog.Builder builder = null;
        switch(id) {
            case DIALOG_SCANNER_NEEDED:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("바코드 앱 설치");
                builder.setMessage("바코드 스캐너 앱 자동 설치?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Uri uri = Uri.parse("market://details?id=com.google.zxing.client.android");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton){

                    }
                });
                break;
        }
    }

}