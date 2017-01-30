package eadjlib.logger;

import junit.framework.TestCase;
import eadjlib.logger.outputs.Output_TERM;
import eadjlib.logger.outputs.Output_TXT;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Log_ConfigTest extends TestCase {
    private Log_Config config = null;

    @Before
    public void setUp() throws Exception {
        config = new Log_Config();
    }

    @After
    public void tearDown() throws Exception {
        config = null;
    }

    @Test
    public void testGetGlobalLogLevel() throws Exception {
        assertEquals(config.getGlobalLogLevel(), Log_Levels.WARNING); //Default value
    }

    @Test
    public void testGetLogExceptionFlag() throws Exception {
        assertTrue(config.getLogExceptionFlag()); //Default value

        Log_Config mocked_config = mock(Log_Config.class);
        when(mocked_config.getLogExceptionFlag()).thenReturn(false);
        assertEquals(mocked_config.getLogExceptionFlag(), false);
    }

    @Test
    public void testGetOutputs() throws Exception {
        assertTrue(config.getOutputs().size() == 2);
        assertTrue(config.getOutputs().elementAt(0).getClass() == Output_TERM.class); //Default value
        assertTrue(config.getOutputs().elementAt(1).getClass() == Output_TXT.class); //Default value
    }
}