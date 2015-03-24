# DesignPattern
common design patterns

大多数时候，要尽量保持Client的代码不变

比较需要谈到修改，为什么这种设计模式适用于**`某种程度的修改`**

**OCP(open/close principle)对扩张开放，对修改封闭。**

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

## Singleton
Cons:
Singleton不能被继承

> Reference: http://coolshell.cn/articles/265.html

单例其实有很多pitfall，感觉平常用Eagar简单直接易理解


### Eager
#### Cons
instance一直保存在内存

### Classic Lazy
#### Cons
需要加锁，效率不高。因为大部分时间instance已经生成，并不需要对`调用方法`加锁。

### Double Check Lazy
对于大部分时候不需要获取锁，效率高一些。只是开始instance未生成的时候才会去获取锁。

#### why volatile

主要在于`singleton = new Singleton()`这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。

1. 给 singleton 分配内存
2. 调用 Singleton 的构造函数来初始化成员变量，形成实例
3. 将singleton对象指向分配的内存空间（执行完这步 singleton才是非 null 了）


但是在 JVM 的即时编译器中存在**指令重排序的优化**。也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是 1-2-3 也可能是 1-3-2。如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，这时 instance 已经是非 null 了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。

使用`volatile`有两个功用：

1. 这个变量不会在多个线程中存在复本，直接从内存读取。
2. 这个关键字会禁止指令重排序优化。也就是说，在 volatile 变量的赋值操作后面会有一个内存屏障（生成的汇编代码上），读操作不会被重排序到内存屏障之前。

但是，这个事情仅在Java 1.5版后有用，1.5版之前用这个变量也有问题，因为老版本的Java的内存模型是有缺陷的。

### Singleton

使用JVM本身机制保证了线程安全问题；由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它只有在getInstance()被调用时才会真正创建；同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖 JDK 版本。

### SingletonEnum
默认枚举实例的创建`SingletonEnum.INSTANCE`是线程安全的，所以不需要担心线程安全的问题。但是在枚举中的其他任何方法的线程安全由程序员自己负责。还有防止上面的通过反射机制调用私用构造器。

## Builder

	CertainObject obj = new CertainObject.CertainObjectBuilder().method1().method2().build();
	
把具体创建过程延缓到具体Builder中

### Difference (builder & factory)
Builder : **某个复杂Object的创建**，该Object中有多个Parts，每个Part又有多种创建方式

FactoryMethod : **某一个Object创建**

AbstractFactory: **某个类别下多个不关联的Object创建** (AppleFactory creates Iphone & Ipad)

个人感觉Factory比较简单，就是对象的创建。而Builder着重在于一个object需要通过很多steps去build。这个steps是自定义的

如果Jeep决定要添加外设GPS，只需在Builder中新增withGps()；想象一下，factory中需要自己去所有建造方法中手动修改代码，并非新增。

如果Jeep决定新增PowerTrain，只需要在Builder中调用withPowerTrain(NewPowerTrain)而已，而factory则需要新增构造方法去构造返回新的Jeep。

### Summary
所以，Builder Pattern更加**细化**地创建一个Object。而Factory则是直接**一次性**创建Object。

面对一个Object的创建**变化频繁**，使用Builder Pattern，实际上将Object的创建延缓到了调用处；而Factory Pattern直接返回已经创建完毕的Object Instance。

其实也挺相似，可以在FactoryMethod传入多个参数控制，不过Builder的形式更为简洁和现代化。

Summary：Factory Pattern其实对新增支持不太好，项目初期可以使用，如果多次修改需要使用其他Creational Pattern

## Prototype
本质覆写clone()方法，使用时直接调用clone创建一个相同的instance

	CertainObject newObject = certainObjectInstance.clone()
	
这样的好处是，有些Object创建过程比较复杂，创建过一次之后，不如clone的效率高。而且某种场合不需要**新创建**的Object，需要运行过后的Object（可能已经被修改过很多次了），这个时候clone就直接迅速，程序不可能记忆Object创建之后所有的修改。

Java中clone比较复杂，约束较多，特别子类很多的情况下。所以Prototype用的不多。

## Adapter
拓展系统中已经存在的类，不能直接对其修改。或者拓展第三方，生成新的接口。

总之是过渡。不能直接修改某个类。于是新建类去拓展接口。