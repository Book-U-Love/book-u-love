#!/bin/sh

if ! docker image ls --format="{{ .Repository }}:{{ .Tag }}" | grep -c gradle:8.3.0-jdk17-jammy-docker >/dev/null; then
    tmp_dir=Dockerfile.meo-s.github.com
    rm -rf $tmp_dir && git clone https://github.com/meo-s/Dockerfile $tmp_dir
    docker build -t gradle:8.3.0-jdk17-jammy-docker $tmp_dir/gradle:8.3.0-jdk17-jammy-docker/
    rm -rf $tmp_dir
fi
