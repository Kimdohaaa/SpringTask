package SpringTask.controller.member;

import SpringTask.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import SpringTask.service.member.MemberService;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    // [1] 환자 등록
    @PostMapping
    public boolean addMember(@RequestBody MemberDto memberDto){
        System.out.println("MemberController.addM");
        System.out.println("memberDto = " + memberDto);

        boolean result = memberService.addMember(memberDto);
        return result;
    }

    // [2] 환자조회
    @GetMapping
    public List<MemberDto> findAll(){
        List<MemberDto> result = memberService.findAll();
        return  result;
    }

    // [3] 환자수정
    @PutMapping
    public boolean updateMember(@RequestBody MemberDto memberDto){
        boolean result = memberService.updateMember(memberDto);
        return result;
    }

    // [4] 환자 삭제
    @DeleteMapping
    public boolean deleteMember(@RequestParam("mno") int mno){
        boolean result = memberService.deleteMember(mno);
        return result;
    }

    // [5] 환자 상세 조회
    @GetMapping("/view")
    public MemberDto find(@RequestParam("mno") int mno){
        MemberDto result = memberService.find(mno);
        return result;
    }
}
