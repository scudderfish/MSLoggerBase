package uk.org.smithfamily.mslogger.dashboards;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class DashboardPagerAdapter extends PagerAdapter
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

    @Override
    public Object instantiateItem(final ViewGroup collection, final int position)
    {

        final DashboardView dvg = new DashboardView(context, position, parent);
        final Dashboard d = dashboards.get(position);

        dvg.setDashboard(d);

        ((ViewPager) collection).addView(dvg, 0);

        return dvg;
    }

    @Override
    public void destroyItem(final View collection, final int position, final Object v)
    {

        final ViewPager viewPager = (ViewPager) collection;
        final View view = (View) v;

        viewPager.removeView(view);
    }

    @Override
    public boolean isViewFromObject(final View view, final Object o)
    {
        return view == ((View) o);
    }
}