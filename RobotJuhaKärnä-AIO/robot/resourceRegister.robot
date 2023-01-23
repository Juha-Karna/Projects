*** Settings ***
Documentation     A resource file with reusable keywords and variables related to register.
Library           SeleniumLibrary

*** Variables ***
${SERVER}         www.verkkokauppa.com
${BROWSER}        Chrome
${DELAY}          0.7
${REGISTER URL}      https://${SERVER}/fi/account/create?next=%2Ffi%2Faccount%2Fcustomer
${WELCOME URL}    https://${SERVER}/fi/account/customer

*** Keywords ***
Open Browser To Register Page
    Open Browser    ${REGISTER URL}    ${BROWSER}
    Maximize Browser Window
    Set Selenium Speed    ${DELAY}
    Register Page Should Be Open

Register Page Should Be Open
    Title Should Be    Asiakkuuden luonti – Verkkokauppa.com

Go To Register Page
    Go To    ${REGISTER URL}
    Register Page Should Be Open

Input Username
    [Arguments]    ${username}
    Input Text    create-account-form-email    ${username}

Input Password
    [Arguments]    ${password}
    Input Text    create-account-form-password    ${password}

Input First Name
    [Arguments]    ${first-name}
    Input Text    create-account-form-first-name    ${first-name}

Input Last Name
    [Arguments]    ${last-name}
    Input Text    create-account-form-last-name    ${last-name}

Input Phone Number
    [Arguments]    ${phone-number}
    Input Text    create-account-form-phone-mobile    ${phone-number}

Check checkbox
    Click Element    //*[@id="app"]/div/div/div/div/form/fieldset/div[7]/label

Submit Credentials
    Click Button    create-account-button

Welcome Page Should Be Open
    Location Should Be    ${WELCOME URL}
    Title Should Be    Asiakastiedot - Tilinhallinta – Verkkokauppa.com