package uk.org.smithfamily.mslogger.comms;

import uk.org.smithfamily.mslogger.ApplicationSettings;
import uk.org.smithfamily.mslogger.ecuDef.InjectedCommand;
import uk.org.smithfamily.mslogger.ecuDef.Megasquirt;

/**
 * Class to send command to the SD card 
 */
public class MS3SDCard
{    
    private Megasquirt ecu;

    public MS3SDCard()
    {
        this.ecu = ApplicationSettings.INSTANCE.getEcuDefinition();
    }
    
    /**
     * SD do command (w 00 00)
     * 
     * Send w <canid> 11 00 00 00 01 XX
     * 
     * Where XX is:
     *   00 Reset and return to normal
     *   01 Reset and wait
     *   02 Stop logging
     *   03 Start logging
     *   04 Put status into buffer
     *   05 Re-initialise card
     */
    public void doCommand()
    {
        
    }
    
    /**
     * SD fetch buffer command (r 00 00)
     * 
     * Send: r <canid> 11 00 00 aa bb
     * 
     * Return aabb bytes from buffer. Used by all read commands.
     */
    public void fetchBuffer()
    {
        
    }
    
    /**
     * SD status command
     * 
     * Send: w <canid> 11 00 00 00 01 04
     * Send: r <canid> 11 00 00 00 10
     * 
     * Returns: 16 bytes of info
     *   Byte 0 = Card status (same as outpc.sd_status)
     *   bit 0: 0=No card, 1=Card present
     *   bit 1: 0=SD, 1=SDHC
     *   bit 2: 0=Not Ready, 1=Ready
     *   bit 3: 0=Not logging, 1=Logging
     *   bit 4: 0=No error, 1=Error
     *   bit 5: 0=V1.x, 1=V2.0 card
     *   bit 6: 0=FAT16, 1=FAT32
     *   bit 7: 0=normal, 1=WTF enabled (this may change)
     *   Byte 1 = Error code
     *   Bytes 2-3 = Sector size
     *   Bytes 4-7 = Card size in sectors
     *   Bytes 8-9 = No. files in directory
     *   Bytes 10-13 = Sector number of directory
     *   Bytes 14-15 = undefined
     * 
     * Note! Only use the status command when the card is already idle. Read outpc.sd_status first.
     */
    public void status()
    {
        byte[] commandWrite = { (byte) 'w', 00, 11, 00, 00, 00, 01, 04 };
        InjectedCommand writeToRAM = new InjectedCommand(commandWrite, 300, true, Megasquirt.MS3_SD_CARD_STATUS_WRITE);
        ecu.injectCommand(writeToRAM);
        
        byte[] commandRead = { (byte) 'r', 00, 11, 00, 00, 00, 10 };
        writeToRAM = new InjectedCommand(commandRead, 300, true, Megasquirt.MS3_SD_CARD_STATUS_READ);
        ecu.injectCommand(writeToRAM);
    }
    
    /**
     * SD read directory command
     * 
     * Send: w <canid> 11 00 01 00 02 <U16 directory chunk>
     * Send: r <canid> 11 00 00 02 02
     * 
     * Returns: a sector with 32 bytes per file in root directory, plus U16 chunk number
     *   Bytes 0-10 = 8.3 filename, space padded as per FAT directory
     *   Byte 11 = 0=ignore, 1=file
     *   Bytes 12-15 = undefined
     *   Bytes 16-23 = absolute sector number (big endian)
     *   Bytes 24-31 = file size _in_bytes_ (little endian direct from media)
     * 
     * Where the directory is longer than 32 entries, multiple reads will be required, chunk 0, 1, etc.
     * 
     * Note 1. The format is similar to the FAT16 directory structure, but MS3 returns sector number
     * instead of cluster number. Non MS3 log files are ignored and not reported.
     * 
     * Note 2. All MS3 log files are created by MS3 as contiguous files. If these files are disturbed from
     * the PC end and made non contiguous, data corruption on the SDcard will occur as the firmware
     * does not support fragmentation due to the severe speed penalty it would incurr.
     * 
     * Note 3. directory chunk no. starts at 0.
     * 
     */
    public void readDirectory()
    {
        
    }

