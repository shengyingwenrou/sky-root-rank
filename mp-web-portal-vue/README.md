# mp-web-portal-vue

> Portal v3.0

## 安装
``` bash
# 安装依赖
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

## 项目技术结构
- webpack
- es6
- vue.js 2
- vue-router
- vuex

## 目录结构
```
├── build              // 构建服务和webpack配置
├── config             // 项目不同环境的配置
├── dist               // 项目build目录
├── src                // 生产目录
│   ├── api            // 后端api请求js
│   ├── assets         // 静态资源,自定义需被webpack打包的静态文件
│   ├── components     // 各种复用组件
│   ├── router         // 路由js文件
│   ├── store          // Vuex的js文件
│   ├── utils          // js工具
│   ├── views          // 各视图界面
│   ├── App.vue        // 主页面 
│   └── main.js        // Webpack 预编译入口
├── static             // 静态资源目录,不需被webpack处理的外部静态文件
├── .eslintrc.js       // eslint语法配置文件
├── index.html         // 项目入口文件
├── package.json       // 项目配置文件
```

## 接口协议
### 约定
- 统一返回值
  - status: 接口调用状态
    - success: 接口调用成功
    - failed: 接口调用失败
  - code: 接口响应代码
  - msg: 接口响应描述信息
  - ts: 系统时间戳，13位时间戳
  - data: 业务数据，由具体接口定义

