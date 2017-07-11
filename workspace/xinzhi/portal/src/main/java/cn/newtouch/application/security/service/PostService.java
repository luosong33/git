package cn.newtouch.application.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.PostDao;
import cn.newtouch.application.security.dao.PostDataDao;
import cn.newtouch.application.security.entity.Post;
import cn.newtouch.application.security.entity.PostData;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	@Autowired
	private PostDataDao postDataDao;

	public Post save(Post post) {
		return postDao.save(post);
	}

	public PostData savePostData(PostData postData) {
		return postDataDao.save(postData);
	}

}