    /**
     * SD read sector command
     * 
     * Send: w <canid> 11 00 02 00 04 <U32 sector number>
     * Send: r <canid> 11 00 00 02 04
     * 
     * <sector data....> <U32 sector number>
     * 
     * Returns: Sector size bytes of data + sector number.
     * 
     * Sector size is always 512 bytes, can check with command 00 00
     */
    public void readSector()
    {
        
    }
    
    /**
     * SD write sector command
     * 
     * Send: w <canid> 11 00 03 02 04 <sector data......> <U32 sector number>
     * 
     * The data sent is a full sector and then the 4 bytes of sector number. Note 02 above is for 512 byte sector.
     * 
     * Used incorrectly this command could corrupt the data on SDcard as it permits re-writing any area of the device (including MBR, FAT, directories etc.)
     */
    public void writeSector()
    {
        
    }
    
    /**
     * SD read stream command
     * 
     * Send: w <canid> 11 00 04 00 01 01
     * 
     * Then 8bit data from the selected stream ADC input is continuously sent by serial. Power cycle the MS3 to stop.
     * 
     * Note! Returned data is raw and not newserial packetised.
     */
    public void readStream()
    {
        
    }
    
    /**
     * SD read compressed file command
     * 
     * Send: w <canid> 11 00 05 00 08 <U32 sector number> <U32 sector count>
     * Send: r <canid> 14 <U16 block num> 08 00
     * 
     * Returns packet with payload: <U08 ok> <U16 block no.> <....data bytes....>
     * 
     * This will be 2048 bytes of data until the last block of data, so normally 2057 bytes total packet size.
     * Block number increments each time starting from zero. (A future release will allow the same block
     * to be requested again in case of a transmission error.)
     */
    public void readCompressedFile()
    {
        
    }
    
    /**
     * SD erase file command
     * 
     * Send: w <canid> 11 00 06 00 06 <ascii 4 byte file number> <U16 start dirblock>
     * 
     * For filename LOG0002.MS3 send ascii '0' '0' '0' '2'. (48, 48, 48, 50)
     * 
     * Sending the actual directory block no. that the file entry appears in will speed up the deletion.
     * 
     * Otherwise use zero to force code to find it. MS3 will delete the directory entry and the FAT chain.
     * 
     * Returns nothing.
     * 
     * Busy bit will be set during operation. Poll for completion.
     */
    public void eraseFile()
    {

    }
    
    /**
     * SD speed test command
     *
     * Send: w <canid> 11 00 07 00 04 <U32 sector number> <U32 num sectors>
     * 
     * The code will blindy erase the sectors you request.
     * Poll outpc.sd_status until card is not busy.
     * 
     * Send: r <canid> 11 00 00 00 0d
     * 
     * Returns:
     *   U32 sector down count (use for progress meter)
     *   U32 total time 0.1ms units
     *   U16 minimum time
     *   U16 maximum time
     *   U08 stat 0 = running 1 = done 2 = error
     */
    public void speedTest()
    {
        
    }
    
    /**
     * RTC read command
     * 
     * Send: w <canid> 11 00 08 00 08
     * Send: r <canid> 11 00 00 02 02
     * 
     * Returns 8 bytes: <U08 sec> <U08 min> <U08 hr> <U08 date> <U08 mon> <U16 year>
     * 
     * Reads the local or CAN realtime clock
     */
    public void RTCRead()
    {
        
    }
    
    /**
     * RTC write command
     * 
     * Send: w <canid> 11 00 09 00 08 <U08 sec> <U08 min> <U08 hr> <U08 date> <U08 mon> <U16 year>
     * 
     * Sets the (local) realtime clock
     */
    public void RTCWrite()
    {
        
    }
}
