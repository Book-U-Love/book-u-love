#!/bin/bash

repo_dir="$(realpath "$(dirname "$0")/..")"

# shellcheck disable=all
. $repo_dir/scripts/include/log.sh
# shellcheck disable=all
. $repo_dir/scripts/include/require-gradle-8.3.0-jdk17-jammy-docker.sh

for service_dir in "$repo_dir/workspace/bookulove-backend/"*; do
    if test -d "$service_dir/src/main/resources"; then
        application_yaml="$(find "$service_dir/src/main/resources" -type f -regex ".+/application.ya?ml" -print)"
        yq -i ".spring.profiles.active = \"prod\"" "$application_yaml"
    fi
done

docker run --rm -it \
    --volume /var/run/docker.sock:/var/run/docker.sock \
    --volume "$repo_dir/workspace/bookulove-backend:/workspace" \
    gradle:8.3.0-jdk17-jammy-docker \
    sh -c "cd /workspace && gradle clean && gradle bootBuildImage -x test --parallel --build-cache -g .gradle.cache"

if test "$?" -neq 0; then
    fatal "failed to build any of services."
fi

for image in $(docker image ls --format="{{ .Repository }}:{{ .Tag }}+{{ .ID }}"); do
    if [[ $image =~ ^\<none\>:\<none\>\+(.+) ]]; then
        docker image rm "$(echo "$image" | rev | cut -d+ -f1 | rev)"
    fi
done
