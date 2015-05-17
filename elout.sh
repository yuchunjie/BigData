#!/bin/bash


curl -XPOST localhost:9200 -d '
{
"query": {
"bool": {
"must": [
{
"match_all": { }
}
],
"must_not": [ ],
"should": [ ]
}
},
"from": 0,
"size": 10,
"sort": [ ],
"facets": { }
}
'


