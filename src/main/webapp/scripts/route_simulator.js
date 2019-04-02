var RouteServiceFactory = (function(){

    var routeService;

    function createObject(){

        var RouteService = function(){

            var init = function(){
                this.socket = stomp;
            }

            var send = function(data){
               // this.socket.send(JSON.stringify(data));
                
                stomp.send("/app/publish", {}, JSON.stringify(data));
            }

            this.init = init;
            this.send = send;
        };

        var object = new RouteService();
        return object;
    }

    var getRouteService = function (){
        if(!routeService){
            routeService = createObject();
        }
        return routeService;
    }

    return {
      getRouteService: getRouteService
    };

})();


var mapController = (function(){

    var map;

    var sourceMarker;
    var destinationMarker;

    var directionsService;
    var directionsDisplay;


    var source;
    var destination;

    var routeService = RouteServiceFactory.getRouteService();

    var markers = [];

    var init = function(){
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 8
        });

        directionsService = new google.maps.DirectionsService;
        directionsDisplay = new google.maps.DirectionsRenderer({
            map: map
        });

        var sourceElement = document.getElementById('source');
        var destinationElement = document.getElementById('destination');

        var source = sourceElement.value;
        var destination = destinationElement.value;

        if(google.maps.places && google.maps.places.SearchBox){
            var sourceSearchBox = new google.maps.places.SearchBox(sourceElement);
            var destinationSearchBox = new google.maps.places.SearchBox(destinationElement);

            sourceSearchBox.addListener('places_changed', function() {
                var places = sourceSearchBox.getPlaces();

                source = places[0].geometry.location;

                map.setCenter(places[0].geometry.location);
                sourceMarker = new google.maps.Marker({
                    position: places[0].geometry.location,
                    map: map,
                    title: 'Source!'
                });
                map.setZoom(12);
            });

            destinationSearchBox.addListener('places_changed', function() {
                var places = destinationSearchBox.getPlaces();

                destination = places[0].geometry.location;

                map.setCenter(places[0].geometry.location);
                destinationMarker = new google.maps.Marker({
                    position: places[0].geometry.location,
                    map: map,
                    title: 'Destination!'
                });
                map.setZoom(12);
            });
        }
    };

    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    var simulateRoute = function(){
        clearMarkers();

        var sourceElement = document.getElementById('source');
        var destinationElement = document.getElementById('destination');

        source = sourceElement.value;
        destination = destinationElement.value;

        var directionsDisplay = new google.maps.DirectionsRenderer();
        directionsDisplay.setMap(map);

        directionsService.route({
            origin: source,
            destination: destination,
            travelMode: google.maps.TravelMode.DRIVING
        }, function(response, status) {
            // Route the directions and pass the response to a function to create
            // markers for each step.
            var route = response.routes[0];
            var path = route.overview_path;
            directionsDisplay.setDirections(response);

            var pathTrackerIndex = 0;
            var currentPositionMarker;
            var simulateLocationChanging = function() {
                var point = path[pathTrackerIndex];

                console.log(point.lat() + " " + point.lng());

                if (!currentPositionMarker) {
                    currentPositionMarker = new google.maps.Marker;
                    currentPositionMarker.setMap(map);
                }

                currentPositionMarker.setPosition(point);

                var vehicleIdElement = document.getElementById('vehicle_id');
                var vehicle_id = vehicleIdElement.value;

                console.log(point.lat() + " " + point.lng());
                var logObj = {lat: point.lat(), long: point.lng(), vehicle_id: vehicle_id};

                routeService.send(logObj);

                pathTrackerIndex++;

                if (pathTrackerIndex < path.length) {
                    setTimeout(simulateLocationChanging, 1000);
                }

            };
            simulateLocationChanging();
        });
    };

    return {
        init: init,
        simulateRoute: simulateRoute
    }

})();





