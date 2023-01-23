*** Settings ***
Documentation     A resource file with reusable keywords and variables related to login and invalid login. Juha Kärnä
Library           SeleniumLibrary

*** Variables ***
${SERVER}         www.verkkokauppa.com
${BROWSER}        Chrome
${DELAY}          0.7
${VALID USER}     email4.email@gmail.com
${VALID PASSWORD}    Mode1234
${LOGIN URL}      https://${SERVER}/fi/account/login
${WELCOME URL}    https://${SERVER}/fi/orders
${ERROR URL}      https://${SERVER}/fi/account/login/email

*** Keywords ***
Open Browser To Login Page
    Open Browser    ${LOGIN URL}    ${BROWSER}
    Maximize Browser Window
    Set Selenium Speed    ${DELAY}
    Login Page Should Be Open

Login Page Should Be Open
    Title Should Be    Kirjaudu sisään – Verkkokauppa.com

Go To Login Page
    Go To    ${LOGIN URL}
    Login Page Should Be Open

Input Username
    [Arguments]    ${username}
    Input Text    login-form-email    ${username}

Input Password
    [Arguments]    ${password}
    Input Text    login-form-password    ${password}

Submit Credentials
    Click Button    login-button

Welcome Page Should Be Open
    Set Selenium Speed    ${DELAY}
    Location Should Be    ${WELCOME URL}
    Title Should Be    Avoimet tilaukset - Tilinhallinta – Verkkokauppa.com