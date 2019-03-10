<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/WEB-INF/lib/jquery.js"></script>
    <script type="text/javascript">
        function getList(type, obj) {
            //$("#loading_" + type).show(); // запускаем крутящееся колесико
            $.post("/WEB-INF/view/city.php", {type: type, id: $("#" + obj).val()}, onAjaxSuccess);

            function onAjaxSuccess(data) {
                out = document.getElementById(type);
                for (var i = out.length - 1; i >= 0; i--) {
                    out.options[i] = null;
                }
                eval(data);
                //$("#loading_" + type).hide(); // скрываем крутящееся колесико
            }
        }
    </script>
    <script>
        function validate() {
            var result = true;
            var name = $('#name').val();
            var login = $('#login').val();
            var password = $('#password').val();
            var email = $('#email').val();
            var country = $('#country').val();
            var city = $('#city').val();
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
    </script>
</head>
<body>
<br>
<br>
<div class="container">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>name</th>
            <th>login</th>
            <th>password</th>
            <th>email</th>
            <th>role</th>
            <th>country</th>
            <th>region</th>
            <th>city</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <form action="${pageContext.servletContext.contextPath}/user_create_servlet" method="post"
              onsubmit="validate()">
            <td>
                <input class="form-control input-sm" id="name" type="text" name='name'>
            </td>
            <td>
                <input class="form-control input-sm" id="login" type="text" name='login'>
            </td>
            <td>
                <input class="form-control input-sm" id="password" type="text" name='password'>
            </td>
            <td>
                <input class="form-control input-sm" id="email" type="text" name='email'>
            </td>
            <td>
                <select name="role" id="role">
                    <option value="admin">Admin</option>
                    <option value="moderator">Moderator</option>
                    <option value="user">User</option>
                </select>
            </td>
            <td>
                <select name="countryid" id="country" onchange="getList('region', 'country')" style="width:300px;">
                    <option value="4">Австралия</option>
                    <option value="63">Австрия</option>
                    <option value="81">Азербайджан</option>
                    <option value="173">Ангуилья</option>
                    <option value="177">Аргентина</option>
                    <option value="245">Армения</option>
                    <option value="7716093">Арулько</option>
                    <option value="248">Беларусь</option>
                    <option value="401">Белиз</option>
                    <option value="404">Бельгия</option>
                    <option value="425">Бермуды</option>
                    <option value="428">Болгария</option>
                    <option value="467">Бразилия</option>
                    <option value="616">Великобритания</option>
                    <option value="924">Венгрия</option>
                    <option value="971">Вьетнам</option>
                    <option value="994">Гаити</option>
                    <option value="1007">Гваделупа</option>
                    <option value="1012">Германия</option>
                    <option value="1206">Голландия</option>
                    <option value="2567393">Гондурас</option>
                    <option value="277557">Гонконг</option>
                    <option value="1258">Греция</option>
                    <option value="1280">Грузия</option>
                    <option value="1366">Дания</option>
                    <option value="2577958">Доминиканская республика</option>
                    <option value="1380">Египет</option>
                    <option value="1393">Израиль</option>
                    <option value="1451">Индия</option>
                    <option value="277559">Индонезия</option>
                    <option value="277561">Иордания</option>
                    <option value="3410238">Ирак</option>
                    <option value="1663">Иран</option>
                    <option value="1696">Ирландия</option>
                    <option value="1707">Испания</option>
                    <option value="1786">Италия</option>
                    <option value="1894">Казахстан</option>
                    <option value="2163">Камерун</option>
                    <option value="2172">Канада</option>
                    <option value="582029">Карибы</option>
                    <option value="2297">Кипр</option>
                    <option value="2303">Киргызстан</option>
                    <option value="2374">Китай</option>
                    <option value="582040">Корея</option>
                    <option value="2430">Коста-Рика</option>
                    <option value="582077">Куба</option>
                    <option value="2443">Кувейт</option>
                    <option value="2448">Латвия</option>
                    <option value="582060">Ливан</option>
                    <option value="2505884">Ливан</option>
                    <option value="2509">Ливия</option>
                    <option value="2514">Литва</option>
                    <option value="2614">Люксембург</option>
                    <option value="582041">Македония</option>
                    <option value="277563">Малайзия</option>
                    <option value="582043">Мальта</option>
                    <option value="2617">Мексика</option>
                    <option value="582082">Мозамбик</option>
                    <option value="2788">Молдова</option>
                    <option value="2833">Монако</option>
                    <option value="2687701">Монголия</option>
                    <option value="582065">Морокко</option>
                    <option value="277551">Нидерланды</option>
                    <option value="2837">Новая Зеландия</option>
                    <option value="2880">Норвегия</option>
                    <option value="582051">О.А.Э.</option>
                    <option value="582105">Остров Мэн</option>
                    <option value="582044">Пакистан</option>
                    <option value="582046">Перу</option>
                    <option value="2897">Польша</option>
                    <option value="3141">Португалия</option>
                    <option value="3156">Реюньон</option>
                    <option value="3159" selected="selected">Россия</option>
                    <option value="277555">Румыния</option>
                    <option value="5647">Сальвадор</option>
                    <option value="277565">Сингапур</option>
                    <option value="582067">Сирия</option>
                    <option value="5666">Словакия</option>
                    <option value="5673">Словения</option>
                    <option value="5678">Суринам</option>
                    <option value="5681">США</option>
                    <option value="9575">Таджикистан</option>
                    <option value="277567">Тайвань</option>
                    <option value="582050">Тайланд</option>
                    <option value="582090">Тунис</option>
                    <option value="9638">Туркменистан</option>
                    <option value="277569">Туркмения</option>
                    <option value="9701">Туркс и Кейкос</option>
                    <option value="9705">Турция</option>
                    <option value="9782">Уганда</option>
                    <option value="9787">Узбекистан</option>
                    <option value="9908">Украина</option>
                    <option value="10648">Финляндия</option>
                    <option value="10668">Франция</option>
                    <option value="277553">Хорватия</option>
                    <option value="10874">Чехия</option>
                    <option value="582031">Чили</option>
                    <option value="10904">Швейцария</option>
                    <option value="10933">Швеция</option>
                    <option value="582064">Эквадор</option>
                    <option value="10968">Эстония</option>
                    <option value="3661568">ЮАР</option>
                    <option value="11002">Югославия</option>
                    <option value="11014">Южная Корея</option>
                    <option value="582106">Ямайка</option>
                    <option value="11060">Япония</option>
                </select>
            </td>
            <td>
                <select name="regionid" id="region" onchange="getList('city', 'region')" style="width:300px;">
                    <option value="1998532">Адыгея</option>
                    <option value="3160">Алтайский край</option>
                    <option value="3223">Амурская обл.</option>
                    <option value="3251">Архангельская обл.</option>
                    <option value="3282">Астраханская обл.</option>
                    <option value="3296">Башкортостан(Башкирия)</option>
                    <option value="3352">Белгородская обл.</option>
                    <option value="3371">Брянская обл.</option>
                    <option value="3407">Бурятия</option>
                    <option value="3437">Владимирская обл.</option>
                    <option value="3468">Волгоградская обл.</option>
                    <option value="3503">Вологодская обл.</option>
                    <option value="3529">Воронежская обл.</option>
                    <option value="3630">Дагестан</option>
                    <option value="3673">Еврейская обл.</option>
                    <option value="3675">Ивановская обл.</option>
                    <option value="3703">Иркутская обл.</option>
                    <option value="3751">Кабардино-Балкария</option>
                    <option value="3761">Калининградская обл.</option>
                    <option value="3827">Калмыкия</option>
                    <option value="3841">Калужская обл.</option>
                    <option value="3872">Камчатская обл.</option>
                    <option value="3892">Карелия</option>
                    <option value="3921">Кемеровская обл.</option>
                    <option value="3952">Кировская обл.</option>
                    <option value="3994">Коми</option>
                    <option value="4026">Костромская обл.</option>
                    <option value="4052">Краснодарский край</option>
                    <option value="4105">Красноярский край</option>
                    <option value="4176">Курганская обл.</option>
                    <option value="4198">Курская обл.</option>
                    <option value="4925">Ленинградская обл.</option>
                    <option value="4227">Липецкая обл.</option>
                    <option value="4243">Магаданская обл.</option>
                    <option value="4270">Марий Эл</option>
                    <option value="4287">Мордовия</option>
                    <option value="4312" selected="selected">Москва и Московская обл.</option>
                    <option value="4481">Мурманская обл.</option>
                    <option value="3563">Нижегородская обл.</option>
                    <option value="4503">Новгородская обл.</option>
                    <option value="4528">Новосибирская обл.</option>
                    <option value="4561">Омская обл.</option>
                    <option value="4593">Оренбургская обл.</option>
                    <option value="4633">Орловская обл.</option>
                    <option value="4657">Пензенская обл.</option>
                    <option value="4689">Пермская обл.</option>
                    <option value="4734">Приморский край</option>
                    <option value="4773">Псковская обл.</option>
                    <option value="4800">Ростовская обл.</option>
                    <option value="4861">Рязанская обл.</option>
                    <option value="4891">Самарская обл.</option>
                    <option value="4969">Саратовская обл.</option>
                    <option value="5011">Саха (Якутия)</option>
                    <option value="5052">Сахалин</option>
                    <option value="5080">Свердловская обл.</option>
                    <option value="5151">Северная Осетия</option>
                    <option value="5161">Смоленская обл.</option>
                    <option value="5191">Ставропольский край</option>
                    <option value="5225">Тамбовская обл.</option>
                    <option value="5246">Татарстан</option>
                    <option value="3784">Тверская обл.</option>
                    <option value="5291">Томская обл.</option>
                    <option value="5326">Тульская обл.</option>
                    <option value="5312">Тыва (Тувинская Респ.)</option>
                    <option value="5356">Тюменская обл.</option>
                    <option value="5404">Удмуртия</option>
                    <option value="5432">Ульяновская обл.</option>
                    <option value="5473">Хабаровский край</option>
                    <option value="2316497">Хакасия</option>
                    <option value="2499002">Ханты-Мансийский АО</option>
                    <option value="5507">Челябинская обл.</option>
                    <option value="5543">Чечено-Ингушетия</option>
                    <option value="5555">Читинская обл.</option>
                    <option value="5600">Чувашия</option>
                    <option value="2415585">Чукотский АО</option>
                    <option value="5019394">Ямало-Ненецкий АО</option>
                    <option value="5625">Ярославская обл.</option>

                </select>
            </td>
            <td>
                <select name="city_id" id="city" style="width:300px;">
                    <option value="4313">Абрамцево</option>
                    <option value="4314">Алабино</option>
                    <option value="4315">Апрелевка</option>
                    <option value="4316">Архангельское</option>
                    <option value="4317">Ашитково</option>
                    <option value="7592923">Байконур</option>
                    <option value="4318">Бакшеево</option>
                    <option value="4319">Балашиха</option>
                    <option value="4320">Барыбино</option>
                    <option value="4321">Белоомут</option>
                    <option value="4322">Белые Столбы</option>
                    <option value="4323">Бородино</option>
                    <option value="4324">Бронницы</option>
                    <option value="4325">Быково</option>
                    <option value="4326">Валуево</option>
                    <option value="4327">Вербилки</option>
                    <option value="4328">Верея</option>
                    <option value="4329">Видное</option>
                    <option value="4330">Внуково</option>
                    <option value="4331">Вождь Пролетариата</option>
                    <option value="4332">Волоколамск</option>
                    <option value="4333">Вороново</option>
                    <option value="4334">Воскресенск</option>
                    <option value="4335">Восточный</option>
                    <option value="4336">Востряково</option>
                    <option value="4337">Высоковск</option>
                    <option value="4338">Голицино</option>
                    <option value="4339">Деденево</option>
                    <option value="4340">Дедовск</option>
                    <option value="4341">Джержинский</option>
                    <option value="4342">Дмитров</option>
                    <option value="4343">Долгопрудный</option>
                    <option value="4344">Домодедово</option>
                    <option value="4345">Дорохово</option>
                    <option value="4346">Дрезна</option>
                    <option value="4347">Дубки</option>
                    <option value="4348">Дубна</option>
                    <option value="4349">Егорьевск</option>
                    <option value="4350">Железнодорожный</option>
                    <option value="4351">Жилево</option>
                    <option value="4352">Жуковский</option>
                    <option value="4353">Загорск</option>
                    <option value="4354">Загорянский</option>
                    <option value="4355">Запрудная</option>
                    <option value="4356">Зарайск</option>
                    <option value="4357">Звенигород</option>
                    <option value="4358">Зеленоград</option>
                    <option value="4359">Ивантеевка</option>
                    <option value="4360">Икша</option>
                    <option value="4361">Ильинский</option>
                    <option value="4362">Истра</option>
                    <option value="4363">Калининград</option>
                    <option value="4364">Кашира</option>
                    <option value="4365">Керва</option>
                    <option value="4366">Климовск</option>
                    <option value="4367">Клин</option>
                    <option value="4368">Клязьма</option>
                    <option value="4369">Кожино</option>
                    <option value="4370">Кокошкино</option>
                    <option value="4371">Коломна</option>
                    <option value="4372">Колюбакино</option>
                    <option value="3118538">Королев</option>
                    <option value="4373">Косино</option>
                    <option value="4374">Котельники</option>
                    <option value="4375">Красково</option>
                    <option value="4376">Красноармейск</option>
                    <option value="4377">Красногорск</option>
                    <option value="4378">Краснозаводск</option>
                    <option value="7593075">Краснознаменск</option>
                    <option value="4379">Красный Ткач</option>
                    <option value="4380">Крюково</option>
                    <option value="4381">Кубинка</option>
                    <option value="4382">Купавна</option>
                    <option value="4383">Куровское</option>
                    <option value="4384">Лесной Городок</option>
                    <option value="4385">Ликино-Дулево</option>
                    <option value="4386">Лобня</option>
                    <option value="4387">Лопатинский</option>
                    <option value="4388">Лосино-Петровский</option>
                    <option value="4389">Лотошино</option>
                    <option value="4390">Лукино</option>
                    <option value="4391">Луховицы</option>
                    <option value="4392">Лыткарино</option>
                    <option value="4393">Львовский</option>
                    <option value="4394">Люберцы</option>
                    <option value="4395">Малаховка</option>
                    <option value="4396">Михайловское</option>
                    <option value="4397">Михнево</option>
                    <option value="4398">Можайск</option>
                    <option value="4399">Монино</option>
                    <option value="4400" selected="selected">Москва</option>
                    <option value="4401">Муханово</option>
                    <option value="4402">Мытищи</option>
                    <option value="4403">Нарофоминск</option>
                    <option value="4404">Нахабино</option>
                    <option value="4405">Некрасовка</option>
                    <option value="4406">Немчиновка</option>
                    <option value="4407">Новобратцевский</option>
                    <option value="4408">Новоподрезково</option>
                    <option value="4409">Ногинск</option>
                    <option value="4410">Обухово</option>
                    <option value="4411">Одинцово</option>
                    <option value="4412">Ожерелье</option>
                    <option value="4413">Озеры</option>
                    <option value="4414">Октябрьский</option>
                    <option value="4415">Опалиха</option>
                    <option value="4416">Орехово-Зуево</option>
                    <option value="4417">Павловский Посад</option>
                    <option value="4418">Первомайский</option>
                    <option value="4419">Пески</option>
                    <option value="4420">Пироговский</option>
                    <option value="4421">Подольск</option>
                    <option value="4422">Полушкино</option>
                    <option value="4423">Правдинский</option>
                    <option value="4424">Привокзальный</option>
                    <option value="4425">Пролетарский</option>
                    <option value="3138841">Протвино</option>
                    <option value="4426">Пушкино</option>
                    <option value="4427">Пущино</option>
                    <option value="4428">Радовицкий</option>
                    <option value="4429">Раменское</option>
                    <option value="4430">Реутов</option>
                    <option value="4431">Решетниково</option>
                    <option value="4432">Родники</option>
                    <option value="4433">Рошаль</option>
                    <option value="4434">Рублево</option>
                    <option value="4435">Руза</option>
                    <option value="4436">Салтыковка</option>
                    <option value="4437">Северный</option>
                    <option value="4438">Сергиев Посад</option>
                    <option value="4439">Серебряные Пруды</option>
                    <option value="4440">Серпухов</option>
                    <option value="4441">Солнечногорск</option>
                    <option value="4442">Солнцево</option>
                    <option value="4443">Софрино</option>
                    <option value="4444">Старая Купавна</option>
                    <option value="4445">Старбеево</option>
                    <option value="4446">Ступино</option>
                    <option value="4447">Сходня</option>
                    <option value="4448">Талдом</option>
                    <option value="4449">Текстильщик</option>
                    <option value="4450">Темпы</option>
                    <option value="4451">Тишково</option>
                    <option value="4452">Томилино</option>
                    <option value="4453">Троицк</option>
                    <option value="4454">Туголесский Бор</option>
                    <option value="4455">Тучково</option>
                    <option value="4456">Уваровка</option>
                    <option value="4457">Удельная</option>
                    <option value="4458">Успенское</option>
                    <option value="4459">Фирсановка</option>
                    <option value="4460">Фосфоритный</option>
                    <option value="4461">Фрязино</option>
                    <option value="4462">Фряново</option>
                    <option value="4463">Химки</option>
                    <option value="4464">Хорлово</option>
                    <option value="4465">Хотьково</option>
                    <option value="4466">Черкизово</option>
                    <option value="4467">Черноголовка</option>
                    <option value="4468">Черусти</option>
                    <option value="4469">Чехов</option>
                    <option value="4470">Шарапово</option>
                    <option value="4471">Шатура</option>
                    <option value="4472">Шатурторф</option>
                    <option value="4473">Шаховская</option>
                    <option value="4474">Шереметьевский</option>
                    <option value="4475">Щелково</option>
                    <option value="4476">Щербинка</option>
                    <option value="4477">Электрогорск</option>
                    <option value="4478">Электросталь</option>
                    <option value="4479">Электроугли</option>
                    <option value="4480">Яхрома</option>
                </select>
            </td>
            <td>
                <input type="hidden" name="action" value="create">
                <button type='submit'> Create User</button>
            </td>
        </form>
        </tbody>
    </table>
</div>
</body>
</html>

