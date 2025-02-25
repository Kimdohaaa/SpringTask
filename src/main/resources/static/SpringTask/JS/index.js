console.log('index js open');

let reservationData = [];
// [1] 예약 현황 출력
const findR = () => {
    let tbody = document.querySelector('tbody');
    let html = ``;

    axios.get('/reservation')
        .then(r => {
            console.log(r)
            reservationData = r.data;
            r.data.forEach(item => {
                html += `
                    <tr>
                        <td>${item.rno}</td>
                        <td>${item.mname}</td>
                        <td>${item.dname}</td>
                        <td>${item.rdate}</td>
                        <td>${item.rtime}</td>
                        <td>
                            <button onclick="rUpdate(${item.rno})">수정</button>
                            <button onclick="rDelete(${item.rno})">삭제</button>
                        </td>
                    </tr>
                `;
            })
            tbody.innerHTML = html;
        })
        .catch(e => {
            console.error(e);
        });
}
findR();

// [1-2] 예약 수정
const rUpdate = (rno) => {
    let rtime = prompt("수정할 시간 : ")
    let rdate = prompt("수정할 날짜 : ")

    const obj = {rno,rtime,rdate}

    axios.put('/reservation', obj)
        .then(r=>{
            if(r.data == true){
                alert('수정 성공');
                findR();
            }
        })
        .catch(e => {console.error(e);});
}

// [1-3] 예약 취소
const rDelete = (rno) => {

    if(confirm('정말 삭제하시겠습니까?')){
        axios.delete(`/reservation?rno=${rno}`)
            .then(r => {
                if(r.data == true){
                    alert('삭제 성공');
                    findR();
                }
            })
            .catch(e => {console.error(e);});
    }
}
let membersData = [];
// [2-1] 환자 내역 조회
const findM = () => {
    let memberbox = document.querySelector('.memberbox');
    let html = ``;

    axios.get('/member')
        .then(r => {

            console.log(r)
            membersData = r.data;
            r.data.forEach(item => {
                html += `
                    <tr>
                        <td>${item.mno}</td>
                        <td onclick="rDetailM(${item.mno})">${item.mname}</td>
                        <td>${item.mphone}</td>
                        <td>
                            <button onclick="mUpdateM(${item.mno})">수정</button>
                            <button onclick="mDeleteM(${item.mno})">삭제</button>
                        </td>
                    </tr>
                `;
            })
            memberbox.innerHTML = html;

        })
        .catch(e => {
            console.error(e);
        });
}
findM();

// [2-2] 환자별 예약 상세 보기
const rDetailM = (mno) => {

    let memberHead = document.querySelector('.memberhead');
    let memberbody = document.querySelector('.memberbody');
    let detailMN = document.querySelector('.detailMN');
    let hhtml = ``;
    let bhtml =``;
    axios.get(`/reservation/findM?mno=${mno}`)
        .then(r => {
            console.log(r)
            console.log(r.data.pname)
               hhtml += `
                                                            <tr>
                                                                <th>예약번호</th>
                                                                <th>예약날짜</th>
                                                                <th>예약시간</th>
                                                                <th>의사이름</th>
                                                                <th>진료과</th>
                                                            </tr>`
            r.data.forEach(item => {
                detailMN.innerHTML = `<br/>
                                       ${item.mname} 님의 예약 현황`;


                bhtml += `
                    <tr>
                        <td>${item.rno}</td>
                        <td>${item.rdate}</td>
                        <td>${item.rtime}</td>
                        <td>${item.dname}</td>
                        <td>${item.pname}</td>
                    </tr>
                `;

            })
            memberHead.innerHTML = hhtml;
            memberbody.innerHTML = bhtml;
        })
        .catch(e => {
            console.error(e);
        });
}


// [2-3] 환자 등록
const addM = () => {
    let mname = document.querySelector('#mname').value;
    let mphone = document.querySelector('#mphone').value;


console.log(mphone)
    if(mname == "" || mphone == ""){
        alert("모든 항목을 입력하세요.")
        return;
    }
    if(mname.length > 10){
        alert("이름은 10자 이하로 입력하세요.")
        return;
    }
    if(mphone.length > 13 || mphone.length < 10){
        alert("올바른 형식의 전화번호를 입력하세요.")
        return;
    }
    const existingMember = membersData.find(member => member.mphone === mphone);
        if (existingMember) {
            alert("이미 존재하는 전화번호입니다.");
            return;
        }
    const obj = {mname,mphone}

    axios.post('/member', obj)
        .then(r=>{
            if(r.data == true){
                alert('등록 성공');
                findM();
            }
        })
        .catch(e => {console.error(e);});
}


// [2-4] 환자수정
const mUpdateM = (mno) => {
    let mname = prompt("수정할 이름 : ")
    let mphone = prompt("수정할 전화번호 : ")

    if(mname == "" || mphone == ""){
            alert("모든 항목을 입력하세요.")
            return;
        }
        if(mname.length > 10){
            alert("이름은 10자 이하로 입력하세요.")
            return;
        }
        if(mphone.length > 13 || mphone.length < 10){
            alert("올바른 형식의 전화번호를 입력하세요.")
            return;
        }
        const existingMember = membersData.find(member => member.mphone === mphone);
            if (existingMember) {
                alert("이미 존재하는 전화번호입니다.");
                return;
            }

    const obj = {mno,mname,mphone}

    axios.put('/member', obj)
        .then(r=>{
            if(r.data == true){
                alert('수정 성공');
                findM();
            }
        })
        .catch(e => {console.error(e);});
}

