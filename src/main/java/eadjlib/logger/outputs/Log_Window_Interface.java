package eadjlib.logger.outputs;

import eadjlib.logger.Log_TimeStamp;

/**
 * Interface for a GUI log window
 */
public interface Log_Window_Interface {
    /**
     * Appends to the log view a log message
     *
     * @param origin_name Class name where message originated
     * @param log_level   Log level
     * @param log_number  Log message number
     * @param time_stamp  Time stamp
     * @param objects     Message
     */
    public void append(String origin_name, int log_level, Long log_number, Log_TimeStamp time_stamp, Object... objects);

    /**
     * Appends to the log view an exception
     *
     * @param origin_name Class name where message originated
     * @param time_stamp  Time stamp
     * @param log_number  Log message number
     * @param e           Exception
     */
    public void append(String origin_name, Log_TimeStamp time_stamp, Long log_number, Exception e);
}