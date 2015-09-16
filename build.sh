#! /bin/bash
#
# date : 2015 09 15
# author : Benjamin Sientzoff
# build streamAnalyser with ant
#

LIB="/lib"
BUILD_FILE="streamanalyser.xml"
BUILD_PROP="streamanalyser.properties"
BUILD_THREAD_CLASS="dv147.TestParrallelAcessor"
BUILD_MONO_CLASS="dv147.StreamAnalyzer"


# teste le bon nombre de paramètres
if [ $# -ne 1 ] ; then
	echo "Give one argument : ";
	echo "	thread : to build using thread";
	echo "	mono : to build using monolotic";
	exit 1;
fi

if [ "thread" = "$1" ] ; then
	MAIN_CLASS="$BUILD_THREAD_CLASS";
	else
		MAIN_CLASS="$BUILD_MONO_CLASS";
fi

echo $LIB;
echo $BUILD_FILE;
echo $BUILD_PROP;
echo $MAIN_CLASS ;
ant -q -lib $LIB -f $BUILD_FILE -propertyfile $BUILD_PROP -main $MAIN_CLASS ;


exit 0;