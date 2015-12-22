# vertx-tcp-bridged-chat-server
A port of the Vert.x chat Web Server in the examples repo to TCP,
so that it can use the TCP bridge to communicate with the Android Chat app: 

Normal './gradlew shadowJar' to build. Then 'java -jar build/libs/vertx-tcp-bridged-chat-server-3.2.0-fat.jar' to run.

Android client that connects to it is here: https://github.com/abdlquadri/VertxEventBusChat
