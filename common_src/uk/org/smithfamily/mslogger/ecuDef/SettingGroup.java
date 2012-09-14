package uk.org.smithfamily.mslogger.ecuDef;

import java.util.ArrayList;
import java.util.List;

public class SettingGroup
{

    private String name;
    private String description;
    private List<SettingOption> options = new ArrayList<SettingOption>();

    public class SettingOption
    {
        private String flag;
        private String description;

        public SettingOption(String flag,String description)
        {
            this.flag = flag;
            this.description = description;
        }

        public String getFlag()
        {
            return flag;
        }

        public String getDescription()
        {
            return description;
        }
    }
    public SettingGroup(String name,String description)
    {
        this.name = name;
        this.description = description;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public void addOption(String flag,String description)
    {
        SettingOption option = new SettingOption(flag,description);
        options.add(option);
    }
    public List<SettingOption> getOptions()
    {
        return options;
    }
 }
