package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEdittext;
    private EditText mLocationEdittext;
    private EditText mShareText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEdittext=findViewById(R.id.website_edittext);
        mLocationEdittext=findViewById(R.id.location_edittext);
        mShareText=findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEdittext.getText().toString();
        Uri webpage=Uri.parse(url);

        Intent intent=new Intent(Intent.ACTION_VIEW,webpage);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else
        {
            Log.d("Intent_error","Can't found any application");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEdittext.getText().toString();
        Uri addressUrl=Uri.parse("geo:0.0?q=" + loc);

        Intent intent=new Intent(Intent.ACTION_VIEW,addressUrl);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else
        {
            Log.d("Intent_error","Can't found any application");
        }

    }

    public void shareText(View view) {
        String txt = mShareText.getText().toString();
        String mimeType="text/plain";
        ShareCompat.IntentBuilder.from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_txt)
                .setText(txt)
                .startChooser();
    }
}