*** Settings ***
Library    String
Library    Collections
Library    OperatingSystem
Library    DatabaseLibrary
Library    SeleniumLibrary
Library    DateTime
Library    validationcode.py

*** Variables ***
${PATH}    C:/RobotProjects/UIpath/BlankProcess1/
${PATH2}    C:/RobotProjects/UIpath/BlankProcess1/
@{ListToDB}
${InvoiceNumber}    empty

${dbname}    rpaproject
${dbuser}    robotuser
${dbpass}    password
${dbhost}    localhost
${dbport}    3306


*** Keywords ***

Make Connection
    [Arguments]    ${dbtoconnect}
    Connect To Database    pymysql    ${dbtoconnect}    ${dbuser}    ${dbpass}    ${dbhost}    ${dbport}


*** Keywords ***
Add Row Data to List
    [Arguments]    ${items}

    @{AddInvoiceRowData}    Create List
    Append To List    ${AddInvoiceRowData}    ${InvoiceNumber}
    Append To List    ${AddInvoiceRowData}    ${items}[8]
    Append To List    ${AddInvoiceRowData}    ${items}[0]
    Append To List    ${AddInvoiceRowData}    ${items}[1]
    Append To List    ${AddInvoiceRowData}    ${items}[2]
    Append To List    ${AddInvoiceRowData}    ${items}[3]
    Append To List    ${AddInvoiceRowData}    ${items}[4]
    Append To List    ${AddInvoiceRowData}    ${items}[5]
    Append To List    ${AddInvoiceRowData}    ${items}[6]

    Append To List    ${ListToDB}     ${AddInvoiceRowData}

*** Keywords ***
Add Invoice Header To DB
    [Arguments]    ${items}    ${rows}
    Make Connection    ${dbname}
    
    # Set dateformat
    ${invoiceDate} =    Convert Date    ${items}[3]    date_format=%d.%m.%Y    result_format=%Y-%m-%d
    ${dueDate} =    Convert Date    ${items}[4]    date_format=%d.%m.%Y    result_format=%Y-%m-%d

    # Invoice status variable
    ${InvoiceStatus}=    Set Variable    0
    ${invoiceComment}=    Set Variable    All ok

    # Validate reference number
    ${refStatus}=    Is Reference Number Correct    ${items}[2]

    IF   not ${refStatus}
        ${InvoiceStatus}=    Set Variable    1
        ${invoiceComment}=    Set Variable    Reference number error
    END

    # Validate Iban number
    ${ibanStatus}=    Check IBAN    ${items}[6]
    IF   not ${ibanStatus}
        ${InvoiceStatus}=    Set Variable    2
        ${invoiceComment}=    Set Variable    IBAN number error
    END

    # Validate Sum number
    ${sumStatus}=    Check Amounts From Invoices    ${items}[9]    ${rows}
    IF   not ${sumStatus}
        ${InvoiceStatus}=    Set Variable    3
        ${invoiceComment}=    Set Variable    Amount difference
    END

    ${insertStmt}=    Set Variable    insert into invoiceheader (invoicenumber, companyname, companycode, referencenumber, invoicedate, duedate, bankaccountnumber, amountexvat, vat, totalamount, invoicestatus_id, comments) values ('${items}[0]', '${items}[1]', '${items}[5]', '${items}[2]', '${invoiceDate}', '${dueDate}', '${items}[6]', '${items}[7]', '${items}[8]', '${items}[9]', '${InvoiceStatus}', '${invoiceComment}');
    Execute Sql String     ${insertStmt}

*** Keywords ***
Check Amounts From Invoices
    [Arguments]    ${totalSumFromHeader}    ${invoiceRows}
    ${status}=    Set Variable    ${False}
    ${totalAmountFromRows}=    Evaluate    0


    FOR    ${element}    IN    @{invoiceRows}
        ${totalAmountFromRows}=    Evaluate    ${totalAmountFromRows}+${element}[8]
    END

    ${diff}=    Convert To Number    0.01
    ${totalSumFromHeader}=    Convert To Number    ${totalSumFromHeader}
    ${totalAmountFromRows}=    Convert To Number    ${totalAmountFromRows}

    ${status}=    Is Equal    ${totalSumFromHeader}    ${totalAmountFromRows}    ${diff}

    [Return]    ${status}

*** Keywords ***
Check IBAN
    [Arguments]    ${iban}
    ${iban}=    Remove String    ${iban}    ${SPACE}
    ${status}=    Set Variable    ${False}
    Log To Console    ${iban}

    ${length}=    Get Length    ${iban}

    IF    ${length} == 18
        ${status}=    Set Variable    ${True}
    END
    [Return]    ${status}

