package eadjlib.logger.outputs;

import eadjlib.logger.Log_Levels;
import eadjlib.logger.Log_TimeStamp;
import static eadjlib.logger.outputs.ANSI_Colours.*;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class Formatter_TERM_COLORTest {
    @Test
    public void format() throws Exception {
        Formatter_TERM_COLOR formatter = new Formatter_TERM_COLOR();
        LocalDateTime now = LocalDateTime.now();
        Log_TimeStamp ts = new Log_TimeStamp(now);
        String expected = BLUE + "[  100]" + RESET + " " + ts.getDate() + " - " + ts.getTime() + " " + YELLOW + Log_Levels.txtLevels[3] + RESET + " + " + CYAN + "[Formatter_TERMTest]" + RESET + " a description message.";
        String returned = formatter.format("Formatter_TERMTest", 3, new Long(100), ts, "a description message.");
        assertEquals(expected, returned);
    }

    @Test
    public void format1() throws Exception {
        Formatter_TERM_COLOR formatter = new Formatter_TERM_COLOR();
        LocalDateTime now = LocalDateTime.now();
        Log_TimeStamp ts = new Log_TimeStamp(now);
        Exception e = new IOException("Exception message");
        String expected = BLUE + "[  100]" + RESET + "\t" + BROWN + "===" + RESET + "Exception raised in " + CYAN + "[Formatter_TERMTest]" + RESET + BROWN + "===" + RESET + System.lineSeparator() + "\t";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        expected += sw.toString() + System.lineSeparator();
        String returned = formatter.format("Formatter_TERMTest", ts, new Long(100), e);
        assertEquals(returned, expected);
    }

}