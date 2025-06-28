pipeline {
    agent any // Hoặc chỉ định agent cụ thể nếu có (ví dụ: agent { docker { image 'maven:3.8.1-jdk-11' } })

    tools {
        // Cấu hình Maven tool đã cài đặt trong Jenkins
        // Đảm bảo bạn đã cài đặt Maven trong Manage Jenkins -> Global Tool Configuration
        maven 'Maven 3.8.1' 
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ivy159205/spring-crud.git'
            }
        }

        stage('Build') {
            steps {
                // Chạy lệnh Maven để build dự án
                // clean install sẽ xóa build cũ và tạo build mới
                sh 'mvn clean install -DskipTests' 
            }
        }

        stage('Test') {
            steps {
                echo 'Kiểm thử ứng dụng...'
            }
        }

        stage('Package') {
            steps {
                // Tạo file JAR/WAR cuối cùng
                sh 'mvn package -DskipTests' 
                // Lưu ý: mvn package cũng sẽ build và test
                // Nếu bạn đã có stage build riêng, có thể tùy chỉnh
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def appName = 'crud-sqlserver-0.0.1-SNAPSHOT.jar' // Tên file JAR của bạn
                    // Tìm file JAR được tạo ra
                    def jarFile = findFiles(glob: 'target/*.jar')[0] 
                    if (jarFile) {
                        appName = jarFile.name
                    } else {
                        error 'Không tìm thấy file JAR. Đảm bảo build thành công.'
                    }

                    // Ví dụ triển khai lên một server từ xa bằng SSH
                    // Đảm bảo bạn đã cài SSH Publishers Plugin và cấu hình SSH Servers trong Jenkins
                    // (Dashboard -> Manage Jenkins -> Configure System -> Publish over SSH)
                    // Thay thế 'your_ssh_server_config' bằng tên cấu hình SSH của bạn
                    // Thay thế '/path/to/remote/server/apps/' bằng đường dẫn trên server đích
                    sshPublisher(publishers: [
                        sshPublisherDesc(
                            configName: 'your_ssh_server_config', 
                            transfers: [
                                sshTransfer(
                                    sourceFiles: "target/${appName}", 
                                    removePrefix: 'target', 
                                    remoteDirectory: '/path/to/remote/server/apps/',
                                    execCommand: """
                                        sudo systemctl stop your-spring-app-service || true
                                        sudo mv /path/to/remote/server/apps/${appName} /path/to/remote/server/apps/old_${appName} || true
                                        sudo mv /path/to/remote/server/apps/${appName} /path/to/remote/server/apps/
                                        sudo systemctl start your-spring-app-service
                                        sudo systemctl status your-spring-app-service
                                    """
                                )
                            ]
                        )
                    ])

                    // Hoặc triển khai bằng Docker (nếu bạn dùng Docker)
                    // withDockerRegistry([credentialsId: 'your-docker-registry-credentials', url: 'https://your-docker-registry.com']) {
                    //     sh "docker build -t your-registry/your-spring-app:latest ."
                    //     sh "docker push your-registry/your-spring-app:latest"
                    // }
                    // sh "ssh your-server 'docker pull your-registry/your-spring-app:latest && docker stop your-spring-app || true && docker rm your-spring-app || true && docker run -d --name your-spring-app -p 8080:8080 your-registry/your-spring-app:latest'"
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline completed successfully!'
            // Có thể gửi thông báo thành công ở đây
        }
        failure {
            echo 'Pipeline failed!'
            // Có thể gửi thông báo thất bại ở đây
        }
    }
}