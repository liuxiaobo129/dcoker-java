package com.language;

import com.github.dockerjava.core.DockerClientBuilder;

import java.io.IOException;

public class DockerClient {

    public static void main(String[] args) throws IOException {

        com.github.dockerjava.api.DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        String containerId = "ee85a0a319c27a5745d3d5a461c587d8910847bd261e2fbe87ac0da85895149f"; // 替换为实际的容器 ID

        // 停止容器
        dockerClient.stopContainerCmd(containerId).exec();
        System.out.println("容器已停止: " + containerId);

        // 删除容器
        dockerClient.removeContainerCmd(containerId).exec();
        System.out.println("容器已删除: " + containerId);

        dockerClient.close();
    }
}
