# PlayAndroid
Kotlin 版 玩Android
#  前言：
继上次用 kotlin 编写了 一款简单 [豆瓣电影 app](http://www.wanandroid.com/blog/show/2298) 后。体验到了kotlin 的魅力。加上这段时间学习了 MVP 模式、MVVM模式，心痒痒，就像做个 app 来练练手，正当犹豫要选择哪一种来练手的时候，无意中看见另一种的模式[艺术图片应用 T-MVVM](http://www.wanandroid.com/blog/show/2357)~ 感觉说的挺有道理的。好奇心驱使我去试一下这种模式，说干就干。

#  玩Android

玩Android 采用的是 **LiveData+ViewModel+RxJava+okHttp+Retrofit+Glide** 架构
架构方面借鉴了 [艺术图片应用 T-MVVM](http://www.wanandroid.com/blog/show/2357) 也加入了自己的想法~     真香~~

#### API

[鸿洋大大的 玩Android API](http://www.wanandroid.com/blog/show/2)

#### 框架

- [anko](https://github.com/Kotlin/anko)
- [Loadsir](https://github.com/KingJA/LoadSir)
- [rxkotlin](https://github.com/ReactiveX/RxKotlin)
- [rxandroid](https://github.com/ReactiveX/RxAndroid)
- [retrofit](https://github.com/square/retrofit)
- [glide](https://github.com/bumptech/glide)
- [banner](https://github.com/youth5201314/banner)
- [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
- [webView](https://github.com/Justson/AgentWeb)
- [flowlayout](https://github.com/hongyangAndroid/FlowLayout)
- [litepal](https://github.com/LitePalFramework/LitePal) 

## 无图说 **  
![欢迎页面](https://img-blog.csdnimg.cn/20181029174425184.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029174836472.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029174917436.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029174940736.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2018102917500270.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029175020831.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029175040559.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029175050636.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)

####  扫码体验
[直接下载](https://fir.im/jtkp?release_id=5bd6f220959d697d8a58fea8)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181105212408587.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MDU5NTUxNg==,size_16,color_FFFFFF,t_70)

版本：
## v 1.0.9
- [√] 修复加载更多和刷新数据出现 缺少数据(page)
- [√] 添加首页 news 标签
- [√] 我的TODO页面显示

## v 1.0.8

- [√] 添加首页双击标题栏 返回顶部(仿微信朋友圈)
- [√] 我的收藏添加下拉刷新功能
- [√] 优化了 [项目] 代码
- [√] 优化了 [体系] 代码

## v 1.0.7

- [√] cookie 失效问题(能够正确退出。不再是登录)

## v 1.0.6

- [√] 升级了 RxJava、RxKotlin。优化了RxKotlin代码
- [√] 优化了一些代码细节

## v 1.0.5

- [√] 修复 登录成功后没有正确显示该用户的收藏文章
- [√] 升级 lib 版本

## v 1.0.4

- [√] 优化 [首页]、[搜索]、[收藏]代码 (抽取到父类 ArticleListFragment)
- [√] 修改收藏bug(没有网络仍然可以本地显示收藏)
- [√] 调整目录结构，抽取 [搜索] 到公共模块
- [√] 删除无用代码

## v 1.0.3

- [√] 优化了 [体系]、[公众号] 代码，修复bug (在我的界面取消收藏，体系、公众号界面没有刷新收藏状态)

## v 1.0.2

- [√] 完成 [公众号] 模块、转移 [我的界面] 至 主页侧边栏

## v 1.0.1

- [√] 注册界面

## v 1.0.0

- [√] 登录、退出（自动登录）
- [√] 常用网站
- [√] 收藏
- [√] 主页
- [√] 项目
- [√] 体系
- [√] 导航
- [√] 搜索
- [√] 我的

#  最后

以上就是整个 玩 Android，后续会更新剩下的功能，优化代码，添加相关注释~
欢迎大家交流学习。

#  关于我
- [Github](https://github.com/hyzhan43)
- [CSDN](https://blog.csdn.net/weixin_40595516)
- Email：1063523767@qq.com

