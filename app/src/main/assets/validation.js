ymaps.ready(init);

function init() {
}

function geocode(requestText) {
     // Забираем запрос из поля ввода.
     var request = requestText;
     console.log(requestText);

     ymaps.geocode(request).then(function (res) {
         var obj = res.geoObjects.get(0),
             error, hint;

         if (obj) {
             // Об оценке точности ответа геокодера можно прочитать тут: https://tech.yandex.ru/maps/doc/geocoder/desc/reference/precision-docpage/
             switch (obj.properties.get('metaDataProperty.GeocoderMetaData.precision')) {
                 case 'exact':
                     error = false;
                     break;
                 case 'number':
                 case 'near':
                 case 'range':
                     error = 'Неточный адрес, требуется уточнение';
                     hint = 'Уточните номер дома';
                     break;
                 case 'street':
                     error = 'Неполный адрес, требуется уточнение';
                     hint = 'Уточните номер дома';
                     break;
                 case 'other':
                 default:
                     error = 'Неточный адрес, требуется уточнение';
                     hint = 'Уточните адрес';
             }
         } else {
             error = 'Адрес не найден';
             hint = 'Уточните адрес';
         }

         // Если геокодер возвращает пустой массив или неточный результат, то показываем ошибку.
         if (hint) {
             showError(error);
             showMessage(hint);
         } else {
             var coord = obj.geometry.getCoordinates();
             var address = obj.getAddressLine();
             showResult(coord[0], coord[1], address);
         }
     }, function (e) {
         var error = JSON.stringify(e);
         console.log(error);
         showUnauthorized(error)
     });
}

function showResult(x, y, address) {
    Android.showResult(x, y, address);
}

function showError(message) {
    Android.showError(message);
}

function showMessage(message) {
    Android.showMessage(message);
}

function showUnauthorized(error){
    Android.showUnauthorized(error)
}