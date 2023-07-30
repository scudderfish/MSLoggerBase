package uk.org.smithfamily.mslogger.dashboards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

/**
 *
 */
public class DashboardPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener
{
    private final Context context;
    private final List<Dashboard> dashboards;
    private final DashboardViewPager parent;

    public DashboardPagerAdapter(final Context c, final DashboardViewPager parent)
    {
        this.context = c;
        this.parent = parent;
        dashboards = DashboardIO.INSTANCE.loadDash();
    }

    @Override
    public int getCount()
    {
        return dashboards.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup collection, final int position)
    {
        final Dashboard d = dashboards.get(position);
        final DashboardView dvg = new DashboardView(context, position, parent, d);

        dvg.setDashboard(d);

        collection.addView(dvg, 0);

        return dvg;
    }

    @Override
    public void destroyItem(@NonNull final View collection, final int position, @NonNull final Object v)
    {

        final ViewPager viewPager = (ViewPager) collection;
        final View view = (View) v;

        viewPager.removeView(view);
    }

    @Override
    public boolean isViewFromObject(@NonNull final View view, @NonNull final Object o)
    {
        return view == o;
    }

    @Override
    public void onPageScrollStateChanged(final int arg0)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(final int arg0, final float arg1, final int arg2)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(final int arg0)
    {
        // TODO Auto-generated method stub

    }
}