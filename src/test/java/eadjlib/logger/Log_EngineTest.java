package eadjlib.logger;

import eadjlib.logger.outputs.Output;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Log_EngineTest {

    @Test(expected = NullPointerException.class)
    public void testGetInstanceEx() {
        Log_Engine.load(null);
        Log_Engine.getInstance();
    }

    @Test
    public void testLoad() throws Exception {
        Log_Engine engine = new Log_Engine(new Log_Config());
        Log_Engine.load(engine);
        assertEquals(engine, Log_Engine.getInstance());
    }

    @Test
    public void testGetInstance() throws Exception {
        Log_Engine engine = new Log_Engine(new Log_Config());
        Log_Engine.load(engine);
        assertEquals(engine, Log_Engine.getInstance());
    }

    @Test
    public void testProcessLogMsg() throws Exception {
        Log_Config mocked_config = mock(Log_Config.class);
        when(mocked_config.getOutputs()).thenReturn(new Vector<Output>());
        when(mocked_config.getGlobalLogLevel()).thenReturn(Log_Levels.DEBUG);
        Log_Engine engine = new Log_Engine(mocked_config);
        Log_TimeStamp ts = new Log_TimeStamp();
        engine.processLogMsg(ts, Log_Levels.WARNING, "Log_EngineTest", "Description..");
        verify(mocked_config, times(1)).getOutputs();
        when(mocked_config.getGlobalLogLevel()).thenReturn(Log_Levels.OFF);
        engine.processLogMsg(ts, Log_Levels.WARNING, "Log_EngineTest", "Description..");
        verify(mocked_config, times(1)).getOutputs();
    }

    @Test
    public void testProcessException() throws Exception {
        Log_Config mocked_config = mock(Log_Config.class);
        when(mocked_config.getOutputs()).thenReturn(new Vector<Output>());
        Log_Engine engine = new Log_Engine(mocked_config);
        Log_TimeStamp ts = new Log_TimeStamp();

        when(mocked_config.getLogExceptionFlag()).thenReturn(true);
        when(mocked_config.getGlobalLogLevel()).thenReturn(Log_Levels.OFF);
        engine.processException(ts, "Log_EngineTest", new Exception(""));
        verify(mocked_config, times(0)).getOutputs();

        when(mocked_config.getGlobalLogLevel()).thenReturn(Log_Levels.DEBUG);
        engine.processException(ts, "Log_EngineTest", new Exception(""));
        verify(mocked_config, times(1)).getOutputs();

        when(mocked_config.getLogExceptionFlag()).thenReturn(false);
        engine.processException(ts, "Log_EngineTest", new Exception(""));
        verify(mocked_config, times(1)).getOutputs();
    }
}