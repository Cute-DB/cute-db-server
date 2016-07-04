(function() {
angular.module('cuteDB')
    .controller('runShowController',['$http', '$routeParams', '$scope', function($http, $routeParams, $scope) {

        $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid})
            .success(function (data) {
                $scope.selectedRun = data;

                $scope.donutOptions = {
                    chart: {
                        type: 'pieChart',
                        height: 450,
                        donut: true,
                        x: function(d){return d.key;},
                        y: function(d){return d.y;},
                        showLabels: true,
                    }
                };


                $scope.donutData = [
                    {
                        key: "Low",
                        y: $scope.selectedRun.lowHits
                    },
                    {
                        key: "Medium",
                        y: $scope.selectedRun.mediumHits
                    },
                    {
                        key: "High",
                        y: $scope.selectedRun.highHits
                    },
                    {
                        key: "Critical",
                        y: $scope.selectedRun.criticalHits
                    }
                ];
            })


    }]);
})();