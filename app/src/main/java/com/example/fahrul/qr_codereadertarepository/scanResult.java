package com.example.fahrul.qr_codereadertarepository;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.downloader.Status;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class scanResult extends AppCompatActivity {

    TextView title,scount;
    ProgressBar progressBar;
    private static final int GALLERY_PICK = 1;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        title = findViewById(R.id.title);
        scount = findViewById(R.id.count);
        progressBar = findViewById(R.id.progressBardownload);
        Toast.makeText(scanResult.this, "Memulai Download Buku TA from TA repository", Toast.LENGTH_LONG).show();



      /* isReadStoragePermissionGranted();
       isWriteStoragePermissionGranted();*/
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);
        final File yourAppDir = new File(Environment.getExternalStorageDirectory()+"/QR_Reader");

        if(!yourAppDir.exists() && !yourAppDir.isDirectory())
        {
            // create empty directory
            if (yourAppDir.mkdirs())
            {
                Log.i("CreateDir","App dir created");
            }
            else
            {
                Log.w("CreateDir","Unable to create app dir!");
            }
        }
        else
        {
            Log.i("CreateDir","App dir already exists");
            String displayvalue = getIntent().getStringExtra("displayvalue");
            String url  = "";
            url = getIntent().getStringExtra("valueurl");
            final String path = yourAppDir.getPath();
            String gettitle = getIntent().getStringExtra("valuetitle");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());
            Log.e("This is Title", gettitle);
            final int id = PRDownloader.download(url , path, "TA"+currentDateandTime+gettitle+".pdf").build().getDownloadId();
            final int downloadId = PRDownloader.download(displayvalue , path,"TA"+currentDateandTime+gettitle+".pdf"     ).build().setOnStartOrResumeListener(new OnStartOrResumeListener() {
                @Override
                public void onStartOrResume() {
                    PRDownloader.resume(id);
                   // Toast.makeText(scanResult.this, "save in "+path, Toast.LENGTH_LONG).show();

                }
            }).setOnProgressListener(new OnProgressListener() {
                @Override
                public void onProgress(Progress progress) {
                    long totalbyte = progress.totalBytes;
                    long currentbyte = progress.currentBytes;
                   int count = (int) (currentbyte * 100 / totalbyte);
                   scount.setText(""+count);
                   progressBar.setProgress(count);





                }
            }).start(new OnDownloadListener() {
                @Override
                public void onDownloadComplete() {
                    Log.e("SUCCES", "Download complete");
                    Intent intent = new Intent(scanResult.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(scanResult.this, "download sukses", Toast.LENGTH_SHORT).show();
                    Toast.makeText(scanResult.this, "Swipe for see downloads files", Toast.LENGTH_LONG).show();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finish();
                }

                @Override
                public void onError(Error error) {
                    Toast.makeText(scanResult.this, "download FAILED", Toast.LENGTH_LONG).show();

                }
            });



            title.setText(displayvalue);
        }


    }

   /* public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Read Storage","Permission is granted1");
                return true;
            } else {

                Log.v("Read Storage","Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Read Storage","Permission is granted1");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Write Storage","Permission is granted2");
                return true;
            } else {

                Log.v("Write Storage","Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Write Storage","Permission is granted2");
            return true;
        }
    }*/


}
