'use strict';

const parentCategoryRegist = () => {

    const parent_name = document.querySelector('#parent_category_name').value;

    if(!parent_name){
        alert('카테고리명을 입력해주세요');

        return false;
    } else {
        const formData = {};
        formData['categoryName'] = parent_name;

        const xhr = new XMLHttpRequest();

        xhr.open("POST", "/category", true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {

                const categoryList = document.querySelector('#accordionExample');
                const parentResponse = JSON.parse(xhr.responseText);

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
                                                      <input type="text" class="form-control" placeholder="카테고리명" aria-label="Recipient's username" aria-describedby="button-addon2">
                                                      <button class="btn btn-outline-secondary" type="button" id="button-addon2">+</button>
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

            } else {
                console.log('response', xhr.responseText);
            }
        };

        xhr.send(JSON.stringify(formData));
    }
}


document.querySelector('#btn_parent_regist').addEventListener('click', parentCategoryRegist);
