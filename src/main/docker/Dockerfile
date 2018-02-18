FROM java:8-jdk
MAINTAINER Ipman1971

ARG user=masmovil
ARG artifact=masmovil-challenge-1.0.0.jar

VOLUME /tmp
EXPOSE 8085

ENV USER_NAME $user
ENV APP_HOME /home/$USER_NAME/apps

RUN useradd -ms /bin/bash $USER_NAME && mkdir $APP_HOME

ADD $artifact $APP_HOME/application.jar

RUN chown $USER_NAME $APP_HOME/application.jar

USER $USER_NAME
WORKDIR $APP_HOME

RUN bash -c 'touch application.jar'

ENTRYPOINT ["java","-jar","application.jar"]
