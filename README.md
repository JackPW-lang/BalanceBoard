# BalanceBoard - A JavaFX Application

### Update: Development slowed until the Fall 2025 Semester, where this app & its development will officially be supervised by a professor at the School of Computer Science at McGill. Following this, development will continue in full force.

A JavaFX-based productivity tool designed to help students prevent procrastination and avoid missing due dates. Inspired by real-world student challenges, this app provides an intuitive agenda system, built-in analytics, and a reward system to streamline academic workflow and promote better time management.

## Features

- **Task Management:** Organize assignments and deadlines with multiple views (weekly, daily, monthly calendar).

- **Agenda System:** Tasks are structured as objects with key attributes like due dates and completion status.

- **Analytics & Productivity Tracking:** Tracks submission patterns, procrastination trends, and generates a personalized productivity score.

- **Gamified Reward System:** Virtual trophies recognize task completion and consistency.

- **Smart Scheduling:** Prioritizes tasks based on due dates and importance.

- **Grade Calculator:** Computes required final exam scores to achieve desired letter grades.

- **AI Integration (Future Plan):** Adaptive reminders, predictive analytics, and personalized task suggestions

## Getting Started

Ensure you have the following installed:

- Java 21+

- JavaFX SDK

- Maven (for dependency management)

- SQLite (for data persistence)

## Installation:

## Running the Application

## Target Audience

BalanceBoard is designed for students ranging from high school to university/college, helping them effectively manage academic workloads and deadlines. Future updates may introduce separate High School and University modes to better tailor productivity tracking and organization to different education levels.

## 🤝 Contributing

At this time, contributions are not permitted as this project is private.

## 📄 License (This project shall NOT be considered open source, at this time.)

Copyright (c) 2025 Jack Parry-Wingfield.

All rights reserved.

This software and its source code are proprietary and confidential. Unauthorized copying, distribution, modification, or use of any part of this software without explicit permission from the author is strictly prohibited.

**Developed with ❤️ by Jack Parry-Wingfield**

## 📜 Documentation

If you wish to view this documentation in LaTEX, a full LaTeX-based documentation is available in the docs/ directory. You can compile it using:
```bash
$ pdflatex balanceboard.tex
```
### *Abstract*
For as long as education has existed, students have struggled with time management, often overes-
timating their available time and missing critical academic deadlines. Balancing a busy schedule while
knowing when to start each task is challenging, leading to unnecessary setbacks and, for many, an over-
whelming cycle of falling behind. However, it doesn’t have to be this way. Inspired by my younger
sibling’s academic hardships in their very first university semester, this application is designed to put an
end to time management failures and procrastination, ensuring that completed work gets submitted on
time, and further helping students achieve a sustainable work-life balance.

The application provides an intuitive agenda system, allowing users to manage tasks in multiple views,
including week, day, and calendar-month modes. Tasks are structured as objects with key attributes like
due dates and completion status, enabling efficient organization. To further motivate students, a reward
system featuring virtual trophies recognizes task completion and consistency. Additionally, built-in
analytics track submission patterns, time taken to start tasks, and other productivity metrics. These
insights contribute to a personalized productivity score, providing students with a clear overview of their
work habits over daily, weekly, monthly, and yearly periods. Though not initially implemented, AI and
machine learning will be used to better cater to each student and their unique work habits, as no two
students are exactly the same.

The application is developed using JavaFX for the user interface, offering a responsive and visually
intuitive experience for students managing their tasks. The core logic is implemented in Java, utilizing
its strong object-oriented paradigm to structure task management, scheduling, and analytics. Data
persistence will be handled using SQLite, providing a lightweight yet efficient database solution for
storing user tasks, progress, and productivity metrics. In future iterations, machine learning and AI will
be integrated to personalize user experiences. This may involve TensorFlow for Java or Weka to analyze
study habits and optimize task recommendations based on individual productivity trends. Additional
libraries and frameworks, such as JFoenix for enhanced UI components and Quartz Scheduler for task
reminders, may also be incorporated to improve functionality and user engagement.
This application is designed for students ranging from high school to university/college, helping them
effectively manage their academic workload and deadlines. Recognizing the differences in workflow and
academic structure between these levels, a possible feature under consideration is the implementation
of two distinct modes: High School Mode and University Mode. This would tailor task organization,
reminders, and productivity tracking to better suit the unique challenges faced by students at each stage.
While this feature is not yet guaranteed, future iterations may explore its feasibility based on user needs
and feedback.

