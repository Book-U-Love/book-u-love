#!/bin/bash

function info {
    script_name="$(echo "$0" | rev | cut -d / -f 1 | rev)"
    echo "[INFO|$script_name] $1"
}

function fatal {
    script_name="$(echo "$0" | rev | cut -d / -f 1 | rev)"
    echo "[FATAL|$script_name] $1"
    exit 1
}
