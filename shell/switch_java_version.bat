@echo off

set jdk_1.6=D:\development\Java\jdk1.6.0_45
set jdk_1.7=D:\development\Java\jdk1.7.0_40

if %java_home% == %jdk_1.6% (
	setx java_home %jdk_1.7% -m
) else if %java_home% == %jdk_1.7% (
	setx java_home %jdk_1.6% -m
) else (
	setx java_home %jdk_1.6% -m
)