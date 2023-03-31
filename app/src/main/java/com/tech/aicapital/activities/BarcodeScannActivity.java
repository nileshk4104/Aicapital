//package com.tech.aicapital.activities;
//
//import android.Manifest;
//import android.animation.ObjectAnimator;
//import android.animation.ValueAnimator;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//
//import android.util.SparseArray;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.ViewTreeObserver;
//import android.view.WindowManager;
//import android.view.animation.AccelerateDecelerateInterpolator;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.vision.CameraSource;
//import com.google.android.gms.vision.Detector;
//import com.google.android.gms.vision.barcode.Barcode;
//import com.google.android.gms.vision.barcode.BarcodeDetector;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.ChecksumException;
//import com.google.zxing.DecodeHintType;
//import com.google.zxing.FormatException;
//import com.google.zxing.LuminanceSource;
//import com.google.zxing.MultiFormatReader;
//import com.google.zxing.NotFoundException;
//import com.google.zxing.RGBLuminanceSource;
//import com.google.zxing.Reader;
//import com.google.zxing.Result;
//import com.google.zxing.common.HybridBinarizer;
//import com.tech.aicapital.MainActivity;
//import com.tech.aicapital.R;
//
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Hashtable;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//public class BarcodeScannActivity extends AppCompatActivity {
//    //initialize variables to make them global
////    private ImageButton Scan;
//    private static final int SELECT_PHOTO = 100;
//    private static final int CHOOSEIMAGE = 101;
//    //for easy manipulation of the result
//    public String barcode;
//    SurfaceView surfaceView;
//    TextView txtBarcodeValue;
//    private BarcodeDetector barcodeDetector;
//    private CameraSource cameraSource;
//    private static final int REQUEST_CAMERA_PERMISSION = 201;
//    Button btnAction;
//    String intentData = "";
//    boolean isEmail = false;
//    View view;
//    ObjectAnimator animator;
//    View scannerLayout;
//    View scannerBar;
//
//    @BindView(R.id.btnContinue)
//    Button btnContinue;
//
//    @BindView(R.id.imgGallery)
//    ImageButton Scan;
//
//    @BindView(R.id.txtHotelName)
//    TextView txtHotelName;
//    @BindView(R.id.backtext)
//    TextView backtext;
//    @BindView(R.id.ed_mobile_no)
//    TextView ed_mobile_no;
//    Uri selectedImage;
//    Bitmap bMap;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.barcode_scanner);
//        ButterKnife.bind(this);
//        initViews();
//        scannerLayout = findViewById(R.id.scannerLayout);
//        scannerBar = findViewById(R.id.scannerBar);
//
//        animator = null;
//
//        ViewTreeObserver vto = scannerLayout.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout()
//            {
//                scannerLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//                    scannerLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                } else {
//                    scannerLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//                float destination = (float)(scannerLayout.getY() + scannerLayout.getHeight());
//                animator = ObjectAnimator.ofFloat(scannerBar, "translationY", scannerLayout.getY(), destination);
//                animator.setRepeatMode(ValueAnimator.REVERSE);
//                animator.setRepeatCount(ValueAnimator.INFINITE);
//                animator.setInterpolator(new AccelerateDecelerateInterpolator());
//                animator.setDuration(3000);
//                animator.start();
//            }
//        });
//        ed_mobile_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                makecall1();
//            }
//        });
//        backtext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              finish();
//            }
//        });
////        ed_mobile_no.setOnKeyListener(new View.OnKeyListener() {
////            @Override
////            public boolean onKey(View view, int i, KeyEvent keyEvent) {
////                makecall1();
////                return false;
////
////            }
////        });
//        Scan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent photoPic = new Intent(Intent.ACTION_PICK);
//                photoPic.setType("image/*");
//                startActivityForResult(photoPic, CHOOSEIMAGE);
//            }
//        });
//    }
//    private void launchMediaScanIntent() {
//        Intent photoPic = new Intent(Intent.ACTION_PICK);
//        photoPic.setType("image/*");
//        startActivityForResult(photoPic, SELECT_PHOTO);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch (requestCode) {
//            case SELECT_PHOTO:
//                if (resultCode == RESULT_OK) {
////doing some uri parsing
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    InputStream imageStream = null;
//                    try {
//                        //getting the image
//                        imageStream = getContentResolver().openInputStream(selectedImage);
//                    } catch (FileNotFoundException e) {
//                        Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//                    //decoding bitmap
//                    Bitmap bMap = BitmapFactory.decodeStream(imageStream);
//                    Scan.setImageURI(selectedImage);// To display selected image in image view
//                    int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
//                    // copy pixel data from the Bitmap into the 'intArray' array
//                    bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(),bMap.getHeight());
//
//                    LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(),bMap.getHeight(), intArray);
//                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//                    Reader reader = new MultiFormatReader();// use this otherwise
//                    // ChecksumException
//                    try {
//                        Hashtable<DecodeHintType, Object> decodeHints = new Hashtable<DecodeHintType, Object>();
//                        decodeHints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//                        decodeHints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
//                        Result result = reader.decode(bitmap, decodeHints);
//                        //*I have created a global string variable by the name of barcode to easily manipulate data across the application*//
//                        barcode =  result.getText().toString();
//                        //do something with the results for demo i created a popup dialog
//                        if(barcode!=null){
//                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                            builder.setTitle("Scan Result");
//                            builder.setIcon(R.mipmap.ic_launcher);
//                            builder.setMessage("" + barcode);
//                            AlertDialog alert1 = builder.create();
//                            alert1.setButton(DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which)
//                                {
//                                    Intent i = new Intent (getBaseContext(),BarcodeScannActivity.class);
//                                    startActivity(i);
//                                }
//                            });
//                            alert1.setCanceledOnTouchOutside(false);
//                            alert1.show();}
//                        else
//                        {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                            builder.setTitle("Scan Result");
//                            builder.setIcon(R.mipmap.ic_launcher);
//                            builder.setMessage("Nothing found try a different image or try again");
//                            AlertDialog alert1 = builder.create();
//                            alert1.setButton(DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent i = new Intent (getBaseContext(),BarcodeScannActivity.class);
//                                    startActivity(i);
//                                    finish();
//                                }
//                            });
//
//                            alert1.setCanceledOnTouchOutside(false);
//
//                            alert1.show();
//
//                        }
//                        //the end of do something with the button statement.
//
//                    } catch (NotFoundException e) {
//                        Toast.makeText(getApplicationContext(), "Nothing Found", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    } catch (ChecksumException e) {
//                        Toast.makeText(getApplicationContext(), "Something weird happen, i was probably tired to solve this issue", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    } catch (FormatException e) {
//                        Toast.makeText(getApplicationContext(), "Wrong Barcode/QR format", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    } catch (NullPointerException e) {
//                        Toast.makeText(getApplicationContext(), "Something weird happen, i was probably tired to solve this issue", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//                }
//            case CHOOSEIMAGE:
//                if (resultCode == RESULT_OK) {
//                    selectedImage = imageReturnedIntent.getData();
//                    InputStream imageStream = null;
//                    try {
//                        //getting the image
//                        imageStream = getContentResolver().openInputStream(selectedImage);
//                    } catch (FileNotFoundException e) {
//                        Toast.makeText(getApplicationContext(), "File not found", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//                    //decoding bitmap
//                    bMap = BitmapFactory.decodeStream(imageStream);
////                    Scan.setImageURI(selectedImage);// To display selected image in image view
//                    int[] intArray = new int[bMap.getWidth() * bMap.getHeight()];
//                    // copy pixel data from the Bitmap into the 'intArray' array
//                    bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(),
//                            bMap.getHeight());
//
//                    LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(),
//                            bMap.getHeight(), intArray);
//                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//
//                    Reader reader = new MultiFormatReader();// use this otherwise
//                    // ChecksumException
//                    try {
//                        Hashtable<DecodeHintType, Object> decodeHints = new Hashtable<DecodeHintType, Object>();
//                        decodeHints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//                        decodeHints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
//
//                        Result result = reader.decode(bitmap, decodeHints);
//                        //*I have created a global string variable by the name of barcode to easily manipulate data across the application*//
//                        barcode =  result.getText().toString();
//
//                        //do something with the results for demo i created a popup dialog
//                        if(barcode!=null)
//                        {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                            builder.setTitle("Scan Result");
//                            builder.setIcon(R.mipmap.ic_launcher);
//                            builder.setMessage("" + barcode);
//                            txtBarcodeValue.setText(barcode);
//                            AlertDialog alert1 = builder.create();
////                            alert1.setButton(DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
////                                @Override
////                                public void onClick(DialogInterface dialog, int which) {
//////                                    Intent i = new Intent (getBaseContext(),BarcodeScannActivity.class);
//////                                    startActivity(i);
////                                    groceryPresenter.getFriendInfo(barcode);
////
////                                }
////                            });
////
////                            alert1.setCanceledOnTouchOutside(false);
////
////                            alert1.show();
////                        }
//
//                        }
//                        else
//                        {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                            builder.setTitle("Scan Result");
//                            builder.setIcon(R.mipmap.ic_launcher);
//                            builder.setMessage("Nothing found try a different image or try again");
//                            AlertDialog alert1 = builder.create();
//                            alert1.setButton(DialogInterface.BUTTON_POSITIVE, "Done",new DialogInterface.OnClickListener()
//                        {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent i = new Intent (getBaseContext(),BarcodeScannActivity.class);
//                                    startActivity(i);
//                                }
//                            });
//                            alert1.setCanceledOnTouchOutside(false);
//                            alert1.show();
//                        }
//                        //the end of do something with the button statement.
//
//                    } catch (NotFoundException e) {
//                        Toast.makeText(getApplicationContext(), "Nothing Found", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    } catch (ChecksumException e) {
//                        Toast.makeText(getApplicationContext(), "Something weird happen, i was probably tired to solve this issue", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    } catch (FormatException e) {
//                        Toast.makeText(getApplicationContext(), "Wrong Barcode/QR format", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    } catch (NullPointerException e) {
//                        Toast.makeText(getApplicationContext(), "Something weird happen, i was probably tired to solve this issue", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//
//                }
//        }
//    }
//    private void makecall() {
//
//        if(txtBarcodeValue.getText().toString().equalsIgnoreCase("No Barcode Detected")){
//
//            alertDialog("No QR code detected , please scan QR code or Reopen the same page ");
//        }else{
////            Intent intent=new Intent(BarcodeScannActivity.this, FriendTransferActivity.class);
////            intent.putExtra("title","Pay Bill / Amount ");
////            intent.putExtra("BARCODE",true);
////            intent.putExtra("mobileno",txtBarcodeValue.getText().toString());
////            startActivity(intent);
////            finish();
//        }
//
//
//    }
//
//    private void alertDialog(String message)
//    {
//
////        AlertDialog.Builder builder = new AlertDialog.Builder(this);
////        LayoutInflater inflater = getLayoutInflater();
////        View dialogLayout = inflater.inflate(R.layout.alert_dialog,null);
////        final AlertDialog dialogu = builder.create();
////        dialogu.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
////        dialogu.setView(dialogLayout, 0, 0, 0, 0);
////        dialogu.setCanceledOnTouchOutside(true);
////        dialogu.setCancelable(true);
////        WindowManager.LayoutParams wlmp = dialogu.getWindow().getAttributes();
////        wlmp.gravity = Gravity.CENTER;
////
////
////
////        builder.setView(dialogLayout);
////        dialogu.show();
//
//    }
//    private void makecall1() {
//    }
//    private void initViews() {
//        txtBarcodeValue = (TextView) findViewById(R.id.txtBarcodeValue);
//        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
//        btnAction = (Button) findViewById(R.id.btnAction);
//        btnAction.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        btnContinue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makecall();
//            }
//        });
//    }
//    private void initialiseDetectorsAndSources() {
//        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();
//        cameraSource = new CameraSource.Builder(this, barcodeDetector)
//                .setRequestedPreviewSize(1920, 1080).setAutoFocusEnabled(true) //you should add this feature
//                .build();
//        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder)
//            {
//                try {
//                    if (ActivityCompat.checkSelfPermission(BarcodeScannActivity.this,
//                            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        cameraSource.start(surfaceView.getHolder());
//                    } else {
//                        ActivityCompat.requestPermissions(BarcodeScannActivity.this, new
//                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//            }
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                cameraSource.stop();
//            }
//        });
//
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//            }
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections)
//            {
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//                if (barcodes.size() != 0) {
//                    txtBarcodeValue.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (barcodes.valueAt(0).email != null)
//                            {
//                                txtBarcodeValue.removeCallbacks(null);
//                                intentData = barcodes.valueAt(0).email.address;
//                                txtBarcodeValue.setText(intentData);
//                                isEmail = true;
//                                btnAction.setText("Cancel");
//                            }
//                            else
//                             {
//                                isEmail = false;
//                                btnContinue.setText("Proceed to Pay");
//                                intentData = barcodes.valueAt(0).displayValue;
//                                txtBarcodeValue.setText(intentData);
//                                try
//                                {
//                                    cameraSource.release();
//                                    verifyShop("7744976045");
//                                    animator.cancel();
//                                }
//                                catch (Exception e)
//                                {
//                                }
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
//    private void verifyShop(String mobileno) {
////        groceryPresenter.getInformation(mobileno);
//    }
//
//
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//    }
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        initialiseDetectorsAndSources();
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent mainActivity = new Intent(this, MainActivity.class);
//        startActivity(mainActivity);
//        finish();
//    }
//
//
//}