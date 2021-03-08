#!/bin/bash

export PLATFORM_SAMPLES_INSTALLDIR=
export LIBDIR=PLATFORM_SAMPLES_INSTALLDIR/api-platform-samples/doc-samples/java-hello/lib 
javac -d target -classpath ${LIBDIR}/expressions-1.0.0.jar:${LIBDIR}/message-flow-1.0.0.jar:. com/apigee/edge/saml/EncodeSAML.java
cd target
jar -cf EncodeSAML.jar com
find . 

