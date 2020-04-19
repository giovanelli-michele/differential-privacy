#include <jni.h>
#include <stdio.h>
#include <iostream>
#include <string.h>
#include "ExampleJava.h"
#include "differential_privacy/algorithms/confidence-interval.pb.h"
#include "differential_privacy/algorithms/util.h"
#include "differential_privacy/proto/data.pb.h"
#include "differential_privacy/proto/util.h"
#include "animals_and_carrots.h"

using differential_privacy::BoundingReport;
using differential_privacy::ConfidenceInterval;
using differential_privacy::DefaultEpsilon;
using differential_privacy::GetValue;
using differential_privacy::Output;
using differential_privacy::example::CarrotReporter;
using differential_privacy::base::StatusOr;

// Set the epsilon value
double epsilon = 4 * DefaultEpsilon();
CarrotReporter reporter("differential_privacy/example/animals_and_carrots.csv", epsilon);

JNIEXPORT jint JNICALL Java_ExampleJava_Sum(JNIEnv *, jobject) {
    return reporter.Sum();
}


JNIEXPORT jdouble JNICALL Java_ExampleJava_Mean(JNIEnv *, jobject) {
    return reporter.Mean();
}


JNIEXPORT jint JNICALL Java_ExampleJava_CountAbove(JNIEnv *, jobject, jint limit) {
    return (reporter.CountAbove(limit));
}

JNIEXPORT jint JNICALL Java_ExampleJava_Max(JNIEnv *, jobject) {
    return reporter.Max();
}


JNIEXPORT jdouble JNICALL Java_ExampleJava_PrivacyBudget(JNIEnv *, jobject) {
    return reporter.PrivacyBudget();
}


JNIEXPORT jint JNICALL Java_ExampleJava_PrivateSum(JNIEnv *, jobject) {
    return GetValue<int>(reporter.PrivateSum(.25).ValueOrDie());
}


JNIEXPORT jdouble JNICALL Java_ExampleJava_PrivateMean(JNIEnv *, jobject) {
    StatusOr<Output> mean_status = reporter.PrivateMean(.25);
    if (!mean_status.ok()) {
    printf("Error obtaining mean:\n");
    printf(
      "The animals were not able to get the private mean with the current privacy parameters. This is due to the small size of the dataset and random chance. Please re-run report_the_carrots to try again.\n");
       return -1.0;
    } else {
        Output mean_output = mean_status.ValueOrDie();
        BoundingReport report = mean_output.error_report().bounding_report();
        double mean = GetValue<double>(mean_output);
        int lower_bound = GetValue<int>(report.lower_bound());
        int upper_bound = GetValue<int>(report.upper_bound());
        double num_inputs = report.num_inputs();
        double num_outside = report.num_outside();
        std::cout << "DP mean output: " << mean_output.DebugString() << std::endl;
        std::cout <<
            "The animals help Fred interpret the results. " << mean << " is the DP mean. Since no bounds were set for  the DP mean algorithm, bounds on the "
            << "input data were automatically determined. Most of the data fell between [" << lower_bound <<", " << upper_bound <<"]. Thus, these bounds were used to determine clamping "
            << "and global sensitivity. In addition, around " << num_inputs << " input values fell "
            << "inside of these bounds, and around " << num_outside << " inputs fell outside of these "
            << "bounds. num_inputs and num_outside are themselves DP counts.\n"<< std::endl;
        return GetValue<double>(mean_output);
    }
}

JNIEXPORT jint JNICALL Java_ExampleJava_PrivateCountAbove(JNIEnv *, jobject, jint limit) {
    Output count_output = reporter.PrivateCountAbove(.25, 90).ValueOrDie();
    int count = GetValue<int>(count_output);
    ConfidenceInterval ci =
        count_output.error_report().noise_confidence_interval();
    double confidence_level = ci.confidence_level();
    double lower_bound = ci.lower_bound();
    double upper_bound = ci.upper_bound();
    std::cout << "DP count output: " <<  count_output.DebugString() << std::endl;
    std::cout <<
        "The animals tell Fred that "  << count << "is the DP count. [" << lower_bound << ", " << upper_bound << "] is the " <<
        confidence_level <<" confidence interval of the noise added to the count.\n" << std::endl;
    return count;
}


JNIEXPORT jint JNICALL Java_ExampleJava_PrivateMax(JNIEnv *, jobject) {
    if (reporter.PrivacyBudget() <= 0) {
        std::cout << "Error querying for count: " << (reporter.PrivateMax(.25).status().message()) << std::endl;
        return -1;
    } else {
        return GetValue<int>(reporter.PrivateMax(.25).ValueOrDie());
    }
}

