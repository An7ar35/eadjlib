package eadjlib.logger.outputs;

import eadjlib.logger.Log_TimeStamp;
import eadjlib.logger.fileIO.FileOutput;
import eadjlib.logger.microLogger.MicroLogger;

import java.io.IOException;

/**
 * Output to '.csv' file
 */
public class Output_CSV extends Output {
    private Formatter_Interface formatter;
    private FileOutput out = null;

    /**
     * Constructor
     *
     * @param output_name Name of output
     * @param formatter   Output formatter to use
     * @throws IOException when creating a FileOutput fails
     */
    public Output_CSV(String output_name, Formatter_Interface formatter) throws IOException {
        super(output_name, GlobalOutputTypes.CSV);
        this.formatter = formatter;
        try {
            out = new FileOutput("logs", output_name + ".csv");
        } catch (IOException e) {
            MicroLogger.INSTANCE.log_Error("IOException raised in [Output_CSV.Output_CSV( ", output_name, " )]");
            MicroLogger.INSTANCE.log_ExceptionMsg(e);
            throw e;
        }
    }

    /**
     * Constructor
     *
     * @param output    Name of output
     * @param formatter Output formatter to use
     * @throws IOException when creating a FileOutput fails
     */
    public Output_CSV(FileOutput output, Formatter_Interface formatter) throws IOException {
        super(output.getFileName(), GlobalOutputTypes.CSV);
        this.formatter = formatter;
        out = output;
    }

    /**
     * Sets the name of the output
     *
     * @param output_name Output name
     */
    @Override
    public void setName(String output_name) {
        super.setName(output_name);
    }

    /**
     * Gets the name of the output
     *
     * @return Output name
     */
    @Override
    public String getOutputName() {
        return super.getOutputName();
    }

    /**
     * Get the type of the output
     *
     * @return Output type
     */
    @Override
    public GlobalOutputTypes getOutputType() {
        return super.getOutputType();
    }

    /**
     * Outputs message to a CSV file
     *
     * @param origin_name Origin name of the call
     * @param log_level   Log level
     * @param log_number  Message number in session
     * @param time_stamp  Time stamp of the message
     * @param objects     Message description (not used in "Output" parent class)
     */
    @Override
    public void output(String origin_name, int log_level, Long log_number, Log_TimeStamp time_stamp, Object... objects) {
        try {
            out.appendString(formatter.format(origin_name, log_level, log_number, time_stamp, objects));
        } catch (IOException e) {
            MicroLogger.INSTANCE.log_Error("IOException raised in [Output_CSV.output( ", origin_name, ", ", log_level, ", ", log_number, ", ", time_stamp, ", ", objects, " )]");
            MicroLogger.INSTANCE.log_ExceptionMsg(e);
        }
    }

    /**
     * Outputs exception information to a CSV file
     *
     * @param origin_name Name of the message's origin
     * @param time_stamp  Time stamp of the message
     * @param log_number  Message number in session
     * @param e           Exception raised
     */
    @Override
    public void output(String origin_name, Log_TimeStamp time_stamp, Long log_number, Exception e) {
        try {
            out.appendString(formatter.format(origin_name, time_stamp, log_number, e));
        } catch (IOException exception) {
            MicroLogger.INSTANCE.log_Error("IOException raised in [Output_CSV.output( ", origin_name, ", ", time_stamp, " )] <-Output Exception version.");
            MicroLogger.INSTANCE.log_ExceptionMsg(exception);
        }
    }
}
