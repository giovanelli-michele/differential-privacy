import java.util.ArrayList;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Dp_func {
    static {
        String path = Dp_func.class.getResource("libdp-func.so").toString();
        String current = "";
        try {
            current = new java.io.File( "." ).getCanonicalPath();

            // At this point, we extracted the file we needed.
            extractContentFromJar(path, current);

            // loading dp-func
            System.load(current + "/lib/libdp-func.so");
            System.out.println("\n***********************************************");
            System.out.println("Loaded dp-func library");
            System.out.println("***********************************************\n");
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static void extractContentFromJar(String path, String current) throws Exception {
        InputStream in = null;
        OutputStream out = null;

        try {
            URL url = new URL(path);
            JarURLConnection  conn = (JarURLConnection)url.openConnection();
            JarFile jarfile = conn.getJarFile();
            JarEntry jarEntry = conn.getJarEntry();
            in = new BufferedInputStream(jarfile.getInputStream(jarEntry));
            File file = new File(current + "/lib");
            file.mkdir();

            // libdp-func.so exported to lib folder
            out = new BufferedOutputStream(new FileOutputStream("lib/libdp-func.so"));
            byte[] buffer = new byte[2048];
            for (;;)  {
                int nBytes = in.read(buffer);
                if (nBytes <= 0) break;
                out.write(buffer, 0, nBytes);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    private native int DpCount(double[] doubleArray);
    private native int DpCount(double[] doubleArray, double epsilon);

    private native int DpSum(int[] intArray);
    private native int DpSum(int[] intArray, double epsilon);
    private native int DpSum(int[] intArray, double epsilon, double lower, double upper);
    private native int DpSum(int[] intArray, double lower, double upper);

    private native double DpSum(double[] doubleArray);
    private native double DpSum(double[] doubleArray, double epsilon);
    private native double DpSum(double[] doubleArray, double epsilon, double lower, double upper);
    private native double DpSum(double[] doubleArray, double lower, double upper);

    private native double DpMean(double[] doubleArray);
    private native double DpMean(double[] doubleArray, double epsilon);
    private native double DpMean(double[] doubleArray, double epsilon, double lower, double upper);
    private native double DpMean(double[] doubleArray, double lower, double upper);

    private native double DpVariance(double[] doubleArray);
    private native double DpVariance(double[] doubleArray, double epsilon);
    private native double DpVariance(double[] doubleArray, double epsilon, double lower, double upper);
    private native double DpVariance(double[] doubleArray, double lower, double upper);

    private native double DpStd(double[] doubleArray);
    private native double DpStd(double[] doubleArray, double epsilon);
    private native double DpStd(double[] doubleArray, double epsilon, double lower, double upper);
    private native double DpStd(double[] doubleArray, double lower, double upper);

    private native int DpNtile(int[] intArray, double percentile, double lower, double upper);
    private native int DpNtile(int[] intArray, double percentile, double lower, double upper, double epsilon);
    private native double DpNtile(double[] doubleArray, double percentile, double lower, double upper);
    private native double DpNtile(double[] doubleArray, double percentile, double lower, double upper, double epsilon);

    private native void setEpsilon(double epsilon);

    public static void main(String[] args) {
        // creating Data Double ArrayList
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

        // creating Int Data ArrayList
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

        // Dp_func creation
        Dp_func libfunc = new Dp_func();
        double epsilon = 0.1;

        if (args.length > 0) {
            libfunc.setEpsilon(Double.parseDouble(args[0]));
        }

        // set epsilon
        // libfunc.setEpsilon(epsilon);

        // diffPrivacy count
        System.out.println("True count: " + doubleList.size());
        System.out.println("DP count with default epsilon: " + libfunc.DiffPrivacyCount(myNum));

        // diffPrivacy count with Epsilon
        System.out.println("DP count with custom epsilon (" + epsilon + "): " + libfunc.DiffPrivacyCount(myNum, epsilon));

        // diffPrivacy Integer sum
        System.out.println("\nTrue Integer sum: " + sumInt);
        System.out.println("DP Integer sum with default epsilon and auto bounds: " + libfunc.DiffPrivacySum(myIntNum));
        System.out.println("DP Integer sum with custom epsilon and auto bounds: " + libfunc.DiffPrivacySum(myIntNum, epsilon));
        System.out.println("DP Integer sum with custom epsilon and custom bounds: " + libfunc.DiffPrivacySum(myIntNum, epsilon, 0.0, 100.0));
        System.out.println("DP Integer sum with default epsilon and custom bounds: " + libfunc.DiffPrivacySum(myIntNum, 0.0, 100.0));

        // diffPrivacy Double sum
        System.out.println("\nTrue Double sum: " + sum);
        System.out.println("DP Double sum with default epsilon and auto bounds: " + libfunc.DiffPrivacySum(myNum));
        System.out.println("DP Double sum with custom epsilon and auto bounds: " + libfunc.DiffPrivacySum(myNum, epsilon));
        System.out.println("DP Double sum with custom epsilon and custom bounds: " + libfunc.DiffPrivacySum(myNum, epsilon, 0.0, 100.0));
        System.out.println("DP Double sum with default epsilon and custom bounds: " + libfunc.DiffPrivacySum(myNum, 0.0, 100.0));

        // diffPrivacy Double mean
        double mean = sum/doubleList.size();
        System.out.println("\nTrue mean: " + mean);
        System.out.println("DP mean with default epsilon and auto bounds: " + libfunc.DiffPrivacyMean(myNum));
        System.out.println("DP mean with custom epsilon and auto bounds: " + libfunc.DiffPrivacyMean(myNum, epsilon));
        System.out.println("DP mean with custom epsilon and custom bounds: " + libfunc.DiffPrivacyMean(myNum, epsilon, 0.0, 100.0));
        System.out.println("DP mean with default epsilon and custom bounds: " + libfunc.DiffPrivacyMean(myNum, 0.0, 100.0));

        // diffPrivacy Double variance
        double var = 0;

        for( int n = 0; n < doubleList.size(); n++ )
        {
            var += (myNum[n] - mean) * (myNum[n] - mean);
        }
        var /= doubleList.size();

        System.out.println("\nTrue var: " + var);
        System.out.println("DP var with default epsilon and auto bounds: " + libfunc.DiffPrivacyVar(myNum));
        System.out.println("DP var with custom epsilon and auto bounds: " + libfunc.DiffPrivacyVar(myNum, epsilon));
        System.out.println("DP var with custom epsilon and custom bounds: " + libfunc.DiffPrivacyVar(myNum, epsilon, 0.0, 1.0));
        System.out.println("DP var with default epsilon and custom bounds: " + libfunc.DiffPrivacyVar(myNum, 0.0, 1.0));

        // diffPrivacy Double std
        double std = Math.sqrt(var);
        System.out.println("\nTrue std: " + std);
        System.out.println("DP std with default epsilon and auto bounds: " + libfunc.DiffPrivacyStd(myNum));
        System.out.println("DP std with custom epsilon and auto bounds: " + libfunc.DiffPrivacyStd(myNum, epsilon));
        System.out.println("DP std with custom epsilon and custom bounds: " + libfunc.DiffPrivacyStd(myNum, epsilon, 0.0, 1.0));
        System.out.println("DP std with default epsilon and custom bounds: " + libfunc.DiffPrivacyStd(myNum, 0.0, 1.0));

        System.out.println("\nDP int Ntile with default epsilon: " + libfunc.DiffPrivacyNtile(myIntNum, 0.9, 0.0, 100.0));
        System.out.println("DP int Ntile with custom epsilon: " + libfunc.DiffPrivacyNtile(myIntNum, 0.9, 0.0, 100.0, epsilon));
        System.out.println("\nDP double Ntile with default epsilom: " + libfunc.DiffPrivacyNtile(myNum, 0.9, 0.0, 1.0));
        System.out.println("DP double Ntile with custom epsilon: " + libfunc.DiffPrivacyNtile(myNum, 0.9, 0.0, 1.0, epsilon));
        /*
        // diffPrivacy Ntile
        libfunc.DpNtile(myNum, 0.5, 0.0, 1.0);*/

    }

    /**
     * This method calls the Google's DpCount method over an array of numbers
     * with the default epsilon value.
     * @param doubleArray: the array of numbers in input
     * @return the result of the DpCount method
     */
    int DiffPrivacyCount(double[] doubleArray) {
        return this.DpCount(doubleArray);
    }

    /**
     * This method calls the Google's DpCount method over an array of numbers
     * with a user-defined epsilon value.
     * @param doubleArray: the array of numbers in input
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpCount method
     */
    int DiffPrivacyCount(double[] doubleArray, double epsilon) {
        return this.DpCount(doubleArray, epsilon);
    }

    /**
     * The method calls the Google's DpSum method over an array of integers
     * with the default epsilon value.
     * @param intArray: the array of integers
     * @return the result of the DpSum method
     */
    int DiffPrivacySum(int[] intArray) {
        return this.DpSum(intArray);
    }

    /**
     * The method calls the Google's DpSum method over an array of integers
     * with a user-defined epsilon value.
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpSum method
     */
    int DiffPrivacySum(int[] intArray, double epsilon) {
        return this.DpSum(intArray, epsilon);
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
        return this.DpSum(intArray, epsilon, lower, upper);
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
        return this.DpSum(intArray, lower, upper);
    }

    /**
     * The method calls the Google's DpSum method over an array of numbers
     * with the default epsilon value.
     * @param intArray: the array of integers
     * @return the result of the DpSum method
     */
    double DiffPrivacySum(double[] doubleArray) {
        return this.DpSum(doubleArray);
    }

    /**
     * The method calls the Google's DpSum method over an array of numbers
     * with a user-defined epsilon value.
     * @param intArray: the array of integers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the DpSum method
     */
    double DiffPrivacySum(double[] doubleArray, double epsilon) {
        return this.DpSum(doubleArray, epsilon);
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
        return this.DpSum(doubleArray, epsilon, lower, upper);
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
        return this.DpSum(doubleArray, lower, upper);
    }

    /**
     * The method calls the Google's DpMean method over an array of numbers
     * with the default epsilon value.
     * @param doubleArray: the array of numbers
     * @return the result of the Mean method
     */
    double DiffPrivacyMean(double[] doubleArray) {
        return this.DpMean(doubleArray);
    }

    /**
     * The method calls the Google's DpMean method over an array of numbers
     * with a user-defined epsilon value.
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the Mean method
     */
    double DiffPrivacyMean(double[] doubleArray, double epsilon) {
        return this.DpMean(doubleArray, epsilon);
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
        return this.DpMean(doubleArray, epsilon, lower, upper);
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
        return this.DpMean(doubleArray, lower, upper);
    }

    /**
     * The method calls the Google's DpVariance method over an array of numbers
     * with the default epsilon value.
     * @param doubleArray: the array of numbers
     * @return the result of the Variance method
     */
    double DiffPrivacyVar(double[] doubleArray) {
        return this.DpVariance(doubleArray);
    }

    /**
     * The method calls the Google's DpVariance method over an array of numbers
     * with a user-defined epsilon value
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the Variance method
     */
    double DiffPrivacyVar(double[] doubleArray, double epsilon) {
        return this.DpVariance(doubleArray, epsilon);
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
        return this.DpVariance(doubleArray, epsilon, lower, upper);
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
        return this.DpVariance(doubleArray, lower, upper);
    }

    /**
     * The method calls the Google's DpStandardDeviation method over an array of numbers
     * with the default epsilon value
     * @param doubleArray: the array of numbers
     * @return the result of the Standard Deviation method
     */
    double DiffPrivacyStd(double[] doubleArray) {
        return this.DpStd(doubleArray);
    }

    /**
     * The method calls the Google's DpStandardDeviation method over an array of numbers
     * with a user-defined epsilon value
     * @param doubleArray: the array of numbers
     * @param epsilon: the epsilon value used by the algorithm
     * @return the result of the Standard Deviation method
     */
    double DiffPrivacyStd(double[] doubleArray, double epsilon) {
        return this.DpStd(doubleArray, epsilon);
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
        return this.DpStd(doubleArray, epsilon, lower, upper);
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
        return this.DpStd(doubleArray, lower, upper);
    }

    int DiffPrivacyNtile(int[] intArray, double percentile, double lower, double upper) {
        return this.DpNtile(intArray, percentile, lower, upper);
    }

    int DiffPrivacyNtile(int[] intArray, double percentile, double lower, double upper, double epsilon) {
        return this.DpNtile(intArray, percentile, lower, upper, epsilon);
    }

    double DiffPrivacyNtile(double[] doubleArray, double percentile, double lower, double upper) {
        return this.DpNtile(doubleArray, percentile, lower, upper);
    }

    double DiffPrivacyNtile(double[] doubleArray, double percentile, double lower, double upper, double epsilon) {
        return this.DpNtile(doubleArray, percentile, lower, upper, epsilon);
    }


    // Now adding spark class
    abstract class DiffPrivacySum {

    }
}
