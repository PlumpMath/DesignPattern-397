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

Reference:

1. [https://github.com/iluwatar/java-design-patterns](https://github.com/iluwatar/java-design-patterns)
2. [http://stackoverflow.com/questions/1673841/examples-of-gof-design-patterns](http://stackoverflow.com/questions/1673841/examples-of-gof-design-patterns)

**Creational Patterns**

* Abstract Factory
* Builder
* Factory Method
* Prototype
* Property (non GoF)
* Singleton

**Structural Patterns**

* Adapter
* Bridge (how to use it?)
* Composite
* Decorator
* Facade
* Flyweight
* Proxy
* Service Locator (non GoF)
* Servant (non GoF)
* Event Aggregator (non GoF)

**Behavioral Patterns**

* Chain of Responsibility
* Command
* Interpreter
* Iterator
* Mediator
* Memento
* Observer
* State
* Strategy
* Template Method
* Visitor
* Null Object (non GoF)

**Idioms**

* Execute Around
* Double Checked Locking
* Poison Pill
* Callback



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

就像 micro-service 中的 API gateway，gateway 聚合多个micro-services来给外部调用，隐藏内部服务（安全），加快响应速度（内网或者 cloud net 内部多个网络请求）。同样也 decouple 了业务（mirco-service）和安全（facade 可以单独加 authentication）以及 reroute（facade 做 load-balancer）

缺点是 facade 需要持续更新（粗粒度 api），并且是对外唯一接口，容易成为瓶颈。

## Flyweight

Flyweight让“相同对象”只有一份，而Singleton是只有一个对象。(比如Wranger，F150都用 V6 engine，Wranger，F150这些类中，就直接 refer 同一个 V6 engine 就可以了，不需要每次都创建一个 engine；所以很多重复相同的东西就抽象出来，在内存中用一个副本就好)

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

## Service Locator

与Dependency Injection一样，decouple了实现类与调用。

需要实现实例的时候，直接`ServiceLocator.lookup(jndiName)`，系统中有**InitialContext**负责去创建实例。个人觉得相比DI，ServiceLocator比较适合多集群调用同一实例，一个Server可以专门负责创建实例。

对于调用者，只需知道“地址”——jndi name

跟DI一样，缺点是**隐藏了类的依赖关系，使得本来可以在编译器暴露的问题，在运行时才会发生**。

调用：

	Context context = new InitialContext();
	DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/drp");
	conn = dataSource.getConnection();
	
注册：

	<resource-ref>
		<description>Oracle Datasource drp</description>
		<res-ref-name>jdbc/drp</res-ref-name>
		<res-type>javax.sql.DataSource</res-type>
		<res-auth>Container</res-auth>
	</resource-ref>
	
## Servant
又是增加一个中间类

如果几个类有**共同的行为**，又**不想定义**在这些类中（实在是想不出来具体的例子），就写在Servant（中间类）中。

如果无法定义在这些类中，提取出来与别的类交互，跟Service层又挺像的。

## Event Aggregator

在Observer/Subsriber中，每一个event都有相应的Observer去处理该event。过多的event会导致过多的Observer。

所以引入一个Event Aggregator去handle所有的Event，并相应分发到对应的handlers/subsribers中。这样的decouple掉了不同Handlers/Subscribers和不同的Observers之间的联系。其实也就是拓展了一个Observer去handle所有的event。

比如一个界面的所有操作，就比较适合放到CurrentPanelEventAggregator中，易读结构也好。


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
* 类似函数传递，函数可以成为一等公民
    
        method(command, param){
            command.do(param); // pass with commandA.do(pA) or comandB.do(pB); commandA and commandB could be totally different
        }


#### Cons
增加中间层，麻烦。试想其实项目中每次具体方法调用都可以抽象成一个个具体的Command。

#### Examples

    // Apache Camel DefaultShutdownStrategy.java
    Future<?> future = getExecutorService().submit(new ShutdownTask(context, routesOrdered, timeout, timeUnit, suspendOnly, abortAfterTimeout, timeoutOccurred));

`ShutdownTask`是Runnable的子类，传入参数执行操作。

## Interpreter
* 一个句子需要解释的时候用。
* 抽象语法树
<p>

	a + b	
	
	// a & b is terminal expression
	// + is nonterminal expression
	a=3
	b=2

易于拓展（添加nonterminalExpression），易于修改（只用修改相应的nonterminalExpression）

其实最终的Context.expression中就是把句子构造成

	new Expression(new ExpressionA(new ExpressionB, new ExpressionC())) //先构造出解析的规模
	
然后在每个Expression中具体做解释。

实际开发用的比较少，可以选择第三方分析程序或者编译器生成器来处理。

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

## Memento
就是增加一个中间类Memento来save当前想要的state。这样在Originator对象中创建一个方法来**专门**操作saveToMemento()，不用在main/client中来具体写
	
	# remove following codes from clients
	Originator backup = new ...
	backup.set(...)

直接调用originator.saveToMemento()就好。

用于需要维持历史记录的情形，缺点是会消耗内存（不敢苟同，都要维持记录了你还要怎么着）

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

## Strategy
针对某一个method有多种implementation。

可以把该method的implementation和要使用它的对象decouple掉。

方便在调用处修改。

## Template Method
规范化一个固定形式的行为。减少代码重复量，并且约束子类行为。

## Visitor
`Douple Dispatch`???

>In software engineering, double dispatch is a special form of multiple dispatch, and a mechanism that dispatches a function call to different concrete functions depending on the runtime types of two objects involved in the call. In most object-oriented systems, the concrete function that is called from a function call in the code depends on the dynamic type of a single object and therefore they are known as single dispatch calls, or simply virtual function calls.

使用场景：Object A,B,C... implements|extends Parent，多个同种类别下的类。

好处是不会影响Object类本身的结构。坏处是每增加一个Object都要add一个Vistor Interface里的method，因为Vistor里的method接收不同的参数。

同样Composite适用使用Visitor去区别遍历中各个方法的操作。但是个人觉得Vistor适用于对Object A,B,C做enhancement，不适合直接调用A,B,C的方法。

比如Composite中file.delete()，就不适合Visitor

1. 要么把delete的逻辑从Object中转移到Vistor中，那么file.delete()就不能直接调用了
2. 要么在Visitor中直接调用file.delete()，那么感觉多此一举

所以像代码示例中类似

1. 只做对本身属性的enhancement
2. 每一个Visitor就是表示一个功能，所有该功能的逻辑都在这里。需要新的功能，再创建一个新的Visitor
3. 讲多个类的组成结构与每个类的处理逻辑给decouple掉了

## Null Object
把null抽象为一个Object，从此该NullObject就有默认的行为。

不会有NullPointerException，因为NullObject.method()被覆写了，有个default action，但是感觉不利于debug，可以在该method()中加入记录。

不想在代码中作过多的null判断可以使用该模式，代码也优美一点。

感觉不太实用，不过如果null作为一个合理合法legitimate的出现，可以选择NullObject，比较优美直观，也更加面向对象。

## Execute Around
感觉有点Command，Template Method，Strategy的结合。形似function programming。

    method(){
        before(); // always same
        differentAction();
        after(); // always same
    }

抽取出differentAction，减少代码量。

## Poison Pill
producer-consumer实际中，consumer可能会一直在处理message。系统需要一个graceful way to shutdown it，因为直接consumer.interrupt()会终止正在处理某个message的过程，这样会导致misbehavior。

通过send一个Poison Pill Message，consumer会在处理这个Message的时候（consumer往往只在一个时间段内处理一个message）选择一个shutdown的做法。

当然，这个只适用比较简单的Message System。

## Callback

与Command，Execute，Obeserver/Subscriber形式差不多。

用于在一个task（线程，事件）中hookup一个callback method，并且这个callback可以获得task里的部分参数。

跟Command一样感觉可以模拟functional programming。

##### Difference between callback & Observer
个人感觉Observer跟适用centralized的一个系统，有很多的参与者。

callback更偏向单个单个hook。使用简单。东西太多了就要考虑使用observer去管理。

Observer本质使用的就是callback机制，更像是一个pattern去管理callback。

##### Diff between callback & command
command是一个reusable executable command。封装好了parameters。直接用就行。

callback可以接收task里的变量作为参数，“回调”式处理这些往往必不可少的参数。不好封装起来，因为是callback往往需要这些不确定的参数。

其实也没多大区别:)