This documentation provides a comprehensive overview of the application, detailing its purpose,
design, and technical aspects. It begins with an introduction outlining the app’s objectives and the
motivation behind its development. The technology stack section describes the key technologies used
and their respective roles in building the application. App functionality is then explored, highlighting core
features and their significance in enhancing student productivity. Following this, the user interface design
section presents the app’s visual structure and explains how functionality is balanced with simplicity.
The user guide offers detailed instructions on maximizing the application’s potential, ensuring users can
efficiently navigate and utilize its features. Additionally, data management is discussed, explaining how
task-related data is stored, retrieved, and maintained for persistence. Finally, the documentation assesses
potential future improvements, including the integration of artificial intelligence and machine learning to
further personalize and optimize the user experience.

### *1 - Introduction*

The transition from high school to university—and even from elementary or middle school to high
school—is a significant leap in both academic workload and expectations. Across all levels of education,
falling behind in coursework can feel overwhelming, and once a student is struggling to catch up, it
becomes all too easy to miss crucial deadlines and face unnecessary penalties.
This issue is personal to me. My younger brother, who had already faced motivational struggles in
high school, encountered a particularly difficult first semester in university. Despite having completed
most of his work, a simple lapse in due-date awareness led to multiple missed deadlines across all his
courses, resulting in a failing CGPA. However, I knew this outcome did not reflect his abilities. Like
many students, he simply lost track of deadlines—a challenge we all face at some point.
After a deep discussion on motivation and organization, he went on to ace his next semester, proving
that success is not a matter of ability, but of structure and awareness. This experience reinforced
the importance of maintaining an organized schedule and keeping track of tasks effectively—hence, the
motivation behind this application.

This application is a comprehensive productivity tool designed to help students effectively manage
their academic responsibilities and minimize the impact of procrastination and missed deadlines. At its
core, it features an intuitive agenda system that allows users to organize tasks across multiple views,
including weekly, daily, and calendar-month modes, catering to different planning preferences. Each task
is treated as an object with key attributes, such as a due date and completion status, ensuring structured
task management and efficient tracking of upcoming deadlines.

To further engage and motivate students, the app includes a reward system that recognizes consis-
tency and task completion. By earning virtual trophies or achievement badges, students receive positive
reinforcement, helping to build better work habits over time. Additionally, the app offers built-in an-
alytics that track key productivity patterns, such as submission trends, time taken to initiate tasks,
and overall work consistency. These data points contribute to a personalized productivity score, giving
students a clear overview of their performance across various timeframes (daily, weekly, monthly, and
yearly).

Future versions of the application will explore the integration of AI-driven insights, which will analyze
individual work habits and suggest optimal study schedules based on past behavior. This will allow for
personalized recommendations, ensuring that students receive tailored guidance on when and how to
structure their workload most effectively. By combining structured task management, motivation-driven
rewards, and analytical insights, this application serves as a smart academic companion, empowering
students to take control of their productivity and achieve greater success in their studies.

### *2 - Technology Stack*
### *3 - App Functionality*
#### *3.1 - The Core Feature*
#### *3.2 - Analytics & Producivity Tracking*
#### *3.3 - Task Prioritization & Smart Scheduling*
#### *3.4 - Gamified Reward System*
#### *3.5 - Built-In Grade Calculator*
#### *3.6 - Future AI/ML Enhancements*
### *4 - User Interface Design*
#### *4.1 - Launching the app for the first time (Setup Screen)*
#### *4.2 - Launching the app regularly (Welcome Screen)*
### *5 - User Guide*
### *6 - Data Management*
### *7 - Future Improvements & AI Integration*
### *Conclusion*
### *References*





