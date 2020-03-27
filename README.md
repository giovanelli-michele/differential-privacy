# Differential Privacy

## Michele Giovanelli's Thesis

### Java DP-functions
In the [java folder](https://github.com/giovanelli-michele/differential-privacy/tree/master/differential_privacy/java) I added a JNI Java class that calls the native C++ functions implemented by Google.

In particular, in the Java class I implemented COUNT, SUM, MEAN, VARIANCE, STANDARD DEVIATION and NTILE functions, based on Google's Algorithms and corresponding to the POSTGRES functions developed in the library.

I also wrote a main method in the Java class that calls these methods on random data.

It's possible to run it by compiling the full library from the main directory
```shell
bazel build differential-privacy/...
``` 
and than running the Java Main class
```shell
bazel run differential-privacy/java:DiffPrivacy
```

### Example of Differential Privacy using JNI 
In the [example folder](https://github.com/giovanelli-michele/differential-privacy/tree/master/differential_privacy/example) I added a JNI Java class that calls the native functions of the C++ library in order to show the same example proposed by the Google team.

It's possible to run the example by compiling the full library from the main directory
```shell
bazel build differential-privacy/...
``` 
and than running the Java Main class
```shell
bazel run differential-privacy/example:Main
```

## Google's Differential Privacy
This project contains a C++ library of ε-differentially private algorithms,
which can be used to produce aggregate statistics over numeric data sets
containing private or sensitive information. In addition, we provide a
stochastic tester to check the correctness of the algorithms. Currently, we
provide algorithms to compute the following:

  * Count
  * Sum
  * Mean
  * Variance
  * Standard deviation
  * Order statistics (including min, max, and median)

We also provide an implementation of the laplace mechanism that can be used to
perform computations that aren't covered by our pre-built algorithms.

All of these algorithms are suitable for research, experimental or production
use cases.

This project also contains a
[stochastic tester](https://github.com/google/differential-privacy/tree/master/differential_privacy/testing),
used to help catch regressions that could make the differential privacy
property no longer hold.

## How to Build

This project uses [bazel](https://bazel.build) for building and dependency
resolution. Install bazel and run the following:

```bazel build differential_privacy/...```

## Examples

Here's a minimal example showing how to compute the count of some data:

```
#include "differential_privacy/algorithms/count.h"

// Epsilon is a configurable parameter. A lower value means more privacy but
// less accuracy.
int64_t count(const vector<double>& vals, double epsilon) {
  // Construct the Count object to run on double inputs.
  std::unique_pointer<differential_privacy::Count<double>> count =
     differential_privacy::Count<double>::Builder().SetEpsilon(epsilon)
                                                   .Build()
                                                   .ValueOrDie();

  // Compute the count and get the result.
  differential_privacy::Output result = count->Result(v.begin(), v.end());

  // GetValue can be used to extract the value from an Output protobuf. For
  // count, this is always an int64_t value.
  return differential_privacy::GetValue<int64_t>(result);
}

```

We also include the following example code:
- A [tool for releasing epsilon-DP aggregate statistics](https://github.com/google/differential-privacy/tree/master/differential_privacy/example).
- A [PostgreSQL extension](https://github.com/google/differential-privacy/tree/master/differential_privacy/postgres)
that adds epsilon-DP aggregate functions.

## Caveats

All of our code assume that each user contributes only a single row to each
aggregation. You can use the library to build systems that allow multiple
contributions per user - [our paper](https://arxiv.org/abs/1909.01917) describes
one such system. To do so, multiple user contributions should be combined before
they are passed to our algorithms. We chose not to implement this step at the
library level because it's not the logical place for it - it's much easier to
sort contributions by user and combine them together with a distributed
processing framework before they're passed to our algorithms.

## Support

We will continue to publish updates and improvements to the library. We will not
accept pull requests for the immediate future. We will respond to issues filed
in this project. If we intend to stop publishing improvements and responding to
issues we will publish notice here at least 3 months in advance.

## License

[Apache License 2.0](LICENSE)

## Support Disclaimer

This is not an officially supported Google product.
