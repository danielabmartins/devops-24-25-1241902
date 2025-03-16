# Class Assignment 1: Technical Report

**Author:** Daniela Bento Martins (1241902)<br>
**Programme:** SWitCH DEV<br>
**Course:** DevOps</br>

### **Introduction**

This DevOps assignment is divided into two parts. The first part explores the Version Control with Git and includes an 
alternative to Git. The second part explores Build Tools with Gradle.

Part 1 was also divided into three parts. The first, is intended to work without branches, the second relies on the use
of branches and the third, and final, explores an alternative solution to Git.

Part 2 follows the topic of using build tools with Gradle, focusing on its practical applications.

---
##  Part 1

### The Setup
Due to the nature of this project some configurations and changes needed to be made so that the application runs smoothly.
I will be explaining these changes in the following steps.

- **Cloning the tutorial**

We were given access to a repository containing the Tutorial React.js and Spring Data REST application. This allowed us
to clone it and create a local copy using the following command:

```shell
git clone https://github.com/spring-guides/tut-react-and-spring-data-rest
```

- **Creating my repository**

I then created my own repository *devops-24-25-1241902* in github to use throughout this project for all the assignments.
Once my repository was created, I cloned it into the right folder on my computer using the command:<br>
```shell
git clone <repository-URL> "C:\Users\danie\Desktop\SWitCH\2_Semestre\DevOps"
```

- **Copying the 'basic' folder**

In order to be able to run the application, I had to copy the basic folder inside the first repository to my own.
I created the folder *CA1* and inside, the folder *part1*. 

```shell
 cp -r "C:\Users\danie\Desktop\SWitCH\2_Semestre\DevOps\tut-react-and-spring-data-rest\basic" "C:\Users\danie\Desktop\SWitCH\2_Semestre\DevOps\devops-24-25-1241902\CA1\part1"
 ```


- **Creating the .gitignore file**

