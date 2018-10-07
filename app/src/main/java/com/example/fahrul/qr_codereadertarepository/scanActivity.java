package com.example.fahrul.qr_codereadertarepository;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Status;
import com.downloader.request.DownloadRequest;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;

import java.io.File;
import java.util.List;

import dmax.dialog.SpotsDialog;
import info.androidhive.barcode.BarcodeReader;
import info.androidhive.barcode.BarcodeReader.BarcodeReaderListener;

public class scanActivity extends AppCompatActivity implements BarcodeReaderListener{

    BarcodeReader barcodeReader;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);





        alertDialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Qr code bukan sebuah Url..")


                .build();






    }

    @Override
    public void onScanned(Barcode barcode) {

        barcodeReader.playBeep();
        String valuetitle = barcode.url.title.toString();
        String valueurl = barcode.url.url.toString();
        Log.e("This is display value", valuetitle+""+valueurl);
        Intent intent = new Intent(scanActivity.this,scanResult.class);
        intent.putExtra("valuetitle",valuetitle );
        intent.putExtra("valueurl", valueurl);
        intent.putExtra("displayvalue", barcode.displayValue);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

       /*Log.e("value format is", ""+barcode.valueFormat);
        Log.e("url is", ""+barcode.url);
        Log.e("wifi is", ""+barcode.wifi.ssid+"/"+barcode.wifi.password);
        Log.e("  format is", ""+barcode.format);
        String valueformat = String.valueOf(barcode.valueFormat);

        if (valueformat.equalsIgnoreCase("8")){
            String value = barcode.url.title+":"+barcode.url.url;
            Intent intent = new Intent(scanActivity.this,scanResult.class);
            intent.putExtra("value", value);
            startActivity(intent);


        }else {

            alertDialog.show();
*/
    }







    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Toast.makeText(scanActivity.this, "Error occurred while scanning " + errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCameraPermissionDenied() {

        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
