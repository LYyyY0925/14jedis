import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

public class jy
{
//    public static void main(String[] args) {
//        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("localhost");
//        post post = new post();
//        post.setAuthor("gll");
//        post.setContent("blog");

    //        post.setTitle("my blog");
//        Long postId = savePost(post, jedis);
//        System.out.println("保存成功");
//        post post1 = getPost(jedis, postId);
//        System.out.println(post1);
//        System.out.println("获取成功");
//    }
    public static Long savePost(Post post, Jedis jedis){
        Long posts = jedis.incr("posts");
        String postStr = JSON.toJSONString(posts);
        jedis.set("post:"+posts+":data",postStr);
//        jedis.hset()
        return posts;
    }

    public static Post getPost(Jedis jedis, Long posyId){
        String post = jedis.get("post:" + posyId + ":data");
        Post post1 = JSON.parseObject(post, Post.class);
        return post1;
    }
}