Next, a .gitignore file was needed to showcase the list of files and directories to ignore when making a commit. For this,
I used [this website](https://www.toptal.com/developers/gitignore/) to generate this file and then added it to my
repository.

- **Executing the application**

With everything set, I am now able to run the application through my repository whenever I need it. To do this, I need 
to move to the *basic* folder and run the command ``/mvnw spring-boot:run`` 
After this, I need to enter [this url](http://localhost:8080/) to check the application.

- **Structuring and organising the project**

Throughout this project I will be using **Issues** feature in GitHub as a way to keep track of my tasks and ideas. These
issues can also be linked to my commits if I refer to them in my commit message.
As seen by the following example, I created an issue regarding one of the main tasks for this project named *"Add 'Job
Years' - validation and testing #1"* and the message contained a direct reference to the number of that issue, thus linking them.
~~~shell
git commit -m "Added validations and tests to Employee (related to issue #1)"
~~~

**Tags** will also play a very important part in the making of this project. They are used to mark specific points in
the repository's history. These tags can, and will, be pushed to the remote repository to ensure everyone can understand
the structure of this project. Using the following commands allow us to send add a tag to our project and then push it to the
remote repository:

~~~shell
git tag v1.0.0
git push origin v1.0.0
~~~

- **Starting**
My first commit to the GitHub repository (*'Initial Commit'*) was meant to officially establish the version history of my assignments 
in a remote location and thus ensure an organised start. For this I used the command ``git push -u origin main``.

The second (and most meaningful commit) sent to the repository happened once all of these changes and configurations were
all set. This commit (*'Added Basic folder and did small tweaks'*) followed the structure where I staged,
committed, and pushed my changes to the remote repository:

~~~shell
git add .
git commit -m "Added Basic folder and did small tweaks"
git push
~~~

## Part 1
As referenced earlier, this assignment  will follow a structured approach to version control and feature development. The first part of the project will be developed entirely within the master branch,
In the second part, branching strategies will be introduced to manage development more effectively. Additionally, I will also explore and present an alternative technological solution for version control that does not rely on Git.

### Part 1.1

### Goals & Requirements
 - Use of *tags* to keep track of the versions of the application;
 - Develop a new feature to add a new field to the application (*Job Years*);
 - Add unit tests for testing the creation of Employees and the validation of their attributes;
 - Practice debugging both the server and the client.
 - Use meaningful commits and tags.

### Development 
As mentioned before, I began by copying the basic folder from the given repository and commit it with the right tag associated to it. For this project, I am meant to follow the major.minor.revision
pattern so this was tagged as **v1.1.0**. I was able to do this with the aforementioned commands.

Regarding the development of the new feature, we were tasked with adding a new field *Job Years* to the Employee entity. As the provided example mentions the fictional world of Lord 
of the Rings, I chose to maintain this theme by using similar examples in my tests.

I will go through each class, detailing the changes and additions made, including new methods, attributes, and tests, to fulfil the project requirements.

- **Employee.java:** 

- As requested, I added the jobYears field along with its getter and setter methods. To ensure data integrity, I also included validations in the setter methods. This was done to prevent invalid values, 
from being assigned and thus avoids potential errors. Both methods for jobYears can be found below and the setter method illustrates the updates
made to the other setter methods:

~~~java
	public int getJobYears(){
		return jobYears;
	}

	public void setJobYears(int jobYears) {
		if(areJobYearsInvalid(jobYears))
			throw new IllegalArgumentException("Insert a valid number of job years.");
		this.jobYears = jobYears;
	}
~~~

It was also necessary to validate all attributes: First Name, Last Name, Description, and Job Years. The first three, being of type String, were validated to ensure they were
neither null nor empty. For Job Years, an int type, I decided to restrict the value to non-negative numbers, with an upper limit of 100.

~~~java
	private boolean isFirstNameInvalid (String firstName) {
        return firstName == null || firstName.isBlank();
    }

	private boolean isLastNameInvalid (String lastName) {
		return lastName == null || lastName.isBlank();
	}

	private boolean isDescriptionInvalid (String description) {
		return description == null || description.isBlank();
	}
    
    private boolean isJobTitleInvalid (String jobTitle) {
        return jobTitle == null || jobTitle.isBlank();
    }

	private boolean areJobYearsInvalid(int jobYears) {
		
		return jobYears < 0 || jobYears > 100;
	}
~~~ 
Finally, I added validation to the Employee constructor by calling the existing validation methods for each attribute. This makes sure that any invalid data is caught when creating 
an Employee.

~~~java 
	public Employee(String firstName, String lastName, String description, int jobYears) {

		if(isFirstNameInvalid(firstName)){
			throw new IllegalArgumentException("First name cannot be empty!");
		}

		if(isLastNameInvalid(lastName)){
			throw new IllegalArgumentException("Last name cannot be empty!");
		}

		if(isDescriptionInvalid(description)){
			throw new IllegalArgumentException("Description cannot be empty!");
        }
        
        if(isJobTitleInvalid(jobTitle)){
            throw new IllegalArgumentException("Job title cannot be empty!");
        }

		if (areJobYearsInvalid(jobYears)){
			throw new IllegalArgumentException("Insert a valid number of job years.");
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
        this.jobTitle = jobTitle;
        this.jobYears = jobYears;

	}
~~~

- **EmployeeTest.java**: 

This class was added to the newly created test directory, as there were no existing tests for the Employee class. 
The tests focus on verifying the functionality of the Employee constructor and ensuring that attribute validation works correctly

~~~java
@Test
    void shouldCreateEmployee() {
        //Arrange

        // Act
        Employee employee = new Employee("Frodo", "Baggins", "Ring Bearer","Adventurer", 3);

        // Assert
        assertNotNull(employee);
        assertEquals("Frodo", employee.getFirstName());
        assertEquals("Baggins",employee.getLastName());
        assertEquals("Ring Bearer", employee.getDescription());
        assertEquals("Adventurer",employee.getJobTitle());
        assertEquals(3,employee.getJobYears());
    }
    

    public static Stream<Arguments> provideInvalidArguments() {
        return Stream.of(
                arguments(null,"Baggins","Ring Bearer","Adventurer",3,"First name cannot be empty!"),
                arguments("","Baggins","Ring Bearer","Adventurer", 3,"First name cannot be empty!"),
                arguments(" ","Baggins","Ring Bearer","Adventurer", 3,"First name cannot be empty!"),
                arguments("Frodo",null,"Ring Bearer","Adventurer", 3,"Last name cannot be empty!"),
                arguments("Frodo","","Ring Bearer","Adventurer", 3,"Last name cannot be empty!"),
                arguments("Frodo"," ","Ring Bearer","Adventurer",3,"Last name cannot be empty!"),
                arguments("Frodo","Baggins",null,"Adventurer",3,"Description cannot be empty!"),
                arguments("Frodo","Baggins","","Adventurer",3,"Description cannot be empty!"),
                arguments("Frodo","Baggins"," ","Adventurer",3,"Description cannot be empty!"),
                arguments("Frodo", "Baggins", "Ring Bearer",null,3, "Job title cannot be empty!"),
                arguments("Frodo", "Baggins", "Ring Bearer","",3, "Job title cannot be empty!"),
                arguments("Frodo", "Baggins", "Ring Bearer"," ",3, "Job title cannot be empty!"),
                arguments("Frodo","Baggins","Ring Bearer","Adventurer",-1,"Insert a valid number of job years."),
                arguments("Frodo","Baggins","Ring Bearer","Adventurer",101,"Insert a valid number of job years.")
        ); 
    }
    @ParameterizedTest
    @MethodSource("provideInvalidArguments")
    void testInvalidArguments(String firstName,String lastName, String description, String jobTitle, int jobYears, String expectedMessage) throws IllegalArgumentException {

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName,lastName,description,jobTitle,jobYears);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }
~~~ 

The following examples demonstrate tests for the setter methods of the Employee class attributes.For each attribute, I’ve included two tests:
one that uses valid values to ensure the setter updates the attribute correctly, and another that uses invalid values to verify that the appropriate exception is thrown.
For the String type attributes, tests are focused on handling null and empty values, while for the int type attribute, tests ensure that the job years fall within an acceptable range (e.g., between 0 and 100).

Below is an example of a test for setting a valid value for job years, followed by a test that verifies invalid job years trigger the appropriate exception.

~~~java
    @Test
    void testSetJobYears_ValidValue() {
        //arrange
        Employee employee = new Employee();
        //act
        employee.setJobYears(5);
        //assert
        assertEquals(5, employee.getJobYears());
    }
    @Test
    void testSetJobYears_InvalidValue() {
        Employee employee = new Employee();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setJobYears(-1)),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setJobYears(101))
        );
    }
