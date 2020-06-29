#!/usr/bin/env bash

# Get process ids, expects fzf to be installed.

base_url=http://localhost:8080/application/
tmpf=/tmp/foo.txt

# Read ids
curl -s $base_url | jq ".[].id" | fzf | tr -d '"' > $tmpf

# Read process info

process_id=$(cat $tmpf)
# echo "selected process_id : $process_id"

# Get task ids
curl -s $base_url$process_id/tasks | jq 'keys[]' | tr -d '"' > $tmpf

task_id=$(cat $tmpf)

tid=$base_url$process_id/Task/$task_id

curl -s $tid | jq .

# Complete it
echo "Url used: $tid" 1>&2
echo "Complete task?"
read

curl -s -X POST -H "content-type: application/json" -d "{}" $tid

