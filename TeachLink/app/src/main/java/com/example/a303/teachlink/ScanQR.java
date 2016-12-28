package com.example.a303.teachlink;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ScanQR extends AppCompatActivity {
    static ArrayList<String> rocalllist=new ArrayList<>();
    private TextView TV_Message;
    private Button BT_Scan,BT_Over;
    private static final String PACKAGE = "com.google.zxing.client.android";
    private static final int REQUEST_BARCODE_SCAN = 0;
    static private RollcallData rollcallData;
    static private User user;
    static private String jsonStr;
    //*****juiz*******
    private static Handler mHandler;
    //***************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        findViews();
        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");
        //*****juiz*******get
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //What you want to do with these data? Just write here!
                super.handleMessage(msg);
                Bundle bundle= msg.getData();
                int select=msg.what;
                /*num 1: login*/
                String content = bundle.getString("content");//json
//                Log.d("Con",content);

            }
        };
        //*****juiz*******

    }

    private void findViews() {
        TV_Message = (TextView) findViewById(R.id.TV_Message);
        BT_Scan = (Button) findViewById(R.id.BT_Scan);
        BT_Over=(Button) findViewById(R.id.BT_Over) ;
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
        BT_Over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogFragment alertFragment = new AlertDialogFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                alertFragment.show(fragmentManager, "alert");



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

    public static class AlertDialogFragment
            extends DialogFragment implements DialogInterface.OnClickListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.MakeSure)
                    .setIcon(R.drawable.alert)
                    .setMessage(R.string.MakeSureMessage)
                    .setPositiveButton(R.string.text_btYes, this)
                    .setNegativeButton(R.string.text_btNo, this)
                    .create();
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    rollcallData=new RollcallData(rocalllist,user.getChooseclass());
                    Gson gson = new Gson();
                    jsonStr = gson.toJson(rollcallData);
                    Log.d("JsonTryR",jsonStr);
                    //********juiz*********send
                    try {
                        URL url = new URL("http://192.168.1.170");
                        ArrayMap<String , String> reqData = new ArrayMap();
                        reqData.put("select","roll_call");
                        reqData.put("classStu",jsonStr);
                        WebData webData = new WebData(url,mHandler);
                        webData.setReqData(reqData);
                        webData.getData();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    //*****************

                    Intent intent=new Intent(getActivity(),TeacherMain.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("user",user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
                default:
                    break;
            }
        }
    }


}