~~~ 

- **DatabaseLoader.java:**

The class was modified to incorporate the jobYears field when saving sample employee data to the database. This update ensures that the employee records now include the 
number of years each employee has worked, enabling the application to showcase this feature immediately upon startup.

~~~java
	@Override
	public void run(String... strings) throws Exception { // <4>
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer","Adventurer",3));
	}
~~~

- **app.js:**
- 
The React components were updated to include the new jobYears field, allowing users to view the number of years an employee has worked. The EmployeeList 
and Employee components now display this information in a dedicated 'Job Years' column within the employee table, ensuring that the feature is visible right from the start.

~~~javascript
class EmployeeList extends React.Component{
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee._links.self.href} employee={employee}/>
		);
		return (
			<table>
				<tbody>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Description</th>
					<th>Job Title</th>
                    <th>Job Years</th>
				</tr>
				{employees}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobTitle}</td>
                <td>{this.props.employee.jobYears}</td>

			</tr>
		)
	}
}
~~~

**Debugging**

After making sure the jobYears field was integrated, I ran the app using ``./mvnw spring-boot:run `` to test it live at `` http://localhost:8080/``  This helped me 
check that everything was working as expected in the app and that the feature fit in smoothly with the rest of the functionality. I also did a quick code review to
ensure the data was being handled correctly on the server side and that jobYears was displaying properly on the client side.

**Finishing up**

Once everything was running smoothly, I needed to commit these changes to the remote repository. As previously mentioned, I add created an *Issue* with the title 'Add 'Job Years'- 
validation and testing #1' and so, I needed to reference it to link them together. For this, the steps followed were:
~~~shell
git add .
git commit -m "Added validations and tests to Employee (related to issue #1)"
git push
~~~
I also needed to add a new tag to indicate that this part was completed. As I learned that tags can be annotated, I started adding´notes related to the commit I was 
linking the tag with. For this I ran the following commands:

