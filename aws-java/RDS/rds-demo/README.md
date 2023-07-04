This is Demo to connect the database that is hosted in AWS using RDS.

Steps to connect to DB hosted on AWS (through RDS service)

1. Login into AWS console and go to RDS service.
2. create database by clicking on createDatase(after clicking on DB Instances)
3. while creating DB, choose Public Availablity Yes and add inbound rule for MYSQL in vpc security group of RDS.
4. pass the url(AWS_RDS_MYSQL_URL),username(AWS_RDS_MYSQL_USERNAME) and password(AWS_RDS_MYSQL_PASSWORD) through enviromental variables to connect to DB that's hosted in AWS


Postman collection is added to test the apis.
