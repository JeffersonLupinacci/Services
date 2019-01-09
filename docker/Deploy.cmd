@echo off
cls

@echo.
@echo Build Steps
@echo.

call Commands\Clear.cmd
call Commands\BuildMaven.cmd
call Commands\Keytools.cmd
call Commands\CreateBaseImage.cmd
call Commands\BuildCompose.cmd
