var map;
function initMap(){
   var les={lat:37.500600, lng:127.036268};
   map=new google.maps.Map(
      document.getElementById('map'),
      {zoom:16,center:les}
   );
   
   new google.maps.Marker(
      {position:les, map:map, label:"기준 위치"}
   );
}
initMap();