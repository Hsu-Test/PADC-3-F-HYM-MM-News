package xyz.hsuyeemon.news.events;

import xyz.hsuyeemon.news.data.vo.LoginUserVO;

/**
 * Created by Dell on 1/25/2018.
 */

public class SuccessRegisterEvent {

    private LoginUserVO mRegisterUser;

    public SuccessRegisterEvent(LoginUserVO registerUser){
        mRegisterUser = registerUser;
    }

    public LoginUserVO getmRegiterUser() {
        return mRegisterUser;
    }
}
