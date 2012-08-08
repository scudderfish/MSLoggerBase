package uk.org.smithfamily.mslogger.ecuDef;

import java.util.ArrayList;
import java.util.List;

public class MSDialog
{
    private String name = "";
    private String label = "";
    
    private List<DialogField> fieldsList = new ArrayList<DialogField>();
    private List<DialogPanel> panelsList = new ArrayList<DialogPanel>();

    public MSDialog(String name, String label)
    {
        this.name = name;
        this.label = label;
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

    public String generateCode()
    {
        String output = String.format("d = new MSDialog(\"%s\",\"%s\");\n", name, label);
        
        for (DialogField df : fieldsList)
        {
            output += df.generateCode();
        }

        for (DialogPanel dp : panelsList)
        {
            output += dp.generateCode();
        }

        return output;
    }
    
    @Override
    public String toString()
    {
        return "MSDialog [name=" + name + ", label=" + label + ", fieldsList=" + fieldsList + ", panelsList=" + panelsList + "]";
    }   
}
