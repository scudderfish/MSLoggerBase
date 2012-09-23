package uk.org.smithfamily.mslogger.ecuDef;

import java.util.ArrayList;
import java.util.List;

public class SettingGroup
{

    private String              name;
    private String              description;
    private List<SettingOption> options = new ArrayList<SettingOption>();

    public class SettingOption
    {
        private String       flag;
        private String       description;
        private SettingGroup group;

        public SettingOption(SettingGroup group, String flag, String description)
        {
            this.group = group;
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

        public SettingGroup getGroup()
        {
            return group;
        }

        @Override
        public String toString()
        {
            return description;
        }
    }

    public SettingGroup(String name, String description)
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

    public void addOption(String flag, String description)
    {
        SettingOption option = new SettingOption(this, flag, description);
        options.add(option);
    }

    public List<SettingOption> getOptions()
    {
        return options;
    }
}
