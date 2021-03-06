/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class DpSum */

#ifndef _Included_DpSum
#define _Included_DpSum
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     DpSum
 * Method:    createAlgorithm
 * Signature: (ZZDDD)Z
 */
JNIEXPORT jboolean JNICALL Java_DpSum_createAlgorithm
  (JNIEnv *, jobject, jboolean, jboolean, jdouble, jdouble, jdouble);

/*
 * Class:     DpSum
 * Method:    insertElement
 * Signature: (D)Z
 */
JNIEXPORT jboolean JNICALL Java_DpSum_insertElement
  (JNIEnv *, jobject, jdouble);

/*
 * Class:     DpSum
 * Method:    getAlgorithmResult
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_DpSum_getAlgorithmResult
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