~~~shell
git tag v1.2.0 -m "Employee validations and tests"
git push origin v1.2.0
~~~
I also learned that using ``git show <NameOfTag>`` allows me to see all the details related to the specific tag.

To finalise, it was intended for me to send this README file as proper documentation for this part. So, I added a new commit and marked it with the tag *ca1-part1.1*.

~~~shell
git add .
git commit -m "Updated README file (related to issue #2)."
git tag ca1-part1.1 -m "Updated the ReadMe file for part 1.1"
git push
git push origin ca1-part1.1
~~~

### part 1.2

### Goals & Requirements

- Implement a Git workflow using feature and bug-fix branches to ensure isolated development;
- Develop and test a new feature (*email field) in a dedicated branch;
-Create a second branch (fix-invalid-email) to address and validate proper email formats.
- Practice debugging both the server and the client.
- Merge completed and tested branches into the master branch.
- Use meaningful commits and tags.

### Development 

This part focuses on implementing a branch-based development workflow. With this approach, I can ensure that
the master branch remains stable for publishing the application. Throughout this part I used two different branches
that helped me achieve this goal. 

The project requirements included adding a new feature with corresponding tests and addressing bug fixes. To
avoid redundancy, I have chosen not to show the full code again and instead, focus solely on the creation and
use of branches.

- #### Using the master branch 

To start out this section, I used the command ``git branch`` to confirm that I was on the right branch, in this case
*main*. At this point, this command proved to be necessary, but it became very useful throughout the project as 
new branches were added. This helped me confirm that I was working on the correct branch.

- #### Developing new features 
A new branch was created to isolate and manage all developments associated with the new email feature. To do this, I used a new command to create the *email-field* branch and in it, I was able to
add the email feature to the employee without working on the main branch. Here I'll showcase the commands I used, the first relates to the creation of a new branch. With this command, I
automatically move to this branch. However, I can always use the second command to check if I am on the right location.

~~~shell
git checkout -b email-field
git branch
~~~

- #### Integration and Testing of the Email Field
As stated before, adding the feature *email* mirrors the addition of *jobYears* in part 1.1.
To summarize, the email was added to the employee, I created a specific getter and a setter method and updated all the classes that contained information about the employee. In the 
**Employee.java** I also added a method to validate this new field. At this point of the project, the only validation necessary was whether the String sent was null or blank. Therefore,
at this point, the method looked as such:

~~~java
private boolean isEmailInvalid(String email) {
		return email == null || email.isBlank();
	}
~~~

It was necessary to update all existing tests to accommodate the new email field, and I also added two new tests specifically
for the email setter method. The first test checks for a valid email whilst the second checks for invalid values.

~~~java
@Test
    void testSetEmail_ValidValue() {
        //arrange
        Employee employee = new Employee();
        //act
        employee.setEmail("frodobaggins@shiremail.com");
        //assert
        assertEquals("frodobaggins@shiremail.com",employee.getEmail());
    }

    @Test
    void testSetEmail_InvalidValue() {
        Employee employee = new Employee();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail("")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail(" "))
        );
    }
~~~~


Finally, I debugged both the server and client components of the application as to detect and resolve any issues introduced
by the creation of the email field.

- #### Merging with the main

To merge changes from the email-field branch, I first committed the updates. In the message I mention the previously
created issues with 'Closes #3 #4 #7'. I have learned that using a keyword like *closes* or *fixes* along with the number
of the issue inside the commit message, automatically closes it. However, in this case, only issue #3 closed. As I looked
into this, I understood that I need to write *closes* before each issue if I want all of them to close. In the future, I
will apply this command properly.

Going back to the merging, I then pushed the email-field branch upstream with ```git push --set-upstream origin email-field```.
After switching to the main branch, I merged the email-field branch with the *--no-ff* option to maintain a clear commit history. 
The updated main branch was then pushed using git push. Finally, I tagged the new version as v1.3.0 and pushed the tag to the remote repository.

~~~shell
#commit the changes
git add .
git commit -m "Added new email field and tests. Closes #3 #4 #7"

