package com.yangke.dao;

import com.yangke.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by yangke on 16/8/16.
 */
@Mapper
public interface QuestionDAO {

    String TABLE_NAME = "question";
    String INSERT_FIELDS ="title,content,created_date,user_id,comment_count,attention_count";


    @Insert({"insert into ",TABLE_NAME, " (",INSERT_FIELDS,
            ") values (#{title},#{content},#{createdDate},#{userId},#{commentCount}),#{attentionCount};"})
    int addQuestion(Question question);

    List<Question> selectLatestQuestions(@Param("userId") int userId, @Param("offset") int offset,
                                         @Param("limit") int limit);

}
