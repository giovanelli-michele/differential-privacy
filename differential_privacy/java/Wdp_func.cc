#include "jni.h"
#include <stdio.h>
#include <iostream>
#include <string.h>
#include "Wdp_func.h"
#include "dp_func.h"

double epsilon = 0;
DpCount* myDpCount;
DpSum* myDpSum;
DpMean* myDpMean;
DpVariance* myDpVariance;
DpStandardDeviation* myDpStDev;
DpNtile* myDpNtile;

JNIEXPORT void JNICALL Java_Dp_1func_setEpsilon(JNIEnv *, jobject, jdouble custom_epsilon){
    epsilon = custom_epsilon;
    std::cout << "Set epsilon to " << custom_epsilon << std::endl << std::endl;



}

JNIEXPORT jint JNICALL Java_Dp_1func_DpCount___3D(JNIEnv *env, jobject, jdoubleArray doubleArray){

    jsize size = env->GetArrayLength(doubleArray);
    jdouble *doubleCArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "count";
    std::string str2 = "count";
    std::string* ptr = &str;
    myDpCount = new DpCount(ptr, true);
    jint i = 0;

    while( i < size) {
        myDpCount->AddEntry(doubleCArray[i]);
        i++;
    }

    int result = myDpCount->Result(ptr);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;


}

// DP COUNT
JNIEXPORT jint JNICALL Java_Dp_1func_DpCount___3DD(JNIEnv *env, jobject, jdoubleArray doubleArray, jdouble custom_epsilon){

    jsize size = env->GetArrayLength(doubleArray);
    jdouble *doubleCArray = env->GetDoubleArrayElements(doubleArray, NULL);
    std::string str = "count";
    std::string str2 = "count";
    std::string* ptr = &str;
    myDpCount = new DpCount(ptr, false, custom_epsilon);
    jint i = 0;

    while( i < size) {
        myDpCount->AddEntry(doubleCArray[i]);
        i++;
    }

    int result = myDpCount->Result(ptr);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;

}
// ****************************
// DP SUM
// ****************************
JNIEXPORT jint JNICALL Java_Dp_1func_DpSum___3I(JNIEnv *env, jobject, jintArray intArray){

    jsize size = env->GetArrayLength(intArray);
    jint *intCArray = env->GetIntArrayElements(intArray, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, true);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(intCArray[i]);

        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }

    int result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;

}

JNIEXPORT jint JNICALL Java_Dp_1func_DpSum___3ID(JNIEnv *env, jobject, jintArray intArray, jdouble epsilon){

    jsize size = env->GetArrayLength(intArray);
    jint *intCArray = env->GetIntArrayElements(intArray, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, false, epsilon, true);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(intCArray[i]);


        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }


    int result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;

}

JNIEXPORT jint JNICALL Java_Dp_1func_DpSum___3IDDD(JNIEnv *env, jobject, jintArray intArray, jdouble epsilon, jdouble lower, jdouble upper){

    jsize size = env->GetArrayLength(intArray);
    jint *intCArray = env->GetIntArrayElements(intArray, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, false, epsilon, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(intCArray[i]);


        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }


    int result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;

}

JNIEXPORT jint JNICALL Java_Dp_1func_DpSum___3IDD(JNIEnv *env, jobject, jintArray intArray, jdouble lower, jdouble upper){

    jsize size = env->GetArrayLength(intArray);
    jint *intCArray = env->GetIntArrayElements(intArray, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, true, -1, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(intCArray[i]);


        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }

    int result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;

}


JNIEXPORT jdouble JNICALL Java_Dp_1func_DpSum___3D(JNIEnv *env, jobject, jdoubleArray array){

    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, true);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(CArray[i]);
        i++;
    }

    double result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;

}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpSum___3DD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon){

    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, false, epsilon, true);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(CArray[i]);
        i++;
    }


    double result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;

}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpSum___3DDDD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon, jdouble lower, jdouble upper){

    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, false, epsilon, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(CArray[i]);
        i++;
    }


    double result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;

}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpSum___3DDD(JNIEnv *env, jobject, jdoubleArray array, jdouble lower, jdouble upper){

    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "sum";
    std::string str2 = "sum";
    std::string* pointerSum = &str;
    myDpSum = new DpSum(pointerSum, true, -1, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpSum->AddEntry(CArray[i]);
        i++;
    }

    double result = myDpSum->Result(pointerSum);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;

}








