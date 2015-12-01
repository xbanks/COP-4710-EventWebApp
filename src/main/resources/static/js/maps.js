function initMap() {
    var myLatlng = {lat: -25.363, lng: 131.044};

    var map = new google.maps.Map(document.getElementById('google-map'), {
        zoom: 4,
        center: myLatlng
    });

    var input = /** @type {!HTMLInputElement} */(
      document.getElementById('location'));
    var autocomplete = new google.maps.places.Autocomplete(input);
    autocomplete.bindTo('bounds', map);
    var infowindow = new google.maps.InfoWindow();
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, -29)
    });

    autocomplete.addListener('place_changed', function(){
        infowindow.close();
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            window.alert("There is no geometry for this place");
            return;
        }

        if(place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
        } else{
            map.setCenter(place.geometry.location);
            map.setZoom(17);
        }

        marker.setIcon(/** @type {google.maps.Icon} */({
          url: place.icon,
          size: new google.maps.Size(71, 71),
          origin: new google.maps.Point(0, 0),
          anchor: new google.maps.Point(17, 34),
          scaledSize: new google.maps.Size(35, 35)
        }));

        marker.setPosition(place.geometry.location);
        marker.setVisible(true);

        var address = '';
        if (place.address_components) {
          address = [
            (place.address_components[0] && place.address_components[0].short_name || ''),
            (place.address_components[1] && place.address_components[1].short_name || ''),
            (place.address_components[2] && place.address_components[2].short_name || '')
          ].join(' ');
        }

        infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
        infowindow.open(map, marker);
        document.getElementById('placeId').setAttribute('value', place.place_id);
        document.getElementById('placeName').setAttribute('value', place.name);
        document.getElementById('placeLat').setAttribute('value', place.geometry.location.lat());
        document.getElementById('placeLon').setAttribute('value', place.geometry.location.lng());
        document.getElementById('hidden_location').setAttribute('value', 999);
        console.log(place.geometry);

  });
}