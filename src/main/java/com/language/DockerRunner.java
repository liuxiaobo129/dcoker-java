package com.language;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DockerRunner {
    public static void main(String[] args) {
        try {
            // 定义 Docker 运行命令
            String[] command = {"docker", "run", "--name", "my_container", "-d", "-p", "8080:50000", "jenkins/jenkins:latest"};

            // 创建 ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            // 启动进程
            Process process = processBuilder.start();

            // 读取并打印输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待进程执行完成
            int exitCode = process.waitFor();
            System.out.println("Docker run exit code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}