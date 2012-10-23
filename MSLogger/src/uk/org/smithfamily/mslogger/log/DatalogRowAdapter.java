package uk.org.smithfamily.mslogger.log;

import java.util.ArrayList;
import java.util.List;

import uk.org.smithfamily.mslogger.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * Custom adapter which have two rows (datalog name and datalog size) + a checkbox
 */
public class DatalogRowAdapter extends BaseAdapter
{
    private DatalogRowAdapterCallback callback;
    
    private List<DatalogRow> datalogRows;

    private LayoutInflater mInflater;

    public DatalogRowAdapter(Context context, ArrayList<DatalogRow> results)
    {
        datalogRows = results;
        mInflater = LayoutInflater.from(context);
    }

    public void clear()
    {
        datalogRows.clear();
    }

    @Override
    public int getCount()
    {
        return datalogRows.size();
    }

    @Override
    public Object getItem(int position)
    {
        return datalogRows.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public boolean isItemSelected(int position)
    {
        return datalogRows.get(position).isSelected();
    }

    public List<DatalogRow> getAllSelected()
    {
        List<DatalogRow> selectedRows = new ArrayList<DatalogRow>();

        for (DatalogRow datalogRow : datalogRows)
        {
            if (datalogRow.isSelected())
            {
                selectedRows.add(datalogRow);
            }
        }

        return selectedRows;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.viewdatalog_row, null);

            holder = new ViewHolder();
            holder.txtDatalogName = (TextView) convertView.findViewById(R.id.datalog_name);
            holder.txtDatalogSize = (TextView) convertView.findViewById(R.id.datalog_size);
            holder.chkSelected = (CheckBox) convertView.findViewById(R.id.selected);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.chkSelected.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                datalogRows.get(position).setSelected(isChecked);

                callback.onDatalogSelected();
            }
        });

        convertView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CheckBox selected = (CheckBox) v.findViewById(R.id.selected);

                selected.setChecked(!selected.isChecked());

                datalogRows.get(position).setSelected(selected.isChecked());

                callback.onDatalogSelected();
            }
        });

        holder.txtDatalogName.setText(datalogRows.get(position).getDatalogName());
        holder.txtDatalogSize.setText(datalogRows.get(position).getDatalogSize());
        holder.chkSelected.setChecked(datalogRows.get(position).isSelected());

        return convertView;
    }

    public void setCallback(DatalogRowAdapterCallback callback)
    {
        this.callback = callback;
    }
    
    class ViewHolder
    {
        TextView txtDatalogName;
        TextView txtDatalogSize;
        CheckBox chkSelected;
    }
}