*** Keywords ***
Add Invoice Row To DB
    [Arguments]    ${items}
    Make Connection    ${dbname}
    ${insertStmt}=    Set Variable    insert into invoicerow (invoicenumber, rownumber, description, quantity, unit, unitprice, vatpercent, vat, total) values ('${items}[0]', '${items}[1]', '${items}[2]', '${items}[3]', '${items}[4]', '${items}[5]', '${items}[6]', '${items}[7]', '${items}[8]');
    Execute Sql String     ${insertStmt}

*** Test Cases ***
Read CSV file to list 
    Make Connection    ${dbname}
    ${outputHeader}=    Get File    ${PATH2}InvoiceHeaderData.csv
    ${outputRows}=    Get File    ${PATH2}InvoiceRowData.csv

    Log    ${outputHeader}
    Log    ${outputRows}

    @{headers}=    Split String    ${outputHeader}    \n
    @{rows}=    Split String    ${outputRows}    \n

    #poistetaan ensimmäinen (otsikko) rivi ja viimeinen (tyhjä) rivi
    ${length}=    Get Length    ${headers}
    ${length}=    Evaluate    ${length}-1
    ${index}=    Convert To Integer    0

    Remove From List    ${headers}    ${length}
    Remove From List    ${headers}    ${index}

    ${length}=    Get Length    ${rows}
    ${length}=    Evaluate    ${length}-1

    Remove From List    ${rows}    ${length}
    Remove From List    ${rows}    ${index}

    Set Global Variable    ${headers}
    Set Global Variable    ${rows}

Loop all invoicerows
    #käydään läpi kaikki laskurivit
    FOR    ${element}    IN    @{rows}
        Log    ${element}
        
        @{items}=    Split String    ${element}    ;
        ${rowInvoiceNumber}=    Set Variable    ${items}[7]
    
        Log    ${rowInvoiceNumber}
        Log    ${InvoiceNumber}

        IF    '${rowInvoiceNumber}' == '${InvoiceNumber}'
            Log    Lisätään rivejä laskulle
            
            # lisää käsiteltävän laskun tiedot listaan
            Add Row Data to List    ${items}

        ELSE
            Log    Pitää tutkia onko tietokantalistassa jo rivejä
            ${length}=    Get Length    ${ListToDB}
            IF    ${length} == ${0}
                Log    Ensimmäisen Laskun tapaus
                # päivitä laskunumero
                ${InvoiceNumber}=    Set Variable    ${rowInvoiceNumber}
                Set Global Variable    ${InvoiceNumber}
                
                # lisää käsiteltävän laskun tiedot listaan
                Add Row Data to List    ${items}

            ELSE
                Log    Lasku vaihtuu, pitää käsitellä myös otsikkodata

                # Etsi laskun otsikko rivi
                FOR    ${headerElement}    IN    @{headers}
                    ${headerItems}=    Split String    ${headerElement}    ;
                    IF    '${headerItems}[0]' == '${InvoiceNumber}'
                        Log    Lasku Löytyi

                        # Syötä laskun otsikkorivi tietokantaan
                        Add Invoice Header To DB    ${headerItems}    ${ListToDB}

                        # Syötä laskun rivit tietokantaan
                        FOR    ${rowElement}    IN    @{ListToDB}
                            Add Invoice Row To DB    ${rowElement}
                        END
                    END
                    
                END

                # Valmista prosessi seuraavaan laskuun
                @{ListToDB}    Create List
                Set Global Variable    ${ListToDB}
                ${InvoiceNumber}=    Set Variable    ${rowInvoiceNumber}
                Set Global Variable    ${InvoiceNumber}

                # lisää käsiteltävän laskun tiedot listaan
                Add Row Data to List    ${items}
            END
        END
    END

    #Viimeisen laskun tapaus
    ${length}=    Get Length    ${ListToDB}
    IF    ${length} > ${0}
        Log    Viimeisen laskun otsikkokäsittely
        # Etsi laskun otsikko rivi
        FOR    ${headerElement}    IN    @{headers}
            ${headerItems}=    Split String    ${headerElement}    ;
            IF    '${headerItems}[0]' == '${InvoiceNumber}'
                Log    Lasku Löytyi

                # Syötä laskun otsikkorivi tietokantaan
                Add Invoice Header To DB    ${headerItems}    ${ListToDB}

                # Syötä laskun rivit tietokantaan
                FOR    ${rowElement}    IN    @{ListToDB}
                    Add Invoice Row To DB    ${rowElement}
                END
            END
                    
        END
    END
