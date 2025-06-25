## 📢 项目简介

本工程基于[youlai-boot](https://www.youlai.tech/youlai-boot)改造而来

基于 JDK 17、Spring Boot 3、Spring Security 6、JWT、Redis、Mybatis-Plus、Vue 3、Element-Plus 构建的前后端分离单体权限管理系统。

- **🚀 开发框架**: 使用 Spring Boot 3 和 Vue 3，以及 Element-Plus 等主流技术栈，实时更新。

- **🔐 安全认证**: 基于 Spring Security 6 原生架构，集成 JWT 令牌自动续期（无状态）和 Redis 会话多端互斥管理（实时强制离线）双重认证机制，构建企业级身份安全中枢。

- **🔑 权限管理**: 基于 RBAC 模型，实现细粒度的权限控制，涵盖接口方法和按钮级别。

- **🛠️ 功能模块**: 包括用户管理、角色管理、菜单管理、部门管理、字典管理等功能。




## 📁 项目目录


<details>
<summary> 目录结构 </summary>
<br>

```ini
admin-template-boot
├── docker                              # Docker 目录
│   ├── docker-compose.yml              # docker-compose 脚本
├── sql                                 # SQL脚本
│   ├── mysql                           # MySQL 脚本
├── src                                 # 源码目录
│   ├── auth                            # 登录认证
│   ├── common                          # 公共模块
│   │   ├── annotation                  # 注解定义
│   │   ├── base                        # 基础类
│   │   ├── constant                    # 常量
│   │   ├── enums                       # 枚举类型
│   │   ├── exception                   # 异常处理
│   │   ├── model                       # 数据模型
│   │   ├── result                      # 结果封装
│   │   └── util                        # 工具类
│   ├── config                          # 自动装配配置
│   │   └── property                    # 配置属性目录
│   ├── core                            # 核心功能
│   │   ├── aspect                      # 切面(日志、防重提交)
│   │   ├── filter                      # 过滤器(请求日志、限流)
│   │   ├── handler                     # 处理器(数据权限、数据填充)
│   │   └── security                    # Spring Security 安全模块
│   ├── module                          # 业务模块
│   │   ├── member                      # 会员模块【业务模块演示】
│   │   ├── order                       # 订单模块【业务模块演示】
│   │   ├── product                     # 商品模块【业务模块演示】
│   ├── module                          # 插件扩展
│   │   ├── knife4j                     # Knife4j 扩展
│   │   ├── mybatis                     # Mybatis 扩展
│   ├── shared                          # 共享模块
│   │   ├── file                        # 文件模块
│   │   ├── codegen                     # 代码生成模块
│   │   ├── mail                        # 邮件模块
│   │   ├── sms                         # 短信模块
│   │   └── websocket                   # WebSocket 模块
│   ├── system                          # 系统模块
│   │   ├── controller                  # 控制层
│   │   ├── converter                   # MapStruct 转换器
│   │   ├── event                       # 事件处理
│   │   ├── handler                     # 处理器
│   │   ├── listener                    # 监听器
│   │   ├── model                       # 模型层
│   │   │   ├── bo                      # 业务对象
│   │   │   ├── dto                     # 数据传输对象
│   │   │   ├── entity                  # 实体对象
│   │   │   ├── form                    # 表单对象
│   │   │   ├── query                   # 查询参数对象
│   │   │   └── vo                      # 视图对象
│   │   ├── mapper                      # 数据库访问层
│   │   └── service                     # 业务逻辑层
│   └── StartupApplication              # 启动类
└── end                             
```
</details>



## 🚀 项目启动

📚 完整流程参考: [项目启动](https://www.youlai.tech/youlai-boot/1.%E9%A1%B9%E7%9B%AE%E5%90%AF%E5%8A%A8/)

1. **克隆项目**

   ```bash
   git clone git@github.com:tobeflyboy/admin-template.git
   ```

2. **数据库初始化**

   执行 [init.sql](sql/mysql/init.sql) 脚本完成数据库创建、表结构和基础数据的初始化。

3. **修改配置**

   默认连接`有来`线上 MySQL/Redis（仅读权限），本地开发时请修改 [application-dev.yml](src/main/resources/application-dev.yml) 中的 MySQL 和 Redis 连接信息。

4. **启动项目**

   执行 [StartupApplication.java](src/main/java/com/nutcracker/StartupApplication.java) 的 main 方法完成后端项目启动；

   访问接口文档地址 [http://localhost:8080/doc.html](http://localhost:8080/doc.html) 验证项目启动是否成功。
