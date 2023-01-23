*** Settings ***
Documentation     A test with a single test to check if item was added to cart successfully. Juha Kärnä
Resource          resourceAddToCart.robot

*** Test Cases ***
Valid Added To Cart
    Open Browser To Product Page
    Add To Cart
    Check If Product Is in Cart
    [Teardown]    Close Browser