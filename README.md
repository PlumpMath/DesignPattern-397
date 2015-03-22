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

## Singleton
Cons:
Singleton不能被继承

> Reference: http://coolshell.cn/articles/265.html

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

