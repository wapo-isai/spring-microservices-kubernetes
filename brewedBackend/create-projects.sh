#!/usr/bin/env bash

cd microservices

spring init \
--boot-version=3.3.4 \
--type=maven-project \
--java-version=17 \
--packaging=jar \
--name=gateway \
--package-name=com.wapo.springcloud.gateway \
--groupId=com.wapo.springcloud.gateway \
--dependencies=actuator \
--version=1.0.0-SNAPSHOT \
gateway

cd ..
