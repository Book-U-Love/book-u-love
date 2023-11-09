#!/bin/bash

set -e
set -o pipefail

docker login
for image in $(docker image ls --format="{{ .Repository }}:{{ .Tag }}"); do
    if [[ $image =~ ^bookulove/.+service.+ ]] && ! [[ $image =~ example-service ]]; then
        # docker push "$image"
        echo $image
    fi
done
