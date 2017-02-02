package eadjlib.logger.outputs;

import eadjlib.logger.Log_Levels;
import eadjlib.logger.Log_TimeStamp;

import java.io.PrintWriter;
import java.io.StringWriter;

import static eadjlib.logger.outputs.ANSI_Colours.*;


/**
 * Colour formatter for console output (UNIX SHELL/Cygwin)
 */
public class Formatter_TERM_COLOR implements Formatter_Interface {
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
        String log_level_coloured = Log_Levels.txtLevels[log_level];
        switch (log_level) {
            case Log_Levels.FATAL:
                log_level_coloured = RED + log_level_coloured + RESET;
                break;
            case Log_Levels.ERROR:
                log_level_coloured = PURPLE + log_level_coloured + RESET;
                break;
            case Log_Levels.WARNING:
                log_level_coloured = YELLOW + log_level_coloured + RESET;
                break;
            case Log_Levels.MSG:
                break;
            case Log_Levels.DEBUG:
                log_level_coloured = GREEN + log_level_coloured + RESET;
                break;
            case Log_Levels.TRACE:
                log_level_coloured = GREY + log_level_coloured + RESET;
                break;

        }

        String s = String.format("%s[%5d]%s %s - %s %s + %s[%s]%s ",
                BLUE,
                log_number,
                RESET,
                time_stamp.getDate(),
                time_stamp.getTime(),
                log_level_coloured,
                CYAN,
                origin_name,
                RESET
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
        String s = String.format("%s[%5d]%s\t%s===%sException raised in %s[%s]%s%s===%s",
                BLUE,
                log_number,
                RESET,
                BROWN,
                RESET,
                CYAN,
                origin_name,
                RESET,
                BROWN,
                RESET
        );
        s += System.lineSeparator() + "\t";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        s += sw + System.lineSeparator();
        return s;
    }
}
