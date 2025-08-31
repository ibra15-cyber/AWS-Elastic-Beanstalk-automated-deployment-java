package com.ibra.java_elasticbean_lab;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class WebController {

    @GetMapping("/")
    public String home() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>AWS Elastic Beanstalk - Spring Boot</title>
                <style>
                    * { margin: 0; padding: 0; box-sizing: border-box; }
                    body { 
                        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; 
                        line-height: 1.6; 
                        color: #333; 
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        min-height: 100vh;
                    }
                    .container { 
                        max-width: 1200px; 
                        margin: 0 auto; 
                        padding: 2rem; 
                    }
                    .header { 
                        background: rgba(255, 255, 255, 0.95); 
                        padding: 2rem; 
                        border-radius: 15px; 
                        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2); 
                        margin-bottom: 2rem; 
                        text-align: center; 
                    }
                    .header h1 { 
                        color: #232f3e; 
                        margin-bottom: 0.5rem; 
                        font-size: 2.5rem; 
                    }
                    .header p { 
                        color: #666; 
                        font-size: 1.2rem; 
                    }
                    .content { 
                        background: rgba(255, 255, 255, 0.95); 
                        padding: 2rem; 
                        border-radius: 15px; 
                        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2); 
                    }
                    .card { 
                        background: #f8f9fa; 
                        padding: 1.5rem; 
                        border-radius: 10px; 
                        margin-bottom: 1rem; 
                        border-left: 4px solid #667eea; 
                    }
                    .card h3 { 
                        color: #232f3e; 
                        margin-bottom: 1rem; 
                    }
                    .tech-stack { 
                        display: grid; 
                        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); 
                        gap: 1rem; 
                        margin-top: 2rem; 
                    }
                    .tech-item { 
                        background: #e9ecef; 
                        padding: 1rem; 
                        border-radius: 8px; 
                        text-align: center; 
                    }
                    .status { 
                        text-align: center; 
                        margin-top: 2rem; 
                        padding: 1rem; 
                        background: #d4edda; 
                        color: #155724; 
                        border-radius: 8px; 
                        border: 1px solid #c3e6cb; 
                    }
                    .footer { 
                        text-align: center; 
                        margin-top: 2rem; 
                        color: rgba(255, 255, 255, 0.8); 
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>🚀 AWS Elastic Beanstalk triggered</h1>
                        <p>Spring Boot Application Deployment</p>
                    </div>
                    
                    <div class="content">
                        <div class="card">
                            <h3>Welcome to Java on Elastic Beanstalk!</h3>
                            <p>This Spring Boot application has been successfully deployed using AWS Elastic Beanstalk with full CI/CD pipeline automation.</p>
                        </div>
                        
                        <div class="tech-stack">
                            <div class="tech-item">
                                <strong>Spring Boot 3</strong>
                                <p>Modern Java framework</p>
                            </div>
                            <div class="tech-item">
                                <strong>Java 17</strong>
                                <p>Latest LTS version</p>
                            </div>
                            <div class="tech-item">
                                <strong>Maven</strong>
                                <p>Build automation</p>
                            </div>
                            <div class="tech-item">
                                <strong>AWS EB</strong>
                                <p>Platform as a Service</p>
                            </div>
                        </div>
                        
                        <div class="status">
                            ✅ Application status: <strong>Running successfully</strong>
                        </div>
                    </div>
                    
                    <div class="footer">
                        <p>Deployed via GitHub Actions CI/CD | Spring Boot | Elastic Beanstalk</p>
                    </div>
                </div>
            </body>
            </html>
            """;
    }

    @GetMapping("/health")
    public String health() {
        return String.format("""
            {
                "status": "OK",
                "timestamp": "%s",
                "service": "spring-boot-elasticbeanstalk",
                "version": "1.0.0",
                "environment": "production"
            }
            """, Instant.now());
    }

    @GetMapping("/api/greeting")
    public String greeting() {
        return """
            {
                "message": "Hello from Spring Boot!",
                "timestamp": "%s",
                "framework": "Spring Boot 3"
            }
            """.formatted(Instant.now());
    }
}