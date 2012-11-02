package uk.org.smithfamily.mslogger.dashboards;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;


public class DashboardPagerAdapter extends PagerAdapter
{
    private Context context;
    private List<Dashboard> dashboards;

    public DashboardPagerAdapter(Context c)
    {
        this.context = c;
        dashboards = DashboardIO.INSTANCE.loadDash();
        
    }
    @Override
    public int getCount()
    {
        return dashboards.size();
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position)
    {
 
        DashboardView dvg = new DashboardView(context,position);
        Dashboard d = dashboards.get(position);
        
        dvg.setDashboard(d);
        
        ((ViewPager) collection).addView(dvg, 0);

        return dvg;
    }

    @Override
    public void destroyItem(View collection, int position, Object v)
    {
        
        ViewPager viewPager = (ViewPager) collection;
        View view = (View) v;
        
        viewPager.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object o)
    {
        return view == ((View) o);
    }
}