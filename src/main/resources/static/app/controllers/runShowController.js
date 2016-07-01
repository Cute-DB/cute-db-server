(function() {
angular.module('cuteDB')
    .controller('runShowController',['$http', '$routeParams', '$scope', function($http, $routeParams, $scope) {

        $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid})
            .success(function (data) {
                $scope.selectedRun = data;
            })
    }]);
})();