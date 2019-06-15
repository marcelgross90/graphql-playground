
all:
	mvn clean verify

build:
	mvn clean package

start:
	java -jar target/wishlist-0.0.1-SNAPSHOT.jar