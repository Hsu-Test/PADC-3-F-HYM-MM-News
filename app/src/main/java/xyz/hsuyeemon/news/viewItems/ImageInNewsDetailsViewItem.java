package xyz.hsuyeemon.news.viewItems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.hsuyeemon.news.R;

/**
 * Created by Dell on 12/10/2017.
 */

public class ImageInNewsDetailsViewItem extends FrameLayout {

    @BindView(R.id.iv_news_detail_image)
    ImageView ivNewsDetailImage;

    public ImageInNewsDetailsViewItem(@NonNull Context context) {
        super(context);
    }

    public ImageInNewsDetailsViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageInNewsDetailsViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        ButterKnife.bind(this,this);

    }
    public void setData(String imageUrl){
        Glide.with(ivNewsDetailImage.getContext())
                .load(imageUrl)
                .into(ivNewsDetailImage);

    }
}
