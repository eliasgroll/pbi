where java.exe >nul 2>nul
if %errorlevel%==1 (
    @echo java.exe not found in path. stopping at this point.
) else (
	java -jar %~dp0\pbi.jar %1 %2 %3 %4
)