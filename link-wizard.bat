@echo off

pushd .

set source_path="C:\Users\jtyler\Documents\Programming\Java\Minecraft\Wizard"
set dest_path=".\src\minecraft\net\minecraft\src"


REM remove old links or files
del /s /q  %dest_path%\EntityGlowingOrb.java
del /s /q  %dest_path%\ItemExplosiveStaff.java
del /s /q  %dest_path%\ItemFireStaff.java
del /s /q  %dest_path%\ItemHolyStaff.java
del /s /q  %dest_path%\ItemLightningStaff.java
del /s /q  %dest_path%\ItemTeleportationStaff.java
del /s /q  %dest_path%\ItemWizardStaff.java
del /s /q  %dest_path%\mod_Wizard.java


REM rebuild links
mklink	%dest_path%\EntityGlowingOrb.java		%source_path%\EntityGlowingOrb.java
mklink	%dest_path%\ItemExplosiveStaff.java		%source_path%\ItemExplosiveStaff.java
mklink	%dest_path%\ItemFireStaff.java			%source_path%\ItemFireStaff.java
mklink	%dest_path%\ItemHolyStaff.java			%source_path%\ItemHolyStaff.java
mklink	%dest_path%\ItemLightningStaff.java		%source_path%\ItemLightningStaff.java
mklink	%dest_path%\ItemTeleportationStaff.java	%source_path%\ItemTeleportationStaff.java
mklink	%dest_path%\ItemWizardStaff.java		%source_path%\ItemWizardStaff.java
mklink	%dest_path%\mod_Wizard.java				%source_path%\mod_Wizard.java


popd

pause
