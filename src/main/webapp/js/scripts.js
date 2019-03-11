var country_selected = false;

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
    if (country == '') {
        result = false;
        alert('Please, enter your Country');
    }
    if (city == '') {
        result = false;
        alert('Please, enter your City');
    }
    return result;
}

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

function fillCountries() {
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
            $('#country_name').html('<option value="0">-Выберите страну-</option>' + result);
        }
    });
}

function fillCities(country) {
    $.ajax({
        url: '/city',
        method: 'POST',
        data: "country=" + country,
        complete: function (response) {
            var result = JSON.parse(response.responseText);
            var cities = '';
            for (var i = 0; i < cities.length; i++) {
                var city = cities[i];
                result +=
                    "<option value=\"" + city + "\">" + city + "</option>";
            }
            $('#city_name').html('<option value="0">-Выберите город-</option>' + result);
        }
    });
}


