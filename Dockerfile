# Sử dụng một base image có sẵn Java (ví dụ: OpenJDK)
FROM openjdk:21-jdk-slim

# Cài đặt OpenSSH Server và các tiện ích cần thiết
RUN apt-get update && \
    apt-get install -y openssh-server procps && \
    mkdir /var/run/sshd

# Tạo một người dùng để SSH vào (khuyến nghị, không dùng root)
RUN useradd -ms /bin/bash devuser
# Mở khóa tài khoản devuser bằng cách đặt mật khẩu rỗng
RUN echo "devuser: " | chpasswd -e

# Cấu hình SSH Server
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin no/' /etc/ssh/sshd_config
# Sửa lỗi: Thay 'ss2_config' thành 'sshd_config'
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication no/' /etc/ssh/sshd_config
RUN sed -i 's/UsePAM yes/UsePAM no/' /etc/ssh/sshd_config
RUN sed -i 's/#PubkeyAuthentication yes/PubkeyAuthentication yes/' /etc/ssh/sshd_config

# Thêm Public Key của Jenkins vào authorized_keys của devuser
COPY id_rsa_new.pub /home/devuser/.ssh/authorized_keys
# Đặt quyền hạn chặt chẽ cho thư mục .ssh và file authorized_keys
RUN chmod 700 /home/devuser/.ssh
RUN chmod 600 /home/devuser/.ssh/authorized_keys
RUN chown -R devuser:devuser /home/devuser/.ssh

# Đặt thư mục làm việc cho ứng dụng của bạn
WORKDIR /app

# Copy file JAR của ứng dụng Spring Boot vào container
# Đảm bảo tên file này khớp với tên file JAR thực tế của bạn
COPY target/crud-sqlserver-0.0.1-SNAPSHOT.jar app.jar

# Expose cổng SSH
EXPOSE 22

# Expose cổng cho ứng dụng Spring Boot của bạn (ví dụ: 8080)
EXPOSE 8080

# Lệnh mặc định khi container khởi động (chạy SSH Server và ứng dụng Spring Boot)
CMD ["sh", "-c", "/usr/sbin/sshd -D -e & java -jar app.jar"]