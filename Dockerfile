FROM livingobjects/jre8

ADD ./target/demo-0.0.1-SNAPSHOT.jar   ./demo.jar

CMD java -jar -Djava.security.egd=file:/dev/./urandom  ./demo.jar