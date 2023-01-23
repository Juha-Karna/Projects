*** Settings ***
Documentation     A resource file with reusable keywords and variables related to addToCart. Juha Kärnä
Library           SeleniumLibrary

*** Variables ***
${SERVER}         www.verkkokauppa.com
${BROWSER}        Chrome
${DELAY}          0.7
${CART URL}      https://${SERVER}/fi/product/725093/Asus-GeForce-TUF-RTX3080-O10G-V2-GAMING-naytonohjain
${WELCOME URL}    https://${SERVER}/fi/orders

*** Keywords ***
Open Browser To Product Page
    Open Browser    ${CART URL}    ${BROWSER}
    Maximize Browser Window
    Set Selenium Speed    ${DELAY}
    Product Page Should Be Open

Product Page Should Be Open
    Title Should Be    Asus GeForce TUF-RTX3080-O10G-V2-GAMING -näytönohjain – Verkkokauppa.com

Add To Cart
    Click Button    //*[@id="main"]/section/aside/div[2]/div[2]/div[1]/div[2]/button[1]

Check If Product Is in Cart
    Click Element    //*[@id="checkout"]
    Page Should Contain Element    //*[@id="main"]/div[2]/section/div[1]/div/a

