package com.example.vehiclemanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_REF_NAME= "volleyregisterlogin";
    private static final String KEY_ID="keyid";
    private static final String KEY_USERNAME="keyusername";
    private static final String KEY_PASSWORD="keypassword";
    private static final String KEY_IMAGE ="keyimage";
    private static final String KEY_ROLE="keyrole";
    private static final String KEY_NAME="keyname";
    private static final String KEY_ADDRESS="keyaddress";
    private static final String KEY_PHONE="keyphone";
    private static SharedPrefManager mInstance;
    private static Context ctx;
    private SharedPrefManager(Context context){
        ctx=context;
    }
    public static synchronized SharedPrefManager getInstance(Context context){
        if(mInstance==null){
            mInstance=new SharedPrefManager(context);
        }
        return mInstance;
    }
    public void userLogin(User user, AccountDetail detail)
    {
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_REF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(KEY_ID,user.getMa_tk());
        editor.putString(KEY_USERNAME,user.getTen_tk());
        editor.putString(KEY_PASSWORD,user.getMat_khau());
        editor.putString(KEY_IMAGE,user.getImage());
        editor.putString(KEY_ROLE,user.getQuyen());
        editor.putString(KEY_NAME,detail.getTen());
        editor.putString(KEY_ADDRESS,detail.getDia_chi());
        editor.putString(KEY_PHONE,detail.getSdt());
        editor.apply();
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_REF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,null) != null;
    }
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_REF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_IMAGE, null),
                sharedPreferences.getString(KEY_ROLE, null)
        );
    }
    public AccountDetail getDetail(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_REF_NAME, Context.MODE_PRIVATE);
        return new AccountDetail(
                sharedPreferences.getString(KEY_NAME,null),
                sharedPreferences.getString(KEY_ADDRESS,null),
                sharedPreferences.getString(KEY_PHONE,null)
        );
    }
    public void logout(){
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_REF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx,LoginActivity.class));
    }
}
