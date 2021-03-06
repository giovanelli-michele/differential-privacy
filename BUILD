#
# Copyright 2019 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

""" Additional bazel imports for differential privacy library """

exports_files([
    "differential_privacy_deps.bzl",
])

cc_binary(
    name = "libdp-func.so",
    copts = ["-Wno-sign-compare"],
    linkshared = 1,
    linkstatic = 1,
    deps = [
        ":dp_func",
        "//differential_privacy/algorithms:bounded-mean",
        "//differential_privacy/algorithms:bounded-standard-deviation",
        "//differential_privacy/algorithms:bounded-sum",
        "//differential_privacy/algorithms:bounded-variance",
        "//differential_privacy/algorithms:count",
        "//differential_privacy/algorithms:order-statistics",
        "//differential_privacy/algorithms:util",
    ],
)

cc_library(
    name = "dp_func",
    srcs = [
        "//differential_privacy/spark:Wdp_func.cc",
        "//differential_privacy/spark:dp_func.cc",
    ],
    hdrs = [
        "//differential_privacy/spark:Wdp_func.h",
        "//differential_privacy/spark:dp_func.h",
        "//differential_privacy/spark:jni.h",
        "//differential_privacy/spark:jni_md.h",
    ],
    copts = ["-Wno-sign-compare"],
    deps = [
        "//differential_privacy/algorithms:bounded-mean",
        "//differential_privacy/algorithms:bounded-standard-deviation",
        "//differential_privacy/algorithms:bounded-sum",
        "//differential_privacy/algorithms:bounded-variance",
        "//differential_privacy/algorithms:count",
        "//differential_privacy/algorithms:order-statistics",
        "//differential_privacy/algorithms:util",
    ],
    alwayslink = True,
)

java_binary(
    name = "DiffPrivacy",
    srcs = [
        "//differential_privacy/spark:DiffPrivacyCount.java",
        "//differential_privacy/spark:InvalidInputValuesException.java",
    ],
    main_class = "DiffPrivacyCount",
    resources = [":libdp-func.so"],
    runtime_deps = [":libdp-func.so"],
    deps = [
        ":libdp-func.so",
        "@maven//:org_apache_spark_spark_catalyst_2_12",
        "@maven//:org_apache_spark_spark_sql_2_12",
    ],
)
