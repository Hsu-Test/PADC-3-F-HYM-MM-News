package xyz.hsuyeemon.news.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.hsuyeemon.news.data.vo.LoginUserVO;
import xyz.hsuyeemon.news.events.SuccessLoginEvent;
import xyz.hsuyeemon.news.network.NewsDataAgent;
import xyz.hsuyeemon.news.network.RetrofitDataAgent;

/**
 * Created by Dell on 1/21/2018.
 */

public class LoginUserModel {

    private static LoginUserModel sModel;
    private NewsDataAgent mDataAgent;
    private LoginUserVO mLoginUser;

    private  LoginUserModel(){
        mDataAgent = RetrofitDataAgent.getsObjInstance();    //abstract
        EventBus.getDefault().register(this);

    }

    public static LoginUserModel getObjInstance(){
        if(sModel== null){
            sModel = new LoginUserModel();
        }
        return sModel;
    }

    public void loginUser(String phoneNo, String password){
        mDataAgent.loadLoginUser(phoneNo,password);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        mLoginUser = event.getLoginUser();

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
