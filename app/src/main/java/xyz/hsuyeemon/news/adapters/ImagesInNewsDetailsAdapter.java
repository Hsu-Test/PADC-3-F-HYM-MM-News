package xyz.hsuyeemon.news.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.hsuyeemon.news.R;
import xyz.hsuyeemon.news.viewItems.ImageInNewsDetailsViewItem;

/**
 * Created by Dell on 12/10/2017.
 */

public class ImagesInNewsDetailsAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        /*
        if(object instanceof View)
            return true;
        else
            return false;
        */
      //  return (object instanceof View); //coz instanceof is performance expensive
        return (view==(View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context=container.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        ImageInNewsDetailsViewItem view= (ImageInNewsDetailsViewItem) layoutInflater.inflate(R.layout.item_news_details_images,container,false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
