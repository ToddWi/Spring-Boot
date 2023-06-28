/**
 * 포스트 업데이트 & 삭제
 */
document.addEventListener('DOMContentLoaded', () => {
   
   const btnDelete = document.querySelector('button#btnDelete');
   const btnUpdate = document.querySelector('button#btnUpdate');
   
   const postModifyForm = document.querySelector('#postModifyForm');
   
    
    btnDelete.addEventListener('click', () => {
       
       const check = confirm('삭제할까요?')
        
        if(check) {
        modifyForm.action = './delete';
        modifyForm.method = 'post';
        modifyForm.submit();
        }
          
    });
    
    
    btnUpdate.addEventListener('click', (e) => {
        const title = document.querySelector('#title').value;
        const content = document.querySelector('#content').value;
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력...');
            return;
        }
        
        const result = confirm('변경된 내용을 업데이트할까요?')
        if (!result) {
            return;
        }
        
        postModifyForm.action = '/post/update';
        postModifyForm.method = 'post';
        postModifyForm.submit();
        
    });
});