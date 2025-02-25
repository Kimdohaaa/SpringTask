package SpringTask.model.mapper.member;

import SpringTask.model.dto.MemberDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    // [1] 환자 등록
    @Insert("insert into member (mname, mphone) values (#{mname}, #{mphone})")
    public  boolean addMember(MemberDto memberDto);

    // [2] 환자 조회
    @Select("select * from member")
    public List<MemberDto> findAll();

    // [3] 환자 수정
    @Update("update member set mname = #{mname}, mphone = #{mphone} where mno = #{mno}")
    public boolean updateMember(MemberDto memberDto);

    // [4] 환자 삭제
    @Delete("delete from member where mno = #{mno}")
    public boolean deleteMember(int mno);

    // [5] 환자 상세조회
    @Select("select * from member where mno = #{mno}")
    public MemberDto find(int mno);

}
