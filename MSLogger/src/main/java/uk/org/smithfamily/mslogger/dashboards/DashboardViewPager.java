package uk.org.smithfamily.mslogger.dashboards;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class DashboardViewPager extends ViewPager
{
    private boolean editMode = false;

    /**
     *
     */
    public DashboardViewPager(final Context context, final AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     *
     */
    public DashboardViewPager(final Context context)
    {
        super(context);
    }

    /**
     *
     */

    public boolean onInterceptTouchEvent(final MotionEvent arg0)
    {
        return !editMode;
    }

    /**
     * @return true if we are in edit mode, false otherwise
     */
    public boolean isEditMode()
    {
        return editMode;
    }

    /**
     * Set the current edit mode state
     * 
     * @param editMode The new edit mode state
     */
    public void setEditMode(final boolean editMode)
    {
        this.editMode = editMode;
    }

}
