*** Settings ***
Documentation     A test with a single test for valid login. Juha Kärnä
Resource          resourceLogin.robot

*** Test Cases ***
Valid Login
    Open Browser To Login Page
    Input Username    email4.email@gmail.com
    Input Password    Mode1234
    Submit Credentials
    Welcome Page Should Be Open
    [Teardown]    Close Browser