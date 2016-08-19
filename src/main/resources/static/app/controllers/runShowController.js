(function() {
angular.module('cuteDB')
    .controller('runShowController',['$http', '$routeParams', '$scope', function($http, $routeParams, $scope) {

        $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid})
            .success(function (data) {
                $scope.selectedRun = data;
                if($scope.selectedRun.ended) {

                    var delta = Math.abs($scope.selectedRun.ended - $scope.selectedRun.started) / 1000;
                    // calculate (and subtract) whole days
                    var days = Math.floor(delta / 86400);
                    delta -= days * 86400;
                    // calculate (and subtract) whole hours
                    var hours = Math.floor(delta / 3600) % 24;
                    delta -= hours * 3600;
                    // calculate (and subtract) whole minutes
                    var minutes = Math.floor(delta / 60) % 60;
                    delta -= minutes * 60;
                    // what's left is seconds
                    var seconds = delta % 60;  // in theory the modulus is not required


                    $scope.elapsedTime = '';
                    if(hours != null && hours != 0)
                        $scope.elapsedTime = hours + ' h ';
                    if(minutes != null && minutes != 0)
                        $scope.elapsedTime = $scope.elapsedTime + minutes + ' min ';
                    if(seconds != null && seconds != 0)
                        $scope.elapsedTime = $scope.elapsedTime + seconds + ' sec';

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