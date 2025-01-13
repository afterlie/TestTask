# shellcheck disable=SC2046
test_file="src/test/resources/FailedTests.txt"
if [ -f "$test_file" ]; then
  if [ -s "$test_file" ]; then
    mvn clean test -Dtest="$(tr '\n' ',' < $test_file)"
  else
    echo "test file is empty"
  fi
fi
rm -rf allure-results/*
