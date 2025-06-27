<div align="center">
  <img alt="admin-template-web" width="80" height="80" src="./src/assets/logo.png">
  <h1>admin-template-web</h1>
</div>
## 项目简介
本工程基于[vue3-element-admin](https://gitcode.com/youlai/vue3-element-admin)改造而来

[admin-template-web](https://github.com/tobeflyboy/admin-template/tree/main/admin-template-web) 基于 Vue3、Vite、TypeScript 和 Element-Plus 搭建的极简开箱即用企业级后台管理前端模板。 配套 Java 后端 [admin-template-boot](https://github.com/tobeflyboy/admin-template/tree/main/admin-template-boot) 快速开发。


## 项目特色

- **简洁易用**：基于 [vue-element-admin](https://gitee.com/panjiachen/vue-element-admin) 升级的 Vue3 版本，无过渡封装 ，易上手。
- **数据交互**： 支持 `Mock` 数据和[线上接口文档](https://www.apifox.cn/apidoc/shared-195e783f-4d85-4235-a038-eec696de4ea5)，并提供配套的 [Java](https://gitee.com/youlaiorg/youlai-boot) 和 [Node](https://gitee.com/youlaiorg/youlai-nest) 后端源码。

- **系统功能：** 提供用户管理、角色管理、菜单管理、部门管理、字典管理等功能模块。
- **权限管理：** 支持动态路由、按钮权限、角色权限和数据权限等多种权限管理方式。

- **基础设施：** 提供国际化、多布局、暗黑模式、全屏、水印、接口文档和代码生成器等功能。
- **持续更新**：项目持续开源更新，实时更新工具和依赖。


## 项目截图

🖥️ **控制台**

![](https://www.youlai.tech/storage/blog/2025/04/30/20250430085342.png)

⚡**接口文档**

![](https://www.youlai.tech/storage/blog/2025/01/18/20250118183539.png)

📲 **移动端**

![](https://www.youlai.tech/storage/blog/2025/04/30/app.jpg)

## 项目启动


- **环境准备**

| 环境类型       | 名称                     |
|----------------|-----------------------------|
| **运行环境**   | Node 18 + (推荐[22.9.0](https://npmmirror.com/mirrors/node/v22.9.0/))  |
> ⚠️ 注意：Node.js 20.6.0版本存在兼容性问题，请勿使用


- **快速开始**

```bash
# 克隆代码
git clone git@github.com:tobeflyboy/admin-template.git

# 切换目录
cd admin-template/admin-template-web

# 安装 pnpm
npm install pnpm -g

# 设置镜像源(可忽略)
pnpm config set registry https://registry.npmmirror.com

# 安装依赖
pnpm install

# 启动运行
pnpm run dev
```


## 项目部署

执行 `pnpm run build` 命令后，项目将被打包并生成 `dist` 目录。接下来，将 `dist` 目录下的文件上传到服务器 `/usr/share/nginx/html` 目录下，并配置 Nginx 进行反向代理。

```bash
pnpm run build
```

以下是 Nginx 的配置示例：

```nginx
server {
    listen      80;
    server_name localhost;

    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }

    # 反向代理配置
    location /prod-api/ {
        # 请将 api.nutcracker.com 替换为您的后端 API 地址，并注意保留后面的斜杠 /
        proxy_pass http://api.nutcracker.com/;
    }
}
```

更多详细信息，请参考这篇文章：[Docker安装Nginx](https://blog.csdn.net/wangxin_wangxin/article/details/148948096) 

## 本地Mock

项目同时支持在线和本地 Mock 接口，默认使用线上接口，如需替换为 Mock 接口，修改文件 `.env.development` 的 `VITE_MOCK_DEV_SERVER` 为  `true` **即可**。

## 后端接口

> 如果您具备Java开发基础，按照以下步骤将在线接口转为本地后端接口，创建企业级前后端分离开发环境，助您走向全栈之路。

1. 获取基于 `Java` 和 `SpringBoot` 开发的后端 [admin-template-boot](https://github.com/tobeflyboy/admin-template/tree/main/admin-template-boot) 源码。
2. 根据后端工程的说明文档 [README.md](https://github.com/tobeflyboy/admin-template/tree/main/admin-template-boot#%E9%A1%B9%E7%9B%AE%E8%BF%90%E8%A1%8C) 完成本地启动。
3. 修改 `.env.development` 文件中的 `VITE_APP_API_URL` 的值，将其从 https://api.youlai.tech 更改为 http://localhost:8080 即可。


## 注意事项

- **自动导入插件自动生成默认关闭**

  模板项目的组件类型声明已自动生成。如果添加和使用新的组件，请按照图示方法开启自动生成。在自动生成完成后，记得将其设置为 `false`，避免重复执行引发冲突。

  ![](https://foruda.gitee.com/images/1687755823137387608/412ea803_716974.png)

- **项目启动浏览器访问空白**

  请升级浏览器尝试，低版本浏览器内核可能不支持某些新的 JavaScript 语法，比如可选链操作符 `?.`。

- **项目同步仓库更新升级**

  项目同步仓库更新升级之后，建议 `pnpm install` 安装更新依赖之后启动 。

- **项目组件、函数和引用爆红**

	重启 VSCode 尝试


## 提交规范

执行 `pnpm run commit` 唤起 git commit 交互，根据提示完成信息的输入和选择。

![](https://foruda.gitee.com/images/1687755823165218215/c1705416_716974.png)
