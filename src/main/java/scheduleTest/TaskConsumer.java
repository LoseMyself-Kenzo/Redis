package scheduleTest;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * Created by LoseMyself on 2017/3/6.
 */
public class TaskConsumer implements Runnable{
    Jedis jedis = new Jedis("localhost");

    public void run() {
        Random random = new Random();

        while(true){
            // 从任务队列task-queue中获取一个任务,并将该任务放入暂存队列tmp-queue
            String taskid = jedis.rpoplpush("task-queue","tmp-queue");
            // 处理任务,此处为睡觉
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            // 模拟任务成功和失败
            if(random.nextInt(13) % 7 == 0){
                // 将本次任务从暂存队列tmp-queue中弹回到任务队列task-queue中
                jedis.rpoplpush("tmp-queue","task-queue");
                System.out.println(taskid + "处理失败");
            }else {
                // 将本次任务从暂存队列tmp-queue中清楚
                jedis.rpop("tmp-queue");
                System.out.println(taskid + "处理成功,被清楚");
            }
        }
    }
}
