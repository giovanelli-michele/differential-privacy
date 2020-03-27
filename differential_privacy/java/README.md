# Java Differential Privacy
Here's an example of execution of the main method of the Java Dp_func class with one Double array and, when possible, one Double array.

```shell
***********************************************
Loaded dp-func library
***********************************************

True count: 10000
DP count with default epsilon: 9999
DP count with custom epsilon (0.1): 9984

True Integer sum: 494768
DP Integer sum with default epsilon and auto bounds: 494592
DP Integer sum with custom epsilon and auto bounds: 479232
DP Integer sum with custom epsilon and custom bounds: 494592
DP Integer sum with default epsilon and custom bounds: 494592

True Double sum: 4978.630400466516
DP Double sum with default epsilon and auto bounds: 4978.0
DP Double sum with custom epsilon and auto bounds: 4928.0
DP Double sum with custom epsilon and custom bounds: 4096.0
DP Double sum with default epsilon and custom bounds: 4992.0

True mean: 0.4978630400466516
DP mean with default epsilon and auto bounds: 0.49030080782312924
DP mean with custom epsilon and auto bounds: 0.498991935483871
DP mean with custom epsilon and custom bounds: 0.4615384615384599
DP mean with default epsilon and custom bounds: 0.4896000000000029

True var: 0.08384280884554482
DP var with default epsilon and auto bounds: 0.09176151106781497
DP var with custom epsilon and auto bounds: 0.079420431665023
DP var with custom epsilon and custom bounds: 0.08111693328039482
DP var with default epsilon and custom bounds: 0.08372949019543788

True std: 0.28955622743354154
DP std with default epsilon and auto bounds: 0.30265222148553456
DP std with custom epsilon and auto bounds: 0.2904100656848074
DP std with custom epsilon and custom bounds: 0.28306925853614895
DP std with default epsilon and custom bounds: 0.28936304026592374

DP int Ntile with default epsilon: 90
DP int Ntile with custom epsilon: 89

DP double Ntile with default epsilom: 0.898782428782741
DP double Ntile with custom epsilon: 0.8948836624894347

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
In order to perform the Differential Privacy MEAN algorithm, I wrote four overloaded methods in the Java class, as for the DpSUM algorithm (but only with double return type).

## VARIANCE
In order to perform the Differential Privacy VARIANCE algorithm, I wrote four overloaded methods in the Java class, as for the DpMean algorithm.

## STANDARD DEVIATION
In order to perform the Differential Privacy STANDARD DEVIATION algorithm, I wrote four overloaded methods in the Java class, as for the DpMean algorithm.

## NTILE
In order to perform the Differential Privacy STANDARD DEVIATION algorithm, I wrote four overloaded methods.
In particular, it has been necessary to implement these methods with user-defined bounds, as the algorithm performs poorly without bounds (from Google's documentation).
