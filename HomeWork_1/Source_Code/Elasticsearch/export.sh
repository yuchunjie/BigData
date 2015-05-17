#!/bin/bash

curl -XPOST http://localhost:9200/spam_mail/6attr/_export -d '{
  "running":true,
  "state": {
    "mode":"export",
    "path":"file:///root/Workspace/BigData/HomeWork_1/Source_Code/Elasticsearch/test_test.tar.gz"
  }
}'

