pipeline {
    agent any

    environment {
        // Đặt tên cho image Docker của bạn
        IMAGE_NAME = 'vanesline/hisw'
        // Tên của container sẽ chạy
        CONTAINER_NAME = 'spring-app'
    }

    stages {
        stage('🧹 Clean Workspace') {
            steps {
                // Dọn dẹp không gian làm việc cũ
                cleanWs()
            }
        }

        stage('⬇️ Checkout Code') {
            steps {
                // Lấy code từ kho chứa Git
                checkout scm
            }
        }

        stage('🛠️ Build Application') {
            steps {
                sh 'chmod +x ./mvnw' // Cấp quyền thực thi cho Maven wrapper
                // Build ứng dụng Spring Boot, bỏ qua test để build nhanh hơn
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('🏗️ Build Docker Image') {
            steps {
                script {
                    // Build image mới với tag là số thứ tự của lần build
                    sh "docker build -t ${IMAGE_NAME}:${env.BUILD_NUMBER} ."
                    // Gắn thêm tag 'latest' để tiện cho việc triển khai
                    sh "docker tag ${IMAGE_NAME}:${env.BUILD_NUMBER} ${IMAGE_NAME}:latest"
                }
            }
        }

        stage('🚀 Deploy') {
            steps {
                script {
                    // Dừng container cũ nếu đang chạy
                    sh "docker stop ${CONTAINER_NAME} || true"
                    // Xóa container cũ
                    sh "docker rm ${CONTAINER_NAME} || true"
                    // Chạy container mới từ image vừa build
                    // Thay 8286 bằng cổng ứng dụng của bạn
                    sh "docker run -d --name ${CONTAINER_NAME} -p 8286:8286 ${IMAGE_NAME}:latest"
                }
            }
        }
    }

    post {
        always {
            // Dọn dẹp các image Docker cũ không còn được sử dụng để tiết kiệm dung lượng
            sh 'docker image prune -f'
        }
    }
}