#push the email-field branch upstream
git push --set-upstream origin email-field

#switch to main and merge the changes
git checkout main
git merge  - -no-ff email-field

#push to update the main branch
git push

#tag
git tag v1.3.0
git push origin v1.3.0
~~~

- #### Creating a new branch for bug fixing

It was now necessary to create another branch to address the bug fix for email validation. This new branch was
created with the command ``git checkout -b fix-invalid-email`` similarly to the previous branch.

I created the fix-invalid-email branch to address bugs in email validation and ensure that any email sent contains 
an "@" sign. This fix involved enhancing the Employee class with validation logic to enforce the correct email format.
I went back to the method *'isEmailInvalid'* and added the new requirement:

~~~java
private boolean isEmailInvalid(String email) {
		return email == null || email.isBlank() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}
~~~

Though I could have used something like *email.contains("@")* to do this verification, I decided to go a little further and 
create a regex to ensure that the email abides normal email rules. This is a more detailed and strict approach that can
catch more errors. The alternative would allow emails to contain a '@' without a local or a domain part. It could also allow 
two or more '@' hence why I chose a stricter version. Below is the same test seen before but with more invalid emails
that don't fit the regex rules.

~~~java
  @Test
    void testSetEmail_InvalidValue() {
        Employee employee = new Employee();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail("")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail(" ")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail("frodobaggins.shiremail.com")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail("frodobaggins@")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail("@shiremail.com")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setEmail("frodo@@shiremail.com"))
        );
    }
~~~

- #### Wrapping up

I repeated the previous steps mentioned regarding testing and validating this new update. I merged everything with the main
branch using the steps aforementioned and added the tag **v1.3.1** (which indicates a minor fix) to this commit.
This version update shows the ongoing improvements in the application's functionality and reliability.

Once everything was finished, this part of the assignment was sent to the repository with the tag **ca1-part1.2**. 



### **The Results**

- **Implementation** 

At the end of these two parts of the Class Assignment 1, the application looked as follows:

![part1application.png](images/part1application.png)

While the first three fields (First Name, Last Name, and Description) were already part of the model, I added the
**Job Years** and **Email** sections in this project. The part *Job Title* was intended to be added during the introduction/demonstration
of this project. Due to a smaller issue, I was only able to add it once I was finishing part1.2 of this assignment. Therefore,
I went back and added the field to all the classes necessary and updated all the methods. I also updated this ReadMe file
and all the examples provided as to illustrated what was required. This version was tagged with ```v1.1.1``` as it should have been a 
follow-up on that section.

These additions provide a more complete and detailed view of what makes up an Employee.

- **Branches**
The branches created can still be found in the repository using the command ``git branch`` and the * symbol indicates
which branch the user is on at the moment.

![part1branches.png](images/part1branches.png)

- **Tags**
Using the command ``git tag`` I am able to see which tags I have used for this project. This allows me to organise
myself and this assignment whilst marking specific points of its history as significant. Below is an example of all the
tags I have used so far:

![part1tags.png](images/part1tags.png)


- **Issues** 

 *Issues* was the perfect tool to keep me organised and to keep track of what needed to be done. During the first part
of this assignment (part1.1) I closed all the issues manually. However, I then learned that I can close them automatically
using the commit messages. This approach is much simpler and easier to see the history of the problem and how it was
solved. For this assignment I am also tagging every issue with 'CA1' as to help me organise my work.
 The picture below exemplifies some of the issues I created for this project.

![part1issues.png](images/part1issues.png)

---
This section gives a clear view of how the application evolved, with new features added, branches used for development,
and milestones marked with tags. The visuals provided show how version control works and how the project grew over time. 
Using issues also helped keep everything organised and easy to follow, making sure all the changes are well-documented.


### Alternative to Git

As an alternative technological solution for version control, I looked into Mercurial.

