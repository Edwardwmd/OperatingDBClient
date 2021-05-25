##  - 说明

​      此库是关于Android开发中进程通信学习的关键一环，里面主要针对AIDL实现两个进程之间获取和发送数据的过程，这里先对这两个进程做一个需求简述：有两个进程，一个是客户端Client（下面简称C端），另一个是远程服务端RemoteService（下面简称S端）,其中C端要查询S端数据库的数据，这时C端需要通过在S端建立的Action以及S端Service的全包名来启动S端服务。并将需求发送给S端，当S端接收到C端的需求时，就先调用本地数据库（这里使用Rxjava3操作本地数据库），按需求查询结果，将结果返回到Binder中，然后C端通过Binder拿到数据结果。

<img src="/art/CS端数据交互.png" style="zoom:50%;" />

## - 结构

​      此库中包含了两个进程：C-S端

<img src="/art/aidl结构.png" style="zoom:50%;" />

<img src="/art/aidl结构2.png" style="zoom:50%;" />



##  - 展示

​      注：两个进程都要打开，C端才能连接到S端的服务，否则无法连接。

<img src="/art/Aidl进程间通信.gif" style="zoom:50%;" />





