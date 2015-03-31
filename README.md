# DesignPattern
common design patterns

大多数时候，要尽量保持Client的代码不变

比较需要谈到修改，为什么这种设计模式适用于**`某种程度的修改`**

**OCP(open/close principle)对扩张开放，对修改封闭。**

* abstract class表现object
* interface约束action
* `abstractClass/Interface object = new LastLayerImplementation() // 只替换最后一层`
* 自身模式逻辑和业务逻辑分离
* 重用！

**不必纠结设计模式形式，很多其实抽象出来代码本质是一样的，比如Command和Strategy**

##### Undo
1. what the hell is bridge pattern? 


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

## Bridge

    CertainObjectImpl oneImpl = new CertainObjectImpl(new SameAction())
    oneImpl.someAction()
    oneImpl.specificAction() // differs from different kinds of implementations

用于一个Object具有多个维度平行的属性和行为

* Wrangler使用Gaosoline Engine
* Wrangler使用Diesel Engine
* Renegade使用Turbo Engine
* ...

他们都可以启动发动机，但是没必要创造

1. GaosolineWrangler
2. DieselWrangler
3. TurboWrangler
4. GaosolineRenegade
5. DieselRenegade
6. TurboRenegade

未来可能会增加更多的Engine和Vehicle，Bridge可以更好的**平行增加**二个维度上的属性，而不用去生成**多种组合下的类**。

抽象出相同的行为，Bridge多用于**多维度平行拓展**的Object。

## Composite

解决Tree的Representation。自己包含自己。

##### Cons
为了讲Leaf和Node共同表现出来，抽取了共同的method和各自特殊的method。Leaf和node均有可能需要暴露自己本不该有的方法。但是该模式解决了统一问题，否则系统需要创建多个方法来满足leaf和node适配问题。

	// Composite
	systemMethod(AbstractComposite impl);
	
	// Non Composite
	systemMethod(LeafA leafA);
	systemMethod(LeafB leafB);
	systemMethod(Node node);
	
## Decorator
（覆写|拓展|隐藏）某个implementation的方法
	
	AbstractClass|Interface obj = new ConcreteImpl()
	obj.method()
	
	// after decoration
	AbstractClass|Interface decorator = new Decorator(new ConcreteImpl)
	decorator.method()
	
	
注意Decoration和ConcreteImpl是属于**一个级别**的类，仍是属于**面向接口编程**。

	// 仍然需要implement所有abstract methods
	Decorator extends|implements AbstractClass|Interface
	
##### Pros
* 仍可以使用ConcreteImpl的implemented methods，节省代码
* 不用去完成大量subclass的构建。比方说要更改某个方法的实现，得去修改某些subclass，或者继承subclass去实现新方法

##### Cons
* 与原实现容易混淆


## Facade
客户端通过调用Facade的methods就可以使用子系统。

Facade封装整合sub systems的方法给Client调用。

就是创建一个高级对外接口，封装内部各个功能。

基本上平常都用，Service封装DAO的，DAO封装底层的。

好处是Client不用自己去调用sub system里的各种methods了。

## Flyweight

Flyweight让“相同对象”只有一份，而Singleton是只有一个对象。

（个人感觉connection pool之类也算flyweight，当然主要是减少开销，也算另一方面的轻量）

适用

* 只读对象（String）
* 共享对象

##### Pros
* 内存占用小

##### Cons
* 需要工厂管理，Object太多时，查询需要时间

## Proxy

对某些方法进行代理，可以在这些方法前后添加额外的功能。

	AbstractClass|Interface obj = new ConcreteImpl();
	obj.method();
	
	AbstractClass|Interface objProxy = new Proxy();
	objProxy.method()

与Decorator一样，Proxy与ConcreteImpl是属于**一个级别**的类

##### Difference between Proxy and Decorator
* 运行：Decorator是runtime动态传入，Proxy是开始就写死了。
* 情景：Decorator多是单纯对**方法**进行扩充，Proxy更多是**业务**上的需要（Service层加入Transaction控制）。
* Decorator中ConcreteImpl一般可以继续使用，Proxy中一般只使用Proxy才能完成业务需要，ConcreteImpl是不足以支持业务要求

## Chain of responsibility

二种

* `显式Chain`： 外置流程用Chain对象来管理，Handler向Chain注册产生顺序
* `隐式Chain`： 每个Handler对象内置next handler

特点：

