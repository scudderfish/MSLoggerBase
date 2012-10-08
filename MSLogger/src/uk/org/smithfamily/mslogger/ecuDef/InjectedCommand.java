package uk.org.smithfamily.mslogger.ecuDef;

/**
 * A class to encapulate a command to the ECU to inject into the flow over the serial link
 * @author dgs
 *
 */
public class InjectedCommand
{
    private byte[] command;
    private boolean returnResult;
    private int resultId;
    private int delay;

    /**
     * 
     * @param command       The command to send to the ECU
     * @param delay         ms to wait after sending the command before attempting any read
     * @param returnResult  true if we expect the ECU to reply
     * @param resultId      An ID to apply to the result
     */
    public InjectedCommand(byte[] command, int delay, boolean returnResult, int resultId)
    {
        this.command = command;
        this.delay = delay;
        this.returnResult = returnResult;
        this.resultId = resultId;
    }

    public byte[] getCommand()
    {
        return command;
    }

    public boolean isReturnResult()
    {
        return returnResult;
    }

    public int getResultId()
    {
        return resultId;
    }

    public int getDelay()
    {
        return delay;
    }
}
