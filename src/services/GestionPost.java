package services;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.Comment;
import entity.Like;
import entity.Post;
import interfaces.CommentServiceRemote;
import interfaces.LikeServiceRemote;
import interfaces.PostServiceRemote;
import javafx.collections.ObservableList;



public class GestionPost {
	
	public void AddPost(Post P){
		String jndiName="PIJEE-ear/PIJEE-ejb/PostService!interfaces.PostServiceRemote"; 
		PostServiceRemote productproxy;
		try {
			Context context = new InitialContext();
			 productproxy=(PostServiceRemote) context.lookup(jndiName);	
			 productproxy.AddPost(P);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		///display product 
		
	}
	public void AddComment(Comment c){
		String jndiName="PIJEE-ear/PIJEE-ejb/CommentService!interfaces.CommentServiceRemote"; 
		CommentServiceRemote productproxy;
		try {
			Context context = new InitialContext();
			 productproxy=(CommentServiceRemote) context.lookup(jndiName);	
			 productproxy.AddComment(c);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		///display product 
		
	}
	public List<Comment> ListComments(int id){
		System.out.println("aaaaaaaaaaa"+String.valueOf(id));
		String jndiName="PIJEE-ear/PIJEE-ejb/CommentService!interfaces.CommentServiceRemote"; 
		CommentServiceRemote productproxy = null;
		try {
			Context context = new InitialContext();
			 productproxy=(CommentServiceRemote) context.lookup(jndiName);	
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		///display product 
		return  productproxy.FindAllComments(id);
		
	}
	
	
	public void UpdatePost(Post P){
		String jndiName="PIJEE-ear/PIJEE-ejb/PostService!interfaces.PostServiceRemote"; 
		PostServiceRemote productproxy=null;
		try {
			Context context = new InitialContext();
			 productproxy=(PostServiceRemote) context.lookup(jndiName);	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		///display product 
		 productproxy.UpdatePost(P);
	}

	
	public void RemovePost(Post P){
		String jndiName="PIJEE-ear/PIJEE-ejb/PostService!interfaces.PostServiceRemote"; 
		PostServiceRemote productproxy=null;
		try {
			Context context = new InitialContext();
			 productproxy=(PostServiceRemote) context.lookup(jndiName);	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		///display product 
		 productproxy.RemovePost(P.getId());
	}
	
	public List<Post> FindAllPosts(){
		String jndiName="PIJEE-ear/PIJEE-ejb/PostService!interfaces.PostServiceRemote"; 
		PostServiceRemote productproxy=null;
		try {
			Context context = new InitialContext();
			 productproxy=(PostServiceRemote) context.lookup(jndiName);	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		///display product 
		return productproxy.FindAllPosts();
	}
	
	public Post FindPost(int id){
		String jndiName="PIJEE-ear/PIJEE-ejb/PostService!interfaces.PostServiceRemote"; 
		PostServiceRemote productproxy=null;
		try {
			Context context = new InitialContext();
			 productproxy=(PostServiceRemote) context.lookup(jndiName);	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productproxy.FindPostById(id);
	}
	public void AddLike(Like l){
		String jndiName="PIJEE-ear/PIJEE-ejb/LikeService!interfaces.LikeServiceRemote"; 
		LikeServiceRemote productproxy;
		try {
			Context context = new InitialContext();
			 productproxy=(LikeServiceRemote) context.lookup(jndiName);	
			 productproxy.AddLike(l);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//end
		///display product 
	
}
	
	public void RemoveLike(Integer iduser,Integer idcomment)
	{
		String jndiName="PIJEE-ear/PIJEE-ejb/LikeService!interfaces.LikeServiceRemote"; 
		LikeServiceRemote productproxy=null;
		try {
			Context context = new InitialContext();
			 productproxy=(LikeServiceRemote) context.lookup(jndiName);	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//end
		///display product 
		 productproxy.RemoveLike(iduser, idcomment);
	}
}
