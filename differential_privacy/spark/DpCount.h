/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class DpCount */

#ifndef _Included_DpCount
#define _Included_DpCount
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     DpCount
 * Method:    createAlgorithm
 * Signature: (ZD)Z
 */
JNIEXPORT jboolean JNICALL Java_DpCount_createAlgorithm
  (JNIEnv *, jobject, jboolean, jdouble);

/*
 * Class:     DpCount
 * Method:    insertElement
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_DpCount_insertElement
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     DpCount
 * Method:    getAlgorithmResult
 * Signature: ()D
 */
JNIEXPORT jint JNICALL Java_DpCount_getAlgorithmResult
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
