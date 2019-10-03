package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import userLogic.Comment;

public class DaoComment extends Dao{

    public DaoComment() {
		super();
    }
	
    @Override
	public ArrayList<Comment> selectQuery(String query) throws SQLException{
		ArrayList<Comment> result = new ArrayList<Comment>();
        stmt = conn.createStatement();                                           
        rs = stmt.executeQuery(query);
        while (rs.next()) {
        	Comment newComment = new Comment();
        	newComment.setIdOwner(rs.getString(2));
        	newComment.setDescription(rs.getString(3));
        	newComment.setToneName(rs.getString(4));
        	newComment.setScore(rs.getFloat(5));
        	result.add(newComment);
        }

        rs.close();
        stmt.close();
        conn.commit();
        return result;
    }

}
