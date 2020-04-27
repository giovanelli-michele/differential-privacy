import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.expressions.MutableAggregationBuffer;
import org.apache.spark.sql.expressions.UserDefinedAggregateFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.*;


public class DpCount extends UserDefinedAggregateFunction {

    /**
     * This method creates a DpCount algorithm with the epsilon-value decided by user
     * @param default_epsilon if true, must use default epsilon
     * @param epsilon custom epsilon value (default_epsilon=false)
     * @return true if algorithm is created, false otherwise
     */
    public native boolean createAlgorithm(boolean default_epsilon, double epsilon);

    /**
     * This method adds a double element into the algorithm
     * @param element the element to add
     * @return true if element is added, false otherwise
     */
    public native boolean insertElement(double element);

    /**
     * This method tries to regain the result of DpCount
     * @return the DpCount result
     */
    public native int getAlgorithmResult();

    /**
     * Epsilon value
     */
    private double epsilon = 0;


    /**
     * This constructor sets the epsilon value, loads the c++ library
     * and creates an instance of the algorithm.
     * @param eps the epsilon set by user
     */
    public DpCount(double eps) {
        System.out.println("Setting epsilon to " + eps);
        epsilon = eps;

        System.loadLibrary("dpspark");

        System.out.println("\n***********************************************");
        System.out.println("Loaded dpspark library");
        System.out.println("***********************************************\n");

        boolean initAlgo = this.createAlgorithm(false, epsilon);
        System.out.println((initAlgo) ? "Algorithm created!!\n" : "Error during algorithm creation\n");
    }

    /**
     * This constructor loads the c++ library
     * and creates an instance of the algorithm
     * with the default epsilon value.
     */
    public DpCount() {
        System.loadLibrary("dpspark");

        System.out.println("\n***********************************************");
        System.out.println("Loaded dpspark library");
        System.out.println("***********************************************\n");

        boolean initAlgo = this.createAlgorithm(true, 0);
        System.out.println((initAlgo) ? "Algorithm created with default epsilon!!\n" : "Error during algorithm creation\n");
    }

    /**
     * This method describes the schema of input to the UDAF. Since, we are implementing
     * the UDAF for DpCount which require a single value no matter about the type,
     * we do not set anything inside.
     * @return the input schema defined
     */
    public StructType inputSchema() {
        List<StructField> inputFields = new ArrayList<>();
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
        return DataTypes.IntegerType;
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
     * As we accept any type of data, if data is not a double, we generate a random
     * double value instead of the element (we only count it, not use it for other calcs)
     * @param buffer the buffer (not used)
     * @param input the input value
     */
    public void update(MutableAggregationBuffer buffer, Row input) {
        boolean done;
        double element = 0.0;
        try{
            element = input.getDouble(0);
        } catch (ClassCastException e) {
            element = Math.random();
        }

        done = this.insertElement(element);

        if (!done) {
            System.out.println("Error while adding element to algorithm...");
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
    public Integer evaluate(Row buffer) {
        return this.getAlgorithmResult();
    }

}