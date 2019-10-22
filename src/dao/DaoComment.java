package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.Comment;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class DaoComment to execute the database operations of the comments. 
 * The class inherits the class Dao.
 */
public class DaoComment extends Dao{

    public DaoComment() {
		super();
    }
	
    /**
     * The method execute the SELECT query that retrieves information of the comments from the database
     * @param query with the query that is going to be executed
     */
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
