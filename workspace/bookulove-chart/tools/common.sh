#!/bin/bash

if ! command -v yq >/dev/null; then
    echo "\"yq\" command not found."
    echo "* https://github.com/mikefarah/yq"
    exit 1
fi

MAIN_DIR="$(realpath "$(dirname "$0")/..")"
export MAIN_DIR
