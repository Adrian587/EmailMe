# Emailer

## App that sends email reminders

What will this app do? 
- Send emails at scheduled times to recipients. 
- These reminders will be sent to an email after a certain duration of time. 
- Input your email/reminder message/duration-frequency.

**What will the application do?**    

You will be able to write a list of emails 
that will be saved on the app. In the future, you will be able to 
insert your email and a time duration 
and you will receive your reminders after the duration. 

**Who will use it?**

Students, Teachers, people who have trouble remembering things.

**Why is this project of interest to you?**

I have trouble remembering things, so having my reminders emailed to me would be really useful.

## User Stories:
- As a user, I want to be able to delete my emails.
- As a user, I want to be able to add an email.
- As a user, I want to be able to save my list of emails. 
- As a user, I want the option of being able to load my previous emails. 

## Instructions for grader: 
- To add an email to a list of emails, type in the recipient and sender email address in the text fields. Then write your message and click the "send" button. To delete an email, click "view messages" and click on the row of the email you want to delete. 
- To trigger the audio component, click the "play sound" button.
- To save the emails you have written, click the "save" button. 
- To load the emails you have written into the messages screen, click the "load" button.

**Note: I will eventually implement Sendgrid's api to send emails but until then I have defaulted a column in the messages screen and no emails are being sent.** 
 
## Phase 4: 
- Test and design a class that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.
    - The class that is robust is the model package and it is the ReminderList class. This class's removeReminder method throws a custom checked exception, which is called the NoSuchReminderException and there are both tests that expect and don't expect this exception to be thrown.
  
-  Identify places where there is too much coupling or where there is poor cohesion. 
    - The two places where I noticed there was poor cohesion were: 
        - One: In my DefaultPageController class, I was giving the class responsibilities such as saving messages which should be a part of the Singleton State class. Therefore, the State class has the same responsibilities, but I have more cohesion because this was lifted from the DefaultPageController
        - Two: I noticed that the Saver class has two responsibilites, one is loading the user data and one is saving the user data. So I created a new Loader class to make each class responsible for just one thing to increase cohesion. 