# Steps to create EC2 instance and deploy Spring Boot application

## Create EC2 Instance through AWS Console

→ Login into AWS Console using IAM User credentials

![image_1](/ec2/basic-deployment/images/0.png)

→ Navigate to EC2 Dashboard either by clicking EC2 in recently visited or through search or Services Menu(Services -> Compute -> EC2)

![image_2](/ec2/basic-deployment/images/1.png)

→ To create EC2 Instance got the Instances view and click on Launch Instance

![image_3](/ec2/basic-deployment/images/2.png)

→ Provide the name of the EC2 Instance and select the AMI (linux/windows/mac …)

![image_4](/ec2/basic-deployment/images/3.png)

→ Select the Instance type and create Key Pair which will be used to connect to ec2 instance from terminal using SSH

![image_5](/ec2/basic-deployment/images/4.png)

→ Create a new key pair by clicking on Create new key pair(link) and download the pem file which will be needed to connect to EC2 Instance using SSH

![image_6](/ec2/basic-deployment/images/5.png)

![image_7](/ec2/basic-deployment/images/6.png)

![image_8](/ec2/basic-deployment/images/7.png)

-> check on HTTP option in Network Settings which will add an inbound rule in Security Group so setup an endpoint

![image_9](/ec2/basic-deployment/images/8.png)

→ If you want to execute any commands on ec2 instance startup then you can place that in User Data which is under Advance Details

![image_9](/ec2/basic-deployment/images/8.png)
(Ignore the other options in Advance Details for this demo)

→ Click on Launch to create and start the ec2 instance

![image_10](/ec2/basic-deployment/images/9.png)

→ EC2 instance got created and shown under the  Instances

![image_11](/ec2/basic-deployment/images/10.png)

→ Click on the instance to see the details 

![image_12](/ec2/basic-deployment/images/11.png)

→ We can see the public IP and private IP under details in the details tab. And Inbound and Outbound rules under Security Tab.

![image_13](/ec2/basic-deployment/images/12.png)

→ We can see the Networking , Storage (EBS volume) and other details under respective tabs (ignoring for this demo)

→ Now take the public IP which is required to connect to EC2 Instance from terminal
 public IP : *13.201.45.56*

## Connect EC2 Instance using SSH

→ Open the terminal and navigate to the folder where we placed the pem file.

![image_14](/ec2/basic-deployment/images/13.png)

→ Use the below command to connect to ec2 instance

`SSH -i ec2_key_pair.pem ec2-user@13.201.45.56`

![image_15](/ec2/basic-deployment/images/14.png)

→ You will see the permission error as you don’t have permission to use the pem file. There are two ways to resolve this.
 1. Using sudo  
     `sudo SSH -i ec2_key_pair.pem ec2-user@13.201.45.56`
    ![image_16](/ec2/basic-deployment/images/15.png)
 2. Using chmod  
     `chmod 0400 ec2_key_pair.pem`  
     `SSH -i ec2_key_pair.pem ec2-user@13.201.45.56`
    
 ![image_17](/ec2/basic-deployment/images/16.png)   

→ Now we connected to ec2 instance that we created using ssh. Now we can tryout ping and create a folder for our spring boot app

 ![image_18](/ec2/basic-deployment/images/17.png)   

 ![image_19](/ec2/basic-deployment/images/18.png)  

## Deploy Spring boot application into EC2 instance

→ we create a spring boot app with an endpoint and it will work in local as below : 

![image_20](/ec2/basic-deployment/images/19.png) 

→ Build the jar using maven package command

![image_21](/ec2/basic-deployment/images/20.png) 

→ By the above action, we can see the jar created under the target folder. 

![image_22](/ec2/basic-deployment/images/21.png) 

→ Deploy this into ec2 instance using scp command  

`scp -i ec2_key_pair.pem basic-deployment/basic-demo/target/myHelloApp.jar ec2-user@13.201.45.56:my-apps`

![image_23](/ec2/basic-deployment/images/22.png) 

→ Install Java on ec2 instance

We required Java to run the jar that we copied, so install java using the below command in ec2 instance.

`sudo yum install java`

![image_24](/ec2/basic-deployment/images/23.png) 

![image_25](/ec2/basic-deployment/images/24.png) 

→ Run the spring boot jar using Java command  
`java -jar myHelloApp.jar`

![image_26](/ec2/basic-deployment/images/25.png) 

→ Access the api using POSTMAN

http://13.201.45.56:4455/hello/Gundamaiah

![image_27](/ec2/basic-deployment/images/26.png) 

 Here we got a timeout error , because we are running our app on port 4455 which is not open to connect in the ec2 instance security group. So to open the port, add an inbound rule that will allow the connections to ec2 instance for this port.

→ Add inbound rule in security group for port 4455

![image_28](/ec2/basic-deployment/images/27.png) 

→ Click on edit Inbound rules to add a new rule 

![image_29](/ec2/basic-deployment/images/28.png) 

→ Add rule 

![image_30](/ec2/basic-deployment/images/29.png)

![image_31](/ec2/basic-deployment/images/30.png)

→ Now we can access the api using port 4455

![image_32](/ec2/basic-deployment/images/31.png)

## Stop the spring boot application in EC2

→ To stop the spring boot app which is running on ec2 instance, we need to get PID of the process first by using the below command.  

`ps -ef | grep java`

![image_33](/ec2/basic-deployment/images/32.png)

→ Now kill the process using the command.

`kill -9 26306`

![image_34](/ec2/basic-deployment/images/33.png)

→ reverifying the running  processes

![image_35](/ec2/basic-deployment/images/34.png)

Now we will get connection refused error if we try to access the api

![image_36](/ec2/basic-deployment/images/35.png)

## Stop and the Terminate the EC2 Instance 

As we are done with our basic demo, we need to clean up the resources, so we need to stop and terminate the ec2 instance now(we can terminate the ec2 instance directly as well)

→ Stop EC2 Instance

![image_37](/ec2/basic-deployment/images/36.png)

![image_38](/ec2/basic-deployment/images/37.png)

![image_39](/ec2/basic-deployment/images/38.png)

→ Terminate the EC2 instance

![image_40](/ec2/basic-deployment/images/39.png)

![image_41](/ec2/basic-deployment/images/40.png)

![image_42](/ec2/basic-deployment/images/41.png)

<p align="center">***************************END***************************</p>





    

    


 


