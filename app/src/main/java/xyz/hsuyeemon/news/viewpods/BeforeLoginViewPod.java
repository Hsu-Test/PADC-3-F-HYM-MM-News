package xyz.hsuyeemon.news.viewpods;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.data.models.LoginUserModel;
import xyz.hsuyeemon.news.data.models.NewsModel;
import xyz.hsuyeemon.news.delegates.BeforeLoginDelegate;
import xyz.hsuyeemon.news.network.NewsDataAgent;

/**
 * Created by Dell on 1/20/2018.
 */

public class BeforeLoginViewPod extends RelativeLayout {

    private BeforeLoginDelegate mDelegate;
    private EditText etEmailOrPhone;
    private EditText etPassword;

    private NewsModel mModel;

    public BeforeLoginViewPod(Context context) {
        super(context);
    }

    public BeforeLoginViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeforeLoginViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);


    }

    public void setmDelegate(BeforeLoginDelegate delegate){  //will call from host activity
        mDelegate = delegate;
    }

    @OnClick(R.id.btn_to_login)
    public void onTapToLogin(View view){
        mDelegate.onTapToLogin();

    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View view){  //view => the item we clicked
        mDelegate.onTapToRegister();
    }


}
