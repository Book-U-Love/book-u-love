#!/bin/bash

set -e
set -o pipefail

MAIN_DIR="$(realpath "$(dirname "$0")/..")"
OPENAPI_SPECS_DIR="$MAIN_DIR/docker-compose/data/swagger-ui/specs"
GRADLE_IMAGE=gradle:8.3.0-jdk17-jammy

function openapi3-task {
    docker run --rm -i \
        --user "$(id -u):$(id -g)" \
        --volume "$MAIN_DIR/workspace/bookulove-backend:/workspace" \
        $GRADLE_IMAGE sh -c "cd /workspace && gradle clean && gradle openapi3 --parallel --build-cache -g .gradle.cache"
    return $?
}

function prepare-swagger-ui {
    mkdir -p "$OPENAPI_SPECS_DIR"
    rm -f "$OPENAPI_SPECS_DIR"/*.yaml

    OPENAPI_SPEC_URLS=
    for SUBPROJECT_DIR in "$MAIN_DIR/workspace/bookulove-backend/"*; do
        if ! [ -d "$SUBPROJECT_DIR" ]; then
            continue
        fi

        if ! [ -f "$SUBPROJECT_DIR/build/api-spec/openapi3.yaml" ]; then
            continue
        fi

        SERVICE_NAME="$(echo "$SUBPROJECT_DIR" | rev | cut -d "/" -f 1 | rev)"
        cp "$SUBPROJECT_DIR/build/api-spec/openapi3.yaml" "$OPENAPI_SPECS_DIR/$SERVICE_NAME.yaml"
        OPENAPI_SPEC_URLS="$OPENAPI_SPEC_URLS,{url:\"/swagger-ui/specs/$SERVICE_NAME.yaml\",name:\"$SERVICE_NAME\"}"
    done

    mkdir -p "$MAIN_DIR/docker-compose/env"
    echo "URLS='[${OPENAPI_SPEC_URLS:1}]'" >"$MAIN_DIR/docker-compose/env/swagger-ui.env"
}

openapi3-task
prepare-swagger-ui
