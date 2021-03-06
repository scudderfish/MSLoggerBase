package uk.org.smithfamily.mslogger.ecuDef;

import java.util.ArrayList;
import java.util.List;

public class MSDialog
{
    private String name = "";
    private String label = "";
    private String axis = "";
    
    private List<DialogField> fieldsList = new ArrayList<DialogField>();
    private List<DialogPanel> panelsList = new ArrayList<DialogPanel>();

    public MSDialog(String name, String label, String axis)
    {
        this.name = name;
        this.label = label;
        this.axis = axis;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getAxis()
    {
        return axis;
    }

    public void setAxis(String axis)
    {
        this.axis = axis;
    }

    public List<DialogField> getFieldsList() {
        return fieldsList;
    }
    
    public void addField(DialogField field) {
        this.fieldsList.add(field);
    }
    
    public List<DialogPanel> getPanelsList() {
        return panelsList;
    }
    
    public void addPanel(DialogPanel panel) {
        this.panelsList.add(panel);
    }
    
    @Override
    public String toString()
    {
        return "MSDialog [name=" + name + ", label=" + label + ", axis=" + axis + ", fieldsList=" + fieldsList + ", panelsList=" + panelsList + "]";
    }
}
