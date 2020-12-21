![ZiuretiVeliau banner](/readme-media/ziureti-veliau-banner.png)
## ŽIŪRĖTI VĖLIAU - Blog web application

###### Developer: [Šarūnas Verbus](https://www.linkedin.com/in/sarunas-verbus/)    Client: [Codeacademy.lt](https://www.codeacademy.lt/)

### CONTENTS
- INTRODUCTION
- LAUNCHING INSTRUCTIONS
- APPLICATION DESCRIPTION
- EDUCATIONAL GOAL LIST
- ACHIEVEMENTS
- FAILURES

### INTRODUCTION
This is an educational project for intermediate CodeAcademy exam on Java Spring MVC course. The application serves blog intended for publishing various online content recommendations that users come to find out for either checking immediately or for watching later. The recommended content is served as interactive "article" feed in form of grid. Each article has: title, publishing date, type of content, hyperlink to recommended webpage, short summary about the content and descriptive illustration. All pieces of data must be provided by administrator when creating new recommendation or editing existing ones. The feed shows truncated versions of the summaries, in order to read full descriptions, users can expand the articles by clicking on "READ MORE" button. The recommendations can be commented and marked as "agreed" if the user finds them to be timeworthy. For targeted content user can filter out recommendations by content type with any string passed as query parameter or to use the predefined most common-ones automatically wired-up in the navigation menu.

### LAUNCHING INSTRUCTIONS
1. `git clone https://github.com/verbusharas/sv-blog.git`
2. Rename `application-example.properties` to `application-properties` and configure it according to your database and credentials
3. Make sure that `-Dspring.profiles.active=test` argument is passed to VM before launch.
3. Launch the Spring Boot application
4. Hibernate will seed the database with mock-up data. If Hibernate DDL exception is thrown on the initial launch, do the following steps (this only need to be done on the initial launch):
  a) stop the application
  b) in resources/db/data.sql - comment-out role_users table block
  c) launch and stop the application again
  d) uncomment the role_users block
  e) launch the application
  
  ### APPLICATION DESCRIPTION AND FEATURES
  #### User roles
  There are two roles and three authentication states for user to be in: 
  1. Anonymous user 
  2. Logged in user with role 'USER'
  3. Logged in user with role 'ADMIN'
  
  Anonymous user can browse the recommendations feed, expand articles, see the user discussions, but cannot comment themselves. Upon entering the comments section - anonymous users are informed about this restriction and can directly access registration/login pages from there. Logged in users can comment, delet or edit their own previous comments. Administrators can delete any comment and modify their own, create/delete/modify posts, view/delete/promote/demote registered users.
  
  #### Validation
  When registering as new user, all the fields must not be blank. Additionaly username is checked for being a valid email address, avatar to be a valid url address. Password is validated with custom validators (borrowed and adjusted from author AEK) for matching with `confirmPassword` field and meeting a strong password constraints (at least 8 characters length, at least 1 upper-case character, at least 1 lower-case character, at least 1 digit, at least 1 special symbol, no sequential sequences of numbers or letters, no whitespaces). Username choice is also validated with custom UniqueValidator for preventing duplicate usernames before sending to db.
