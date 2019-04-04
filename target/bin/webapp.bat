@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\glassfish\jersey\core\jersey-server\2.4.1\jersey-server-2.4.1.jar;"%REPO%"\org\glassfish\jersey\core\jersey-common\2.4.1\jersey-common-2.4.1.jar;"%REPO%"\javax\annotation\javax.annotation-api\1.2\javax.annotation-api-1.2.jar;"%REPO%"\org\glassfish\hk2\osgi-resource-locator\1.0.1\osgi-resource-locator-1.0.1.jar;"%REPO%"\org\glassfish\jersey\core\jersey-client\2.4.1\jersey-client-2.4.1.jar;"%REPO%"\javax\ws\rs\javax.ws.rs-api\2.0\javax.ws.rs-api-2.0.jar;"%REPO%"\com\google\guava\guava\14.0.1\guava-14.0.1.jar;"%REPO%"\org\glassfish\hk2\hk2-api\2.2.0-b21\hk2-api-2.2.0-b21.jar;"%REPO%"\org\glassfish\hk2\hk2-utils\2.2.0-b21\hk2-utils-2.2.0-b21.jar;"%REPO%"\org\glassfish\hk2\external\javax.inject\2.2.0-b21\javax.inject-2.2.0-b21.jar;"%REPO%"\org\glassfish\hk2\hk2-locator\2.2.0-b21\hk2-locator-2.2.0-b21.jar;"%REPO%"\org\glassfish\hk2\external\asm-all-repackaged\2.2.0-b21\asm-all-repackaged-2.2.0-b21.jar;"%REPO%"\org\glassfish\hk2\external\cglib\2.2.0-b21\cglib-2.2.0-b21.jar;"%REPO%"\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;"%REPO%"\org\glassfish\jersey\containers\jersey-container-servlet-core\2.4.1\jersey-container-servlet-core-2.4.1.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-json-jackson\2.4.1\jersey-media-json-jackson-2.4.1.jar;"%REPO%"\org\codehaus\jackson\jackson-core-asl\1.9.11\jackson-core-asl-1.9.11.jar;"%REPO%"\org\codehaus\jackson\jackson-mapper-asl\1.9.11\jackson-mapper-asl-1.9.11.jar;"%REPO%"\org\codehaus\jackson\jackson-jaxrs\1.9.11\jackson-jaxrs-1.9.11.jar;"%REPO%"\org\codehaus\jackson\jackson-xc\1.9.11\jackson-xc-1.9.11.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-core\8.5.34\tomcat-embed-core-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-annotations-api\8.5.34\tomcat-annotations-api-8.5.34.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-jasper\8.5.34\tomcat-embed-jasper-8.5.34.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-el\8.5.34\tomcat-embed-el-8.5.34.jar;"%REPO%"\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper\8.5.34\tomcat-jasper-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-servlet-api\8.5.34\tomcat-servlet-api-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-juli\8.5.34\tomcat-juli-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-jsp-api\8.5.34\tomcat-jsp-api-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-el-api\8.5.34\tomcat-el-api-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper-el\8.5.34\tomcat-jasper-el-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-api\8.5.34\tomcat-api-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-util-scan\8.5.34\tomcat-util-scan-8.5.34.jar;"%REPO%"\org\apache\tomcat\tomcat-util\8.5.34\tomcat-util-8.5.34.jar;"%REPO%"\org\postgresql\postgresql\42.2.1\postgresql-42.2.1.jar;"%REPO%"\org\apache\derby\derbyclient\10.13.1.1\derbyclient-10.13.1.1.jar;"%REPO%"\ca\lambtoncollege\CSD4464-Heroku-2019W\1.0-SNAPSHOT\CSD4464-Heroku-2019W-1.0-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" herokusample.Main %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
