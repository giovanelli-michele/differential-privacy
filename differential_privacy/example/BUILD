#
# Copyright 2019 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# The files in this directory offer an example of how to use the C++
# Differential Privacy library.

licenses(["notice"])  # Apache v2.0

package(default_visibility = ["//visibility:public"])

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

cc_binary(
    name = "report_the_carrots",
    srcs = ["report_the_carrots.cc"],
    deps = [
        ":animals_and_carrots",
        "//differential_privacy/algorithms:confidence_interval_cc_proto",
        "//differential_privacy/algorithms:util",
        "//differential_privacy/proto:data_cc_proto",
        "//differential_privacy/proto:util-lib",
        "@com_google_absl//absl/flags:flag",
        "@com_google_absl//absl/strings:str_format",
    ],
)

cc_library(
    name = "animals_and_carrots",
    srcs = [
        "ExampleJava.cc",
        "animals_and_carrots.cc",
    ],
    hdrs = [
        "ExampleJava.h",
        "animals_and_carrots.h",
    ],
    copts = ["-Wno-sign-compare"],
    data = ["animals_and_carrots.csv"],
    deps = [
        ":copy_jni_hdr_lib",
        "//differential_privacy/algorithms:bounded-mean",
        "//differential_privacy/algorithms:bounded-sum",
        "//differential_privacy/algorithms:count",
        "//differential_privacy/algorithms:order-statistics",
        "//differential_privacy/base:statusor",
        "//differential_privacy/proto:data_cc_proto",
        "@com_google_absl//absl/strings",
    ],
    alwayslink = True,
)

cc_test(
    name = "animals_and_carrots_test",
    srcs = ["animals_and_carrots_test.cc"],
    copts = ["-Wno-sign-compare"],
    deps = [
        ":animals_and_carrots",
        "//differential_privacy/base:status",
        "//differential_privacy/base/testing:status_matchers",
        "@com_google_googletest//:gtest_main",
    ],
)

cc_binary(
    name = "libexample.so",
    copts = ["-Wno-sign-compare"],
    linkshared = 1,
    linkstatic = 1,
    deps = [
        ":animals_and_carrots",
    ],
)

java_binary(
    name = "ExampleJava",
    srcs = [
        "ExampleJava.java",
    ],
    main_class = "ExampleJava",
    deps = [":libexample.so"],
)
