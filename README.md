# Bonvoyal Web Application

Bonvoyal is a Spring Boot-based web application designed to facilitate trip planning and management. This repository contains the backend API, which interacts with a PostgreSQL database and integrates with AWS services.

---

## 🚀 Features

* **Spring Boot** application with RESTful API endpoints
* **PostgreSQL** database integration
* **AWS** integration using the AWS SDK
* **Docker** support for local development
* **Gradle** build system
* **CI/CD** pipeline configured via GitHub Actions

---

## 🛠️ Technologies Used

* Java 17
* Spring Boot
* PostgreSQL
* Docker & Docker Compose
* Gradle
* AWS SDK
* GitHub Actions

---

## 📦 Getting Started

### Prerequisites

* Java 17
* Docker & Docker Compose
* Gradle
* AWS CLI (for AWS integrations)

### Environment Variables

Create a `.env` file in the root directory and define the following variables:

```env
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
DB_NAME=your_db_name
DB_PORT=5432
DB_HOST=localhost

AWS_REGION=your_aws_region
AWS_PROFILE=your_aws_profile
TRIP_FORM_SUBMISSION_SNS_ARN=your_sns_arn
PROFILE=dev
```



### Running Locally with Docker Compose

```bash
docker-compose -p bonvoyal up --build
```



This command builds and starts the application along with a PostgreSQL database. The `-p bonvoyal` flag sets a fixed project name to ensure consistent container and network naming.

### Running Without Docker

1. Ensure PostgreSQL is running and accessible with the credentials specified in your `.env` file.
2. Build the application:

   ```bash
   ./gradlew build
   ```



3. Run the application:

   ```bash
   java -jar build/libs/bonvoyal-web-application.jar
   ```



---

## 🧪 Testing

To run tests:

```bash
./gradlew test
```



Test reports will be generated in `build/reports/tests/test/index.html`.

---

## 🔐 Security Considerations

* **AWS Credentials**: Avoid using root AWS credentials. Instead, create IAM users with minimal required permissions and configure them using the AWS CLI.
* **Sensitive Data**: Ensure that `.env` files and other sensitive information are not committed to version control.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

