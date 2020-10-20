#!/bin/bash

gradle jar

TEST_COUNT=10
result=0
for ((i = 1 ; i <= $TEST_COUNT ; i++)); do
    num=$(printf "%03d" $i)
    echo "test $num"
    java -jar build/libs/virt-mem.jar testData/test${num}.txt >testData/test${num}.log
    #"./testData/test${i}.sh" >"testData/test${i}.out"
    if [[ $? != 0 ]]; then
        echo "FAILED";
        result=1;
        continue;
    fi;
    if (diff testData/test${num}.out testData/expected${num}.out) && (diff testData/test${num}.log testData/expected${num}.log); then
        echo "PASSED"
    else
        echo "FAILED"
        result=1
    fi;
done
exit $result

