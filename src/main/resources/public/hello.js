angular.module('demo', [])
    .controller('Hello', function($scope, $http) {

    $http.get('/greeting').
        then(function(response) {
            $scope.greeting = response.data;
        });
});
