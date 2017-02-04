package eadjlib.logger.outputs;

import eadjlib.logger.Log_TimeStamp;

/**
 * Outputs to GUI log view window
 */
public class Output_WINDOW extends Output {
    private Log_Window_Interface window;

    /**
     * Constructor
     *
     * @param name Output name
     */
    public Output_WINDOW(String name) {
        super(name, GlobalOutputTypes.WINDOW);
    }

    /**
     * Sets the window to send the log messages to
     *
     * @param window Log-view window
     */
    public void setWindowOutput(Log_Window_Interface window) {
        this.window = window;
    }

    /**
     * @param output_name Output name
     */
    @Override
    public void setName(String output_name) {
        super.setName(output_name);
    }

    /**
     * @return
     */
    @Override
    public String getOutputName() {
        return super.getOutputName();
    }

    /**
     * @return
     */
    @Override
    public GlobalOutputTypes getOutputType() {
        return super.getOutputType();
    }

    /**
     * @param origin_name Origin name of the call
     * @param log_level   Log level
     * @param log_number  Message number in session
     * @param time_stamp  Time stamp of the message
     * @param objects     Message description (not used in "Output" parent class)
     */
    @Override
    public void output(String origin_name, int log_level, Long log_number, Log_TimeStamp time_stamp, Object... objects) {
        if (window != null) {
            window.append(origin_name, log_level, log_number, time_stamp, objects);
        }
    }

    /**
     * @param origin_name Origin name of the call
     * @param time_stamp  Time stamp of the message
     * @param log_number  Message number in session
     * @param e           Exception raised
     */
    @Override
    public void output(String origin_name, Log_TimeStamp time_stamp, Long log_number, Exception e) {
        if (window != null) {
            window.append(origin_name, time_stamp, log_number, e);
        }
    }
}
