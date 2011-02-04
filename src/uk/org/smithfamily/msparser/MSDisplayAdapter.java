package uk.org.smithfamily.msparser;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MSDisplayAdapter extends BaseAdapter
{
    private Context  context;
    private String[] texts = { "aaa", "bbb", "ccc", "ddd", "eee", "fff", "eee", "hhh", "iii" };

    public MSDisplayAdapter(Context context)
    {
        this.context = context;
    }

    public int getCount()
    {
        // TODO Auto-generated method stub
        return 6;
    }

    public Object getItem(int arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int arg0)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView tv;
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/ziska.ttf");
        if (convertView == null) {
            tv = new TextView(context);
            tv.setLayoutParams(new GridView.LayoutParams(85, 85));
        }
        else {
            tv = (TextView) convertView;
        }
        
        tv.setTypeface(font);
        tv.setHeight(200);
        tv.setText(texts[position]);
        return tv;
    }

}
