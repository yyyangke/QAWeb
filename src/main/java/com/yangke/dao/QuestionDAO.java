package com.yangke.dao;

import com.yangke.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yangke on 16/8/16.
 */
@Mapper
public interface QuestionDAO {

    String TABLE_NAME = "question";
    String INSERT_FIELDS ="title,content,created_date,user_id,comment_count,attention_count";
    String SELECT_FIELDS = "id, " + INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME, " (",INSERT_FIELDS,
            ") values (#{title},#{content},#{createdDate},#{userId},#{commentCount},#{attentionCount});"})
    int addQuestion(Question question);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where id=#{id}"})
    Question selectQuestionById(int id);

    @Update({"update ",TABLE_NAME," set comment_count=#{commentCount} where id=#{id}"})
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

    List<Question> selectLatestQuestions(@Param("userId") int userId, @Param("offset") int offset,
                                         @Param("limit") int limit);

}
