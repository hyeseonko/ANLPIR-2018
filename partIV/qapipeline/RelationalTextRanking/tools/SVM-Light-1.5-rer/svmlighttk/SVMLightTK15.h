/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class svmlighttk_SVMLightTK */

#ifndef _Included_svmlighttk_SVMLightTK15
#define _Included_svmlighttk_SVMLightTK15
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     svmlighttk_SVMLightTK
 * Method:    load_model
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_svmlighttk_SVMLightTK15_load_1model
  (JNIEnv *, jclass, jstring);

/*
 * Class:     svmlighttk_SVMLightTK
 * Method:    get_threshold
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_svmlighttk_SVMLightTK15_get_1threshold
  (JNIEnv *, jclass);

/*
 * Class:     svmlighttk_SVMLightTK
 * Method:    classify_instance
 * Signature: (ILjava/lang/String;)D
 */
JNIEXPORT jdouble JNICALL Java_svmlighttk_SVMLightTK15_classify_1instance
  (JNIEnv *, jclass, jint, jstring);

#ifdef __cplusplus
}
#endif
#endif
