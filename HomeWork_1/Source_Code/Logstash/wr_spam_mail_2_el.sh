#!/bin/bash
logstash_log_base=~/logstash-1.4.2/_sample_log/spam_mail_log/
logstash_conf=wr_spam_mail_2_el.conf
curl -XDELETE 'hadoop01:9200/sample'
rm $logstash_log_base/sincedb.db
./logstash -f $logstash_conf  --configtest &&  ./logstash -f $logstash_conf | tee wr_spam_mail_2_el.log
