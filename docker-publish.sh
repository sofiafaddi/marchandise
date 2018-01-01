#!/usr/bin/env bash
docker login -u admin -p admin123 localhost:8183
docker login -u admin -p admin123 localhost:8182
docker build -t tiw1-ci:2017-1 tiw1-ci
docker tag tiw1-ci:2017-1 localhost:8183/tiw1-ci:2017-1
docker push localhost:8183/tiw1-ci:2017-1
