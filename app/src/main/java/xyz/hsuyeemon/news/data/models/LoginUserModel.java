package xyz.hsuyeemon.news.data.models;

import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.hsuyeemon.news.data.vo.LoginUserVO;
import xyz.hsuyeemon.news.events.SuccessLoginEvent;
import xyz.hsuyeemon.news.network.NewsDataAgent;
import xyz.hsuyeemon.news.network.RetrofitDataAgent;
import xyz.hsuyeemon.news.utils.AppConstants;
import xyz.hsuyeemon.news.viewpods.LoginUserViewPod;

/**
 * Created by Dell on 1/21/2018.
 */

public class LoginUserModel {

    private static LoginUserModel sModel;
    private NewsDataAgent mDataAgent;
    private LoginUserVO mLoginUser;
    private LoginUserViewPod vpLoginUser;

    private  LoginUserModel(Context context){
        mDataAgent = RetrofitDataAgent.getsObjInstance();    //abstract
        EventBus.getDefault().register(this);
        SharedPreferences sharedPreferences = context .getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,
                Context.MODE_PRIVATE);
        int loginUserId=sharedPreferences.getInt(AppConstants.LOGIN_USER_ID,-1);
        if (loginUserId != -1){
            //user has already logged in.
            String name= sharedPreferences.getString(AppConstants.LOGIN_USER_NAME, null);
            String email=sharedPreferences.getString(AppConstants.LOGIN_USER_EMAIL,null);
            String phoneNo=sharedPreferences.getString(AppConstants.LOGIN_USER_PHONE_NO,null);
            String profileUrl=sharedPreferences.getString(AppConstants.LOGIN_USER_PROFILE_URL,null);
            String coverUrl=sharedPreferences.getString(AppConstants.LOGIN_USER_COVER_URL,null);
            mLoginUser = new LoginUserVO(loginUserId,name,email,phoneNo,profileUrl,coverUrl);
            //vpLoginUser.bindData(mLoginUser);
        }




    }

    public static LoginUserModel getObjInstance(Context context){
        if(sModel== null){
            sModel = new LoginUserModel(context);
        }
        return sModel;
    }

    public void loginUser(Context context, String phoneNo, String password){
        mDataAgent.loadLoginUser(context,phoneNo,password);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        mLoginUser = event.getLoginUser();

        // Save user data in SharedPreferences.
        SharedPreferences sharedPreferences =
                event.getContext().getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();  // start insert
        editor.putInt(AppConstants.LOGIN_USER_ID,event.getLoginUser().getUserId());
        editor.putString(AppConstants.LOGIN_USER_NAME,event.getLoginUser().getName());
        editor.putString(AppConstants.LOGIN_USER_EMAIL,event.getLoginUser().getEmail());
        editor.putString(AppConstants.LOGIN_USER_PHONE_NO,event.getLoginUser().getPhoneNo());
        editor.putString(AppConstants.LOGIN_USER_PROFILE_URL,event.getLoginUser().getProfileUrl());
        editor.putString(AppConstants.LOGIN_USER_COVER_URL,event.getLoginUser().getCoverUrl());

        //editor.commit();             //save immeditely
        editor.apply();  //save above data      // by background thread

    }

    public LoginUserVO getLoginUser(){
        return mLoginUser;
    }

    public boolean isUserLogin(){
        return mLoginUser!=null;
      /*  if(mLoginUser == null){
            return false;
        }
        else
            return true;
            */
    }

    public void logout(){
        mLoginUser = null;
    }


}