JNIEXPORT jdouble JNICALL Java_Dp_1func_DpMean___3D(JNIEnv *env, jobject, jdoubleArray array){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "mean";
    std::string str2 = "mean";
    std::string* pointerMean = &str;
    myDpMean = new DpMean(pointerMean, true);
    jint i = 0;

    while( i < size) {
        myDpMean->AddEntry(CArray[i]);

        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }

    double result = myDpMean->Result(pointerMean);
    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpMean___3DD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "mean";
    std::string str2 = "mean";
    std::string* pointerMean = &str;
    myDpMean = new DpMean(pointerMean, false, epsilon, true);
    jint i = 0;

    while( i < size) {
        myDpMean->AddEntry(CArray[i]);

        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }

    double result = myDpMean->Result(pointerMean);
    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpMean___3DDDD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "mean";
    std::string str2 = "mean";
    std::string* pointerMean = &str;
    myDpMean = new DpMean(pointerMean, false, epsilon, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpMean->AddEntry(CArray[i]);

        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }

    double result = myDpMean->Result(pointerMean);
    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpMean___3DDD(JNIEnv *env, jobject, jdoubleArray array, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "mean";
    std::string str2 = "mean";
    std::string* pointerMean = &str;
    myDpMean = new DpMean(pointerMean, true, -1, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpMean->AddEntry(CArray[i]);

        // std::cout << "Entry: " << doubleCArray[i] << std::endl;
        i++;
    }

    double result = myDpMean->Result(pointerMean);
    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}


JNIEXPORT double JNICALL Java_Dp_1func_DpVariance___3D(JNIEnv *env, jobject, jdoubleArray array){
    jsize size = env->GetArrayLength(array);
    jdouble *Carray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "var";
    std::string str2 = "var";
    std::string* pointerVar = &str;
    myDpVariance = new DpVariance(pointerVar, true);
    jint i = 0;

    while( i < size) {
        myDpVariance->AddEntry(Carray[i]);
        i++;
    }
    double result = myDpVariance->Result(pointerVar);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT double JNICALL Java_Dp_1func_DpVariance___3DD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon){
    jsize size = env->GetArrayLength(array);
    jdouble *Carray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "var";
    std::string str2 = "var";
    std::string* pointerVar = &str;
    myDpVariance = new DpVariance(pointerVar, false, epsilon, true);
    jint i = 0;

    while( i < size) {
        myDpVariance->AddEntry(Carray[i]);
        i++;
    }
    double result = myDpVariance->Result(pointerVar);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT double JNICALL Java_Dp_1func_DpVariance___3DDDD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jdouble *Carray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "var";
    std::string str2 = "var";
    std::string* pointerVar = &str;
    myDpVariance = new DpVariance(pointerVar, false, epsilon, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpVariance->AddEntry(Carray[i]);
        i++;
    }
    double result = myDpVariance->Result(pointerVar);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT double JNICALL Java_Dp_1func_DpVariance___3DDD(JNIEnv *env, jobject, jdoubleArray array, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jdouble *Carray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "var";
    std::string str2 = "var";
    std::string* pointerVar = &str;
    myDpVariance = new DpVariance(pointerVar, true, -1.0, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpVariance->AddEntry(Carray[i]);
        i++;
    }
    double result = myDpVariance->Result(pointerVar);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}


JNIEXPORT jdouble JNICALL Java_Dp_1func_DpStd___3D(JNIEnv *env, jobject, jdoubleArray array){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "std";
    std::string str2 = "std";
    std::string* pointerStd = &str;
    myDpStDev = new DpStandardDeviation(pointerStd, true);
    jint i = 0;

    while( i < size) {
        myDpStDev->AddEntry(CArray[i]);
        i++;
    }
    double result = myDpStDev->Result(pointerStd);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpStd___3DD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "std";
    std::string str2 = "std";
    std::string* pointerStd = &str;
    myDpStDev = new DpStandardDeviation(pointerStd, false, epsilon, true);
    jint i = 0;

    while( i < size) {
        myDpStDev->AddEntry(CArray[i]);
        i++;
    }
    double result = myDpStDev->Result(pointerStd);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpStd___3DDDD(JNIEnv *env, jobject, jdoubleArray array, jdouble epsilon, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "std";
    std::string str2 = "std";
    std::string* pointerStd = &str;
    myDpStDev = new DpStandardDeviation(pointerStd, false, epsilon, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpStDev->AddEntry(CArray[i]);
        i++;
    }
    double result = myDpStDev->Result(pointerStd);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpStd___3DDD(JNIEnv *env, jobject, jdoubleArray array, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "std";
    std::string str2 = "std";
    std::string* pointerStd = &str;
    myDpStDev = new DpStandardDeviation(pointerStd, true, -1, false, lower, upper);
    jint i = 0;

    while( i < size) {
        myDpStDev->AddEntry(CArray[i]);
        i++;
    }
    double result = myDpStDev->Result(pointerStd);

    if (str != str2) {
        std::cout << str << std::endl;
    }
    return result;
}


JNIEXPORT jdouble JNICALL Java_Dp_1func_DpNtile___3DDDD(JNIEnv *env, jobject, jdoubleArray array, jdouble percentile, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "ntile";
    std::string str2 = "ntile";
    std::string* pointerNtile = &str;
    myDpNtile = new DpNtile(pointerNtile, percentile, lower, upper, true);
    jint i = 0;

    while( i < size) {
        myDpNtile->AddEntry(CArray[i]);
        i++;
    }

    double result = myDpNtile->Result(pointerNtile);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;
}

JNIEXPORT jdouble JNICALL Java_Dp_1func_DpNtile___3DDDDD(JNIEnv *env, jobject, jdoubleArray array, jdouble percentile, jdouble lower, jdouble upper, jdouble epsilon){
    jsize size = env->GetArrayLength(array);
    jdouble *CArray = env->GetDoubleArrayElements(array, NULL);
    std::string str = "ntile";
    std::string str2 = "ntile";
    std::string* pointerNtile = &str;
    myDpNtile = new DpNtile(pointerNtile, percentile, lower, upper, false, epsilon);
    jint i = 0;

    while( i < size) {
        myDpNtile->AddEntry(CArray[i]);
        i++;
    }

    double result = myDpNtile->Result(pointerNtile);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;
}

JNIEXPORT jint JNICALL Java_Dp_1func_DpNtile___3IDDD(JNIEnv *env, jobject, jintArray array, jdouble percentile, jdouble lower, jdouble upper){
    jsize size = env->GetArrayLength(array);
    jint *CArray = env->GetIntArrayElements(array, NULL);
    std::string str = "ntile";
    std::string str2 = "ntile";
    std::string* pointerNtile = &str;
    myDpNtile = new DpNtile(pointerNtile, percentile, lower, upper, true);
    jint i = 0;

    while( i < size) {
        myDpNtile->AddEntry(CArray[i]);
        i++;
    }

    int result = myDpNtile->Result(pointerNtile);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;
}

JNIEXPORT jint JNICALL Java_Dp_1func_DpNtile___3IDDDD(JNIEnv *env, jobject, jintArray array, jdouble percentile, jdouble lower, jdouble upper, jdouble epsilon){
    jsize size = env->GetArrayLength(array);
    jint *CArray = env->GetIntArrayElements(array, NULL);
    std::string str = "ntile";
    std::string str2 = "ntile";
    std::string* pointerNtile = &str;
    myDpNtile = new DpNtile(pointerNtile, percentile, lower, upper, false, epsilon);
    jint i = 0;

    while( i < size) {
        myDpNtile->AddEntry(CArray[i]);
        i++;
    }

    int result = myDpNtile->Result(pointerNtile);

    if (str != str2) {
        std::cout << str << std::endl;
    }

    return result;
}
