package com.example.a303.teachlink;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.util.Date;

public class StudentRollcall extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_rollcall);

        Bundle bundle=getIntent().getExtras();
        user=(User) bundle.getSerializable("user");
    }

    public void onGenerateQrCodeClick(View view) {//課號+時間+USER
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis()) ; // 獲取當前時間
        String nowtime = formatter.format(curDate);

        EditText etQRCodeText = (EditText) findViewById(R.id.ET_QRClass);
        String qrCodeText = etQRCodeText.getText().toString()+nowtime+user.getUsername();
        Log.d(TAG, qrCodeText);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // get window size
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        // QR code image's length is 1/2 the smaller side of the window,
        int imageSide = width < height ? width : height;
        imageSide = imageSide / 2;

        // Encode with a QR Code image
        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrCodeText, null,
                Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(),
                imageSide);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            ImageView myImage = (ImageView) findViewById(R.id.ivQRCode);
            myImage.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
