(function() {
angular.module('cuteDB')
    .controller('runDetailController',['$http', '$routeParams', '$scope', '$location', function($http, $routeParams, $scope, $location) {

        $http({method:'GET', url:'/runs/uuid/' + $routeParams.uuid + '/lints'})
            .success(function (data) {

                // Get run lints
                $scope.lints = data ? data : [];
                $scope.sortedLints = {};

                for (var i = 0, len = $scope.lints.length; i < len; i++) {

                    if(! $scope.sortedLints[$scope.lints[i].objectName]){
                        $scope.sortedLints[$scope.lints[i].objectName] = [];
                    }

                    $scope.sortedLints[$scope.lints[i].objectName].push($scope.lints[i]);

                    console.log("sortedLints:"+$scope.sortedLints.size);

                }
            });
    }]);
})();