package eadjlib.logger.outputs;

import eadjlib.logger.Log_Levels;
import eadjlib.logger.Log_TimeStamp;
import eadjlib.logger.fileIO.FileInput;
import eadjlib.logger.fileIO.FileOutput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Output_TXTTest {
    private Output_TXT out = null;
    FileOutput mocked_file_out = null;

    @Before
    public void setUp() throws Exception {
        mocked_file_out = mock(FileOutput.class);
        out = new Output_TXT(mocked_file_out, new Formatter_TXT());
    }

    @After
    public void tearDown() throws Exception {
        out = null;
    }

    @Test
    public void testSetName() throws Exception {
        out.setName("a_file_name.txt");
        assertEquals(out.getOutputName(), "a_file_name.txt");
    }

    @Test
    public void testGetOutputName() throws Exception {
        out.setName("a_file_name.txt");
        assertEquals(out.getOutputName(), "a_file_name.txt");
    }

    @Test
    public void testGetOutputType() throws Exception {
        assertEquals(out.getOutputType(), GlobalOutputTypes.TXT);
    }

    @Test
    public void testOutput() throws Exception {
        LocalDateTime ltd = LocalDateTime.now();
        Log_TimeStamp ts = new Log_TimeStamp(ltd);
        String expected = "[  100] " + ts.getDate() + " - " + ts.getTime() + " " + Log_Levels.txtLevels[4] + " [Output_TERMTest] Description message." + System.lineSeparator();
        out.output("Output_TERMTest", 4, new Long(100), ts, "Description message.");
        verify(mocked_file_out).appendString( expected );
    }

    @Test
    public void testOutputException() throws Exception {
        LocalDateTime ltd = LocalDateTime.now();
        Log_TimeStamp ts = new Log_TimeStamp(ltd);
        Exception e = new IOException("Some exception message.");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        out.output("OutputTest", ts, new Long(100), e);
        String expected = "[  100]\t===Exception raised in [OutputTest] at " + ts.getDate() + " - " + ts.getTime() + "===" + System.lineSeparator() + "\t" + sw.toString() + System.lineSeparator();
        verify(mocked_file_out).appendString(expected);
    }

    @Test
    public void testHardOutput() throws  Exception {
        Output_TXT file_out = new Output_TXT( "FileOutputTest", new Formatter_TXT() );
        LocalDateTime ltd = LocalDateTime.now();
        Log_TimeStamp ts = new Log_TimeStamp(ltd);

        file_out.output("Output_TXTTest", 4, new Long(100), ts, "Description message.");

        List<String> expected_lines = new ArrayList<>();
        expected_lines.add( "[  100] " + ts.getDate() + " - " + ts.getTime() + " " + Log_Levels.txtLevels[4] + " [Output_TXTTest] Description message." );

        FileInput file_in = new FileInput( "logs", "FileOutputTest.txt" );
        List<String> in = file_in.read();
        file_in.deleteFile();

        Assert.assertEquals( "File content is as expected.", expected_lines.get(0), in.get(0));
    }
}