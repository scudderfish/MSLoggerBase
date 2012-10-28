package uk.org.smithfamily.mslogger.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class IndicatorView extends SurfaceView implements SurfaceHolder.Callback
{

    private IndicatorThread thread;

    public IndicatorView(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        thread = new IndicatorThread(getHolder(), context);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        thread.setRunning(false);
        while (retry)
        {
            try
            {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
                // we will try it again and again...
            }
        }

    }

    class IndicatorThread extends Thread
    {
        private volatile boolean running = false;
        private Context context;
        private SurfaceHolder holder;
        private Resources resources;

        public IndicatorThread(SurfaceHolder holder, Context context)
        {
            this.holder = holder;
            this.context = context;
            this.resources = context.getResources();
        }
        public void setRunning(boolean r)
        {
            running = r;
        }
        
        @Override
        public void run()
        {
            Canvas c;
            while(running)
            {
                c = null;
                try
                {
                    c=holder.lockCanvas();
                    synchronized(holder)
                    {
                        drawIndicator(c);
                    }
                }
                finally
                {
                    if (c!= null)
                    {
                        holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }

    public void drawIndicator(Canvas c)
    {
        // TODO Auto-generated method stub
        
    }
}
