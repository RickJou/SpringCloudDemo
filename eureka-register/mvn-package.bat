rd /s /Q target
rd /s /Q logs

echo mvn package -Dmaven.test.skip=true >> temp.bat
echo del temp.bat >> temp2.bat
echo del temp2.bat >> temp2.bat
@call temp.bat
@call temp2.bat
pause