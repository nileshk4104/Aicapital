package com.tech.aicapital;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.os.Handler;
import android.text.Html;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.tech.aicapital.Adapter.DynamicMenuAdapter;
import com.tech.aicapital.activities.AddNewRegistrationActivity;
import com.tech.aicapital.activities.AllArticlesActivity;
import com.tech.aicapital.activities.CategorywiseOfferListActivity;
import com.tech.aicapital.activities.EditProfileActivity;
import com.tech.aicapital.activities.ExpandableListViewActivity;
import com.tech.aicapital.activities.LoginActivity;

import com.tech.aicapital.activities.TypewiseTransactionHistoryActivity;
import com.tech.aicapital.activities.VideoListActivity;
import com.tech.aicapital.activities.WebViewActivity;
import com.tech.aicapital.cart.datalist.CartDataList;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.tech.aicapital.datalist.MenuDataList;
import com.tech.aicapital.datalist.MenuResponse;
import com.tech.aicapital.datalist.SubmenuDetailList;
import com.tech.aicapital.followups.AddFundActivity;
import com.tech.aicapital.followups.AddNewCustomerActivity;
import com.tech.aicapital.followups.DepositPaymentActivity;
import com.tech.aicapital.followups.MyPaymentListActivity;
import com.tech.aicapital.followups.WithdrawActivity;
import com.tech.aicapital.followups.fragment.HomePageFragment;
import com.tech.aicapital.followups.fragment.MainHomePageFragment;
import com.tech.aicapital.followups.fragment.MyTradeListFragment;
import com.tech.aicapital.fragment.CryptoDashboardFragment;
import com.tech.aicapital.fragment.HomeFragment;
import com.tech.aicapital.fragment.MyRewardFragment;
import com.tech.aicapital.fragment.TransactionHistoryFragment;
import com.tech.aicapital.fragment.WalletFragment;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.aicapital.mvps.Constant.USER_ID;
import static com.tech.aicapital.mvps.Constant.USER_MOBILE;
import static com.tech.aicapital.mvps.Constant.isLOGIN;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    BottomNavigationView navigation;
    Toolbar toolbar;
    RecyclerView recyclerView;
    MemberDataResponse memberDataRespons;
    TextView cart_badge;
    ExpandableListView expandableList;
    ExpandableListAdapterMenu mMenuAdapter;
    List<String> listDataHeader;
    HashMap<String, List<SubmenuDetailList>> listDataChild;
    List<SubmenuDetailList> submenuDetailLists;

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener=new
            BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.navigation_home:
                            mFragmentManager = getSupportFragmentManager();
                            mFragmentTransaction = mFragmentManager.beginTransaction();
                            mFragmentTransaction.replace(R.id.containerView, new MainHomePageFragment("")).commit();
                            return true;
                        case R.id.navigation_quiz:
                            mFragmentManager = getSupportFragmentManager();
                            mFragmentTransaction = mFragmentManager.beginTransaction();
                            mFragmentTransaction.replace(R.id.containerView, new MyTradeListFragment("")).commit();
                            return true;
                        case R.id.navigation_ngo:
                            mFragmentManager = getSupportFragmentManager();
                            mFragmentTransaction = mFragmentManager.beginTransaction();
                            mFragmentTransaction.replace(R.id.containerView, new CryptoDashboardFragment("","")).commit();
                            return true;
                        case R.id.navigation_notifications:
                            Intent intennt3=new Intent(MainActivity.this, AllArticlesActivity.class);
                            startActivity(intennt3);
                            return true;
                        case R.id.navigation_profile:
                            mFragmentManager = getSupportFragmentManager();
                            mFragmentTransaction = mFragmentManager.beginTransaction();
                            mFragmentTransaction.replace(R.id.containerView, new WalletFragment(memberDataRespons)).commit();
                            return true;
                    }

                    return false;
                }
            };
    GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    View hView;TextView tvName,tvUserId,textView,tvEditProfile;
    CircleImageView imageView;
    FrameLayout frameLayou;
    boolean isFirstTime=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Pref.getLoginStatus(MainActivity.this,isLOGIN,true);
        Pref.getValue(MainActivity.this,USER_ID,"0");
        Pref.getValue(MainActivity.this,USER_MOBILE,"0");
        memberDataRespons=null;
        expandableList= (ExpandableListView) findViewById(R.id.navigationmenu);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        hView =  navigationView.getHeaderView(0);
        tvName = (TextView) hView.findViewById(R.id.tvName);
        tvUserId = (TextView) hView.findViewById(R.id.tvUserId);
        imageView = (CircleImageView) hView.findViewById(R.id.imageView);
        textView = (TextView) hView.findViewById(R.id.textView);
        tvEditProfile = (TextView) hView.findViewById(R.id.tvEditProfile);

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        tvEditProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeStatusBarColorBlue();
//        configureGoogleClient();

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new MainHomePageFragment("")).commit();
//        mFragmentTransaction.replace(R.id.containerView, new CryptoDashboardFragment("","")).commit();

        getMenuList();
        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {

                String submenu=listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getSubMenuId();

                if(submenu.equalsIgnoreCase("1"))
                {
                    Intent intent=new Intent(MainActivity.this, EditProfileActivity.class);
                    startActivity(intent);
                    return false;
                }
                else if(submenu.equalsIgnoreCase("2"))
                {
                    Intent intent=new Intent(MainActivity.this, EditProfileActivity.class);
                    startActivity(intent);
                    return false;
                }
                else if(submenu.equalsIgnoreCase("3"))
                {
                    Intent intent=new Intent(MainActivity.this, MyBankDetailsActivity.class);
                    startActivity(intent);
                    return false;
                }
                else if(submenu.equalsIgnoreCase("4"))
                {
                    Intent intent=new Intent(MainActivity.this, ExpandableListViewActivity.class);
                    startActivity(intent);
                    return false;
                }
                else if(submenu.equalsIgnoreCase("7"))
                {
                    Intent intent=new Intent(MainActivity.this, DepositPaymentActivity.class);
                    startActivity(intent);
                    return false;
                } else if(submenu.equalsIgnoreCase("26"))
                {
                    Intent intent=new Intent(MainActivity.this, MyPaymentListActivity.class);
                    startActivity(intent);
                    return false;
                }
                else if(submenu.equalsIgnoreCase("8"))
                {
                    Intent intent=new Intent(MainActivity.this, WithdrawActivity.class);
                    startActivity(intent);
                    return false;
                }
                else if(submenu.equalsIgnoreCase("14"))
                {
                    Intent intent=new Intent(MainActivity.this, TypewiseTransactionHistoryActivity.class);
                    startActivity(intent);
                    return false;
                }
               else if(submenu.equalsIgnoreCase("10"))
                {
                    Intent intent=new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("url","http://agricoop.gov.in/");
                    intent.putExtra("name","GOVERNMENT SCHEMES");
                    startActivity(intent);
                    return false;
                }               else if(submenu.equalsIgnoreCase("11"))
                {
                    Intent intent=new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("url","http://agricoop.gov.in/");
                    intent.putExtra("name","GOVERNMENT SCHEMES");
                    startActivity(intent);
                    return false;
                }   else if(submenu.equalsIgnoreCase("12"))
                {
                    Intent intent=new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("url","http://agricoop.gov.in/");
                    intent.putExtra("name","GOVERNMENT SCHEMES");
                    startActivity(intent);
                    return false;
                }
               else if(submenu.equalsIgnoreCase("13"))
                {
                    logout();
                    return false;
                }
                return true;
            }
        });

    }

    private void showMenuList(List<MenuDataList> menuDataLists) {
        submenuDetailLists=new ArrayList<>();
        int[] myImageList = new int[]{R.drawable.ic_account_circle_black_24dp, R.drawable.ic_baseline_perm_identity_24};
        listDataChild = new HashMap<String, List<SubmenuDetailList>>();
        listDataHeader = new ArrayList<String>();
        for (int i=0;i<menuDataLists.size();i++)
        {
            listDataChild.put(menuDataLists.get(i).getMenuName(),
                    menuDataLists.get(i).getSubmenuDetails());
            listDataHeader.add(menuDataLists.get(i).getMenuName());
            submenuDetailLists.addAll(menuDataLists.get(i).getSubmenuDetails());
        }


        DynamicMenuAdapter transactionAdapter = new DynamicMenuAdapter(getApplicationContext(),
                "",submenuDetailLists,true,
                new DynamicMenuAdapter.OnItemClickListener()
                {
                    @Override
                    public void onListItemClick(int position)
                    {


                        if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("1"))
                        {
                            Intent intent=new Intent(MainActivity.this, EditProfileActivity.class);
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("2"))
                        {
                            Intent intent=new Intent(MainActivity.this, EditProfileActivity.class);
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("3"))
                        {
                            Intent intent=new Intent(MainActivity.this, MyBankDetailsActivity.class);
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("4"))
                        {
                            Intent intent=new Intent(MainActivity.this, ExpandableListViewActivity.class);
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("7"))
                        {
                            Intent intent=new Intent(MainActivity.this, DepositPaymentActivity.class);
                            startActivity(intent);

                        } else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("26"))
                        {
                            Intent intent=new Intent(MainActivity.this, MyPaymentListActivity.class);
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("8"))
                        {
                            Intent intent=new Intent(MainActivity.this, WithdrawActivity.class);
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("14"))
                        {
                            Intent intent=new Intent(MainActivity.this, TypewiseTransactionHistoryActivity.class);
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("10"))
                        {
                            Intent intent=new Intent(MainActivity.this, WebViewActivity.class);
                            intent.putExtra("url","http://agricoop.gov.in/");
                            intent.putExtra("name","GOVERNMENT SCHEMES");
                            startActivity(intent);

                        }               else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("11"))
                        {
                            Intent intent=new Intent(MainActivity.this, WebViewActivity.class);
                            intent.putExtra("url","http://agricoop.gov.in/");
                            intent.putExtra("name","GOVERNMENT SCHEMES");
                            startActivity(intent);

                        }   else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("12"))
                        {
                            Intent intent=new Intent(MainActivity.this, WebViewActivity.class);
                            intent.putExtra("url","http://agricoop.gov.in/");
                            intent.putExtra("name","GOVERNMENT SCHEMES");
                            startActivity(intent);

                        }
                        else if(submenuDetailLists.get(position).getSubMenuId().equalsIgnoreCase("13"))
                        {
                            logout();

                        }
                    }

                },
                new DynamicMenuAdapter.OnDoubleClickListener()
                {
                    @Override
                    public void onDetailsView(int position) {

                    }
                });
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(transactionAdapter);


    }


    public void showOrderBottomSheetDialogFragment() {

    }
    @Override
    protected void onResume() {

        if(Utility.isConnectedToInternet(MainActivity.this))
        {
            getMemberDetail(Pref.getValue(MainActivity.this,USER_ID,"0"));
        }
        if(isFirstTime){
        }
    else{
            getProductList();
        }

//        getProductList();
        super.onResume();
    }

    public void shovProductDetails(String shop_id, boolean isAdmin, String shop_name)
    {


    }
    public void getProductList()
    {
        List<CartDataList> cartDataLists=new ArrayList<>();
        cartDataLists=Pref.getFavorites(MainActivity.this);
        if(cartDataLists.size()>0)
        {
            cart_badge.setText(String.valueOf(cartDataLists.size()));
        }
        else cart_badge.setText("0");

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                isFirstTime = data.getBooleanExtra("isFirstTime",false);
            }
        }
    }
