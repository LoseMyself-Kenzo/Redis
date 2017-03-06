#连接进入Redis
    redis-cli进入本地客户端
    redis 127.0.0.1.6397>PING检查是否连接到本地服务,判断服务是否启动
> 远程redis服务redis-cli -h host -p -port -a -password    
> 常用方法:EXISTS key是否存在,EXPIRE key seconds设置过期时间</br>
> pertsist key移除过期时间,保持持久 rename key newkey改名 type key查询值类型
> 
> congfig get requirepass查看密码 config set requirepass xxxxxx设置密码<br>
> 设置完密码后用AUTH xxxxxx验证密码<br>
> 查询所有键 keys * 删除键DEL 名字<br>
> 先用KEYS *查询全部键,或者用EXISTS 键名查询键是否存在,TYPE 键名 返回键的类型<br> 
> EXPIRE 键名 秒数,TTL 键名返回剩余秒数加P为以毫秒为单位,PERSIST 键名移除过期时间

##五种数据结构
+ ###String 
>最基本的数据类型,一个键最大能存储512MB<br>      
``
存数据 set name(键名) "noob(键值)"
``
>
``
取数据 get name(键名)
``   
+ ###Hash
>``
存 hmset user(对象名) username(key1) zpf(value1) password(key2) 950220(value2)
``
>
``
取 hgetall user(对象名)
``
>
每个hash可以存储2的32次方-1键值对(40多亿)

+ ###List
>
``从左(头部)往右(尾部)插入,读取为lrange zpf(名称) 0(起始位置0开始) 10(终止位置)
``
> 
``
 **添加**    lpush zpf username添加元素到左边 rpush zpf password 添加元素到右边
 ``
> 
> 先进后出,后进先出.读只能从头读,即从左边读取<br>
> RPOP移除并获取列表最后一个元素,即移除先输入的数.<br>
> RPOPLPUSH移除并添加到另一个列表中
+ ###Set
>无序集合,通过哈希表实现的,所以其复杂度都是O(1)
>
``sadd demo redis 添加元素,成功返回1,若元素已存在则返回0.集合内元素唯一性第二次插入的相同元素会被忽略
``
>
``smemebers demo 获取元素    
``

+ ###Zset
>有序集合,每个元素都会关联一个double类型的分数,通过分数从小到大来给成员进行排序的
>
``  
添加 zadd key 分数 值
``
>
``查询 zrangebyscore key 0(起始分数) 100(结束分数)
``
