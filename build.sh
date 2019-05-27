#!/bin/sh

iptables -F
echo "y" | docker system prune

#pull dependent tools docker images
echo "pull docker image for kafka/zookeeper"
docker pull spotify/kafka

echo "pull docker image for kafka/zookeeper"
docker pull mongo

cd $HOME/revrep

echo "Running MongoDB"
docker run -p 27017:27017 mongo:latest &

echo "starting zookeeper/kafka server"
docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=127.0.0.1 --env ADVERTISED_PORT=9092 spotify/kafka &

#building maven command
echo "run maven clean/install"
mvn clean
mvn install -Dmaven.test.skip


echo "building revrep docker image"
docker build -t revrep .

echo "Renaming docker image"
docker tag revrep:latest revrep:v1.0

#echo "Creating a network"
#docker network create app-tier --driver bridge


#docker run -d  -p 2181:2181 --network app-tier --name zookeeper-server -e  ALLOW_ANONYMOUS_LOGIN=yes bitnami/zookeeper:latest &
#sleep 10
#docker run -d -p 9092:9092 --network app-tier --name kafka-server -e KAFKA_ZOOKEEPER_CONNECT=zookeeper-server:2181 --env ALLOW_PLAINTEXT_LISTENER=yes  bitnami/kafka:latest &
#sleep 10
#docker run -it --rm --network app-tier -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 bitnami/kafka:latest kafka-topics.sh --list  --zookeeper zookeeper-server:2181 &


sleep 5
echo "Running revrep application"
docker run -ti --net=host revrep:v1.0