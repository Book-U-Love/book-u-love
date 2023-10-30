#!/bin/bash

# shellcheck source=/dev/null
source "$(dirname "$0")/common.sh"

# shellcheck disable=SC2005,SC2094
yq -i ".defaultStorageClass=\"standard\"" values.yaml
