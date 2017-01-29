SupSMS
======

SupSMS is a web application made with Java EE.

It is a Proof of Concept of an SMS service.

Functionalities
------------

- Registered user :
  - Add / Delete / Update contacts
  - Send message to a contact
  - View a conversation with a contact
  - Delete a conversation with a contact
  - Web Service to synchronize SMS and contacts
  - View stats of exchanges SMS with contacts
  - View and edit profile
  - View and edit invoices

- Administrator :
  - Show and manage all users
  - Show all invoices

Installation
------------

- Install Glassfish
- Setup a JDBC connection. The name of your JDBC Ressources should be the same as the `<jta-data-source></jta-data-source>` in [`persistence.xml`][1]
- Setup a JMS connection factory. The Resource Type should be ConnectionFactory
- Download this project and deploy the application

Once launched, you will find the database filled with [some data][2].

------------
###### SUPINFO Project - 4JVA - 12/2014

[1]: https://github.com/xLs51/SupSMS/blob/master/SupSMSEJB/ejbModule/META-INF/persistence.xml
[2]: https://github.com/xLs51/SupSMS/blob/master/SupSMSEJB/ejbModule/com/supinfo/supsms/bean/InitServiceBean.java
