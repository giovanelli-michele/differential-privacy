import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.expressions.MutableAggregationBuffer;
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.*;


public class DpSum extends UserDefinedAggregateFunction {

    /**
     * This method creates a DpSum algorithm with the parameters set by user
     * @param default_epsilon if true, must use default epsilon
     * @param auto_bounds if true, must use auto bounds
     * @param epsilon custom epsilon value (default_epsilon=false)
     * @param lower custom lower bound (auto_bounds=false)
     * @param upper custom upper bound (auto_bounds=false)
     * @return true if algorithm is created, false otherwise
     */
    public native boolean createAlgorithm(boolean default_epsilon, boolean auto_bounds, double epsilon, double lower, double upper);

    /**
     * This method adds a double element into the algorithm
     * @param element the element to add
     * @return true if element is added, false otherwise
     */
    public native boolean insertElement(double element);

    /**
     * This method tries to regain the result of DpSum
     * @return the DpSum result
     */
    public native double getAlgorithmResult();

    /**
     * Epsilon value
     */
    private double epsilon = 0;

    /**
     * Lower bound value
     */
    private double lower = 0;

    /**
     * Upper bound value
     */
    private double upper = 0;

    /**
     * This constructor loads the c++ library
     * and creates an instance of the algorithm
     * with the default epsilon value and auto-bounds.
     */
    public DpSum() {
        System.loadLibrary("dpspark");

        System.out.println("\n***********************************************");
        System.out.println("Loaded dpspark library");
        System.out.println("***********************************************\n");

        boolean initAlgo = this.createAlgorithm(true, true, 0, 0, 0);
        System.out.println((initAlgo) ? "Algorithm created with default epsilon and auto bounds!\n" : "Error during algorithm creation\n");
    }

    /**
     * This constructor sets the epsilon value, loads the c++ library
     * and creates an instance of the algorithm with auto bounds.
     * @param eps the epsilon set by user
     */
    public DpSum(double eps) {
        System.out.println("Setting epsilon to " + eps);
        epsilon = eps;

        System.loadLibrary("dpspark");

        System.out.println("\n***********************************************");
        System.out.println("Loaded dpspark library");
        System.out.println("***********************************************\n");

        boolean initAlgo = this.createAlgorithm(false, true, epsilon, 0, 0);
        System.out.println((initAlgo) ? "Algorithm created with auto-bounds!\n" : "Error during algorithm creation\n");
    }


    /**
     * This constructor loads the c++ library
     * and creates an instance of the algorithm
     * with the default epsilon value.
     */
    public DpSum(double low, double up) {
        System.out.println("Setting bounds to [" + low + ", " + up + "]");
        lower = low;
        upper = up;
        System.loadLibrary("dpspark");

        System.out.println("\n***********************************************");
        System.out.println("Loaded dpspark library");
        System.out.println("***********************************************\n");

        boolean initAlgo = this.createAlgorithm(true, false, 0, lower, upper);
        System.out.println((initAlgo) ? "Algorithm created with default epsilon!\n" : "Error during algorithm creation\n");
    }

    /**
     * This constructor loads the c++ library
     * and creates an instance of the algorithm
     * with the default epsilon value.
     */
    public DpSum(double eps, double low, double up) {
        System.out.println("Setting epsilon to " + eps);
        epsilon = eps;
        System.out.println("Setting bounds to [" + low + ", " + up + "]");
        lower = low;
        upper = up;
        System.loadLibrary("dpspark");

        System.out.println("\n***********************************************");
        System.out.println("Loaded dpspark library");
        System.out.println("***********************************************\n");

        boolean initAlgo = this.createAlgorithm(false, false, epsilon, lower, upper);
        System.out.println((initAlgo) ? "Algorithm created!\n" : "Error during algorithm creation\n");
    }

    /**
     * This method describes the schema of input to the UDAF. Since, we are implementing
     * the UDAF for DpSum which require a single double value,
     * we will set an "input" field of type Double.
     * @return the input schema defined
     */
    public StructType inputSchema() {
        List<StructField> inputFields = new ArrayList<>();
        inputFields.add(DataTypes.createStructField("input", DataTypes.DoubleType, true));
        return DataTypes.createStructType(inputFields);
    }

    /**
     * This method describes the schema of UDAF buffer. Since we are not
     * using buffers, we create an empty List.
     * @return the buffer schema defined
     */
    public StructType bufferSchema() {
        List<StructField> bufferFields = new ArrayList<>();
        return DataTypes.createStructType(bufferFields);
    }

    /**
     * This method describes the output data type. Since we are counting
     * objects, the result is an Integer value.
     * @return the type of the output data
     */
    public DataType dataType() {
        return DataTypes.DoubleType;
    }

    /**
     * This describes whether the UDAF we are implementing is deterministic or not.
     * If the UDAF logic is such that the result is independent of the order in which data is
     * processed and combined then the UDAF is deterministic.
     * @return true if deterministic, false otherwise
     */
    public boolean deterministic() {
        return false;
    }

    /**
     * This method is used to initialize the buffer for each node of
     * Spark cluster. This method is not implemented as we do not use buffers.
     * @param buffer the buffer
     */
    public void initialize(MutableAggregationBuffer buffer) {
    }

    /**
     * The method validates the input and adds it to the algorithm.
     * As we accept only numeric type: if data is not a double, we throw an
     * InvalidInputValueException
     * @param buffer the buffer (not used)
     * @param input the input value
     */
    public void update(MutableAggregationBuffer buffer, Row input) {
        boolean done;
        double element = 0.0;
        if (!input.isNullAt(0)) {
            try {
                element = (double)input.get(0);

                if (element == 0) {
                    throw new InvalidInputValuesException("Input not valid.");
                }
                done = this.insertElement(element);

                if (!done) {
                    System.out.println("Error while adding element to algorithm...");
                }
            } catch (ClassCastException e) {

            }

        } else {
            throw new InvalidInputValuesException("Input not valid.");
        }
    }

    /**
     * The method merges two different buffers. Not used in our application
     * @param buffer1 first buffer
     * @param buffer2 second buffer
     */
    public void merge(MutableAggregationBuffer buffer1, Row buffer2) {

    }

    /**
     * This method is called when all processing is complete and
     * there is only one buffer left. The method will regain the algorithm
     * result.
     * @param buffer the final buffer
     * @return the result of the algorithm
     */
    public Double evaluate(Row buffer) {
        return this.getAlgorithmResult();
    }

}