genrule(
    name = "copy_link_jni_md_header",
    srcs = ["@bazel_tools//tools/jdk:jni_md_header-linux"],
    outs = ["jni_md.h"],
    cmd = "cp -f $< $@",
)

genrule(
    name = "copy_link_jni_header",
    srcs = ["@bazel_tools//tools/jdk:jni_header"],
    outs = ["jni.h"],
    cmd = "cp -f $< $@",
)

cc_library(
    name = "copy_jni_hdr_lib",
    hdrs = [
        ":copy_link_jni_header",
        ":copy_link_jni_md_header",
    ],
    includes = ["."],
)

cc_library(
    name = "differentialPrivacy",
    srcs = [
        "DpFunc.cc",
    ],
    hdrs = [
        "DpFunc.h",
    ],
    copts = ["-Wno-sign-compare"],
    deps = [
        ":copy_jni_hdr_lib",
        "//differential_privacy/algorithms:algorithm",
        "//differential_privacy/algorithms:bounded-mean",
        "//differential_privacy/algorithms:bounded-standard-deviation",
        "//differential_privacy/algorithms:bounded-sum",
        "//differential_privacy/algorithms:bounded-variance",
        "//differential_privacy/algorithms:count",
        "//differential_privacy/algorithms:order-statistics",
        "//differential_privacy/algorithms:util",
        "//differential_privacy/postgres:dp_func",
    ],
    alwayslink = True,
)

cc_binary(
    name = "libdpjava.so",
    copts = ["-Wno-sign-compare"],
    linkshared = 1,
    linkstatic = 1,
    deps = [
        ":differentialPrivacy",
    ],
)

java_binary(
    name = "DpFuncJava",
    srcs = [
        "DpFunc.java",
    ],
    main_class = "DpFunc",
    resources = [":libdpjava.so"],
    runtime_deps = [":libdpjava.so"],
    deps = [":libdpjava.so"],
)
