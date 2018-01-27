package xyz.hsuyeemon.news.events;

import android.content.Context;

import java.util.List;

import xyz.hsuyeemon.news.data.vo.LoginUserVO;

/**
 * Created by Dell on 1/21/2018.
 */

public class SuccessLoginEvent {  //always immutable   should initialize in constructor

    private LoginUserVO loginUser;
    private Context context;

    public SuccessLoginEvent(LoginUserVO loginUserList,Context context) {
        this.loginUser = loginUserList;
        this.context = context;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }

    public Context getContext() {
        return context;
    }
}






