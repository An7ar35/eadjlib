package eadjlib.logger.outputs;

import eadjlib.logger.Log_Levels;
import eadjlib.logger.Log_TimeStamp;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Formatter for console output
 */
public class Formatter_TERM implements Formatter_Interface {
    /**
     * Constructor
     */
    public Formatter_TERM() {
    }

    /**
     * Formats message information for console output
     *
     * @param origin_name Name of the message's origin
     * @param log_level   Log level
     * @param log_number  Message number in log session
     * @param time_stamp  Time stamp of the message
     * @param objects     Message details
     * @return Formatted string
     */
    @Override
    public String format(String origin_name, int log_level, Long log_number, Log_TimeStamp time_stamp, Object... objects) {
        String s = String.format("[%5d] %s - %s %s + [%s] ",
                log_number,
                time_stamp.getDate(),
                time_stamp.getTime(),
                Log_Levels.txtLevels[log_level],
                origin_name
        );
        for (Object o : objects) {
            s += o;
        }
        return s;
    }

    /**
     * Formats Exception information for console output
     *
     * @param origin_name Name of the message's origin
     * @param time_stamp  Time stamp of the message
     * @param log_number  Message number in log session
     * @param e           Exception raised
     * @return Formatted String
     */
    @Override
    public String format(String origin_name, Log_TimeStamp time_stamp, Long log_number, Exception e) {
        String s = String.format("[%5d]\t===Exception raised in [%s]===",
                log_number,
                origin_name
        );
        s += System.lineSeparator() + "\t";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        s += sw + System.lineSeparator();
        return s;
    }
}
