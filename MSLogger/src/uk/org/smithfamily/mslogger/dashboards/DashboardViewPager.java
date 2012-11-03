package uk.org.smithfamily.mslogger.dashboards;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DashboardViewPager extends ViewPager
{
    private boolean editMode = false;

    public DashboardViewPager(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
    }

    public DashboardViewPager(final Context context)
    {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent arg0)
    {
        return !editMode;
    }

    public boolean isEditMode()
    {
        return editMode;
    }

    public void setEditMode(final boolean editMode)
    {
        this.editMode = editMode;
    }
}
