/**
 * Функция проверяет все ли поля формы ввел пользователь.
 * Если поле не заполнено, ввыводит сообщение с просьбой заполнить поле.
 * @returns {boolean}
 */

function validate() {
    let result = true;
    let name = $('#name').val();
    let login = $('#login').val();
    let password = $('#password').val();
    let email = $('#email').val();
    let country = $('#country').val();
    let city = $('#city').val();
    if (name == '') {
        result = false;
        alert('Please, enter your Name');
    }
    if (login == '') {
        result = false;
        alert('Please, enter your Login');
    }
    if (password == '') {
        result = false;
        alert('Please, enter your Password');
    }
    if (email == '') {
        result = false;
        alert('Please, enter your Email');
    }
    if (country == '0') {
        result = false;
        alert('Please, enter your Country');
    }
    if (city == '0') {
        result = false;
        alert('Please, enter your City');
    }
    return result;
}

/**
 * Функция проверяет все ли поля формы ввел пользователь.
 * Если поле не заполнено, ввыводит сообщение с просьбой заполнить поле.
 * @returns {boolean}
 */
function validateLogin() {
    let result = true;
    let login = $('#login').val();
    let password = $('#password').val();
    if (login == '') {
        result = false;
        alert('Please, enter your Login');
    }
    if (password == '') {
        result = false;
        alert('Please, enter your Password');
    }
    return result;
}

/**
 * Функция для заполнения выпадающего списка стран в форме создания нового пользователя.
 * Отправляет POST запрос в CountryServlet.
 * В ответ получает JSON массив с названиями стран и заполняет ими форму #country
 */
function fillCountriesCreate() {
    $.ajax({
        url: '/country',
        method: 'POST',
        complete: function (response) {
            var result = "";
            var countries = JSON.parse(response.responseText);
            for (var i = 0; i < countries.length; i++) {
                var country = countries[i];
                result +=
                    "<option value=\"" + country + "\">" + country + "</option>";
            }
            $('#country').html('<option value="0">-Выберите страну-</option>' + result);
        }
    });
}

/**
 * Функция для заполнения выпадающего списка городов в форме создания нового пользователя.
 * Отправляет POST запрос содержащий название страны в CityServlet.
 * В ответ получает JSON массив с названиями городов и заполняет ими форму #city
 * @param country название страны для которой запрашиваются города
 */
function fillCitiesCreate(country) {
    $.ajax({
        url: '/city',
        method: 'POST',
        data: "country=" + country,
        complete: function (response) {
            var result = "";
            var cities = JSON.parse(response.responseText);
            for (var i = 0; i < cities.length; i++) {
                var city = cities[i];
                result +=
                    "<option value=\"" + city + "\">" + city + "</option>";
            }
            $('#city').html('<option value="0">-Выберите город-</option>' + result);
        }
    });
}

/**
 * Функция создания нового пользователя.
 * Осуществляет проверку заполнения полей вызовом validate().
 * Если проверка прошла успешно, то отправляет форму #create_user
 */
function create() {
    if (validate() == true) {
        $('#create_user').submit();
    } else {
        false;
    }
}
/**
 * Функция для заполнения выпадающего списка стран в форме обновления существующего пользователя.
 * Отправляет POST запрос в CountryServlet.
 * В ответ получает JSON массив с названиями стран и заполняет ими форму #country_update
 */
function fillCountriesUpdate() {
    $.ajax({
        url: '/country',
        method: 'POST',
        complete: function (response) {
            var result = "";
            var countries = JSON.parse(response.responseText);
            for (var i = 0; i < countries.length; i++) {
                var country = countries[i];
                result +=
                    "<option value=\"" + country + "\">" + country + "</option>";
            }
            $('#country_update').html('<option value="0">-Выберите страну-</option>' + result);
        }
    });
}
/**
 * Функция для заполнения выпадающего списка городов в форме обновления существующего пользователя.
 * Отправляет POST запрос содержащий название страны в CityServlet.
 * В ответ получает JSON массив с названиями городов и заполняет ими форму #city_update
 * @param country название страны для которой запрашиваются города
 */
function fillCitiesUpdate(country) {
    $.ajax({
        url: '/city',
        method: 'POST',
        data: "country=" + country,
        complete: function (response) {
            var result = "";
            var cities = JSON.parse(response.responseText);
            for (var i = 0; i < cities.length; i++) {
                var city = cities[i];
                result +=
                    "<option value=\"" + city + "\">" + city + "</option>";
            }
            $('#city_update').html('<option value="0">-Выберите город-</option>' + result);
        }
    });
}
/**
 * Функция обновления существующего пользователя.
 * Осуществляет проверку заполнения полей вызовом validate().
 * Если проверка прошла успешно, то отправляет форму #update_user
 */
function update() {
    if (validate() == true) {
        $('#update_user').submit();
    } else {
        false;
    }
}