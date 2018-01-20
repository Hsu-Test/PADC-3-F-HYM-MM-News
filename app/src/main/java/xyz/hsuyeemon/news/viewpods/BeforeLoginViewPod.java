package xyz.hsuyeemon.news.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.delegates.BeforeLoginDelegate;

/**
 * Created by Dell on 1/20/2018.
 */

public class BeforeLoginViewPod extends RelativeLayout {

    private BeforeLoginDelegate mDelegate;


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
