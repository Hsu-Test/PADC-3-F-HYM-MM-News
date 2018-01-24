package xyz.hsuyeemon.news.events;

import java.util.List;

import xyz.hsuyeemon.news.data.vo.LoginUserVO;

/**
 * Created by Dell on 1/21/2018.
 */

public class SuccessLoginEvent {  //always immutable

    private LoginUserVO loginUser;

    public SuccessLoginEvent(LoginUserVO loginUserList) {
        this.loginUser = loginUserList;
    }

    public LoginUserVO getLoginUser() {
        return loginUser;
    }
}






