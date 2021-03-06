package eadjlib.logger.outputs;

import eadjlib.logger.Log_Levels;
import eadjlib.logger.Log_TimeStamp;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;

public class Formatter_TERMTest {

    @Test
    public void testFormat() throws Exception {
        Formatter_TERM formatter = new Formatter_TERM();
        LocalDateTime now = LocalDateTime.now();
        Log_TimeStamp ts = new Log_TimeStamp(now);
        String expected = "[  100] " + ts.getDate() + " - " + ts.getTime() + " " + Log_Levels.txtLevels[3] + " + [Formatter_TERMTest] a description message.";
        String returned = formatter.format("Formatter_TERMTest", 3, new Long(100), ts, "a description message.");
        assertEquals(expected, returned);
    }

    @Test
    public void testFormat_Exception() throws Exception {
        Formatter_TERM formatter = new Formatter_TERM();
        LocalDateTime now = LocalDateTime.now();
        Log_TimeStamp ts = new Log_TimeStamp(now);
        Exception e = new IOException("Exception message");
        String expected = "[  100]\t===Exception raised in [Formatter_TERMTest]===" + System.lineSeparator() + "\t";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        expected += sw.toString() + System.lineSeparator();
        String returned = formatter.format("Formatter_TERMTest", ts, new Long(100), e);
        assertEquals(expected, returned);
    }
}
