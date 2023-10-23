# GitRepo

This is an IntelliJ project to test SAP CPI message interaction and transformation.

IMPORTANT: Please keep in mind that after checking out these folders, it is important to set correct current location of message JAR file in dependancies.
TO do this, open GroovyCPIMockMsg project -> open Project Structure(hotkey CTRL+ALT+SHIFT+S) -> navigate to Global Libraries -> there should be apache.camel.core and cpi-mock-msg ->
-> click on cpi-mock-msg -> remove class used in this project and add your own jar file (for me it was C:\Users\hreskjan\IdeaProjects\GroovyCPIMockMsg\out\artifacts\cpi_mock_msg\cpi-mock-msg.jar)

Have fun.
