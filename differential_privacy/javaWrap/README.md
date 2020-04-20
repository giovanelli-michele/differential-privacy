# Java Differential Privacy Wrapping
Here's an example of execution of the main method of the Java DpFunc class with one double random array with 10.000 elements.

```shell script
***********************************************
Loaded dpjava library
***********************************************

True Count: 10000
DpCount with custom epsilon 0.1: 9984

True Sum: 4994.074093122075
DpSum with default epsilon: 5002.0

True Mean: 0.4994074093122075
DpMean with bounds [0, 1]: 0.4917111280487805

True Variance: 0.08249751049405228
DpVariance with custom epsilon 5: 0.09055434875463364

True Standard Deviation: 0.2872237986206092
Dp Standard Deviation with custom epsilon 0.1 and bounds [0, 1]: 0.2959214007724162

Dp Ntile with 0.95 percentile and bounds [0, 1]: 0.950840402605854
```

## COUNT
In order to perform the Differential Privacy Count algorithm, I wrote two overloaded methods in the Java class.

```java
/**
 * This method calls the Google's DpCount method over an array of numbers
 * with the default epsilon value.
 * @param doubleArray: the array of numbers in input
 * @return the result of the DpCount method
 */
int DiffPrivacyCount(double[] doubleArray);
```

```java
/**
 * This method calls the Google's DpCount method over an array of numbers
 * with a user-defined epsilon value.
 * @param doubleArray: the array of numbers in input
 * @param epsilon: the epsilon value used by the algorithm
 * @return the result of the DpCount method
 */
int DiffPrivacyCount(double[] doubleArray, double epsilon);
```

## SUM
In order to perform the Differential Privacy SUM algorithm, I wrote eight overloaded methods in the Java class.

```java
/**
 * The method calls the Google's DpSum method over an array of integers
 * with the default epsilon value.
 * @param intArray: the array of integers
 * @return the result of the DpSum method
 */
int DiffPrivacySum(int[] intArray);
```
```java
/**
 * The method calls the Google's DpSum method over an array of integers
 * with a user-defined epsilon value.
 * @param intArray: the array of integers
 * @param epsilon: the epsilon value used by the algorithm
 * @return the result of the DpSum method
 */
int DiffPrivacySum(int[] intArray, double epsilon);
```

```java
/**
 * The method calls the Google's DpSum method over an array of integers
 * with a user-defined epsilon value and user-defined bounds for the algorithm
 * @param intArray: the array of integers
 * @param epsilon: the epsilon value used by the algorithm
 * @param lower: the lower bound used for the algorithm
 * @param upper: the upper bound used for the algorithm
 * @return the result of the DpSum method
 */
int DiffPrivacySum(int[] intArray, double epsilon, double lower, double upper;
```

```java
/**
 * The method calls the Google's DpSum method over an array of integers
 * with the default epsilon value and user-defined bounds for the algorithm
 * @param intArray: the array of integers
 * @param lower: the lower bound used for the algorithm
 * @param upper: the upper bound used for the algorithm
 * @return the result of the DpSum method
 */
int DiffPrivacySum(int[] intArray, double lower, double upper);
```
```java
/**
 * The method calls the Google's DpSum method over an array of numbers
 * with the default epsilon value.
 * @param intArray: the array of integers
 * @return the result of the DpSum method
 */
double DiffPrivacySum(double[] doubleArray);
```
```java
/**
 * The method calls the Google's DpSum method over an array of numbers
 * with a user-defined epsilon value.
 * @param intArray: the array of integers
 * @param epsilon: the epsilon value used by the algorithm
 * @return the result of the DpSum method
 */
double DiffPrivacySum(double[] doubleArray, double epsilon);
```
```java
/**
 * The method calls the Google's DpSum method over an array of numbers
 * with a user-defined epsilon value and user-defined bounds for the algorithm
 * @param intArray: the array of integers
 * @param epsilon: the epsilon value used by the algorithm
 * @param lower: the lower bound used for the algorithm
 * @param upper: the upper bound used for the algorithm
 * @return the result of the DpSum method
 */
double DiffPrivacySum(double[] doubleArray, double epsilon, double lower, double upper);
```
```java
/**
 * The method calls the Google's DpSum method over an array of numbers
 * with the default epsilon value and user-defined bounds for the algorithm
 * @param intArray: the array of integers
 * @param lower: the lower bound used for the algorithm
 * @param upper: the upper bound used for the algorithm
 * @return the result of the DpSum method
 */
double DiffPrivacySum(double[] doubleArray, double lower, double upper);
```

## MEAN
In order to perform the Differential Privacy MEAN algorithm, I wrote eight overloaded methods in the Java class, as for the DpSUM algorithm (but only with double return type).

## VARIANCE
In order to perform the Differential Privacy VARIANCE algorithm, I wrote eight overloaded methods in the Java class, as for the DpMean algorithm.

## STANDARD DEVIATION
In order to perform the Differential Privacy STANDARD DEVIATION algorithm, I wrote eight overloaded methods in the Java class, as for the DpMean algorithm.

## NTILE
In order to perform the Differential Privacy NTILE algorithm, I wrote four overloaded methods.
In particular, it has been necessary to implement these methods with user-defined bounds, as the algorithm performs poorly without bounds (from Google's documentation).
