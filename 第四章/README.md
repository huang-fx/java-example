# 第四章示例项目

包含 Filter_Intercptor、SP_Demo1、SP_Demo2、SP_demo3、SP_demo4 和 test-interface，以及原项目中的参考图片。

## 本地运行配置

数据库相关的 Spring Boot 示例需要在运行环境中设置 DB_PASSWORD。其余连接配置见各项目的 application.yml。
SP_demo4 还需要设置 JWT_SECRET，使用至少 32 字节的随机密钥，不能将实际密钥提交到 Git。
在 IDEA 中可以通过 Run / Edit Configurations / Environment variables 设置这些变量。

各 Java 项目分别以自己的 pom.xml 导入；前端项目在 test-interface 中，安装依赖后使用 package.json 中的命令运行。

仓库排除了 target、node_modules、dist、IDE 私有配置和重复的构建输出。