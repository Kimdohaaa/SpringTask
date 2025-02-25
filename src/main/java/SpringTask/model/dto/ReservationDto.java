package SpringTask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private  int rno;
    private  String mname;
    private String dname;
    private  int mno;
    private  int dno;
    private  String rdate;
    private  String rtime;
    private  String pname;
}
