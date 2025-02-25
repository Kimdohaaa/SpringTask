package SpringTask.model.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Service
public class MemberDto {
    private  int mno;
    private  String mname;
    private  String mphone;
}
