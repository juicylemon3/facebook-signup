Setup and Execution
Set up the project:

Create a new Java project in your IDE (IntelliJ IDEA, Eclipse, etc.).

Add the dependencies listed above to your project's pom.xml (for Maven) or build.gradle (for Gradle) file.

Configure ChromeDriver (WebDriverManager):

The script uses WebDriverManager to automatically download and set up the correct version of ChromeDriver.  You do not need to manually download ChromeDriver.

Run the script:

Compile and run the FacebookSignupAutomation.java file in your IDE.

Notes
This script uses explicit waits (WebDriverWait) to handle the dynamic loading of elements on the Facebook page.  The wait timeout is set to 10 seconds, with a polling interval of 1 second.

The script includes a basic check for successful signup by waiting for an element to appear on the page after submitting the form.  For more robust testing, you should implement more specific checks.

The driver.quit() method is commented out to allow you to view the browser after the script
