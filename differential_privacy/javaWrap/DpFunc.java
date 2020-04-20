import java.util.ArrayList;
import java.util.List;


public class DpFunc {

    static {
        // load library
        System.loadLibrary("dpjava");

        System.out.println("\n***********************************************");
        System.out.println("Loaded dpjava library");
        System.out.println("***********************************************\n");
    }

    // native methods

    /**
     * Native C++ method that calls the DpCount Algorithm
     * @param doubleArray the dataset
     * @param default_epsilon a flag used to determine whether it's necessary to use or not the default epsilon
     * @param epsilon the custom epsilon (used only if default_epsilon is false)
     * @return
     */
    public native int DpCount(double[] doubleArray, boolean default_epsilon, double epsilon);

    /**
     * Native C++ method that calls the DpSum Algorithm
     * @param doubleArray the dataset
     * @param default_epsilon a flag used to determine whether it's necessary to use or not default epsilon
     * @param auto_bounds a flag used to determine whether it's necessary to calculate bounds or not
     * @param epsilon custom epsilon (used only if default_epsilon is false)
     * @param lower custom lower bound (used only if auto_bounds is false)
     * @param upper custom upper bound (used only if auto_bounds is false)
     * @return
     */
    public native double DpSum(double[] doubleArray, boolean default_epsilon, boolean auto_bounds, double epsilon, double lower, double upper);

    public native double DpMean(double[] doubleArray, boolean default_epsilon, boolean auto_bounds, double epsilon, double lower, double upper);

    public native double DpVariance(double[] doubleArray, boolean default_epsilon, boolean auto_bounds, double epsilon, double lower, double upper);

    public native double DpStd(double[] doubleArray, boolean default_epsilon, boolean auto_bounds, double epsilon, double lower, double upper);

    public native double DpNtile(double[] doubleArray, double percentile, double lower, double upper, boolean default_epsilon, double epsilon);

    // main
    public static void main(String[] args) {
        DpFunc library = new DpFunc();
        int i = 0;
        List<Double> doubleList = new ArrayList<Double>();
        while( i < 10000) {
            doubleList.add(Math.random());
            i++;
        }

        // creating double array used by JNI
        double[] myNum = new double[doubleList.size()];
        i = 0;
        double sum = 0;
        while( i < doubleList.size()) {
            myNum[i] = doubleList.get(i);
            sum = sum + doubleList.get(i);
            i++;
        }
        i = 0;
        List<Integer> intList = new ArrayList<Integer>();
        while( i < 10000) {
            intList.add((int)(100*Math.random()));
            i++;
        }

        // creating int array used by JNI
        int[] myIntNum = new int[intList.size()];
        i = 0;
        int sumInt = 0;
        while( i < intList.size()) {
            myIntNum[i] = intList.get(i);
            sumInt = sumInt + intList.get(i);
            i++;
        }

        System.out.println("Count: " + myNum.length);

        System.out.println("DpCount: " + library.DiffPrivacyCount(myNum, 0.1));

        System.out.println("Sum: " + sum);

        System.out.println("DpSum: " + library.DiffPrivacySum(myNum));

        double mean = sum/myNum.length;
        System.out.println("Mean: " + mean);

        System.out.println("DpMean: " + library.DiffPrivacyMean(myNum, 0, 1));

        // diffPrivacy Double variance
        double var = 0;

        for( int n = 0; n < doubleList.size(); n++ )
        {
            var += (myNum[n] - mean) * (myNum[n] - mean);
        }
        var /= doubleList.size();

        System.out.println("Variance: " + var);

        System.out.println("DpVariance: " + library.DiffPrivacyVar(myNum, 5));

        System.out.println("Standard Deviation: " + Math.sqrt(var));

        System.out.println("Dp Standard Deviation: " + library.DiffPrivacyStd(myNum,0.1, 0, 1));

        System.out.println("Dp Ntile: " + library.DiffPrivacyNtile(myNum, 0.95, 0, 1));
    }

    /**
     * This method calls the Google's DpCount method over an array of numbers
     * with the default epsilon value.
     * @param doubleArray: the array of numbers in input
     * @return the result of the DpCount method
     */
    int DiffPrivacyCount(double[] doubleArray) {
        return this.DpCount(doubleArray, true, 0);
    }

    /**
     * This method calls the Google's DpCount method over an array of numbers
     * with a user-defined epsilon value.
     * @param doubleArray: the array of numbers in input
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpCount method
     */
    int DiffPrivacyCount(double[] doubleArray, double epsilon) {
        return this.DpCount(doubleArray, false, epsilon);
    }

