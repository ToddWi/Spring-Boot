/**
 * 포스트 업데이트 & 삭제
 */
document.addEventListener('DOMContentLoaded', () => {
   
   const btnDelete = document.querySelector('button#btnDelete');
   const btnUpdate = document.querySelector('button#btnUpdate');
   
   const modifyForm = document.querySelector('#postModifyForm');
   
    
    btnDelete.addEventListener('click', () => {
       
       const check = confirm('삭제할까요?')
        
        if(check) {
        modifyForm.action = './delete';
        modifyForm.method = 'post';
        modifyForm.submit();
        }
          
    });
    
    
    btnUpdate.addEventListener('click', () => {
       
       const title = document.querySelector('input#title').value;
       const content = document.querySelector('textarea#content').value;
       if(title === '' || content === '') {
            alert("제목과 내용은 반드시 입력");
            return; // 함수 종료
        }
        const check = confirm('업데이트할까요?');
        
        if(check) {
            modifyForm.action = './update';
            modifyForm.method = 'post';
            modifyForm.submit();
        }
       //포스트 업뎃 
    });
});