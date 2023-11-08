#!/bin/bash

repo_dir="$(realpath "$(dirname "$0")/..")"

# shellcheck disable=all
. $repo_dir/scripts/include/log.sh

kind_cluster_name=$1
if [[ ${#kind_cluster_name} == 0 ]]; then
    fatal "kind cluster name must be given."
fi
for image in $(docker image ls --format="{{ .Repository }}:{{ .Tag }}" | grep -E "^bookulove/.+"); do
    if [[ $image =~ example-service ]]; then
        continue
    fi
    if ! kind load docker-image "$image" --name "$kind_cluster_name" 1>/dev/null 2>/dev/null; then
        fatal "failed to load $image."
    fi
    info "$image was successfully loaded to kind cluster \"$kind_cluster_name\"."
done