    /**
     * The method calls the Google's DpSum method over an array of integers
     * with the default epsilon value.
     * @param intArray: the array of integers
     * @return the result of the DpSum method
     */
    int DiffPrivacySum(int[] intArray) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpSum(tempDouble, true, true, 0,0,0);
    }

    /**
     * The method calls the Google's DpSum method over an array of integers
     * with a user-defined epsilon value.
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacySum(int[] intArray, double epsilon) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpSum(tempDouble, false, true, epsilon,0,0);
    }

    /**
     * The method calls the Google's DpSum method over an array of integers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacySum(int[] intArray, double epsilon, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpSum(tempDouble, false, false, epsilon,lower,upper);
    }

    /**
     * The method calls the Google's DpSum method over an array of integers
     * with the default epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacySum(int[] intArray, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpSum(tempDouble, true, false, 0, lower, upper);
    }

    /**
     * The method calls the Google's DpSum method over an array of numbers
     * with the default epsilon value.
     * @param intArray: the array of integers
     * @return the result of the DpSum method
     */
    double DiffPrivacySum(double[] doubleArray) {
        return this.DpSum(doubleArray, true, true, 0, 0, 0);
    }

    /**
     * The method calls the Google's DpSum method over an array of numbers
     * with a user-defined epsilon value.
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpSum method
     */
    double DiffPrivacySum(double[] doubleArray, double epsilon) {
        return this.DpSum(doubleArray, false, true, epsilon, 0, 0);
    }

    /**
     * The method calls the Google's DpSum method over an array of numbers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    double DiffPrivacySum(double[] doubleArray, double epsilon, double lower, double upper) {
        return this.DpSum(doubleArray, false, false, epsilon, lower, upper);
    }

    /**
     * The method calls the Google's DpSum method over an array of numbers
     * with the default epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    double DiffPrivacySum(double[] doubleArray, double lower, double upper) {
        return this.DpSum(doubleArray, true, false, 0, lower, upper);
    }

    /**
     * The method calls the Google's DpMean method over an array of integers
     * with the default epsilon value.
     * @param intArray: the array of integers
     * @return the result of the DpSum method
     */
    int DiffPrivacyMean(int[] intArray) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpMean(tempDouble, true, true, 0,0,0);
    }

    /**
     * The method calls the Google's DpMean method over an array of integers
     * with a user-defined epsilon value.
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyMean(int[] intArray, double epsilon) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpMean(tempDouble, false, true, epsilon,0,0);
    }

    /**
     * The method calls the Google's DpMean method over an array of integers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyMean(int[] intArray, double epsilon, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpMean(tempDouble, false, false, epsilon,lower,upper);
    }

    /**
     * The method calls the Google's DpMean method over an array of integers
     * with the default epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyMean(int[] intArray, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpMean(tempDouble, true, false, 0, lower, upper);
    }
    /**
     * The method calls the Google's DpMean method over an array of numbers
     * with the default epsilon value.
     * @param doubleArray: the array of numbers
     * @return the result of the Mean method
     */
    double DiffPrivacyMean(double[] doubleArray) {
        return this.DpMean(doubleArray, true, true, 0, 0, 0);
    }

    /**
     * The method calls the Google's DpMean method over an array of numbers
     * with a user-defined epsilon value.
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the Mean method
     */
    double DiffPrivacyMean(double[] doubleArray, double epsilon) {
        return this.DpMean(doubleArray, false, true, epsilon, 0, 0);
    }

    /**
     * The method calls the Google's DpMean method over an array of numbers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the Mean method
     */
    double DiffPrivacyMean(double[] doubleArray, double epsilon, double lower, double upper) {
        return this.DpMean(doubleArray, false, false, epsilon, lower, upper);
    }

    /**
     * The method calls the Google's DpMean method over an array of numbers
     * with the default epsilon value and user-defined bounds for the algorithm
     * @param doubleArray: the array of numbers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the Mean method
     */
    double DiffPrivacyMean(double[] doubleArray, double lower, double upper) {
        return this.DpMean(doubleArray, true, false, 0, lower, upper);
    }

    /**
     * The method calls the Google's DpVariance method over an array of integers
     * with the default epsilon value.
     * @param intArray: the array of integers
     * @return the result of the DpSum method
     */
    int DiffPrivacyVar(int[] intArray) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpVariance(tempDouble, true, true, 0,0,0);
    }

    /**
     * The method calls the Google's DpVariance method over an array of integers
     * with a user-defined epsilon value.
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyVar(int[] intArray, double epsilon) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpVariance(tempDouble, false, true, epsilon,0,0);
    }

    /**
     * The method calls the Google's DpVariance method over an array of integers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyVar(int[] intArray, double epsilon, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpVariance(tempDouble, false, false, epsilon,lower,upper);
    }

    /**
     * The method calls the Google's DpVariance method over an array of integers
     * with the default epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyVar(int[] intArray, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpVariance(tempDouble, true, false, 0, lower, upper);
    }

    /**
     * The method calls the Google's DpVariance method over an array of numbers
     * with the default epsilon value.
     * @param doubleArray: the array of numbers
     * @return the result of the Variance method
     */
    double DiffPrivacyVar(double[] doubleArray) {
        return this.DpVariance(doubleArray, true, true, 0, 0, 0);
    }

    /**
     * The method calls the Google's DpVariance method over an array of numbers
     * with a user-defined epsilon value
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the Variance method
     */
    double DiffPrivacyVar(double[] doubleArray, double epsilon) {
        return this.DpVariance(doubleArray, false, true, epsilon, 0, 0);
    }

    /**
     * The method calls the Google's DpVariance method over an array of numbers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the Variance method
     */
    double DiffPrivacyVar(double[] doubleArray, double epsilon, double lower, double upper) {
        return this.DpVariance(doubleArray, false, false, epsilon, lower, upper);
    }

    /**
     * The method calls the Google's DpVariance method over an array of numbers
     * with the default epsilon value and user-defined bounds for the algorithm
     * @param doubleArray: the array of numbers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the Variance method
     */
    double DiffPrivacyVar(double[] doubleArray, double lower, double upper) {
        return this.DpVariance(doubleArray, true, false, 0, lower, upper);
    }

    /**
     * The method calls the Google's DpStd method over an array of integers
     * with the default epsilon value.
     * @param intArray: the array of integers
     * @return the result of the DpSum method
     */
    int DiffPrivacyStd(int[] intArray) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpStd(tempDouble, true, true, 0,0,0);
    }

    /**
     * The method calls the Google's DpStd method over an array of integers
     * with a user-defined epsilon value.
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyStd(int[] intArray, double epsilon) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpStd(tempDouble, false, true, epsilon,0,0);
    }

    /**
     * The method calls the Google's DpStd method over an array of integers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyStd(int[] intArray, double epsilon, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpStd(tempDouble, false, false, epsilon,lower,upper);
    }

    /**
     * The method calls the Google's DpStd method over an array of integers
     * with the default epsilon value and user-defined bounds for the algorithm
     * @param intArray: the array of integers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacyStd(int[] intArray, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpStd(tempDouble, true, false, 0, lower, upper);
    }

    /**
     * The method calls the Google's DpStandardDeviation method over an array of numbers
     * with the default epsilon value
     * @param doubleArray: the array of numbers
     * @return the result of the Standard Deviation method
     */
    double DiffPrivacyStd(double[] doubleArray) {
        return this.DpStd(doubleArray, true, true, 0, 0, 0);
    }

    /**
     * The method calls the Google's DpStandardDeviation method over an array of numbers
     * with a user-defined epsilon value
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the Standard Deviation method
     */
    double DiffPrivacyStd(double[] doubleArray, double epsilon) {
        return this.DpStd(doubleArray, false, true, epsilon, 0, 0);
    }

    /**
     * The method calls the Google's DpStandardDeviation method over an array of numbers
     * with a user-defined epsilon value and user-defined bounds for the algorithm
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the Standard Deviation method
     */
    double DiffPrivacyStd(double[] doubleArray, double epsilon, double lower, double upper) {
        return this.DpStd(doubleArray, false, false, epsilon, lower, upper);
    }

    /**
     * The method calls the Google's DpStandardDeviation method over an array of numbers
     * with the default value and user-defined bounds for the algorithm
     * @param doubleArray: the array of numbers
     * @param lower: the lower bound used for the algorithm
     * @param upper: the upper bound used for the algorithm
     * @return the result of the Standard Deviation method
     */
    double DiffPrivacyStd(double[] doubleArray, double lower, double upper) {
        return this.DpStd(doubleArray, true, false, 0, lower, upper);
    }

    int DiffPrivacyNtile(int[] intArray, double percentile, double lower, double upper) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpNtile(tempDouble, percentile, lower, upper, true, 0);
    }

    int DiffPrivacyNtile(int[] intArray, double percentile, double lower, double upper, double epsilon) {
        double[] tempDouble = new double[intArray.length];
        for (int i=0; i < intArray.length; i++) {
            tempDouble[i] = intArray[i];
        }
        return (int)this.DpNtile(tempDouble, percentile, lower, upper, false, epsilon);
    }

    double DiffPrivacyNtile(double[] doubleArray, double percentile, double lower, double upper) {
        return this.DpNtile(doubleArray, percentile, lower, upper, true, 0);
    }

    double DiffPrivacyNtile(double[] doubleArray, double percentile, double lower, double upper, double epsilon) {
        return this.DpNtile(doubleArray, percentile, lower, upper, false, epsilon);
    }


}