// [2-5] 환자삭제
const mDeleteM = (mno) => {
    if(confirm('정말 삭제하시겠습니까?')){
        axios.delete(`/member?mno=${mno}`)
            .then(r => {
                if(r.data == true){
                    alert('삭제 성공');
                    findM();
                }
            })
            .catch(e => {console.error(e);});
    }
}
// [3] 의사 내역 조회
const findD = () => {
    let doctorbox = document.querySelector('.doctorbox');
    let html = ``;

    axios.get('/doctor')
        .then(r => {
            console.log(r)
            r.data.forEach(item => {
                html += `
                    <tr>
                        <td>${item.dno}</td>
                        <td onclick="rDetailD(${item.dno})">${item.dname}</td>
                        <td>${item.pname}</td>
                        <td>
                            <button onclick="dUpdateD(${item.dno})">수정</button>
                            <button onclick="dDeleteD(${item.dno})">삭제</button>
                        </td>
                    </tr>
                `;
            })
            doctorbox.innerHTML = html;
        })
        .catch(e => {
            console.error(e);
        });
}
findD();

// [3-2] 의사별 예약 상세 보기
const rDetailD = (dno) => {
    let detailD = document.querySelector('.detailD');
    let detailDN = document.querySelector('.detailDN');
    let doctorHead = document.querySelector('.doctorhead');
    let doctorbody = document.querySelector('.doctorbody');
    let hhtml = ``;
    let bhtml =``;

    axios.get(`/reservation/findD?dno=${dno}`)
          .then(r => {
                console.log(r)
                hhtml += ` <tr>
                                                              <th>예약번호</th>
                                                              <th>예약날짜</th>
                                                              <th>예약시간</th>
                                                              <th>환자이름</th>
                                                          </tr>`
                r.data.forEach(item => {
                    detailDN.innerHTML = `<br/>
                                           ${item.dname} 님의 예약 현황`;

                    bhtml += `

                                <tr>
                                    <td>${item.rno}</td>
                                    <td>${item.rdate}</td>
                                    <td>${item.rtime}</td>
                                    <td>${item.mname}</td>
                                <tr>

                    `
                })
                 doctorHead.innerHTML = hhtml;
                 doctorbody.innerHTML = bhtml;
            })
            .catch(e => {
                console.error(e);
            });
}
// [3-3] 의사 등록
const addD = () => {
    let dname = document.querySelector('#dname').value;
    let pname = document.querySelector('#pname').value;

    if(dname == "" || pname == ""){
        alert("모든 항목을 입력하세요.")
        return;
    }
    const obj = {dname, pname}

    axios.post('/doctor', obj)
        .then(r=>{
            if(r.data == true){
                alert('등록 성공');
                findD();
            }
        })
        .catch(e => {console.error(e);});
}

// [3-4] 의사 수정
const dUpdateD = (dno) => {
    let dname = prompt("수정할 이름 : ")
    let pname = prompt("수정할 진료과 : ")
    if(dname == "" || pname == ""){
        alert("모든 항목을 입력하세요.")
        return;
    }
    const obj = {dno,dname, pname}

    axios.put('/doctor', obj)
        .then(r=>{
            if(r.data == true){
                alert('수정 성공');
                findD();
            }
        })
        .catch(e => {console.error(e);});
}

// [3-4] 의사 삭제
const dDeleteD = (dno) => {
    if(confirm('정말 삭제하시겠습니까?')){
        axios.delete(`/doctor?dno=${dno}`)
            .then(r => {
                if(r.data == true){
                    alert('삭제 성공');
                    findD();
                }
            })
            .catch(e => {console.error(e);});
    }
}

// 예약을 위한 의사,환자 내역
const findList = async () => {
    try {
        let [dList, mList] = await Promise.all([
            axios.get('/doctor'),
            axios.get('/member')
        ]);

        list(dList.data, mList.data);
    } catch (error) {
        console.error("Error fetching data:", error);
    }
};

const list = (dList, mList) => {
    console.log(dList);
    console.log(mList);

    let chooseM = document.querySelector('.chooseM');
    let chooseD = document.querySelector('.chooseD');
    let mHtml = ``;
    let dHtml = ``;

    mList.forEach(item => {
        mHtml += `
            <option class="addMno" value="${item.mno}">${item.mname}</option>
        `;
    });
    chooseM.innerHTML = mHtml;

    dList.forEach(item => {
        dHtml += `
            <option class="addDno" value="${item.dno}">${item.dname}</option>
        `;
    });

    chooseD.innerHTML = dHtml;


};

findList();

// 시간 반복문
const time = () => {
    let chooseTime = document.querySelector('.chooseTime');
    html = '';

    for (let i = 1; i <= 24; i++) {
        html += `
            <option class="addRtime" value="${i}">${i}</option>
        `;
    }
    chooseTime.innerHTML = html;
};

time();

// 예약 등록
const addR = () => {
    let mno = document.querySelector('.chooseM').value;
    let dno = document.querySelector('.chooseD').value;
    let rdate = document.querySelector('.addRdate').value;
    let rtime = document.querySelector('.chooseTime').value;
    console.log(mno, dno, rdate, rtime);
    if(mno == '' || dno == '' || rdate == '' || rtime == ''){
        alert('모든 항목을 입력하세요');
        return;
    }
    const existingR = reservationData.find(rData => rData.rdate === rdate && rData.rtime === rtime && rData.dno === parseInt(dno) )
    if(existingR){
        alert("이미 예약된 시간입니다.")
        return
    }

    console.log(mno, dno, rdate, rtime);
    const obj = {mno, dno, rdate, rtime}

    axios.post('/reservation', obj)
        .then(r=>{
            if(r.data == true){
                alert('예약 성공');
                findR();
            }
        })
        .catch(e => {console.error(e);});
}
