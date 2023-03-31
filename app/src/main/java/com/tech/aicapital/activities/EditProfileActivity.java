package com.tech.aicapital.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.squareup.picasso.Picasso;
import com.tech.aicapital.MyBankDetailsActivity;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.FileUtils;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.aicapital.mvps.Constant.USER_ID;

public class EditProfileActivity extends AppCompatActivity {

    boolean isAdd;
    boolean CAMERACAPTURE=false;
    Uri GLOBALURI1,GLOBALURI2,GLOBALURI3,GLOBALURI4;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;

    int IMAGENUMBER=1;


    private static final int PICK_IMAGE_REQUEST = 0;
    public static Uri ImageUri;
    private Uri ImageUri2;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private File destination = null;
    private File destination2 = null;
    private InputStream inputStreamImg;
    private String imgPath = null;
    private String imgPath2 = null;
    private final int PICK_IMAGE_CAMERA = 1, PICK_IMAGE_GALLERY = 2;
    String pincode,tehsilCode;
    LocationManager locationManager;
    Double lat=10.123456,longi=10.254656;
    boolean FIRSTI=true;
    String category_id,category_name,subcity;

    @BindView(R.id.button_update_bank)
    Button button_update_bank;
    @BindView(R.id.button_update_aadhar)
    Button button_update_aadhar;
    @BindView(R.id.button_update_pan)
    Button button_update_pan;


    @BindView(R.id.ed_usercode)
    EditText ed_usercode;
    @BindView(R.id.ed_first_name)
    EditText ed_first_name;
    @BindView(R.id.ed_sponsername)
    EditText ed_sponsername;
    @BindView(R.id.edAadhAR)
    EditText edAadhAR;

    @BindView(R.id.tv_dob)
    EditText tv_dob;
    @BindView(R.id.ed_mobile_no)
    EditText ed_mobile_no;
    @BindView(R.id.ed_mail_id)
    EditText ed_mail_id;
    @BindView(R.id.edPanCard)
    EditText edPanCard;
    @BindView(R.id.ivProfile)
    CircleImageView ivProfile;

    @BindView(R.id.button_next_one)
    Button button_next_one;
    @BindView(R.id.ivAadhar1)
    ImageView ivAadhar1;
    @BindView(R.id.ivAadhar2)
    ImageView ivAadhar2;
    @BindView(R.id.ivPancard)
    ImageView ivPancard;
    @BindView(R.id.tilMobile)
    TextInputLayout tilMobile;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_activity);
        ButterKnife.bind(this);

        Utility.pageSetupBackButton(EditProfileActivity.this,"My Profile");
        sharedPreferences = getSharedPreferences("Reg", 0);

        getMemberDetail();
        ivAadhar1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                IMAGENUMBER=1;
                imageSelect();
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                IMAGENUMBER=4;
                imageSelect();
            }
        });

        ivAadhar2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                IMAGENUMBER=2;
                imageSelect();
            }
        });

        ivPancard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                IMAGENUMBER=3;
                imageSelect();
            }
        });

        button_update_bank.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(EditProfileActivity.this, MyBankDetailsActivity.class);
               startActivity(intent);
            }
        });

        button_update_aadhar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                insertShop(GLOBALURI1,GLOBALURI2);

            }
        });

        button_update_pan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                insertPanCard(GLOBALURI3);
            }
        });

        button_next_one.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(ed_mobile_no.getText().toString().trim().length()<10)
                {
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER CORRECT MOBILE NUMBER",Toast.LENGTH_SHORT).show();
                }
                else{
                    updateProfileData();
                }

            }
        });

        checkAndRequestPermissions(this);
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
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
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

    public void insertProfile(Uri profile){
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        MultipartBody.Part aadhar_front1 = prepareFilePart("profile", profile, this);
        RequestBody user = createPartFromString(ed_usercode.getText().toString());
        Call<NewResponse> call = apiService.updateProfileImage(aadhar_front1,user);
        call.enqueue(new Callback<NewResponse>()
                     {
                         @Override
                         public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                             NewResponse hospitalListResponse = response.body();
                             Utility.hideProgressBar();
                             if (hospitalListResponse != null)
                             {
                                 if(hospitalListResponse.isStatus())
                                 {
                                     Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                     finish();
                                 }else{
                                     Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(),Toast.LENGTH_SHORT).show();
                                 }
                             } else
                                 Toast.makeText(getApplicationContext(),"Unknown Response",Toast.LENGTH_SHORT).show();
                         }
                         @Override
                         public void onFailure(Call<NewResponse> call, Throwable t)
                         {
                             Utility.hideProgressBar();
                             if (t instanceof IOException) {
                                 //Add your code for displaying no network connection error
                                 Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                             } else {
                                 Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                             }
                         }
                     }
        );

