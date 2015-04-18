#!/bin/bash
logstash_log_base=~/logstash-1.4.2/_sample_log/spam_mail_log/
curl -XDELETE 'hadoop01:9200/sample'
rm $logstash_log_base/sincedb.db
./logstash -f wr_spam_mail_2_el.conf | tee wr_spam_mail_2_el.log
