![ZiuretiVeliau banner](/readme-media/ziureti-veliau-banner.png)
## ŽIŪRĖTI VĖLIAU - Blog web application

###### Developer: [Šarūnas Verbus](https://www.linkedin.com/in/sarunas-verbus/)    Client: [Codeacademy.lt](https://www.codeacademy.lt/)

### CONTENTS
- INTRODUCTION
- LAUNCHING INSTRUCTIONS
- APPLICATION DESCRIPTION AND FEATURES
- NOT YET IMPLEMENTED

### INTRODUCTION
This is an educational project for intermediate CodeAcademy exam on Java Spring MVC course. Technologies used: Java, Maven, Spring MVC, Spring Security, Hibernate, MySQL, Thymeleaf, JavaScript. The application serves blog intended for publishing various online content recommendations that users come to find out for either checking immediately or for watching later. The recommended content is served as interactive "article" feed in form of grid. Each article has: title, publishing date, type of content, hyperlink to recommended webpage, short summary about the content and descriptive illustration. All pieces of data must be provided by administrator when creating new recommendation or editing existing ones. The feed shows truncated versions of the summaries, in order to read full descriptions, users can expand the articles by clicking on "READ MORE" button. The recommendations can be commented and marked as "agreed" if the user finds them to be time-worthy (NOT YET IMPLEMENTED!). For targeted content user can filter out recommendations by content type with any string passed as query parameter or to use the predefined most common-ones automatically wired-up in the navigation menu.

### LAUNCHING INSTRUCTIONS
1. `git clone https://github.com/verbusharas/sv-blog.git`
2. Rename `application-example.properties` to `application-properties` and configure it according to your database and credentials
3. Make sure that `-Dspring.profiles.active=test` argument is passed to VM before launch.
3. Launch the Spring Boot application
4. Hibernate will seed the database with mock-up data. If Hibernate DDL exception is thrown on the initial launch, do the following steps (this only need to be done on the initial launch):
 - [x] stop the application
 - [x] in `resources/db/data.sql` - comment-out `role_users` table block
 - [x] launch and stop the application again
 - [x] uncomment the `role_users` block
 - [x] launch the application
  
  ### APPLICATION DESCRIPTION AND FEATURES
  #### User roles
  There are two roles and three authentication states for user to be in: 
  1. Anonymous user 
  2. Logged in user with role 'USER'
  3. Logged in user with role 'ADMIN'
  
  Anonymous user can browse the recommendations feed, expand articles, see the user discussions, but cannot comment themselves. Upon entering the comments section - anonymous users are informed about this restriction and can directly access registration/login pages from there. Logged in users can comment, delet or edit their own previous comments. Administrators can delete any comment and modify their own, create/delete/modify posts, view/delete/promote/demote registered users. Authorization is ensured on template level, on global level and method authorization.
  
  #### Validation
  When registering as new user, all the fields must not be blank. Additionaly username is checked for being a valid email address, avatar to be a valid url address. Password is validated with custom validators (borrowed and adjusted from author AEK) for matching with `confirmPassword` field and meeting a strong password constraints (at least 8 characters length, at least 1 upper-case character, at least 1 lower-case character, at least 1 digit, at least 1 special symbol, no sequential sequences of numbers or letters, no whitespaces). Username choice is also validated with custom UniqueValidator for preventing duplicate usernames before sending to db. Recommendation creation form asks for all the fields not to be empty, to have valid URLs provided, for content type to be at least 3 symbols length (for articles to be properly filtered on demand) and for description text to be not longer than 950 symbols, in order to be on point and keep visual consistency throughout the feed.
  
   #### Styling and scripting
   Client side UI design is fully custom styled with authentic vector graphics, responsive to large PC screens/medium PC screens/tablets/mobile devices. Several pages use JavaScript scripts for better user experience - 1) Focusing and scrolling to targeted html elements 2) Remaining symbols counter for new recommendation description 3) Recommendation image preview before publishing, to make sure the url works and for aesthetical purposes to allow administrator to design the over-all aesthetics of the article feed.
   
   ### NOT YET IMPLEMENTED
   The form styling does not respond well to screen size changes - needs fixing.
   "Agree" button functionality needs to be implemented fully.
   
