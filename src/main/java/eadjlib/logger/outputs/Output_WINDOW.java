package eadjlib.logger.outputs;

import eadjlib.logger.Log_TimeStamp;

public class Output_WINDOW extends Output {
    private Formatter_Interface formatter;

    /**
     * Constructor
     *
     * @param name Output name
     */
    public Output_WINDOW(String name, Formatter_Interface formatter) {
        super(name, GlobalOutputTypes.WINDOW);
        this.formatter = formatter;
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
        super.output(origin_name, log_level, log_number, time_stamp, objects);
    }

    /**
     * @param origin_name Origin name of the call
     * @param time_stamp  Time stamp of the message
     * @param log_number  Message number in session
     * @param e           Exception raised
     */
    @Override
    public void output(String origin_name, Log_TimeStamp time_stamp, Long log_number, Exception e) {
        super.output(origin_name, time_stamp, log_number, e);
    }
}
