#include <jni.h>
#include <stdio.h>
#include <iostream>
#include <string.h>
#include <dp_func.h>
#include "DpMean.h"

using namespace std;

DpMean* myDpMean;

std::string strMean = "";
std::string* ptrMean = &strMean;

JNIEXPORT jboolean JNICALL Java_DpMean_createAlgorithm(JNIEnv *env, jobject, jboolean default_epsilon, jboolean auto_bounds, jdouble epsilon, jdouble lower, jdouble upper) {

    if (default_epsilon) {
        if (auto_bounds) {
            myDpMean = new DpMean(ptrMean, true, 0, true, 0, 0);
        } else {
            myDpMean = new DpMean(ptrMean, true, 0, false, lower, upper);
        }
    } else {
        if (auto_bounds) {
            myDpMean = new DpMean(ptrMean, false, epsilon, true, 0, 0);
        } else {
            myDpMean = new DpMean(ptrMean, false, epsilon, false, lower, upper);
        }
    }

    if (strMean != "") {
        // verifying if any error is thrown
        cout << strMean << endl;
        return false;
    } else {
        return true;
    }
}

JNIEXPORT jboolean JNICALL Java_DpMean_insertElement(JNIEnv *, jobject, jdouble element) {
    myDpMean->AddEntry(element);

    if (strMean != "") {
        // verifying if any error is thrown
        cout << strMean << endl;
        return false;
    } else {
        return true;
    }
}

JNIEXPORT jdouble JNICALL Java_DpMean_getAlgorithmResult(JNIEnv *, jobject) {
    double result = 0;

    result = myDpMean->Result(ptrMean);

    if (strMean != "") {
        // verifying if any error is thrown
        cout << strMean << endl;
    }

    return result;
}