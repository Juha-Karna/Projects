*** Settings ***
Documentation     A test containing tests related to invalid login. Juha Kärnä
Suite Setup       Open Browser To Login Page
Suite Teardown    Close Browser
Test Setup        Go To Login Page
Test Template     Login With Invalid Credentials Should Fail
Resource          resourceLogin.robot

*** Test Cases ***               USER NAME        PASSWORD
Invalid Username                 invalid@gmail.com          ${VALID PASSWORD}
Invalid Password                 ${VALID USER}    invalid
Invalid Username And Password    invalid@gmail.com          sampletext
Empty Password                   ${VALID USER}    ${EMPTY}

*** Keywords ***
Login With Invalid Credentials Should Fail
    [Arguments]    ${username}    ${password}
    Input Username    ${username}
    Input Password    ${password}
    Submit Credentials
    Login Should Have Failed

Login Should Have Failed
    Location Should Be    ${ERROR URL}
    Title Should Be    Kirjaudu sisään – Verkkokauppa.com
    