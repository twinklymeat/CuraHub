# Software Requirements Specification
## For <project name>

Version 0.1  
Prepared by Andrew Peterson & Ramon Saturnino  
CSC-340  
09/17/2025

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|Andrew| 09/17   | initial SRS         |  0.1      |
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
The purpoe of the SRS document is to describe the different requirements for the user and developer view for the CuraHub application.

User-oriented requirements describe the program from the users point of view, basically telling them what the application will be doing. However, Developer-oriented requirements describe the application in a software developmer's point of view, such as describing functional and performance requirements. 

### 1.2 Product Scope
CuraHub is a web-based application that connects doctors and patients through an appointment scheduling system. Doctors can register on the platform to create profiles and offer available appointment slots, while patients can sign up to browse doctors, select a preferred provider, and book appointments directly.


### 1.3 Definitions, Acronyms and Abbreviations                                                                                                                                                                          |

### 1.4 References
* Java - A programming language originally developed by James Gosling at Sun Microsystems. We will be using this language to build the backend service for LocalHarvest Hub.
* Postgresql - Open-source relational database management system.
* API - Application Programming Interface. This will be used to interface the backend and the fronted of our application. 
* HTML - Hypertext Markup Language. This is the code that will be used to structure and design the web application and its content.
* CSS - Cascading Style Sheets. Will be used to add styles and appearance to the web app.
* JavaScript - An object-oriented computer programming language commonly used to create interactive effects within web browsers.Will be used in conjuction with HTML and CSS to make the web app.
* VS Code - An integrated development environment (IDE) for Java. This is where our system will be created. 

### 1.5 Document Overview
Section 1 is the Introduction, and is intended for anyone. Section 2 is about the product and its features, mostly intended for customers, and Section 3 is about the requirements for the product and development process, meant for the developers.

## 2. Product Overview
This section should describe the general factors that affect the product and its requirements. This section does not state specific requirements. Instead, it provides a background for those requirements, which are defined in detail in Section 3, and makes them easier to understand.

### 2.1 Product Functions
Summarize the major functions the product must perform or must let the user perform. Details will be provided in Section 3, so only a high level summary (such as a bullet list) is needed here. Organize the functions to make them understandable to any reader of the SRS. A picture of the major groups of related requirements and how they relate, such as a top level data flow diagram or object class diagram, is often effective.

### 2.2 Product Constraints
This subsection should provide a general description of any other items that will limit the developer’s options. These may include:  

* Interfaces to users, other applications or hardware.  
* Quality of service constraints.  
* Standards compliance.  
* Constraints around design or implementation.
  
### 2.3 User Characteristics
Identify the various user classes that you anticipate will use this product. User classes may be differentiated based on frequency of use, subset of product functions used, technical expertise, security or privilege levels, educational level, or experience. Describe the pertinent characteristics of each user class. Certain requirements may pertain only to certain user classes. Distinguish the most important user classes for this product from those who are less important to satisfy.

### 2.4 Assumptions and Dependencies
List any assumed factors (as opposed to known facts) that could affect the requirements stated in the SRS. These could include third-party or commercial components that you plan to use, issues around the development or operating environment, or constraints. The project could be affected if these assumptions are incorrect, are not shared, or change. Also identify any dependencies the project has on external factors, such as software components that you intend to reuse from another project, unless they are already documented elsewhere (for example, in the vision and scope document or the project plan).

## 3. Requirements

### 3.1 Functional Requirements 

* The System shall allow doctors to create an account a
* The System shall allow doctors to change their availability
* The system shall allow doctors to change their profile description 
* The system shall allow doctors to make their profile available to patients.
* the system shall allow doctors to view a list of their past patients
* the system shall allow doctors to reply to reviews.

#### 3.1.1 User interfaces
Web pages built with HTML CSS and JavaScript

#### 3.1.2 Hardware interfaces
Any device with a web browser

#### 3.1.3 Software interfaces
* Java jdk
* PostgreSQL

### 3.2 Non Functional Requirements 

#### 3.2.1 Performance
* CuraHub will allow any user to log in within 30 seconds
* The CuraHub website will not lag significantly while navigating the website.

#### 3.2.2 Security
* any sensitive user info will be encrypted using their password

#### 3.2.3 Reliability


#### 3.2.4 Availability
* Any maintenance will be done late at night 

#### 3.2.5 Compliance


#### 3.2.6 Cost
* We wont spend money on this project

#### 3.2.7 Deadline
* The project will be finished by December 2025
