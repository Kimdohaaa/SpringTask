package SpringTask.service.member;

import SpringTask.model.dto.MemberDto;
import SpringTask.model.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    // [1] 환자등록
    public boolean addMember(MemberDto member) {
        boolean result = memberMapper.addMember(member);
        return result;
    }

    // [2] 환자조회
    public List<MemberDto> findAll(){
        List<MemberDto> result = memberMapper.findAll();
        return result;
    }

    // [3] 환자수정
    public boolean updateMember(MemberDto member) {
        boolean result = memberMapper.updateMember(member);
        return result;
    }

    // [4] 환자삭제
    public boolean deleteMember(int mno) {
        boolean result = memberMapper.deleteMember(mno);
        return result;
    }

    // [5] 환자상세조회
    public MemberDto find(int mno){
        MemberDto result = memberMapper.find(mno);
        return result;
    }

}
