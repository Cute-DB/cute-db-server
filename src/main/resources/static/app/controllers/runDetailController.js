(function() {
angular.module('cuteDB')
    .controller('runDetailController',['$http', '$routeParams', '$scope', '$location', function($http, $routeParams, $scope, $location) {

        $http({method:'GET', url:'/runs/uuid/' + $routeParams.uuid + '/lints'})
            .success(function (data) {

                // Get run lints
                $scope.lints = data ? data : [];

                $scope.criticalLints = {};
                $scope.highLints = {};
                $scope.mediumLints = {};
                $scope.lowLints = {};

                $scope.sortedLints = {};

                for (var i = 0, len = $scope.lints.length; i < len; i++) {

                    if(! $scope.sortedLints[$scope.lints[i].objectName]){
                        $scope.sortedLints[$scope.lints[i].objectName] = [];
                    }

                    $scope.sortedLints[$scope.lints[i].objectName].push($scope.lints[i]);

                    // if(!$scope.sortedLints[$scope.lints[i].objectName][$scope.lints[i].severity]){
                    //     $scope.sortedLints[$scope.lints[i].objectName][$scope.lints[i].severity] = [];
                    // }
                    //
                    // $scope.sortedLints[$scope.lints[i].objectName][$scope.lints[i].severity].push($scope.lints[i]);


                    console.log("sortedLints:"+$scope.sortedLints.size);

                //     if ($scope.lints[i].severity == 'critical') {
                //         if(! $scope.criticalLints[$scope.lints[i].objectName]){
                //             $scope.criticalLints[$scope.lints[i].objectName] = [];
                //         }
                //         $scope.criticalLints[$scope.lints[i].objectName].push($scope.lints[i]);
                //     }
                //     else if ($scope.lints[i].severity == 'high') {
                //         if(! $scope.highLints[$scope.lints[i].objectName]){
                //             $scope.highLints[$scope.lints[i].objectName] = [];
                //         }
                //         $scope.highLints[$scope.lints[i].objectName].push($scope.lints[i]);
                //     }
                //     else if ($scope.lints[i].severity == 'medium') {
                //         if(! $scope.mediumLints[$scope.lints[i].objectName]){
                //             $scope.mediumLints[$scope.lints[i].objectName] = [];
                //         }
                //         $scope.mediumLints[$scope.lints[i].objectName].push($scope.lints[i]);
                //     }
                //     else if ($scope.lints[i].severity == 'low') {
                //         if(! $scope.lowLints[$scope.lints[i].objectName]){
                //             $scope.lowLints[$scope.lints[i].objectName] = [];
                //         }
                //         $scope.lowLints[$scope.lints[i].objectName].push($scope.lints[i]);
                //     }
                }
                //
                // console.log("criticalLints:"+$scope.criticalLints.size);
                // console.log("highLints:"+$scope.highLints.size);
                // console.log("mediumLints:"+$scope.mediumLints.size);
                // console.log("lowLints:"+$scope.lowLints.size);
            });
    }]);
})();