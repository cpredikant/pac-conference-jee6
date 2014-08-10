angular.module('pacConferenceApp', [])
    .controller('ConferencesCtrl', function ($scope, $http) {
        $http.get('http://localhost:8080/conference-rest-web/rest/public/conference').then(
            function (conferencesResponse) {
                $scope.conferences = conferencesResponse.data;
            });
    })
	.controller('TalksCtrl', function ($scope, $http) {
    $http.get('http://localhost:8080/conference-rest-web/rest/public/talk').then(
        function (talksResponse) {
            $scope.talks = talksResponse.data;
        });
});
