
FROM openjdk:11-jre-slim

LABEL maintainer="Fernando Pineda<fernandopineda3105@gmail.com>"

VOLUME /tmp
RUN export DEBIAN_FRONTEND=noninteractive \
    && apt-get update \
    && apt-get install -y --no-install-recommends \
    && apt-get install -y procps \
    && apt-get install tzdata \
	&& rm -rf /var/lib/apt/lists/*; \
	echo America/Lima > /etc/timezone; \
    rm /etc/localtime; \
	dpkg-reconfigure -fnoninteractive tzdata


ADD target/users-api-*.jar app.jar
ADD entrypoint.sh entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
RUN ["chmod", "+x", "/entrypoint.sh"]