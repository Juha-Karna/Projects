*** Settings ***
Documentation     A test with a single test for valid register. Juha Kärnä
Resource          resourceRegister.robot

*** Test Cases ***
Valid Register
    Open Browser To Register Page
    Input Username    email5.email@gmail.com
    Input Password    Mode1234
    Input First Name    Aku
    Input Last Name    Ankka
    Input Phone Number    0401234567
    Check checkbox
    Submit Credentials
    Welcome Page Should Be Open
    [Teardown]    Close Browser