////
////
    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }
    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context)
    {
        File file = FileUtils.getFile(context, fileUri);
        RequestBody requestFile = RequestBody.create(MediaType.parse(context.getContentResolver().getType(fileUri)), file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }


    public void imageSelect(){
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // CAMERA
            case PICK_IMAGE_CAMERA:
                try {
                    if (IMAGENUMBER==1)
                    {
                        ImageUri = data.getData();
                        bitmap = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                        Log.e("Activity", "Pick from Camera::>>> ");
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        destination = new File(Environment.getExternalStorageDirectory() + "/" +
                                getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                        FileOutputStream fo;
                        try {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgPath = destination.getAbsolutePath();
                        ivAadhar1.setImageBitmap(bitmap);
                        GLOBALURI1 = getImageUri(this, bitmap);
                        ivAadhar1.invalidate();
                    } else    if (IMAGENUMBER==2)
                    {
                        ImageUri = data.getData();
                        bitmap = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                        Log.e("Activity", "Pick from Camera::>>> ");
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        destination = new File(Environment.getExternalStorageDirectory() + "/" +
                                getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                        FileOutputStream fo;
                        try {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgPath = destination.getAbsolutePath();
                        ivAadhar2.setImageBitmap(bitmap);
                        GLOBALURI2 = getImageUri(this, bitmap);
                        ivAadhar2.invalidate();
                    }
                    else    if (IMAGENUMBER==3)
                    {
                        ImageUri = data.getData();
                        bitmap = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        destination = new File(Environment.getExternalStorageDirectory() + "/" +getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                        FileOutputStream fo;
                        try {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgPath = destination.getAbsolutePath();
                        ivPancard.setImageBitmap(bitmap);
                        GLOBALURI3 = getImageUri(this, bitmap);
                        ivPancard.invalidate();
                    }    else    if (IMAGENUMBER==4)
                    {
                        ImageUri = data.getData();
                        bitmap = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                        destination = new File(Environment.getExternalStorageDirectory() + "/" +getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                        FileOutputStream fo;
                        try {
                            destination.createNewFile();
                            fo = new FileOutputStream(destination);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgPath = destination.getAbsolutePath();
                        destination.getName();
                        ivProfile.setImageBitmap(bitmap);
                        GLOBALURI4 = getImageUri(this, bitmap);
                        ivPancard.invalidate();
//                        insertGroupImage(GLOBALURI4,"");
                        insertProfile(GLOBALURI4);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //GALLERY
            case PICK_IMAGE_REQUEST:
                if (requestCode == PICK_IMAGE_REQUEST) {
                    if (resultCode == RESULT_OK) {
                        if (data != null) {
                            if (IMAGENUMBER==1)
                            {
                                ImageUri = data.getData();
                                GLOBALURI1 = ImageUri;
                                ivAadhar1.setImageURI(ImageUri);
                                ivAadhar1.invalidate();
                            }else if (IMAGENUMBER==2)
                            {
                                ImageUri = data.getData();
                                GLOBALURI2 = ImageUri;
                                ivAadhar2.setImageURI(ImageUri);
                                ivAadhar2.invalidate();
                            }  else if (IMAGENUMBER==3)
                            {
                                ImageUri = data.getData();
                                GLOBALURI3 = ImageUri;
                                ivPancard.setImageURI(ImageUri);
                                ivPancard.invalidate();
                            }  else if (IMAGENUMBER==4)
                            {
                                ImageUri = data.getData();
                                GLOBALURI4 = ImageUri;
                                ivProfile.setImageURI(ImageUri);
                                ivProfile.invalidate();
                                insertProfile(GLOBALURI4);
//                                insertGroupImage(GLOBALURI4, "");
                            }
                        }
                    }
                }
                break;
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage)
    {
        if(FIRSTI){
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                    inImage, "Title", null);
            ImageUri=Uri.parse(path);
            return ImageUri;
        }else{
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                    inImage, "Title", null);
            ImageUri2=Uri.parse(path);
            return ImageUri2;
        }

    }
    public void getMemberDetail()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<MemberDataResponse> call = apiService.member_details(Pref.getValue(EditProfileActivity.this,USER_ID,""));
        call.enqueue(new Callback<MemberDataResponse>()
        {
            @Override
            public void onResponse(Call<MemberDataResponse> call, Response<MemberDataResponse> response)
            {
                MemberDataResponse bannerDataResponse = response.body();
                Utility.hideProgressBar();
                if (bannerDataResponse != null)
                {
                    if(bannerDataResponse.isStatus())
                    {
                        if(bannerDataResponse.getData().get(0).getBankstatus().equalsIgnoreCase("2"))
                        {
                            button_update_bank.setVisibility(View.VISIBLE);
                            ed_usercode.setText(bannerDataResponse.getData().get(0).getUserId());
                            ed_first_name.setText(bannerDataResponse.getData().get(0).getUserName());
                            ed_sponsername.setText(bannerDataResponse.getData().get(0).getReferUserId());
                            ed_mobile_no.setText(bannerDataResponse.getData().get(0).getUserMobile());
                            ed_mail_id.setText(bannerDataResponse.getData().get(0).getUserEmail());
                            edAadhAR.setText(bannerDataResponse.getData().get(0).getAadhar());
                            tv_dob.setText(bannerDataResponse.getData().get(0).getDob());
                            edPanCard.setText(bannerDataResponse.getData().get(0).getPan());
                            ed_mobile_no.setEnabled(false);
                        }
                    else{
                            button_update_bank.setVisibility(View.VISIBLE);
                            ed_usercode.setText(bannerDataResponse.getData().get(0).getUserId());
                            ed_first_name.setText(bannerDataResponse.getData().get(0).getUserName());
                            ed_sponsername.setText(bannerDataResponse.getData().get(0).getReferUserId());
                            ed_mobile_no.setText(bannerDataResponse.getData().get(0).getUserMobile());
                            ed_mail_id.setText(bannerDataResponse.getData().get(0).getUserEmail());
                            edAadhAR.setText(bannerDataResponse.getData().get(0).getAadhar());
                            tv_dob.setText(bannerDataResponse.getData().get(0).getDob());
                            edPanCard.setText(bannerDataResponse.getData().get(0).getPan());

                            /* Here you get int representation of an HTML color resources */
                            int yourColorWhenEnabled = ContextCompat.getColor(EditProfileActivity.this, R.color.red);
                            int yourColorWhenDisabled = ContextCompat.getColor(EditProfileActivity.this, R.color.blue);
                            /* Here you get matrix of states, I suppose it is a matrix because using a matrix you can set the same color
                            (you have an array of colors) for different states in the same array */
                            int[][] states = new int[][]{new int[]{android.R.attr.state_enabled}, new int[]{-android.R.attr.state_enabled}};
                            /* You pass a ColorStateList instance to "setDefaultHintTextColor" method, remember that you have a matrix for the states of the view and an array for the colors. So the color in position
                             "colors[0x0]" will be used for every states inside the array in the same position inside the matrix "states", so in the array "states[0x0]". So you have "colors[pos] -> states[pos]", or "colors[pos] -> color used for every states inside the array of view states -> states[pos] */
//                            tilMobile.setDefaultHintTextColor(new ColorStateList(states,
//                                    new int[]{yourColorWhenEnabled, yourColorWhenDisabled}));

                        }

                        Picasso.with(EditProfileActivity.this).load(bannerDataResponse.getMessage()+
                                bannerDataResponse.getData().get(0).getProfileImg()).placeholder(R.mipmap.ic_launcher).into(ivProfile);
                    }
                } else
                    Toast.makeText(getApplicationContext(),"Some error occur", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MemberDataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void updateProfileData()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.updateProfileData(Pref.getValue(EditProfileActivity.this,USER_ID,""),edAadhAR.getText().toString(),
                edPanCard.getText().toString(),ed_mail_id.getText().toString(),tv_dob.getText().toString(),
                ed_first_name.getText().toString(),ed_mobile_no.getText().toString());
        call.enqueue(new Callback<NewResponse>()
        {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse bannerDataResponse = response.body();
                Utility.hideProgressBar();
                if (bannerDataResponse != null)
                {
                    if(bannerDataResponse.isStatus())
                    {
                        Toast.makeText(getApplicationContext(),bannerDataResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(),"UNKNOWN RESPONSE", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
