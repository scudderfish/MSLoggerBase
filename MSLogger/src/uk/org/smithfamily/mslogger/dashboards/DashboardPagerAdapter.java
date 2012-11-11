package uk.org.smithfamily.mslogger.dashboards;

import java.util.List;

import android.content.Context;
import android.support.v4.view.*;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class DashboardPagerAdapter extends PagerAdapter implements OnPageChangeListener
{
    private final Context context;
    private final List<Dashboard> dashboards;
    private final DashboardViewPager parent;

    /**
     * 
     * @param c
     * @param parent
     */
    public DashboardPagerAdapter(final Context c, final DashboardViewPager parent)
    {
        this.context = c;
        this.parent = parent;
        dashboards = DashboardIO.INSTANCE.loadDash();
    }

    /**
     * 
     * @return
     */
    @Override
    public int getCount()
    {
        return dashboards.size();
    }

    /**
     * 
     * @param collection
     * @param position
     * 
     * @return
     */
    @Override
    public Object instantiateItem(final ViewGroup collection, final int position)
    {
        final DashboardView dvg = new DashboardView(context, position, parent);
        final Dashboard d = dashboards.get(position);

        dvg.setDashboard(d);

        ((ViewPager) collection).addView(dvg, 0);

        return dvg;
    }

    /**
     * 
     * @param collection
     * @param position
     * @param v
     */
    @Override
    public void destroyItem(final View collection, final int position, final Object v)
    {

        final ViewPager viewPager = (ViewPager) collection;
        final View view = (View) v;

        viewPager.removeView(view);
    }

    /**
     * 
     * @param view
     * @param o
     * 
     * @return
     */
    @Override
    public boolean isViewFromObject(final View view, final Object o)
    {
        return view == ((View) o);
    }

    /**
     * 
     * @param arg0
     */
    @Override
    public void onPageScrollStateChanged(final int arg0)
    {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     */
    @Override
    public void onPageScrolled(final int arg0, final float arg1, final int arg2)
    {
        // TODO Auto-generated method stub

    }

    /**
     * 
     * @param arg0
     */
    @Override
    public void onPageSelected(final int arg0)
    {
        // TODO Auto-generated method stub

    }
}