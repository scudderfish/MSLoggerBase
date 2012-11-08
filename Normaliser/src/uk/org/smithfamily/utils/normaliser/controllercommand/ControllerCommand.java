package uk.org.smithfamily.utils.normaliser.controllercommand;

public class ControllerCommand
{
    private String name = "";
    private String command = "";
    
    public ControllerCommand(String name, String command)
    {
        this.name = name;
        this.command = command;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getCommand()
    {
        return command;
    }
    
    public void setCommand(String command)
    {
        this.command = command;
    }

    @Override
    public String toString()
    {
        return "ControllerCommand [name=" + name + ", command=" + command + "]";
    }
}
