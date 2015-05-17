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
			   "date": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "from": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "to": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "subject": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "reply_to": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "mime_version": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "message_id": {
                  "type": "string",
                  "index" : "not_analyzed"
               },			   
			   "x_originating_ip": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "content_type": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "content_length": {
                  "type": "long",
                  "index" : "not_analyzed"
               },
			   "file_name": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "file_length": {
                  "type": "long",
                  "index" : "not_analyzed"
               },
			   "charset": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "received": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "ip": {
                  "type": "string",
                  "index" : "not_analyzed"
               },
			   "http": {
                  "type": "string",
                  "index" : "not_analyzed"
               }
            }
         }
      }
}'
