# DesignPattern
common design patterns


## Factory
目的主要是解耦 **decouple**
### Simple Factory | Static Factory Method

一个静态Factory，根据传入参数来创建Object。
#### Pros

简单

##### Cons

那个static factory method逻辑会变复杂代码会暴增

新增Object，Factory需要修改：违反`开闭原则(对扩展开放;对修改封闭)`

### Factory Method

每个object对应一个自己的Factory

#### Pros

每新增Object，都会创建一个与之对应的Factory，想象下新增1K个Object。

*spring使用reflection和configuration返回工厂*

### Abstract Factory

主要针对产品族。产品族里新加一个类别，所有工厂都要进行扩展（包括抽象接口）


AppleFactory创建Iphone & Ipad

GoogleFactory创建Nexus4 & Nexus7

AbstractFactory创建Phone & Tablet