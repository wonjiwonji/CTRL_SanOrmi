var btn1 = document.getElementById('btn1');
btn1.addEventListener('click', cc1);
var btn2 = document.getElementById('btn2');
btn2.addEventListener('click', cc2);
var btn3 = document.getElementById('btn3');
btn3.addEventListener('click', cc3);

var map;
function initMap() {
    var ll = { lat: 37.5006, lng: 127.036268 };
    var lll = { lat: 33.36137772737338, lng: 126.52941200732668 };
    var llll = { lat: 38.11950420422224, lng: 128.4655321759461 };
    var lllll = { lat: 36.342498180510994, lng: 127.2058331712449 };
    map = new google.maps.Map(document.getElementById('map'), { zoom: 15, center: ll });
    const marker = new google.maps.Marker({ position: ll, map: map, title: 'll' });
    const marker2 = new google.maps.Marker({ position: lll, map: map, title: 'lll' });
    const marker3 = new google.maps.Marker({ position: llll, map: map, title: 'llll' });
    const marker4 = new google.maps.Marker({ position: lllll, map: map, title: 'lllll' });
}
initMap();

function cc1() {
    var ll = { lat: 33.36137772737338, lng: 126.52941200732668 };
    map.panTo(ll);
    map.setZoom(12);
}
function cc2() {
    var ll = { lat: 38.11950420422224, lng: 128.4655321759461 };
    map.panTo(ll);
    map.setZoom(12);
}
function cc3() {
    var ll = { lat: 36.342498180510994, lng: 127.2058331712449 };
    map.panTo(ll);
    map.setZoom(12);
}
