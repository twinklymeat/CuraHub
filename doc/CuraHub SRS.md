# Software Requirements Specification
## For CuraHub

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
|Ramon | 09/17   | initial SRS         |  0.1      |
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
CuraHub is a comprehensive web-based healthcare appointment scheduling platform designed to bridge the gap between healthcare providers and patients. The system serves as a digital marketplace where doctors can establish their professional presence, manage their availability, and connect with patients seeking medical care. Patients benefit from an intuitive interface that allows them to discover healthcare providers, compare options, and book appointments seamlessly.

The platform operates on a dual-sided model, catering to both healthcare professionals who need efficient practice management tools and patients who require convenient access to healthcare services. CuraHub eliminates traditional barriers in healthcare scheduling by providing real-time availability, transparent provider information, and streamlined booking processes that work across all modern web browsers and devices.

### 2.1 Product Functions
CuraHub provides the following major functions organized by user type:

**For Doctors:**
* Account creation and profile management
* Availability scheduling and management
* Profile customization and visibility settings
* Patient history and appointment tracking
* Review management and response capabilities

**For Patients:**
* User registration and account management
* Doctor search and discovery
* Appointment booking and scheduling
* Review and rating system
* Appointment history tracking

**System Functions:**
* User authentication and authorization
* Appointment matching and scheduling
* Data storage and retrieval
* Web-based user interface delivery

### 2.2 Product Constraints
The following constraints limit the developer's options for CuraHub:

**Technical Constraints:**
* Web-based application requiring modern web browsers
* Must be compatible with Java JDK and PostgreSQL database
* Development environment limited to VS Code IDE
* No budget allocation for third-party services or tools

**Interface Constraints:**
* User interface must be built using HTML, CSS, and JavaScript
* Must support any device with a web browser
* No mobile app development - web-responsive design only

**Quality of Service Constraints:**
* Login process must complete within 30 seconds
* Website navigation must not lag significantly
* Maintenance windows restricted to late night hours

**Implementation Constraints:**
* Project completion deadline: December 2025
* Zero-cost development approach
* Team size limited to two developers
  
### 2.3 User Characteristics
CuraHub serves two primary user classes with distinct characteristics and needs:

**Primary User Class - Patients:**
* **Frequency of Use:** Occasional to regular users seeking healthcare appointments
* **Technical Expertise:** Basic to intermediate web browsing skills
* **Primary Functions:** Doctor search, appointment booking, review submission
* **Security Level:** Standard user authentication required
* **Educational Level:** General public with basic computer literacy
* **Experience:** Familiar with online booking systems and web interfaces

**Secondary User Class - Doctors:**
* **Frequency of Use:** Regular to frequent users managing their practice
* **Technical Expertise:** Basic to intermediate computer skills
* **Primary Functions:** Profile management, availability scheduling, patient tracking
* **Security Level:** Enhanced authentication for sensitive patient data access
* **Educational Level:** Professional healthcare providers with varying technical comfort
* **Experience:** May have experience with other healthcare management systems

**User Class Priority:** Patients are the most important user class as they drive the demand for the platform. Doctors are essential for platform viability but secondary in terms of user experience priority.

### 2.4 Assumptions and Dependencies

**Development Environment Assumptions:**
* VS Code IDE will remain available and functional throughout development
* Java JDK will be compatible with all required features
* PostgreSQL database will provide adequate performance for expected user load
* Development team will have consistent access to development tools

**Technical Dependencies:**
* **Java JDK:** Required for backend development and API implementation
* **PostgreSQL:** Database management system for storing user and appointment data
* **Web Browser Compatibility:** Assumes users have modern browsers supporting HTML5, CSS3, and JavaScript
* **Internet Connectivity:** Assumes users have reliable internet access for web-based functionality

**External Dependencies:**
* No third-party commercial components or services (maintaining zero-cost approach)
* No external APIs or services beyond standard web technologies
* No dependencies on other software projects or reusable components

**Operational Assumptions:**
* Users will have basic computer literacy for web navigation
* Healthcare providers will be willing to adopt web-based scheduling systems
* Patients will trust online appointment booking for healthcare services
* System will operate within standard business hours with late-night maintenance windows

**Risk Factors:**
* Changes in Java JDK or PostgreSQL versions could impact development
* Browser compatibility issues could limit user accessibility
* Internet connectivity issues could affect system availability

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
