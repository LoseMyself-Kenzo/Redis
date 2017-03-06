import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by LoseMyself on 2017/3/2.
 */
public class RedisJava {
    public static void main(String[] args) {
        // 连接本地的Redis服务
        Jedis jedis = new Jedis("localhost");
        // 验证密码
        jedis.auth("950220");
        System.out.println("连接成功");
        System.out.println("Server is running:"+jedis.ping());
        /*
        字符串操作
        jedis.set("demo","950220");
        System.out.println("The data in redis:"+jedis.get("demo"));
        */
        /*
        list列表操作
        jedis.lpush("d111","1");
        jedis.lpush("d111","2");
        jedis.lpush("d111","3");
        List<String> list = jedis.lrange("d111",0,5);
        for(String li : list){
            System.out.println("The data is "+li);
        }
        */
        /*
        查询key
        */
        Set<String> sets = jedis.keys("*");
        for(String set : sets){
            System.out.println("Set of keys:"+ set);
        }

    }
}
