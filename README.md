## 打包方式

### 手动打包

1. 前往 Dockerfile 目录，打开命令行执行

   ```bash
   $ docker build -t springboot-basic .
   ```

2. 查看生成镜像

   ```bash
   $ docker images

   REPOSITORY                                                        TAG                 IMAGE ID            CREATED             SIZE
   springboot-basic                                                  latest	      bc29a29ffca0        2 hours ago         119MB
   openjdk                                                           8-jdk-alpine        97bc1352afde        5 weeks ago         103MB
   ```

3. 运行

   ```bash
   $ docker run -d -p 9090:8080 springboot-basic
   ```

### 使用 maven 插件打包

1. pom.xml 中添加插件

2. ```xml
   <properties>
       <dockerfile-version>1.4.9</dockerfile-version>
   </properties>

   <plugins>
       <plugin>
           <groupId>com.spotify</groupId>
           <artifactId>dockerfile-maven-plugin</artifactId>
           <version>${dockerfile-version}</version>
           <configuration>
               <repository>${project.build.finalName}</repository>
               <tag>${project.version}</tag>
               <buildArgs>
                   <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
               </buildArgs>
           </configuration>
           <executions>
               <execution>
                   <id>default</id>
                   <phase>package</phase>
                   <goals>
                       <goal>build</goal>
                   </goals>
               </execution>
           </executions>
       </plugin>
   </plugins>
   ```

2. 执行mvn打包命令，因为插件中 `execution` 节点配置了 package，所以会在打包的时候自动执行 build 命令。

   ```bash
   $ mvn clean package -Dmaven.test.skip=true
   ```

3. 查看镜像

   ```bash
   $ docker images

   REPOSITORY                                                        TAG                 IMAGE ID            CREATED             SIZE
   springboot-basic                                                  1.0.0-SNAPSHOT      bc29a29ffca0        2 hours ago         119MB
   openjdk                                                           8-jdk-alpine        97bc1352afde        5 weeks ago         103MB
   ```

4. 运行

   ```bash
   $ docker run -d -p 9090:8080 springboot-basic:1.0.0-SNAPSHOT
   ```

## 参考

- docker 官方文档：https://docs.docker.com/
- Dockerfile 命令，参考文档：https://docs.docker.com/engine/reference/builder/
- maven插件使用，参考地址：https://github.com/spotify/dockerfile-maven
