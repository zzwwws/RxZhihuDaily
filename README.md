# RxZhihuDaily
RxJava版知乎日报，MVP框架实现。

## Disclaim
『知乎』是 知乎. Inc 的注册商标。本软件与其代码非由知乎创作或维护。软件中所包含的信息与内容皆违反版权与知乎用户协议。它是一个免费软件，使用它不收取您任何费用。其中的所有内容均可在[知乎](http://www.zhihu.com)获取。
[RxZhihuDaily](https://github.com/zzwwws/RxZhihuDaily)中所有的资源均来自知乎日报APP。
## Description
### Sketch
整体采用MVP框架实现，这种框架的好处是可以把业务层的逻辑和UI层的展示有效地分离出来。相比于之前写地MVC或者毫无框架，这种写法在项目复杂的情况下，显得高效和清晰。业务和UI层交互是用Retrofit2+RxJava实现，基于对RxJava初浅理解，项目中实际上并没有完全体现初RxJava的精髓。另外采用了ButterKnife注入方式申明View，省去findViewById的写法，特别是在studio下有插件帮助非常好用。
### Design
知乎日报整体是AndroidMaterial Design风格，比如CardView和RecyclerView，CollapsingToolbarLayout etc. RxZhihuDaily尽量还原所有的设计，但有些动画效果由于时间因素，暂不同步。
## ScreenShot
![image](https://github.com/zzwwws/RxZhihuDaily/raw/master/screenshot/1.png) ![image](https://github.com/zzwwws/RxZhihuDaily/raw/master/screenshot/2.png) ![image](https://github.com/zzwwws/RxZhihuDaily/raw/master/screenshot/3.png) ![image](https://github.com/zzwwws/RxZhihuDaily/raw/master/screenshot/4.png) ![image](https://github.com/zzwwws/RxZhihuDaily/raw/master/screenshot/5.png)
## Agenda
1. 设置
2. 夜间模式
3. 缓存方式的优化

## Dependencies
    compile 'com.android.support:appcompat-v7:23.0.1'
    compile 'com.android.support:design:23.0.1'
    compile 'com.android.support:cardview-v7:23.0.1'
    compile 'com.android.support:recyclerview-v7:23.0.+'
    compile 'de.hdodenhof:circleimageview:1.2.1'
    compile 'com.jakewharton:butterknife:7.0.1'
    compile 'com.squareup.retrofit2:retrofit:2.0.0-beta4'
    compile 'com.squareup.retrofit2:converter-jackson:2.0.0-beta4'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.0-beta4'
    compile 'com.github.bumptech.glide:glide:3.7.0'
## Acknowledgement
- [ZhihuDailyPurify](https://github.com/izzyleung/ZhihuDailyPurify)  -  纯净版知乎日报
- [RxWeather](https://github.com/SmartDengg/RxWeather)  -  RxJava实现天气预报

## License
The MIT License (MIT)

Copyright (c) 2016 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
