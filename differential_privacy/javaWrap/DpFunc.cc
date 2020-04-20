#include <jni.h>
#include <stdio.h>
#include <iostream>
#include <string.h>
#include <dp_func.h>
#include "DpFunc.h"


JNIEXPORT jint JNICALL Java_DpFunc_DpCount(JNIEnv *env, jobject, jdoubleArray doubleArray, jboolean default_epsilon, jdouble epsilon) {
    jsize size = env->GetArrayLength(doubleArray);
    jdouble *doubleCArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "";
    std::string* ptr = &str;
    DpCount* myDpCount;
    int result = 0;
    if (default_epsilon) {
        myDpCount= new DpCount(ptr, true);
    } else {
        myDpCount = new DpCount(ptr, default_epsilon, epsilon);
    }

    for(int i = 0; i < size; i++) {
        myDpCount->AddEntry(doubleCArray[i]);
    }

    result = myDpCount->Result(ptr);

    if (str != "") {
        // verifying if any error is thrown
        std::cout << str << std::endl;
    }

    return result;
}

JNIEXPORT jdouble JNICALL Java_DpFunc_DpSum(JNIEnv *env, jobject, jdoubleArray doubleArray, jboolean default_epsilon, jboolean auto_bounds, jdouble epsilon, jdouble lower, jdouble upper) {
    jsize size = env->GetArrayLength(doubleArray);
    jdouble *CArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "";
    std::string* pointerSum = &str;
    DpSum* myDpSum;
    double result;

    if (default_epsilon) {
        if (auto_bounds) {
            myDpSum = new DpSum(pointerSum, true, 0, true, 0, 0);
        } else {
            myDpSum = new DpSum(pointerSum, true, 0, false, lower, upper);
        }
    } else {
        if (auto_bounds) {
            myDpSum = new DpSum(pointerSum, false, epsilon, true, 0, 0);
        } else {
            myDpSum = new DpSum(pointerSum, false, epsilon, false, lower, upper);
        }
    }

    for(int i = 0; i < size; i++) {
        myDpSum->AddEntry(CArray[i]);
    }

    result = myDpSum->Result(pointerSum);

    if (str != "") {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_DpFunc_DpMean(JNIEnv *env, jobject, jdoubleArray doubleArray, jboolean default_epsilon, jboolean auto_bounds, jdouble epsilon, jdouble lower, jdouble upper) {
    jsize size = env->GetArrayLength(doubleArray);
    jdouble *CArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "";
    std::string* pointerMean = &str;
    DpMean* myDpMean;
    double result;

    if (default_epsilon) {
        if (auto_bounds) {
            myDpMean = new DpMean(pointerMean, true, 0, true, 0, 0);
        } else {
            myDpMean = new DpMean(pointerMean, true, 0, false, lower, upper);
        }
    } else {
        if (auto_bounds) {
            myDpMean = new DpMean(pointerMean, false, epsilon, true, 0, 0);
        } else {
            myDpMean = new DpMean(pointerMean, false, epsilon, false, lower, upper);
        }
    }

    for(int i = 0; i < size; i++) {
        myDpMean->AddEntry(CArray[i]);
    }

    result = myDpMean->Result(pointerMean);

    if (str != "") {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_DpFunc_DpVariance(JNIEnv *env, jobject, jdoubleArray doubleArray, jboolean default_epsilon, jboolean auto_bounds, jdouble epsilon, jdouble lower, jdouble upper) {
    jsize size = env->GetArrayLength(doubleArray);
    jdouble *CArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "";
    std::string* pointerVar = &str;
    DpVariance* myDpVariance;
    double result;

    if (default_epsilon) {
        if (auto_bounds) {
            myDpVariance = new DpVariance(pointerVar, true, 0, true, 0, 0);
        } else {
            myDpVariance = new DpVariance(pointerVar, true, 0, false, lower, upper);
        }
    } else {
        if (auto_bounds) {
            myDpVariance = new DpVariance(pointerVar, false, epsilon, true, 0, 0);
        } else {
            myDpVariance = new DpVariance(pointerVar, false, epsilon, false, lower, upper);
        }
    }

    for(int i = 0; i < size; i++) {
        myDpVariance->AddEntry(CArray[i]);
    }

    result = myDpVariance->Result(pointerVar);

    if (str != "") {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_DpFunc_DpStd(JNIEnv *env, jobject, jdoubleArray doubleArray, jboolean default_epsilon, jboolean auto_bounds, jdouble epsilon, jdouble lower, jdouble upper) {
    jsize size = env->GetArrayLength(doubleArray);
    jdouble *CArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "";
    std::string* pointerStd = &str;
    DpStandardDeviation* myDpStDev;
    double result;

    if (default_epsilon) {
        if (auto_bounds) {
            myDpStDev = new DpStandardDeviation(pointerStd, true, 0, true, 0, 0);
        } else {
            myDpStDev = new DpStandardDeviation(pointerStd, true, 0, false, lower, upper);
        }
    } else {
        if (auto_bounds) {
            myDpStDev = new DpStandardDeviation(pointerStd, false, epsilon, true, 0, 0);
        } else {
            myDpStDev = new DpStandardDeviation(pointerStd, false, epsilon, false, lower, upper);
        }
    }

    for(int i = 0; i < size; i++) {
        myDpStDev->AddEntry(CArray[i]);
    }

    result = myDpStDev->Result(pointerStd);

    if (str != "") {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_DpFunc_DpNtile(JNIEnv *env, jobject, jdoubleArray doubleArray, jdouble percentile, jdouble lower, jdouble upper, jboolean default_epsilon, jdouble epsilon) {
    jsize size = env->GetArrayLength(doubleArray);
    jdouble *CArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "";
    std::string* pointerNtile = &str;
    DpNtile* myDpNtile;
    double result;

    if (default_epsilon) {
        myDpNtile = new DpNtile(pointerNtile, percentile, lower, upper, true, 0);
    } else {
        myDpNtile = new DpNtile(pointerNtile, percentile, lower, upper, false, epsilon);
    }

    for(int i = 0; i < size; i++) {
        myDpNtile->AddEntry(CArray[i]);
    }

    result = myDpNtile->Result(pointerNtile);

    if (str != "") {
        std::cout << str << std::endl;
    }
    return result;
}