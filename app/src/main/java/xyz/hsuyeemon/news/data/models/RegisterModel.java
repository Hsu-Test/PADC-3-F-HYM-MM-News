package xyz.hsuyeemon.news.data.models;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xyz.hsuyeemon.news.data.vo.LoginUserVO;
import xyz.hsuyeemon.news.events.SuccessRegisterEvent;
import xyz.hsuyeemon.news.network.NewsDataAgent;
import xyz.hsuyeemon.news.network.RetrofitDataAgent;

/**
 * Created by Dell on 1/25/2018.
 */

public class RegisterModel {

    private static RegisterModel mModel;
    private NewsDataAgent mDataAgent;
    private LoginUserVO mUser;
    private RegisterModel(){

        mDataAgent = RetrofitDataAgent.getsObjInstance();
        EventBus.getDefault().register(this);

    }

    public static RegisterModel getObjectInstance(){
        if(mModel == null){
            mModel = new RegisterModel();
        }
        return mModel;
    }

    public void registerUser(String name,String phoneNo, String password){
        mDataAgent.registerUser(name,phoneNo,password);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getRegisterUser(SuccessRegisterEvent event){
        mUser = event.getmRegiterUser();
    }
}