//    private void configureGoogleClient()
//    {
//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        googleSignInClient = GoogleSignIn.getClient(this, gso);
//        firebaseAuth = FirebaseAuth.getInstance();
//    }
    public void getMemberDetail(String userId)
    {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<MemberDataResponse> call = apiService.member_details(userId);
        call.enqueue(new Callback<MemberDataResponse>() {
            @Override
            public void onResponse(Call<MemberDataResponse> call, Response<MemberDataResponse> response) {
                MemberDataResponse memberDataResponse = response.body();
                memberDataRespons=memberDataResponse;
                if (memberDataResponse != null)
                {
                    if(memberDataResponse.isStatus())
                    {

//                        tvUserId.setText(memberDataResponse.getData().get(0).getUserId());
                        textView.setText(memberDataResponse.getData().get(0).getUserMobile());
                        tvName.setText(memberDataResponse.getData().get(0).getUserName());
                        String prourl=memberDataResponse.getMessage()+memberDataResponse.getData().get(0).getProfileImg();
                        Picasso.with(MainActivity.this).load(prourl).placeholder(R.drawable.defa).into(imageView);
                        if(memberDataResponse.getData().get(0).getUserMobile().equalsIgnoreCase("1"))
                        {
                            Intent intent=new Intent(MainActivity.this,EditProfileActivity.class);
                            startActivity(intent);
                        }
                    }
                else{
                        Utility.showRedMessage(MainActivity.this,memberDataResponse.getMessage());
                    }
                 } else
                    Toast.makeText(getApplicationContext(),"Some error occur", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<MemberDataResponse> call, Throwable t) {

                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void getMenuList()
    {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<MenuResponse> call = apiService.getMenuList();
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                MenuResponse memberDataResponse2 = response.body();

                if (memberDataResponse2 != null)
                {
                    if(memberDataResponse2.isStatus())
                    {
                       showMenuList(memberDataResponse2.getData());
                    }

                 } else
                    Toast.makeText(getApplicationContext(),"Some error occur", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {

                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Utility.showRedMessage(MainActivity.this, "CLICK 2 TIME BACK TO EXIT");
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 1000);
    }
    public void logout()
    {
        Pref.setLoginStatus(MainActivity.this,isLOGIN,false);
        Pref.setValue(MainActivity.this,USER_ID,"0");
        Pref.setValue(MainActivity.this,USER_MOBILE,"0");
        googleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });

        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void showScratch()
    {

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new MyRewardFragment("","")).commit();
        changeStatusBargreen();
    }
    private void changeStatusBarColorBlue()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#0088ff"));
        }
        navigation.setBackgroundColor(Color.parseColor("#EEEEEE"));
        toolbar.setBackgroundColor(Color.parseColor("#0088ff"));
//        toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"));
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // set status text dark
    }
    private void changeStatusBargreen()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#0088FF"));
        }

        toolbar.setBackgroundColor(Color.parseColor("#0088ff"));
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = menuItem.getActionView();
        cart_badge = (TextView) actionView.findViewById(R.id.cart_badge);
        frameLayou = (FrameLayout) actionView.findViewById(R.id.frameLayou);

        cart_badge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showOrderBottomSheetDialogFragment();
            }
        });
        frameLayou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showOrderBottomSheetDialogFragment();
            }
        });
        getProductList();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"Current  have",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_scratchcard) {
            showScratch();
            return true;
        }
        else if (id == R.id.action_showvideo) {
            callWhatsappp();
            return true;
        }
        else if (id == R.id.action_cart) {
            showOrderBottomSheetDialogFragment();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void callWhatsappp() {

    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
