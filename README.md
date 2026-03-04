# Thesis
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![arc42](https://img.shields.io/badge/architecture-arc42-lightgrey.svg?style=for-the-badge)

![App Screenshot](./docs/images/screenshot-landingpage.png)
**Thesis** is a web application connecting students with tutors and topics for their bachelor's thesis. It uses matching as well as filtering functions and offers tutors a very flexible profile edit option.

## Features

 - **Filtering:** Filter tutors and topics based on specific research fields and completed modules.
 - **Matching:** Rank tutors and topics based on specific research fields and completed modules.


## Deployment
The project is dockerized for consistent deployment across different environments.

### Environment

The App needs a GitHub OAuth2 App and a PostgreSQL Database Connection. The keys need to be entered in an `.env` file, which needs to contain the following information:

**applogin.env**
```env
# GitHub OAuth2 Connection
CLIENT_ID=your_client_id
CLIENT_SECRET=your_client_secret
# Database Connection
POSTGRES_USER=your_db_user
POSTGRES_PASSWORD=your_db_password
```

### Quick Start
1. Clone the repository using `git clone <repository-url>`
2. Configure your environment variables in the root directory as explained above
3. Launch the application with `docker compose up [-d]`
4. You can access the application under `http://localhost:8080`

## Technology Stack
### Backend / Frameworks
- Java
- Spring Boot
- Spring Security
- Spring Data JDBC
- Thymeleaf

### Build Management and Automation
- Gradle
- Docker
- Docker Compose

## Architecture 
The Project follows the Onion Architecture.

## Documentation
The Project was documented in German using the arc42-template.

## Credits
The project was written as a university project by a team of three computer science students.