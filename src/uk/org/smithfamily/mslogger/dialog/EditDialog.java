package uk.org.smithfamily.mslogger.dialog;

import uk.org.smithfamily.mslogger.R;
import uk.org.smithfamily.mslogger.ecuDef.MSDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditDialog extends Dialog implements android.view.View.OnClickListener
{
    private MSDialog dialog;
    
    public EditDialog(Context context, MSDialog dialog)
    {
        super(context);
        
        this.dialog = dialog;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.editdialog);
        
        setTitle(dialog.getLabel());
        
        Button buttonBurn = (Button) findViewById(R.id.burn);
        buttonBurn.setOnClickListener(this);
        
        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        int which = v.getId();
        
        if (which == R.id.burn)
        {
            
        }
        else if (which == R.id.cancel)
        {
            cancel();
        }
    }
}
