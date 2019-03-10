<?php
header('Content-Type: text/html; charset=utf-8');
mysql_connect('jdbc:postgresql://127.0.0.1:5432/', 'postgres', 'fastin' );
mysql_select_db('tracker');
mysql_query('set names utf8');

$id   = (int)$_POST['id']; // id объекта (страна или регион)
$type = $_POST['type']; // тип списка, который нужно получить (города или регионы)

sleep(1); // спешить нам некуда

if ($type == 'city') {
	// выбираем города в данном регионе
	$result = mysql_query('SELECT *
    	                   FROM city
    	                   WHERE region_id = '.$id.'
    	                   ORDER BY name');
	if (!empty($result)) {
		echo "out.options[out.options.length] = new Option('выберите город...','none');\n";
		while ($city = mysql_fetch_array($result)) {
			echo "out.options[out.options.length] = new Option('".$city['name']."','".$city['city_id']."');\n";
		}
	}
	else {
		echo "out.options[out.options.length] = new Option('нет городов','none');\n";
	}
}
if ($type == 'region') {
	// выбираем регионы в данной стране
	$result = mysql_query('SELECT *
    	                    FROM region
    	                    WHERE country_id = '.$id.'
    	                    ORDER BY name');
	if (!empty($result)) {
		echo "out.options[out.options.length] = new Option('выберите регион...','none');\n";
		while ($region = mysql_fetch_array($result)) {
			echo "out.options[out.options.length] = new Option('".$region['name']."','".$region['region_id']."');\n";
		}
	}
	else {
		echo "out.options[out.options.length] = new Option('нет регионов','none');\n";
	}
}
?>