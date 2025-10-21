# CuraHub - Software Design 

Version 1  
Prepared by Ramon Saturnino & Andrew Peterson
CSC-340 Team 7  
December 2024

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Product Overview](#1-product-overview)
* 2 [Use Cases](#2-use-cases)
  * 2.1 [Use Case Model](#21-use-case-model)
  * 2.2 [Use Case Descriptions](#22-use-case-descriptions)
    * 2.2.1 [Actor: Doctor](#221-actor-doctor)
    * 2.2.2 [Actor: Patient](#222-actor-patient) 
* 3 [UML Class Diagram](#3-uml-class-diagram)
* 4 [Database Schema](#4-database-schema)

## Revision History
| Name           | Date    | Reason For Changes  | Version   |
| ----           | ------- | ------------------- | --------- |
| Andrew & Ramon | 10/20   | Initial Design      |    1      |
|                |         |                     |           |
|                |         |                     |           |

## 1. Product Overview
CuraHub is a comprehensive web based healthcare appointment scheduling platform designed to bridge the gap between healthcare providers and patients. The system serves as a digital marketplace where doctors can establish their professional presence, manage their availability, and connect with patients seeking medical care. Patients benefit from an intuitive interface that allows them to discover healthcare providers, compare options, and book appointments seamlessly.

The platform operates on a dual sided model, catering to both healthcare professionals who need efficient practice management tools and patients who require convenient access to healthcare services. CuraHub eliminates traditional barriers in healthcare scheduling by providing real-time availability, transparent provider information, and streamlined booking processes.

## 2. Use Cases
### 2.1 Use Case Model
![Use Case Model - Placeholder for CuraHub Use Case Diagram]

### 2.2 Use Case Descriptions

#### 2.2.1 Actor: Doctor
##### 2.2.1.1 Sign Up
A doctor can sign up to create their profile with their name, email, password, phone number, specialty, and practice information. Emails must be unique.
##### 2.2.1.2 Log In
A doctor shall be able to sign in using their registered email and password. After logging in, the doctor shall be directed to their dashboard where they see an overview of their appointments, patient requests, and practice statistics.
##### 2.2.1.3 Update Profile
A doctor shall be able to modify their profile by going to their profile page. They can change their email, password, specialty, practice information, and professional description.
##### 2.2.1.4 Manage Availability
The doctor shall be able to set and modify their available appointment slots. They can add, remove, or update time slots for different days and times.
##### 2.2.1.5 View Patient Requests
A doctor will be able to view appointment requests from patients and approve or reject them based on their availability and patient needs.
##### 2.2.1.6 View Patient History
A doctor will be able to view a list of their past patients and appointment history for reference and continuity of care.
##### 2.2.1.7 Respond to Reviews
A doctor will be able to view and respond to patient reviews and ratings to maintain their professional reputation.

#### 2.2.2 Actor: Patient
##### 2.2.2.1 Sign Up
A patient can sign up to create their profile with their name, email, password, phone number, and address. Emails must be unique.
##### 2.2.2.2 Log In
A patient shall be able to sign in using their registered email and password. After logging in, the patient shall be directed to their dashboard where they see an overview of their appointments and healthcare providers.
##### 2.2.2.3 Search for Doctors
A patient shall be able to search for doctors by specialty, location, or name. They can filter results based on availability, ratings, and other criteria.
##### 2.2.2.4 View Doctor Profiles
A patient shall be able to view detailed doctor profiles including specialty, experience, availability, reviews, and ratings from other patients.
##### 2.2.2.5 Book Appointment
Upon selecting a doctor, a patient shall be able to book an appointment by selecting from available time slots. The appointment will be pending until the doctor approves it.
##### 2.2.2.6 Manage Appointments
A patient shall be able to view their upcoming appointments, cancel or reschedule existing appointments, and view their appointment history.
##### 2.2.2.7 Write Reviews
A patient may write a review and rating for a doctor they have had an appointment with, helping other patients make informed decisions.

## 3. UML Class Diagram
![UML Class Diagram - Placeholder for CuraHub Class Diagram]

## 4. Database Schema
![Database Schema - Placeholder for CuraHub Database Schema]