> Mercurial is a free, distributed source control management tool. It efficiently handles projects of any size and offers an easy and intuitive interface.
>                                   [Mercurial](https://www.mercurial-scm.org/)

In this section, I am going to analyse how Mercurial compares to Git regarding their version control features and how Mercurial
could have been used to solve the requirements of this assignment.

**Comparing Mercurial and Git** 

| Feature                     | Mercurial                                                                                                                                                                       | Git                                                                                                                        |
|-----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| History and Revision        | Mercurial tracks history with SHA-1 hashes in a linear manner and enforces stricter history integrity.                                                                          | Git uses hash IDs for efficient history tracking and allows history modification with commands like rebase                 |
| Branching and Merging       | Mercurial supports branching with named and anonymous branches, offering easy merging but fewer flexible tools. Named branches in Mercurial are permanent and can't be deleted. | Git offers lightweight branching with easy creation, merging, and deletion, allowing branches to be recreated without issues. |
| Architecture                | Mercurial is distributed, with each user having a local copy of the entire repository.                                                                                          | Git is also a distributed version control system, where each user has a full copy of the repository and its history.       |
| Collaboration Features      | Mercurial lacks built-in pull request support, but tools like Bitbucket and Source                                                                                              | Git uses pull requests for collaboration and has a stronger ecosystem of collaboration tools, widely used in open-source and enterprise projects                                                                                                                  |
| Performance and Scalability | Mercurial is fast but may not perform as efficiently with very large repositories.                                                                                              | Git is fast with large repositories and scales well for teams, especially with high-volume changes.|
| Ease of Use                 | Mercurial is easier for beginners due to its simpler command set, though it is less powerful.| Git has a steeper learning curve due to its powerful commands but offers great flexibility once mastered                                                                                                                  |


**Using Mercurial in the Assignment**

If I were to use this alternative in my assignment, here's a small explanation of how that could work.

- **Repository setup and Import:**

To set up the new Mercurial repository for the basic folder, I initialised the repository and added 
the necessary files. Then, I set up a remote repository and pushed the local changes to it. 
Whilst this process is quite similar to Git, Mercurial simplifies things by treating every clone as a full repository,
making distributed version control straightforward to manage.

~~~shell
# Create a new Mercurial repository
mkdir /path/to/devops-24-25-1241902
cd /path/to/devops-24-25-1241902
hg init

# Copy the 'basic' folder into the new repository (assuming extraction)
cp -r /path/to/TutorialReactSpringDataREST/basic .

# Add files to Mercurial tracking
hg add

# Commit the initial files
hg commit -m "Initial commit with 'basic' folder extracted from Tutorial application"
~~~

- **Feature Development and Branch Management:**

In Mercurial, creating a new branch is very straightfoward. With my repository created, I can do the following commands to
create a new branch for the new features.

~~~shell
#Creating a new branch: email-field
hg branch email-field
~~~

- **Committing and Tagging:**

Once all the changes were added, I can commit them to the email-field branch. I am also able to use tags to ensure stable releases. Mercurial
automatically commits the tags added so there is no need for an extra step.

~~~shell
#Committing the changes
hg commit -m "Added email field to the assignment"

#Pushing the new branch to the remote repository
hg push

#Tagging
hg tag v1.0.0

#Pushing to the remote repository
hg push --tags
~~~

- **Merging Features and Wrapping up** 

Once the feature development is complete and thoroughly tested, they can be merged back into the default (Mercurial's version of the main branch).
To do this, I need to move back to the default branch and merge the new feature branch into it. 

~~~shell
#Moving to the default branch
hg update default

#Merging the email-field branch
hg merge email-field

#Committing the merge
hg commit -m "Merging email-field feature into default"

#Pushing to the remote repository
hg push
~~~

In summary, Mercurial is a good alternative to Git, as it offers most of the same features such as branching, merging changes, tagging stable 
releases, and pushing updates to a remote repository. Its simple, beginner-friendly approach means I don’t feel overwhelmed using it,
making Mercurial a great option for managing version control.

--- 
### **Conclusion**

Using the Version Control with Git assignment in **Part1.1** helped me gain a solid understanding of version control systems. 
I worked with the master branch, commited changes, and tagged releases, which laid the groundwork for managing project changes effectively.

**Part1.2** allowed me to explore branching so that I could work on features and bug fixes separately. With this, I was able to keep the project
history clean, isolate changes and merge them back into the main branch effortlessly. 

In the **Final Results** I was able to see how these principles are applied in software development. I integrated new features, maintained
stable version and prepared the project for deployment. Git allowed to easily keep track of the changed that I made and ensured a smooth
development process.

When trying out an **Alternative to Git**, I discovered that Mercurial is a good alternative as it offers almost the same tools. This version
control is simple and easier to use but still stands as a good alternative. Using both gave me a broader perspective on these systems and their role.

---
##  Part 2

### The Setup

This project covers the work done in the DevOps course assignment, where I explored using Gradle as a build tool. The assignment includes a series
of tasks that give hands-on experience with Gradle, starting with the basics like setting it up, and moving on to more advanced features such as 
creating tasks and running unit tests.
After setting up the environment, the report goes on to explain the development of the Gradle Basic Demo, a multithreaded chat server. 
This section highlights the steps involved in building, launching, and connecting multiple clients to the server.

I started by setting up a new directory for the assignment, called /CA2/part2, and cloned the example application from the provided Bitbucket repository.
This repository contained a build.gradle file and included the Gradle Wrapper to maintain a consistent environment. Once everything was installed,
I checked if Gradle was correctly set up by running ``gradle -v`` in the terminal. This command also allows me to see which version of Gradle I am using at the 
moment.

### Gradle Basic Demo
Once everything was set up properly, I studied the ReadMe file attached to the example application I copied before. This example also provided
a Gradle Basic Demo that allowed me to explore a multithreaded chat server. In order to make the most out of this practical exercise, I executed
the following steps:

- **Build Process:**

To start out, I needed to prepare the demo for execution. I executed the command ``./gradlew build`` from the root directory. This compiled the source
code and packaged it into an executable .jar file as seen in the image below:

![part2demoexecution.png](images/part2demoexecution.png)

- **Server Startup:**

The next step was to launch the chat server. For this, I used the command ``  java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001`` 
and, as seen in the image, it started to run and wait for client connections.

![part2chatserver.png](images/part2chatserver.png)

- **Client Connections:**

Now that the Server was running, I needed to initiate the client side. For this, I ran the command ``` ./gradlew runClient```
to ensure that each client would be connected to localhost on port 59001. Once this was ready, a chatbox popped up prompting
the Client to write their name.

![part2chatter.png](images/part2chatter.png)

Since this server can easily manage multiple clients, I created a second client using another terminal window and tested
a conversation between the two Clients.

![part2chatter2people.png](images/part2chatter2people.png)

And whilst the Client side was running, the Server side kept updating whenever a new Client used the application:

![part2chatserverupdated.png](images/part2chatserverupdated.png)

### Adding a new Task

Once I finished the Demo provided, it was time to start working on the application and improve it with the required tasks.

The first task was added to avoid the need for manual command-line input whenever we wanted the server to start. For this, I
added a ``runServer`` to the file ``build.gradle``. This improved the development process by streamlining the server startup.
Now, it is possible to launch the chat server directly with the Gradle command ``./gradlew runServer.`` 

The *runServer* task is defined as a JavaExec type to run Java applications. It has a dependency on the classes task
to ensure that all necessary classes are compiled before starting the server. Additionally, it’s set up to run the ChatServerApp main class on port 59001.

~~~groovy
task runServer(type: JavaExec, dependsOn: classes) {
    group = "DevOps"
    description = "Launches the chat server on port 59001"

    classpath = sourceSets.main.runtimeClasspath

    mainClass = 'basic_demo.ChatServerApp'

    args '59001'
}
~~~

After this update, I needed to execute the task using ``./gradlew runServer`` in the command line.The terminal provided
immediate confirmation of the task’s success with the server running as expected. This addition improved the development
workflow by reducing the steps needed to start the server, and thus, simplifying the process.

![part2runServerTaskRunning.png](images/part2runServerTaskRunning.png)

Once everything was running smoothly, I commited the changed to the repository and closed the issue related to this task.
~~~shell 
git commit -m "Created Gradle task to run the server. Closes #11"
~~~ 

### Adding a unit test

In order to ensure the App class's functionality, I added a unit test. This test was provided beforehand, and it was placed
in a new directory created with the command ``mkdir -p src/test/java/basic_dem ``. This  test verifies that the App
class generates a non-null greeting message, which, while simple, is an important aspect of its functionality.

~~~groovy
package basic_demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}

~~~

To confirm that the test environment was set up correctly, I included the JUnit dependency in the build.gradle 
file, as it is necessary for running the unit tests. This addition ensures that the project correctly identifies and 
runs JUnit tests without any issues.

~~~groovy
testImplementation 'junit:junit:4.12'
~~~

Next, I executed the command ``./gradlew test`` and I was able to see that the test passed successfully.

![part2gradlewtest.png](images/part2gradlewtest.png)

At the end of this section, I committed the changes with the command:

~~~shell
git commit -m “Fixed submodule issue, added unit test (Closes #12) and re-added CA1/part2 as a normal directory”
~~~

*Disclaimer:* Due to a small issue with the submodule, I needed to do some tweaks to ensure the application ran properly and my
assignment was working.

### Adding a Copy type task

The next step was to add a new task of type **Copy** to the build.gradle file, which creates a backup of the source code
to provide a reliable recovery point in case any unexpected issues arise during development. This backup task is used
to duplicate the contents of the src directory into a designated backup location within the project. 
This step is crucial for ensuring we have an up-to-date snapshot of the codebase, particularly before implementing
major changes or updates.

~~~groovy
task backup(type: Copy) {
    group = "DevOps"
    description = "Copies the sources of the application to a backup folder"

    from 'src'
    into 'backup'
}
~~~
To verify the task was working correctly, I executed the command associated with it. I ran ```./gradlew backup```
from the command line and confirmed its successful execution. The output shows that the source code was properly
copied to the backup location, demonstrating that the task effectively protects the project’s code.

![part2gradlewbackup.png](images/part2gradlewbackup.png)

This also resulted in the creation of a new folder named backup in my directory (**backup**), confirming that the
backup process was completed properly. Adding the backup task to the Gradle build script has made the project more 
resilient by making it easy and reliable to back up the code.

![part2backupfolder.png](images/part2backupfolder.png)

Once everything was finished, I sent my changes to the repository with the message:

~~~shell
git commit -m "Added new Copy task for backup. Closes #13"
~~~

### Adding a Zip type task

The last task was to create a new Zip task to package the project's source code into a compressed .zip file. 
This task makes it easier to zip up the src directory, which is handy for backups or sharing the project. It's an
important step for archiving project versions or preparing the code for distribution.

~~~groovy
task archive(type: Zip) {
    group = "DevOps"
    description = "Creates a zip archive of the source code"

    from 'src'
    archiveFileName.set('src_backup.zip')
    destinationDirectory.set(layout.buildDirectory.dir("archives"))
}
~~~~

Once the zip task was set up, I ran it with ```./gradlew zip.``` The terminal output confirmed the task ran smoothly,
indicating that the src directory had been successfully compressed into a ZIP file.

![part2gradlewarchive.png](images/part2gradlewarchive.png)

The success of this task is evident not only in the image above but also by the presence of the ZIP file in my directory.

![part2gradlewArchiveResult.png](images/part2gradlewArchiveResult.png)

This final requisite was commited to the repository with the command:

~~~shell
git commit -m "Added Zip type task. Closes #14"
~~~

### Concluding

Wrapping up this project has given me a much clearer understanding of how Gradle works and how useful it can be in 
real-world development. Throughout the tasks, I got a chance to see firsthand how flexible and powerful Gradle is 
as a build tool.

The project showed how Gradle can help automate essential tasks like building projects, running tests, 
and managing files. These features are key to keeping the development process smooth and efficient, making Gradle
a must-have for any project.

With the tasks  *runServer*, *backup*, and *archive*, I saw how easy it is to extend Gradle's functionality. These 
tasks not only made development easier but also helped improve the project's resilience and made it easier to distribute.

Integrating a unit test into the build process also highlighted how important testing is and how Gradle makes it 
simple to include that step in the workflow. It was a good reminder of why testing is crucial for keeping code
quality high.

All in all, this project has helped me get a better grasp of Gradle and how it fits into the development process.
The skills I’ve gained will definitely come in handy for future projects, making my workflows more efficient and reliable.



