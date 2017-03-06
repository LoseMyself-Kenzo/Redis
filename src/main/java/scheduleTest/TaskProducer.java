package scheduleTest;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.UUID;

/**
 * Created by LoseMyself on 2017/3/6.
 */
public class TaskProducer implements Runnable{

    // 连接本地的Redis服务
    Jedis jedis = new Jedis("localhost");
    public void run() {
        Random random = new Random();
        while(true){
            try{
                Thread.sleep(random.nextInt(600)+600);
                // 随机产生一个通用标志符,当做产生随机事件
                UUID taskid = UUID.randomUUID();
                // 将事件插入到任务队列task-queue中
                jedis.lpush("task-queue",taskid.toString());
                System.out.println("插入一条新的任务: " + taskid);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
