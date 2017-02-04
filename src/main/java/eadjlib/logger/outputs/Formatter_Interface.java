package eadjlib.logger.outputs;

import eadjlib.logger.Log_TimeStamp;

/**
 * Formatter Interface
 */
public interface Formatter_Interface {
    /**
     * Formats a log line for the log output
     *
     * @param origin_name Class name where message originated
     * @param log_level   Log level
     * @param log_number  Log message number
     * @param time_stamp  Time stamp
     * @param objects     Message
     * @return Formatted string
     */
    String format(String origin_name, int log_level, Long log_number, Log_TimeStamp time_stamp, Object... objects);

    /**
     * Formats an exception for the log output
     *
     * @param origin_name Class name where message originated
     * @param time_stamp  Time stamp
     * @param log_number  Log message number
     * @param e           Exception
     * @return Formatted string
     */
    String format(String origin_name, Log_TimeStamp time_stamp, Long log_number, Exception e);
}
