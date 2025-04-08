~~# Class Assignment 2: Virtualization with Vagrant

**Author:** Daniela Bento Martins (1241902)<br>
**Programme:** SWitCH DEV<br>
**Course:** DevOps</br>

### Table of Contents

- [Introduction](#introduction)
- [Part 1](#part-1)

- [Conclusion](#conclusion)

### Introduction


**Part 1** The goal is to practice with VirtualBox using the same projects from the previous assignments but now
inside a VirtualBox/UTM VM with Ubuntu


---

##  Part 1

### Setting Up

This report focuses on virtualization with VMware Workstation in this course. The goal was to get hands-on 
experience setting up and managing virtual machines for software development and operations.

Since I had already set up my virtual machine in a previous course (SCOMRED), I didn’t need to go through 
the initial configuration steps again. My VM was already configured with a Host-only network, a static IP 
address, and essential network tools, allowing me to focus directly on the project tasks.

However, I needed to remotely manage my Virtual Machine. For that, I installed the **OpenSSH** server by using
the command `sudo apt install openssh-server` and then configured it by enabling password authentication. For this,
it was necessary to edit `/etc/ssh/sshd_config` by uncommenting the line `PasswordAuthentication yes`. Once these steps were
completed, I used the command `sudo service ssh restart` to restart the service.

I then had to set up a FTP server so that I could transfer files to and from the VM. I used the command `sudo apt install vsftpd`
to install it and then configure it. I moved to the `/etc/vsftpd.conf` file and uncommented the line `write_enable = YES`.
Once again, I restarted the service with `sudo service vsftpd` so that I could begin working on the project.

Below is an example of what these configurations looked like.
![ca21-vsftpd file.png](images/ca21-vsftpd%20file.png)

### Cloning the Repository

The next step required that my repository got cloned inside my Virtual Machine. For this, I configured secure SSH access
to connect the VM with my GitHub repository.

First, I needed a SSH Key. I generated it in my VM's terminal so that I could securely connect to GitHub. Once it was created,
I displayed it in the terminal. I used the following commands for those steps:

~~~bash 
#generating the key
ssh-keygen -t ed25519 -C "1241902@isep.ipp.pt"

#displaying the key
cat ~/.ssh/id_ed25519.pub
~~~

Then, I logged into my GitHub account's settings so that I could save my key. I headed to **SSH and GPG keys**
and pasted my key in the provided field. This allowed my VM to securely authenticate with GitHub.

Finally, I was able to clone my repository using the following command on my VM's terminal:

`git clone git@github.com:danielabmartins/devops-24-25-1241902.git`

###  Preparing the Development Environment

For this assignment, I am expected to practice using the VM with my previous assignments.To do so, I’ll have to install 
all the dependencies I’ve used before inside VMware.  
Below are all the commands used to install the different dependencies require:

~~~bash
#update and upgrade the already installed packages
sudo apt update
sudo apt upgrade

#install Git for version control and source code management
sudo apt install git 

#install JDK and JRE for Java-based projects
sudo apt install openjdk-17-jdk openjdk-17-jre

#install Maven for building and managing Java projects
sudo apt install maven

#install Gradle for building and managing Java projects
wget https://services.gradle.org/distributions/gradle-8.6-bin.zip
sudo mkdir /opt/gradle
sudo unzip -d /opt/gradle gradle-8.6-bin.zip
~~~

To make Gradle accessible from anywhere in the terminal, I added its bin directory to the system
PATH by updating the *.bashrc* file:  
~~~bash
echo "export GRADLE_HOME=/opt/gradle/gradle-8.6" >> ~/.bashrc
echo "export PATH=\$GRADLE_HOME/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc****
~~~

To ensure that everything was installed correctly and that I was able to start working on my projects,
I used the following commands:

~~~bash
git --version
java --version
mvn --version
gradle --version
~~~
![ca21-installdependencies.png](images/ca21-installdependencies.png)

### Executing the Spring Boot Tutorial Basic Project

My first requirement was to execute the Spring Boot Tutorial Basic Project that I worked with before.
This time, I needed to build and run the project inside my Virtual Machine.

I started by heading to the basic folder inside my repository as this was where the application was.
I executed the command `./mvnw spring-boot:run`.  
Similarly to my first assignment,I needed to access the application. This time, however, I wanted to ensure
that it was accessible externally. For this, I used the URL `http://192.168.56.5:8080/` with my VM's
IP address that I found using the ``ifconfig`` command.  

Once accessed, I was able to see the application running correctly. This meant that the backend was working
and that the framework was serving the content perfectly.
![ca21-table.png](images/ca21-table.png)


### Executing the Gradle_Basic_Demo Project (1)

For this part, I am going to focus on building and running the gradle_basic_demo from CA1.2. Due to the
nature of this project, I needed to run the application in two environments: the virtual and the host machine.

I first moved to the gradle_basic_demo directory in my Virtual Machine where I executed the command
``./gradlew build``

Since I was using an Ubuntu Server, I did not have a desktop environment to run GUI apps like this 
project's chat client. In order to make this work, I opened a terminal on my host machine and 
moved to the gradle_basic_demo directory. Once I ran the appropriate command I created the Client
that could then communicate with the Server running on my Virtual Machine.
Below is the command I used with my VM's IP address and the port number:
``./gradlew runClient --args="192.168.56.5 59001" ``

![ca21-gradlewClientPowershell.png](images/ca21-gradlewClientPowershell.png)
![ca21-gradlewClientPowershellChat.png](images/ca21-gradlewClientPowershellChat.png)

As seen in the images above, I successfully ran the command and opened the chat windows which ensured
that the application was running smoothly. 


### Executing the Gradle_Basic_Demo Project (2)
I was now meant to work on developing and running another component of the
gradle_basic_demo project within the virtual machine. The expected outcome should
be the table with employee information provided in the Spring Boot Tutorial Basic Project.

To begin, I headed to the folder 'react-and-spring-boot' and ran the following commands
to build and run the application properly.
~~~bash
./gradlew build

./gradlew bootRun
~~~

I then headed to my browser with the URL http://192.168.56.5:8080/ so that I could
see the landing page of the Spring Boot application. As expected, I could see the table
with all the employee's information on it. This meant that everything
was running smoothly and I had completed the task with success.
![ca21-table.png](images/ca21-table.png)


### Concluding

This report covers setting up a virtual environment with VirtualBox for the first part of this
Second Class Assignment. It gave me hands-on experience with managing virtual machines using projects
that I was already familiarized with.

The assignment helped me get a better understanding of virtualization and the challenges that 
come with it, like getting the host and VM to communicate properly. Overall, it was a great way 
to build skills that’ll be useful for my future work in DevOps.
