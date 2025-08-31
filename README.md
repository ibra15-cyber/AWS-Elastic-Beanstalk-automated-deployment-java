# Spring Boot Elastic Beanstalk Deployment Guide

This guide covers deploying a Spring Boot application to AWS Elastic Beanstalk, from manual deployment to automated CI/CD with GitHub Actions.

http://java-app-env.eba-p3vubfdw.eu-central-1.elasticbeanstalk.com/

## 📋 Prerequisites

- AWS Account with appropriate permissions
- Java 21+ installed locally
- Gradle build system
- GitHub repository for your Spring Boot application

## 🚀 Manual Deployment

### 1. Build Your Application

```bash
# Build the JAR file
./gradlew clean build

# Make sure gradlew is executable (if needed)
chmod +x gradlew
```

### 2. Create Deployment Package

```bash
# Create deployment directory
mkdir deploy

# Copy built JAR (adjust filename as needed)
cp build/libs/*.jar deploy/

# Create Procfile
echo "web: java -jar java-elasticbean-lab-0.0.1-SNAPSHOT.jar --server.port=5000" > deploy/Procfile

# Create ZIP package
cd deploy
zip -r ../spring-boot-app.zip .
cd ..
```

### 3. Deploy to Elastic Beanstalk

1. **Upload to S3**:
   - Go to AWS S3 Console
   - Upload `spring-boot-app.zip` to your bucket

2. **Create Elastic Beanstalk Application**:
   - Platform: Java
   - Platform branch: Java 21 (Corretto)
   - Application code: Upload from S3
   - Use existing or create new environment

## 🔧 Configuration Files

### Procfile
```
web: java -jar java-elasticbean-lab-0.0.1-SNAPSHOT.jar --server.port=5000
```

### .ebextensions/environment.config (optional)
```yaml
option_settings:
  aws:elasticbeanstalk:application:environment:
    SPRING_PROFILES_ACTIVE: production
    SERVER_PORT: 5000
```

## 🤖 Automated CI/CD with GitHub Actions

### Setup Secrets

Add these secrets to your GitHub repository (Settings → Secrets → Actions):

1. `AWS_ACCESS_KEY_ID` - Your AWS access key
2. `AWS_SECRET_ACCESS_KEY` - Your AWS secret key
3. `S3_BUCKET_NAME` - Your S3 bucket name
4. `EB_APPLICATION_NAME` - Elastic Beanstalk application name
5. `EB_ENVIRONMENT_NAME` - Elastic Beanstalk environment name

### GitHub Actions Workflow

The workflow automatically:
1. Builds Spring Boot application with Gradle
2. Creates deployment package
3. Uploads to S3
4. Deploys to Elastic Beanstalk environment

### Fixing Common Issues

#### Permission Denied Error
If you see `./gradlew: Permission denied`, add this to your workflow:

```yaml
- name: Make gradlew executable
  run: chmod +x ./gradlew
```

#### Or add execute permissions locally:
```bash
chmod +x gradlew
git add gradlew
git commit -m "Make gradlew executable"
git push
```

## 📁 Project Structure

```
spring-boot-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       └── Application.java
│       └── resources/
│           └── application.properties
├── build.gradle
├── gradlew
├── Procfile
├── .ebextensions/
│   └── environment.config
└── .github/
    └── workflows/
        └── deploy.yml
```

## 🐛 Troubleshooting

### Build Issues
```bash
# Clean and rebuild
./gradlew clean build

# Skip tests for faster builds
./gradlew clean build -x test
```

### Permission Issues
```bash
# Make gradlew executable
chmod +x gradlew

# Verify permissions
ls -la gradlew  # Should show -rwxr-xr-x
```

### Deployment Issues
1. Check Elastic Beanstalk logs in AWS Console
2. Verify S3 bucket permissions
3. Ensure Procfile matches your JAR filename
4. Confirm environment variables are set correctly

## ✅ Verification

After deployment, test your application:
- Main endpoint: `https://your-app.elasticbeanstalk.com/`
- Health check: `https://your-app.elasticbeanstalk.com/health`
- API endpoint: `https://your-app.elasticbeanstalk.com/api/greeting`

## 🚀 Next Steps

1. **Add tests** to your GitHub Actions workflow
2. **Set up environment-specific configurations**
3. **Configure custom domain** in Elastic Beanstalk
4. **Set up monitoring** and alerts
5. **Implement database integration** if needed

## 📝 Notes

- Spring Boot apps must listen on port 5000 for Elastic Beanstalk
- The Procfile is essential for Java applications
- GitHub Secrets keep your AWS credentials secure
- Always test deployments in a staging environment first

This setup provides a complete CI/CD pipeline for your Spring Boot applications, from local development to production deployment on AWS Elastic Beanstalk.