#!/bin/sh

echo 'docker任务正在执行: stop application....'
docker stop dalaran

echo 'docker任务正在执行: rm container....'
sleep 2s

docker rm dalaran

echo 'docker任务正在执行: rm image....'
sleep 2s

docker image rm dalaran:latest

echo 'docker任务正在执行: packaging....'
sleep 2s

mvn clean
mvn package -Dmaven.test.skip=true -U

echo 'docker任务正在执行: build docker image....'
sleep 3s
docker build -t dalaran .
docker images

echo 'docker任务正在执行: run docker ....'
sleep 2s
docker run -p 8080:8080  --name dalaran -d dalaran:latest
docker ps -a

echo 'docker应用启动成功: 检查集成平台服务接口....'
sleep 2s
curl -X POST   http://localhost:8080/author/ask  -H 'content-type: application/json'  -d '{ "name":"visitor name"}'