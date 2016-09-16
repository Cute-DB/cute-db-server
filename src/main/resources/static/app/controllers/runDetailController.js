(function() {
angular.module('cuteDB')
    .controller('runDetailController',['$http', '$routeParams', '$scope', '$location', function($http, $routeParams, $scope, $location) {

        $http({method:'GET', url:'/runs/uuid/' + $routeParams.uuid + '/lints'})
            .success(function (data) {

                // Get run lints
                $scope.lints = data ? data : [];

                $scope.criticalLints = new Map();
                $scope.highLints = new Map();
                $scope.mediumLints = new Map();
                $scope.lowLints = new Map();

                for (var i = 0, len = $scope.lints.length; i < len; i++) {

                    if ($scope.lints[i].severity == 'critical') {
                        $scope.criticalLints.set($scope.lints[i].objectName, $scope.lints[i]);
                    }
                    else if ($scope.lints[i].severity == 'high') {
                        $scope.highLints.set($scope.lints[i].objectName, $scope.lints[i]);
                    }
                    else if ($scope.lints[i].severity == 'medium') {
                        $scope.mediumLints.set($scope.lints[i].objectName, $scope.lints[i]);
                    }
                    else if ($scope.lints[i].severity == 'low') {
                        $scope.lowLints.set($scope.lints[i].objectName, $scope.lints[i]);
                    }
                }

                console.log("criticalLints:"+$scope.criticalLints.size);
                console.log("highLints:"+$scope.highLints.size);
                console.log("mediumLints:"+$scope.mediumLints.size);
                console.log("lowLints:"+$scope.lowLints.size);
            });
    }]);
})();