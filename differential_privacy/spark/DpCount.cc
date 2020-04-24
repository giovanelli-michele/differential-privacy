#include <jni.h>
#include <stdio.h>
#include <iostream>
#include <string.h>
#include <dp_func.h>
#include "DpCount.h"

using namespace std;

DpCount* myDpCount;

std::string str = "";
std::string* ptr = &str;


JNIEXPORT jboolean JNICALL Java_DpCount_createAlgorithm(JNIEnv *, jobject, jboolean default_epsilon, jdouble epsilon) {


    if (default_epsilon) {
        myDpCount = new DpCount(ptr, true);
    } else {
        myDpCount = new DpCount(ptr, default_epsilon, epsilon);
    }

    if (str != "") {
        // verifying if any error is thrown
        cout << str << endl;
        return false;
    } else {
        return true;
    }

}

JNIEXPORT jboolean JNICALL Java_DpCount_insertElement(JNIEnv *, jobject, jdouble element) {
    myDpCount->AddEntry(element);

    if (str != "") {
        // verifying if any error is thrown
        cout << str << endl;
        return false;
    } else {
        return true;
    }
}

JNIEXPORT jint JNICALL Java_DpCount_getAlgorithmResult(JNIEnv *, jobject) {
    int result = 0;

    result = myDpCount->Result(ptr);

    if (str != "") {
        // verifying if any error is thrown
        cout << str << endl;
    }

    return result;
}