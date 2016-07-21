(function() {
angular.module('cuteDB')
    .controller('runShowController',['$http', '$routeParams', '$scope', function($http, $routeParams, $scope) {

        $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid})
            .success(function (data) {
                $scope.selectedRun = data;
                if($scope.selectedRun.ended) {
                    $scope.elapsedTime = $scope.selectedRun.ended - $scope.selectedRun.started;
                }
                
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


                $scope.myChartObject = {};
                $scope.myChartObject.type = "Gauge";

                $scope.myChartObject.options = {
                    width: 200,
                    height: 200,
                    redFrom: 0,
                    redTo: 15,
                    yellowFrom: 15,
                    yellowTo: 35,
                    minorTicks: 5
                };

                $scope.myChartObject.data = [
                    ['Label', 'Value'],
                    ['Health', $scope.selectedRun.weightedScore]
                ];
            });



    }]);
})();