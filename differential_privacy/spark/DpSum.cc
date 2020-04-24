#include <jni.h>
#include <stdio.h>
#include <iostream>
#include <string.h>
#include <dp_func.h>
#include "DpSum.h"

using namespace std;

DpSum* myDpSum;

std::string strSum = "";
std::string* ptrSum = &strSum;

JNIEXPORT jboolean JNICALL Java_DpSum_createAlgorithm(JNIEnv *env, jobject, jboolean default_epsilon, jboolean auto_bounds, jdouble epsilon, jdouble lower, jdouble upper) {

    if (default_epsilon) {
        if (auto_bounds) {
            myDpSum = new DpSum(ptrSum, true, 0, true, 0, 0);
        } else {
            myDpSum = new DpSum(ptrSum, true, 0, false, lower, upper);
        }
    } else {
        if (auto_bounds) {
            myDpSum = new DpSum(ptrSum, false, epsilon, true, 0, 0);
        } else {
            myDpSum = new DpSum(ptrSum, false, epsilon, false, lower, upper);
        }
    }

    if (strSum != "") {
        // verifying if any error is thrown
        cout << strSum << endl;
        return false;
    } else {
        return true;
    }
}

JNIEXPORT jboolean JNICALL Java_DpSum_insertElement(JNIEnv *, jobject, jdouble element) {
    myDpSum->AddEntry(element);

    if (strSum != "") {
        // verifying if any error is thrown
        cout << strSum << endl;
        return false;
    } else {
        return true;
    }
}

JNIEXPORT jdouble JNICALL Java_DpSum_getAlgorithmResult(JNIEnv *, jobject) {
    double result = 0;

    result = myDpSum->Result(ptrSum);

    if (strSum != "") {
        // verifying if any error is thrown
        cout << strSum << endl;
    }

    return result;
}