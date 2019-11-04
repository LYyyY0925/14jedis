import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class hash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        Post post = new Post();
        post.setAuthor("gll");
        post.setContent("blog");
        post.setTitle("my blog");
        Long postId = savePost(post,jedis);
        System.out.println("保存成功");
        Post myPost = getPost(postId, jedis);
        System.out.println(myPost);
    }

    static Post getPost(Long postId, Jedis jedis) {
        Map<String, String> myBlog = jedis.hgetAll("post:" + postId + ":data");
        Post post = new Post();
        post.setTitle(myBlog.get("title"));
        post.setContent(myBlog.get("content"));
        post.setAuthor(myBlog.get("author"));
        return post;

    }

    static Long savePost(Post post, Jedis jedis){
        Long postId = jedis.incr("posts");
        Map<String,String> blog = new HashMap<String, String>();
        blog.put("title",post.getTitle());
        blog.put("author",post.getAuthor());
        blog.put("content",post.getContent());
        jedis.hmset("post:"+postId+":data",blog);
        return postId;
    }
}
