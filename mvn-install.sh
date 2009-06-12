#!/bin/sh
 
#install Google App Engine dependencies into local maven repository
 
#copyright 2009 Gravity Rail Pty Ltd
 
export SDK_LIB="/Users/bourke/Downloads/appengine-java-sdk-1.2.1/lib"
export SDK_VER="1.2.1"

# User libraries (should be placed in WEB-INF/lib)
 
mvn install:install-file -Dfile=${SDK_LIB}/user/appengine-api-1.0-sdk-${SDK_VER}.jar \
  -DgroupId=com.google \
  -DartifactId=appengine-api-1.0-sdk \
  -Dversion=${SDK_VER} \
  -Dpackaging=jar \
  -DgeneratePom=true

mvn install:install-file -Dfile=${SDK_LIB}/user/orm/datanucleus-appengine-1.0.1.final.jar \
  -DgroupId=org.datanucleus \
  -DartifactId=appengine \
  -Dversion=1.0.1 \
  -Dpackaging=jar \
  -DgeneratePom=true

mvn install:install-file -Dfile=${SDK_LIB}/user/orm/datanucleus-core-1.1.0.jar \
  -DgroupId=org.datanucleus \
  -DartifactId=core \
  -Dversion=1.1.0 \
  -Dpackaging=jar \
  -DgeneratePom=true
 
mvn install:install-file -Dfile=${SDK_LIB}/user/orm/datanucleus-jpa-1.1.0.jar \
  -DgroupId=org.datanucleus \
  -DartifactId=jpa \
  -Dversion=1.1.0 \
  -Dpackaging=jar \
  -DgeneratePom=true

mvn install:install-file -Dfile=${SDK_LIB}/user/orm/geronimo-jpa_3.0_spec-1.1.1.jar \
  -DgroupId=geronimo \
  -DartifactId=javax.persistence \
  -Dversion=1.1.1 \
  -Dpackaging=jar \
  -DgeneratePom=true

mvn install:install-file -Dfile=${SDK_LIB}/user/orm/geronimo-jta_1.1_spec-1.1.1.jar \
  -DgroupId=geronimo \
  -DartifactId=javax.transaction \
  -Dversion=1.1.1 \
  -Dpackaging=jar \
  -DgeneratePom=true

