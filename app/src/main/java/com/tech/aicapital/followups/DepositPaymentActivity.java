package com.tech.aicapital.followups;


import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.activities.WebViewActivity;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.followups.datalist.AccountDataResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.FileUtils;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepositPaymentActivity extends AppCompatActivity {
    boolean CAMERACAPTURE=false;
    Uri GLOBALURI1;
    SharedPreferences sharedPreferences;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;

    private static final int PICK_IMAGE_REQUEST = 0;
    public static Uri ImageUri;
    private String imgPath = null;
    private Uri ImageUri2;
    private Bitmap bitmap;
    private File destination = null;

    private final int PICK_IMAGE_CAMERA = 1;
    boolean FIRSTI=true;

    @BindView(R.id.btnDepositPayment)
    TextView btnDepositPayment;
    @BindView(R.id.edTransactionNo)
    TextView edTransactionNo;
    @BindView(R.id.edAmount)
    TextView edAmount;


    @BindView(R.id.tvPaymentDetails)
    TextView tvPaymentDetails;

    @BindView(R.id.ivScrrenShot)
    ImageView ivScrrenShot;

    @BindView(R.id.btnAdminWhatsapp)
    Button btnAdminWhatsapp;

    @BindView(R.id.tvPlanDesc)
    TextView tvPlanDesc;
    @BindView(R.id.tvTermsandConditions)
    TextView tvTermsandConditions;

    @BindView(R.id.ivQrcode)
    ImageView ivQrcode;
    @BindView(R.id.chkAgree)
    CheckBox chkAgree;

    String plan_id,plan_name,plan_desc,plan_amount;
    int amt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deposit_payment_activity);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        plan_name=intent.getStringExtra("plan_name");
        plan_id=intent.getStringExtra("plan_id");
        plan_desc=intent.getStringExtra("plan_desc");
        plan_amount=intent.getStringExtra("plan_amount");
        edAmount.setText(plan_amount);
        Utility.pageSetupBackButton(DepositPaymentActivity.this,plan_name);

        checkAndRequestPermissions(this);
        tvPlanDesc.setText(plan_desc);

        ivScrrenShot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                imageSelect();
            }
        });
        tvTermsandConditions.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(DepositPaymentActivity.this, WebViewActivity.class);
                intent.putExtra("url","http://www.capitalindex.live/capindex_return_and_refund_policy.html");
                intent.putExtra("name","Terms and Refund Policy");
                startActivity(intent);
            }
        });
        btnDepositPayment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(edAmount.getText().toString().trim().length()<0)
                {
                    Toast.makeText(getApplicationContext(),"Min deposit 3000/-",Toast.LENGTH_SHORT).show();
                }
                else if(edTransactionNo.getText().toString().trim().length()<4)
                {
                    Toast.makeText(getApplicationContext(),"Enter valid transaction no",Toast.LENGTH_SHORT).show();
                }
                else if(!chkAgree.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"You should agree terms and conditions ",Toast.LENGTH_SHORT).show();
                }
                else if(GLOBALURI1==null)
                {
                    Toast.makeText(getApplicationContext(),"Please upload Screenshot",Toast.LENGTH_SHORT).show();
                }
               else{
                    if(Integer.parseInt(edAmount.getText().toString())<3000)
                    {
                        Toast.makeText(getApplicationContext(),"Min deposit 3000/-",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
//                        amt = Integer.parseInt(edAmount.getText().toString())*100;
//                        startPayment("123456");
                        depositPayment(GLOBALURI1);
                    }
                }
            }
        });
        btnAdminWhatsapp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String phoneNumberWithCountryCode = "+917030380503";
                String message = "I want to Purchase Plan : "+plan_name;
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s",
                                phoneNumberWithCountryCode, message))));

            }
        });


    }

