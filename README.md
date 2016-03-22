:running:BGARefreshLayout-Android:running:
============

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-BGARefreshLayout-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1909)
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-refreshlayout/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-refreshlayout)

开发者使用BGARefreshLayout-Android可以对各种控件实现多种下拉刷新效果、上拉加载更多以及配置自定义头部广告位

### 目前已经实现了四种下拉刷新效果:

* 新浪微博下拉刷新风格（可设置各种状态是的文本，可设置整个刷新头部的背景）
* 慕课网下拉刷新风格（可设置其中的logo和颜色成自己公司的风格，可设置整个刷新头部的背景）
* 美团下拉刷新风格（可设置其中的图片和动画成自己公司的风格，可设置整个刷新头部的背景）
* 类似qq好友列表黏性下拉刷新风格（三阶贝塞尔曲线没怎么调好，刚开始下拉时效果不太好，可设置整个刷新头部的背景）

### 一种上拉加载更多效果
* 新浪微博上拉加载更多（可设置背景、状态文本）

***开发者也可以继承BGARefreshViewHolder这个抽象类，实现相应地抽象方法做出格式各样的下拉刷新效果【例如实现handleScale(float scale, int moveYDistance)方法，根据scale实现各种下拉刷新动画】和上拉加载更多特效，可参考BGAMoocStyleRefreshViewHolder、BGANormalRefreshViewHolder、BGAStickinessRefreshViewHolder、BGAMeiTuanRefreshViewHolder的实现方式。***

### 目前存在的问题

* 当配置自定义头部广告位可滚动时，内容区域和广告位还不能平滑过度。
* 当BGAStickyNavLayout中嵌套RecyclerView或AbsListView，并且第一页的最后一个item刚好在最底部时，加载更多视图会悬浮在最后一个item上面
* 正在刷新或加载更多时，用户上下滑动不会让下拉刷新视图和加载更多视图跟着滑动



## License

    Copyright 2015 bingoogolapple

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