* 每个Handler具有筛选能力。可以去终止Message继续传递，比如UserFilter发现User没有permission，就return掉；也可以不处理分发给下一位。
* 而且更复杂些，Chain不一定是LinkedList的结构，可以同时分发message到多个Handler，或者根据情形分发message可以跳级发送（发给下下下个Handler，并非下个Handler）


#### Usage

* 同级处理更适合显式Chain
* `显示Chain`更适合在configuration file中配置；列出一起注入Chain的List|Set中，否则得一个Handler一个Handler地注册
* 该模式可以decouple掉**顺序sequence**和**处理类Handler**
* 更细一点，动态配置Handler的处理逻辑；比如Logger中，抽象出Priority，在后期配置时才给予Handler业务能力。（比较牵强，下面的iteration都能做到）
* 框架Framework中应该用的比较多，因为需要外部注册（configuration file）无法写入一个iteration中，个人觉得实际中iteration就好了。


##### iteration in one method ?

    void request(){
        request1();
        
        request2();
        
        ...
    }
    
这个其实也不是不可以，就是代码多了会显得臃肿。requestN()怎么办？每次都要修改request()？所以就用循环。

    void request(){
        for(Request request: Requests){
            request.handleRequest();
        }
    }
    
于是...这就成了Chain of responsibility了？

**目的是消灭那个臃肿的iteration**。

## Command

抽象化**执行过程**成一个**对象**。

	Executor.wannaDo() --> WannaDoCommand()

1. 命令作为对象易传递易封装参数。否则，在调用处：首先创建Executor，然后调用wannaDo(a,b,c)。
2. 把Client和Executor给decouple掉了。Client可以不知道具体的Executor，只知道自己执行了某个具体的Command。系统调用各种各样的Commands，不需要知道细节。
3. 同时Command非常细化，功能单一。不像Service层方法众多。

#### Usage
* callback

	event.addCallback(command);
	
	具体handler里面直接调用command.execute()

* undo
	
	保存在history[]中，增加undo()到command中，易于追踪之前执行过的命令，并调用undo()。
	
	否则你想怎么写？一个method里如何记录保持之前执行过的methods?

* 多次reuse一个具体操作。
* 类似于Runnable，一个操作可以抽象出独立于系统，系统直接调用Command.execute()就好。Runnable是后期动态产生的，系统当时无法知道具体Runnable引用和其实现方法签名。


#### Cons
增加中间层，麻烦。试想其实项目中每次具体方法调用都可以抽象成一个个具体的Command。

## Iterator
将遍历逻辑与容器给decouple掉了。Iterator中可以自定义多种遍历逻辑。

## Mediator
同级的Object虽然会相互交互，但是各自引用对方也不太好。而且如果增加第三个交互方，需要在前二个交互的Object都要做修改。

比如sender和receiver，二者虽然交互，但是各自引用对方在各自类中操作又显的不大合适。MailService就是Mediator。

对于对象交互这个行为，如果

* 通信协议复杂
* 交互行为多变

就可以将交互这个具体行为抽象为Mediator，同时可以更换多个Mediator来实现不同的交互行为。一个对象不需要过多关注另一个交互对象是如何接收、有些什么限制之类。

就好比现实生活中，发邮件，和收邮件，具体的如何接收发送委托给了第三方。

#### Usage
* 系统中内部复杂通信

一目了然的就不需要使用该模式了。

## Observer
一个事件的发生触发通知给所有订阅的对象。

***注意concurrent的情况，一个List遍历、添加和删除可能在同一时刻发生。***

#### observer or mediator

* Observer可以动态后期添加Subscriber。Mediator更注重初期默认交互关系绑定。不用后期register subscribers。

* Observer强调1对N去通知某个event，Mediator多用于多对多通信。

## State
用于一个Object有非常多的state。把每个状态（包含各自操作）给decouple掉了。

否组Object.someMethod()里会有很多if...else

    if current state is A then do A-Action
    else if current state is B then do B-Action
    ...
    
State Pattern有

* centralized：在context中switch state。
    
    context每个方法判断current state，自主选择调用哪个state的方法，并切换current state。

* de-centralized：在各自state中switch state(代码实例采用该种)


***注意change state需要考虑concurrent的情况，读取和切换可能在同一时刻发生***


##### Code Example Explanation
增加了个StateContext这个中间类去控制state，防止在主类（Player）中暴露changeState的method。