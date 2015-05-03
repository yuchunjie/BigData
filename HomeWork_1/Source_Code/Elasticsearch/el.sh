#!/bin/bash

curl -XDELETE http://localhost:9200/spam_mail

curl -XPOST http://localhost:9200/spam_mail -d '
{
      "mappings": {
         "6attr": {
            "properties": {
               "delivered_to": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
               "file_length": {
                  "type": "long"
               },
               "file_name": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
               "hyperlink": {
                  "type": "string"
               },
               "is_html": {
                  "type": "boolean"
               },
               "line_id": {
                  "type": "long"
               },
               "src_ip": {
                  "type": "string"
               },
               "charset": {
                  "type": "string",
                  "index" : "not_analyzed"
               }
            }
         }
      }
}'


