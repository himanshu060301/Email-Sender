# Email Sender Application

This project is an Email Sender Application built with a backend using Java Spring Boot and a frontend developed with React. It enables users to send and receive emails, using SMTP for sending and IMAP for receiving. This README will guide you through setting up and running the application.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Usage](#usage)
---

## Features

- **Send Emails**: Users can compose and send emails to specified recipients.
- **Receive Emails**: Fetches and displays received emails using IMAP.
- **Responsive UI**: Built with React, providing an interactive and responsive user experience.

## Tech Stack

- **Backend**: Java, Spring Boot, SMTP and IMAP protocols
- **Frontend**: React, HTML, CSS, JavaScript

## Project Structure

email-sender/
├── backend/
│   ├── src/
│   ├── main/
│   └── resources/
├── frontend/
│   ├── public/
│   ├── src/
└── README.md


## Getting Started

### Prerequisites

Ensure you have the following installed:
- **Java 17+**
- **Node.js 14+**
- **Spring Boot** and **React** environments set up

## Configuration

### Backend Configuration

Configure the following settings in `application.properties` (found under `src/main/resources/`):

For **sending emails**:
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

For **receiving emails**:
```properties
mail.store.protocol=imaps
mail.imaps.host=imap.gmail.com
mail.imaps.port=993
```

### Frontend Configuration

REACT_APP_API_URL=http://localhost:8080/api/v1/email/

## Running the Application
### Backend
1. Navigate to the backend/ directory.
2. Use the following command to start the backend:
  ./mvnw spring-boot:run

Frontend
1. Navigate to the frontend/ directory.
2. Install dependencies:
  npm install
3. Run the React development server:
  npm start

## Usage
1. Open the application in your browser at http://localhost:3000.
2. Use the form to compose and send emails. View the inbox to receive and read emails.

## Contributing
1. Fork the repository
2. Create your feature branch: git checkout -b feature-name
3. Commit your changes: git commit -m 'Add feature'
4. Push to the branch: git push origin feature-name
5. Open a pull request







