# Using Differential Privacy in Apache Spark
In this Bazel *package* I created some Java classes that extend an Apache Spark User Defined Aggregate Function (UDAF) in order to use  the Differential Privacy algorithms inside Spark environment with RDDs.

At the moment, there are three different classes available: DpCount, DpSum and DpMean, which handle *differentially private* Count, Sum and Mean algorithms respectively.

In order to use these classes, you need to build the package with:

```shell script
bazel build differential_privacy/spark:DpSpark_deploy.jar
```

Then, you have to take two Bazel *output targets*, which are `DpSpark_deploy.jar` and `libdpspark.so` from the Bazel output directory and copy them into a folder in your Spark home directory.

These two target are also available at `differential_privacy/spark/targets` folder.

Let's assume that `$SPARK_HOME` is the path to the Spark main directory in your system, and let's assume you created there a folder named `dp_jar` in which you copied the Bazel `targets`.

Then, in order to test the library, you have to launch Spark passing as a parameter the `target` folder, in this way:

```shell script
$SPARK_HOME/bin/spark-shell --jars dp_jar/DpSpark_deploy.jar
```

In order to use the Java classes, you must generate an instance of the class you need, then you have to register a spark UDAF using the class instance. Finally you can use the UDAF over a Spark RDD.

Here's an example with DpCount class:

```scala worksheet
var dps = new DpSum(5, 0, 100);
spark.udf.register("DpSum", dps);
val rdd = spark.read.json("examples/src/main/resources/dataset.json")
val res = rdd.agg(callUDF("DpSum", col("value")).as("res"))
res.show()
```
Output:
```text
Setting epsilon to 5.0
Setting bounds to [0.0, 100.0]
***********************************************
Loaded dpspark library
***********************************************
Algorithm created!
dps: DpSum = DpSum@349686e8

res0: org.apache.spark.sql.expressions.UserDefinedAggregateFunction = DpSum@349686e8

rdd: org.apache.spark.sql.DataFrame = [value: double]

res: org.apache.spark.sql.DataFrame = [res: double]

+-------+
|    res|
+-------+
|42240.0|
+-------+
```
