#!/bin/bash

set -e
set -o pipefail

docker login
for image in $(docker image ls --format="{{ .Repository }}:{{ .Tag }}"); do
    if [[ $image =~ ^bookulove/.+service:[a-z0-9.]+ ]] && ! [[ $image =~ example-service ]]; then
        docker push "$image"
    fi
done
