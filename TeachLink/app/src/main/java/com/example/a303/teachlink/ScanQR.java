package com.example.a303.teachlink;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ScanQR extends AppCompatActivity {
    static ArrayList<String> rocalllist;
    private TextView TV_Message;
    private Button BT_Scan;
    private static final String PACKAGE = "com.google.zxing.client.android";
    private static final int REQUEST_BARCODE_SCAN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        findViews();

    }

    private void findViews() {
        TV_Message = (TextView) findViewById(R.id.TV_Message);
        BT_Scan = (Button) findViewById(R.id.BT_Scan);
        BT_Scan.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(
                        "com.google.zxing.client.android.SCAN");
                try {
                    startActivityForResult(intent, REQUEST_BARCODE_SCAN);
                }
                // 如果沒有安裝Barcode Scanner，就跳出對話視窗請user安裝
                catch (ActivityNotFoundException e) {
                    showDownloadDialog();
                }
            }

        });
    }

    private void showDownloadDialog() {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(this);
        downloadDialog.setTitle("No Barcode Scanner Found");
        downloadDialog
                .setMessage("Please download and install Barcode Scanner!");
        downloadDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("market://search?q=pname:"
                                + PACKAGE);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException ex) {
                            Log.e(ex.toString(),
                                    "Play Store is not installed; cannot install Barcode Scanner");
                        }
                    }
                });
        downloadDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        downloadDialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_BARCODE_SCAN) {
            String message="";
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                //String resultFormat = intent.getStringExtra("SCAN_RESULT_FORMAT");
                message = (contents);
            } else if (resultCode == RESULT_CANCELED) {
                message = "Scan was Cancelled!";
            }

            TV_Message.setText(message);
            rocalllist.add(message);
        }
    }


}