//    public void startPayment(String refno)
//    {
//        Log.e("TAG", "starting Razorpay Checkout");
//
//        Checkout checkout = new Checkout();
//
//        checkout.setImage(R.drawable.ic_launcher);
//
//        final Activity activity = this;
//
//        try {
//            JSONObject options = new JSONObject();
//            options.put("name", "Vastu Nirman");
//            options.put("description", refno);
//            options.put("currency", "INR");
//            options.put("amount", amt);
//            options.put("prefill.contact","7218740825");
//            options.put("prefill.email","nileshk4104@gmil.com");
//            checkout.open(activity, options);
//
//        }
//        catch(Exception e)
//        {
//            Log.e("TAG", "Err "+e);
//        }
//
//    }


    @Override
    protected void onResume() {
        getCurrentAccDetails();
        super.onResume();
    }

    public void depositPayment(Uri img_aadhar)
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        RequestBody amt = createPartFromString(edAmount.getText().toString());
        RequestBody transNo = createPartFromString(edTransactionNo.getText().toString());
        RequestBody planId = createPartFromString("1");
        RequestBody userId = createPartFromString(Pref.getValue(DepositPaymentActivity.this, USER_ID,"0"));
        MultipartBody.Part screenshot = prepareFilePart("img_aadhar",img_aadhar,this);
        Call<NewResponse> call = apiService.depositPayment(transNo,amt,userId,planId,screenshot);
        call.enqueue(new Callback<NewResponse>()
                        {
                         @Override
                         public void onResponse(Call<NewResponse> call, Response<NewResponse> response)
                         {
                             NewResponse hospitalListResponse = response.body();
                             Utility.hideProgressBar();
                             if (hospitalListResponse != null)
                             {
                                 if(hospitalListResponse.isStatus())
                                 {
                                     Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                     finish();
                                 }
                                 else
                                 {
                                     Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                 }
                             } else
                                 Toast.makeText(getApplicationContext(),"Unknown Response",Toast.LENGTH_SHORT).show();
                         }
                         @Override
                         public void onFailure(Call<NewResponse> call, Throwable t)
                         {
                             Utility.hideProgressBar();
                             if (t instanceof IOException)
                             {
                                 //Add your code for displaying no network connection error
                                 Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                             } else {
                                 Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                             }
                         }
                     }
        );
    }

    public void getCurrentAccDetails()
    {

        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<AccountDataResponse> call = apiService.getCurrentAccDetails();
        call.enqueue(new Callback<AccountDataResponse>() {
            @Override
            public void onResponse(Call<AccountDataResponse> call, Response<AccountDataResponse> response) {
                AccountDataResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();
                if (hospitalListResponse != null) {
                    if(hospitalListResponse.isStatus())
                    {
                        if(hospitalListResponse.getData().size()>0)
                        {
                            tvPaymentDetails.setText("Bank Name: "+hospitalListResponse.getData().get(0).getBankName()+"\n"+
                                    "Account No: "+hospitalListResponse.getData().get(0).getAccountNo()+"\n"+
                                    "IFSC Code: "+hospitalListResponse.getData().get(0).getIfscCode()+"\n"+
                                    "Account Name: "+hospitalListResponse.getData().get(0).getAccountName());

//                            GlideApp.with(DepositPaymentActivity.this).load(hospitalListResponse.getMessage()+hospitalListResponse.getData().get(0).getCurAcc()).into(ivQrcode);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else
                    Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AccountDataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"error 2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"error 3", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public static boolean checkAndRequestPermissions(final Activity context)
    {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int RExtstorePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);

        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
        {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (RExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "Requires Access to Camara.", Toast.LENGTH_SHORT)
                            .show();

                } else if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "Requires Access to Your Storage.",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "DO WORK ...",
                            Toast.LENGTH_SHORT).show();

//                    doWork();
                }
                break;
        }
    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString)
    {
        return RequestBody.create(MultipartBody.FORM, descriptionString);
    }
    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context)
    {
        File file = FileUtils.getFile(context, fileUri);
        RequestBody requestFile = RequestBody.create(MediaType.parse(context.getContentResolver().getType(fileUri)), file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
    public void imageSelect()
    {
        permissionsCheck();
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED)
            {
                final CharSequence[] options = {"Take Photo", "Choose From Gallery","Cancel"};
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setTitle("Select Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item)
                    {
                        if (options[item].equals("Take Photo"))
                        {
                            CAMERACAPTURE=true;
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        }
                        else if (options[item].equals("Choose From Gallery"))
                        {
                            CAMERACAPTURE=false;
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, PICK_IMAGE_REQUEST);
                        }
                        else if (options[item].equals("Cancel"))
                        {
                            CAMERACAPTURE=false;
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(getApplicationContext(), "Camera Permission error 1 ", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Camera Permission error 2", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    public void permissionsCheck()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // CAMERA
            case PICK_IMAGE_CAMERA:
                try {
                        ImageUri = data.getData();
                        bitmap = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        destination = new File(Environment.getExternalStorageDirectory() + "/" +getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                        FileOutputStream fo;
                        try
                        {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        imgPath = destination.getAbsolutePath();
                    ivScrrenShot.setImageBitmap(bitmap);
                     GLOBALURI1 = getImageUri(this, bitmap);
                    ivScrrenShot.invalidate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //GALLERY
            case PICK_IMAGE_REQUEST:
                if (requestCode == PICK_IMAGE_REQUEST)
                {
                    if (resultCode == RESULT_OK)
                    {
                        if (data != null)
                        {
                                ImageUri = data.getData();
                                GLOBALURI1 = ImageUri;
                                ivScrrenShot.setImageURI(ImageUri);
                                ivScrrenShot.invalidate();
                        }
                    }
                }
                break;
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage)
    {

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                    inImage, "Title", null);
            ImageUri=Uri.parse(path);
            return ImageUri;


    }
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return super.onSupportNavigateUp();
    }

//    @Override
//    public void onPaymentSuccess(String s) {
//
//    }
//    @Override
//    public void onPaymentError(int i, String s)
//    {
//        Toast.makeText(getApplicationContext(),"Payment Canceled by User  ",Toast.LENGTH_SHORT).show();
//        finish();
//    }



}
