'use strict';


const categoryRegistRequest = (parentId, categoryName) => {
    const formData = {};
    formData['categoryName'] = categoryName;
    if(parentId) formData['parentId'] = parentId;

    let response = null;

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/category", false);
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = () => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {

            response = xhr.responseText;

        }else {
            console.log('error', xhr.responseText);
        }
    };
    xhr.send(JSON.stringify(formData));


    if (response !== null) {
        return response;
    }else {
        return null;
    }
}

const parentCategoryRegist = () => {

    const parent_name = document.querySelector('#parent_category_name').value;

    if(!parent_name){
        alert('카테고리명을 입력해주세요');

        return false;
    } else {
        const formData = {};
        formData['categoryName'] = parent_name;


        let parentResponse = categoryRegistRequest(null, parent_name);

        if (!parentResponse) {
            alert("등록에 실패하였습니다.");
            return false;
        } else {
            parentResponse = JSON.parse(parentResponse);
        }

        console.log(parentResponse);

        const categoryList = document.querySelector('#accordionExample');

        const $parentCategory = `<div class="accordion-item">
                                  <h2 class="accordion-header" id="heading${parentResponse.id}">
                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${parentResponse.id}" aria-expanded="true" aria-controls="collapse${parentResponse.id}">
                                      ${parentResponse.categoryName}
                                    </button>
                                  </h2>
                                  <div id="collapse${parentResponse.id}" class="accordion-collapse collapse" aria-labelledby="heading${parentResponse.id}" data-bs-parent="#accordionExample">
                                    <div class="accordion-body">
                        
                                      <table class="table">
                                        <thead>
                                        <tr>
                                          <th scope="col">카테고리명</th>
                                          <th scope="col"></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                        
                                        <tr>
                                          <td>
                                            <div class="input-group mb-3">
                                              <input type="text" class="form-control" placeholder="카테고리명" aria-label="Recipient's username" aria-describedby="btn_childCategoryRegist${parentResponse.id}">
                                              <button class="btn btn-outline-secondary" type="button" id="btn_childCategoryRegist${parentResponse.id}" onclick="childCategoryRegist(${parentResponse.id}, this)">+</button>
                                            </div>
                                          </td>
                                        </tr>
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                </div>`;

        categoryList.insertAdjacentHTML('beforeend', $parentCategory);
        document.querySelector('#parent_category_name').value = ``;

    }
}


document.querySelector('#btn_parent_regist').addEventListener('click', parentCategoryRegist);


const childCategoryRegist = (parentId, clickButtonTag) => {

    const childCategoryName = clickButtonTag.previousSibling.previousSibling.value;

    let childCategoryResponse = categoryRegistRequest(parentId, childCategoryName);

    if (childCategoryResponse !== null) {
        childCategoryResponse = JSON.parse(childCategoryResponse);
        const $childCategoryElement = ` <tr>
                                          <td>${childCategoryResponse.categoryName}</td>
                                          <td>
                                            <button type="button" class="btn btn-danger btn-sm">삭제</button>
                                          </td>
                                        </tr>`;
        const $registButtonTr = clickButtonTag.parentElement.parentElement.parentElement;
        $registButtonTr.insertAdjacentHTML('beforebegin', $childCategoryElement);
        clickButtonTag.previousSibling.previousSibling.value = ``;
    }


    console.log(childCategoryResponse);
}